package ufc.quixada.npi.gp.controller;

import static ufc.quixada.npi.gp.utils.Constants.PAGINA_FORM_ESTAGIARIO;
import static ufc.quixada.npi.gp.utils.Constants.PAGINA_INICIAL_ESTAGIARIO;
import static ufc.quixada.npi.gp.utils.Constants.PAGINA_SOBRE;
import static ufc.quixada.npi.gp.utils.Constants.PAGINA_ACOMPANHAMENTO_ESTAGIARIO;
import static ufc.quixada.npi.gp.utils.Constants.REDIRECT_PAGINA_INICIAL_ESTAGIARIO;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Estagio;
import ufc.quixada.npi.gp.model.Frequencia;
import ufc.quixada.npi.gp.model.Pessoa;
import ufc.quixada.npi.gp.model.Submissao;
import ufc.quixada.npi.gp.model.Turma;
import ufc.quixada.npi.gp.model.enums.StatusFrequencia;
import ufc.quixada.npi.gp.service.EstagioService;
import ufc.quixada.npi.gp.service.PessoaService;
import ufc.quixada.npi.gp.service.TurmaService;
import ufc.quixada.npi.gp.utils.Constants;

@Controller
@RequestMapping("estagiario")
public class EstagiarioController {

	@Inject
	private PessoaService pessoaService;

	@Inject
	private TurmaService turmaService;

	@Inject
	private EstagioService estagioService;

	@RequestMapping(value = "/MinhasTurmas", method = RequestMethod.GET)
	public String getTurmas(Model model, HttpSession session) {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Estagiario estagiario = pessoaService.getEstagiarioByPessoaCpf(cpf);
		model.addAttribute("estagios", estagiario.getEstagios());
		
		return PAGINA_INICIAL_ESTAGIARIO;
	}

	@RequestMapping(value = "/MeusDados", method = RequestMethod.GET)
	public String getMeusDados(Model model) {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Estagiario estagiario = pessoaService.getEstagiarioByPessoaCpf(cpf);
		model.addAttribute("action", "editar");
		model.addAttribute("estagiario", estagiario);

		return PAGINA_FORM_ESTAGIARIO;
	}

	@RequestMapping(value = "/MeusDados", method = RequestMethod.POST)
	public String postMeusDados(@Valid @ModelAttribute("estagiario") Estagiario estagiario, BindingResult result, HttpSession session, RedirectAttributes redirect, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("action", "editar");
			return PAGINA_FORM_ESTAGIARIO;
		}

		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		estagiario.setPessoa(pessoaService.getPessoaByCpf(cpf));
		pessoaService.editarEstagiario(estagiario);
		redirect.addFlashAttribute("info", "Parabéns, seu cadastro foi realizado com sucesso!");

		return REDIRECT_PAGINA_INICIAL_ESTAGIARIO;
	}
	
	@RequestMapping(value = "/Sobre", method = RequestMethod.GET)
	public String getSobre(){
		return PAGINA_SOBRE;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Presenca/Estagio/{idEstagio}", method = RequestMethod.GET)
	public boolean realizarPresenca(HttpSession session, @PathVariable("idEstagio") Long idEstagio, RedirectAttributes redirectAttributes) {
		
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Estagiario estagiario = pessoaService.getEstagiarioByPessoaCpf(cpf);
		Estagio estagio = estagioService.getEstagioByIdAndEstagiarioId(idEstagio, estagiario.getId());

		if(permitirPresenca(estagio)) {
			Frequencia frequencia = new Frequencia();
			frequencia.setEstagio(estagio);
			frequencia.setData(new Date());
			frequencia.setHorario(new Date());
			frequencia.setStatusFrequencia(StatusFrequencia.PRESENTE);

			estagioService.adicionarFrequencia(frequencia);
			
			return true;
		}

		return false;
	}

	private boolean permitirPresenca(Estagio estagio){
		if (estagio != null){
			Frequencia frequencia = estagioService.getFrequenciaByData(new Date(), estagio.getId());
			if(frequencia == null) {
				return estagioService.liberarPreseca(estagio.getTurma());
			}
		}
		
		return false;
	}

	@RequestMapping(value = "/Acompanhamento/Estagio/{idEstagio}", method = RequestMethod.GET)
	public String getAcompanhamento(Model model, @PathVariable("idEstagio") Long idEstagio) {
		
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Estagiario estagiario = pessoaService.getEstagiarioByPessoaCpf(cpf);
		Estagio estagio = estagioService.getEstagioByIdAndEstagiarioId(idEstagio, estagiario.getId()); 
		
		model.addAttribute("estagio", estagio);

		return PAGINA_ACOMPANHAMENTO_ESTAGIARIO;
	}
		
	@RequestMapping(value = "/Acompanhamento/Estagio/{id}/SubmeterPlano", method = RequestMethod.POST)
	public String postSubmeterPlano(@Valid @RequestParam("anexo") MultipartFile anexo, HttpSession session, Model model, @ModelAttribute("idTurma") Long idTurma, RedirectAttributes redirectAttributes ){
							
		try {
			if(!anexo.getContentType().equals("application/pdf")){
				redirectAttributes.addFlashAttribute("error", "Escolha um arquivo pdf.");
				return "redirect:/estagiario/turma/" + idTurma;
			}
			Pessoa pessoa = getUsuarioLogado(session);
			
			Estagiario estagiario = pessoaService.getEstagiarioByPessoaId(pessoa.getId());
			
			Turma turma = turmaService.getTurmaByIdAndEstagiarioId(idTurma, estagiario.getId());
			
			Tipo tipo = Tipo.PLANO_ESTAGIO;
			
			turmaService.submeterDocumento(estagiario, turma, tipo, anexo);
			
		} catch (IOException e) {
			return "redirect:/500";
		}

		return "redirect:/estagiario/turma/" + idTurma;
	}
	
	@RequestMapping(value = "/Acompanhamento/Estagio/{id}/SubmeterRelatorio", method = RequestMethod.POST)
	public String postSubmeterRelatorio(@Valid @RequestParam("anexo") MultipartFile anexo, HttpSession session, Model model, @ModelAttribute("idTurma") Long idTurma, RedirectAttributes redirectAttributes ){

		try {
			if(!anexo.getContentType().equals("application/pdf")){
				redirectAttributes.addFlashAttribute("error", "Escolha um arquivo pdf.");
				return "redirect:/estagiario/turma/" + idTurma;
			}

			Pessoa pessoa = getUsuarioLogado(session);
			
			Estagiario estagiario = pessoaService.getEstagiarioByPessoaId(pessoa.getId());
			
			Turma turma = turmaService.getTurmaByIdAndEstagiarioId(idTurma, estagiario.getId());
			
			Tipo tipo = Tipo.RELATORIO_FINAL_ESTAGIO;
								
			//turmaService.submeterDocumento(estagiario, turma, tipo, anexo);
			estagioService.submeterDocumento(estagiario, turma, tipo, anexo)
			
		} catch (IOException e) {
			return "redirect:/500";
		}

		return "redirect:/estagiario/turma/" + idTurma;
	}

	@RequestMapping(value = "/Acompanhamento/Estagio/{id}/EditarRelatorio", method = RequestMethod.POST)
	public String postEditarRelatorio(){
		return "";
	}
	
	@RequestMapping(value = "/Acompanhamento/Estagio/{id}/EditarPlano", method = RequestMethod.POST)
	public String postEditarPlano(){
		return "";
	}
	
	private Pessoa getUsuarioLogado(HttpSession session) {
		Pessoa pessoa = (Pessoa) session.getAttribute(Constants.USUARIO_LOGADO);

		if (pessoa == null || pessoa.getNome() == null) {
			pessoa = pessoaService.getPessoaByCpf(SecurityContextHolder.getContext().getAuthentication().getName());
			session.setAttribute(Constants.USUARIO_LOGADO, pessoa);
		}

		return pessoa;
	}
}