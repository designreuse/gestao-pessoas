package ufc.quixada.npi.gp.controller;

import static ufc.quixada.npi.gp.utils.Constants.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Filtro;
import ufc.quixada.npi.gp.model.Folga;
import ufc.quixada.npi.gp.model.Periodo;
import ufc.quixada.npi.gp.model.Projeto;
import ufc.quixada.npi.gp.model.Turma;
import ufc.quixada.npi.gp.service.EstagiarioService;
import ufc.quixada.npi.gp.service.PeriodoService;
import ufc.quixada.npi.gp.service.TurmaService;
import br.ufc.quixada.npi.service.GenericService;

@Component
@Controller
@RequestMapping("coordenador")
public class CoordenadorController {

	@Inject
	private EstagiarioService estagiarioService;
	
	private JRDataSource jrDatasource;

	@Inject
	private GenericService<Projeto> projetoService;

	@Inject
	private TurmaService turmaService;

	@Inject
	private PeriodoService periodoService;

	@Inject
	private GenericService<Folga> folgaService;

	@RequestMapping(value = {"/inicial", "/index"}, method = RequestMethod.GET)	public String inicial(ModelMap modelMap, HttpSession session) throws JRException {
		modelMap.addAttribute("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		return PAGINA_INICIAL_COORDENADOR;
	}
	
	@RequestMapping(value = "/tce-turma/{idTurma}", method = RequestMethod.GET)
	public String gerarTermoDeCompromisso(@PathVariable("idTurma") Long idTurma, ModelMap model) throws JRException {
		jrDatasource = new JRBeanCollectionDataSource(estagiarioService.getEstagiarioTurma(idTurma));
		
		model.addAttribute("datasource", jrDatasource);
		model.addAttribute("format", "pdf");
		return PAGINA_TCE;
	}

	@RequestMapping(value = "/declaracoes-turma/{idTurma}", method = RequestMethod.GET)
	public String gerarDeclaracaoEstagio2( ModelMap model, @PathVariable("idTurma") Long idTurma) throws JRException {
		jrDatasource = new JRBeanCollectionDataSource(estagiarioService.getEstagiarioTurma(idTurma));
		
		model.addAttribute("datasource", jrDatasource);
		model.addAttribute("format", "pdf");
		return PAGINA_DECLARACAO_ESTAGIO;
	}

	@RequestMapping(value = "/projetos", method = RequestMethod.GET)
	public String listarProjetos( ModelMap model) {
		model.addAttribute("projetos", projetoService.find(Projeto.class));
		return PAGINA_LISTAR_PROJETOS;
	}

	@RequestMapping(value = "/projeto", method = RequestMethod.GET)
	public String novoProjeto( ModelMap model) {
		model.addAttribute("projeto", new Projeto());
		return PAGINA_CADASTRAR_PROJETO;
	}
	@RequestMapping(value = "/projeto", method = RequestMethod.POST)
	public String adicionarProjeto(ModelMap model, @Valid @ModelAttribute("projeto") Projeto projeto, BindingResult result) {
		
		if (result.hasErrors()) {
			return PAGINA_CADASTRAR_PROJETO;
		}
		
		if(projeto.getId() == null)
			projetoService.save(projeto);
		else{
			projeto = atualizarProjeto(projeto);
			projetoService.update(projeto);
		}
		return REDIRECT_PAGINA_LISTAR_PROJETOS;
	}

	@RequestMapping(value = "/projeto/{idProjeto}/editar", method = RequestMethod.GET)
	public String editarProjeto(@PathVariable("idProjeto") Long idProjeto, ModelMap model) {
		model.addAttribute("projeto", projetoService.find(Projeto.class, idProjeto));
		return PAGINA_EDITAR_PROJETO;
	}

	@RequestMapping(value = "/projeto/{idProjeto}/detalhes", method = RequestMethod.GET)
	public String detalhesProjeto(@PathVariable("idProjeto") Long idProjeto, ModelMap model) {
		Projeto projeto = projetoService.find(Projeto.class, idProjeto);
		model.addAttribute("projeto", projeto);
		return PAGINA_DETALHES_PROJETO;
	}

	@RequestMapping(value = "/projeto/{idProjeto}/excluir", method = RequestMethod.GET)
	public String excluirProjeto(@PathVariable("idProjeto") Long idProjeto, ModelMap model) {
		Projeto projeto = projetoService.find(Projeto.class, idProjeto);
		
		if(projeto != null){
			projetoService.delete(projeto);
		}
		
		return REDIRECT_PAGINA_LISTAR_PROJETOS;
	}

	@RequestMapping(value = "/{idProjeto}/add-membros-projeto", method = RequestMethod.GET)
	public String vincularMembrosProjeto(ModelMap model, @PathVariable("idProjeto") Long idProjeto) {
		model.addAttribute("projeto", projetoService.find(Projeto.class, idProjeto));
		
		return PAGINA_ADICONAR_MEMBROS_PROJETO;
	}
	
	@RequestMapping(value = "/{idProjeto}/add-membros-projeto", method = RequestMethod.POST)
	public String vincularEstagiariosProjeto(ModelMap modelMap, HttpSession session, @RequestParam("turma") Long turma, @PathVariable("idProjeto") Long idProjeto) {
		modelMap.addAttribute("projeto", projetoService.find(Projeto.class, idProjeto));
		modelMap.addAttribute("estagiarios", turmaService.find(Turma.class, turma).getEstagiarios());

		return PAGINA_ADICONAR_MEMBROS_PROJETO;
	}

	@RequestMapping(value = "/vincular-membros-projeto", method = RequestMethod.POST)
	public String vincularMembrosProjeto(ModelMap model, @ModelAttribute("projeto") Projeto projeto) {
		projeto = atualizarProjeto(projeto);
		projetoService.update(projeto);
		return REDIRECT_PAGINA_LISTAR_PROJETOS;
	}

	@RequestMapping(value = "/estagiarios", method = RequestMethod.GET)
	public String listaEstagiarios(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("filtro", new Filtro());
		return PAGINA_LISTAR_ESTAGIARIOS_PERIODO;
	}

	@RequestMapping(value = "/estagiarios-turma", method = RequestMethod.POST)
	public String estagiarios(ModelMap modelMap, HttpSession session, @RequestParam("turma") Long turma) {
		List<Estagiario> estagiarios = turmaService.find(Turma.class, turma).getEstagiarios();
		modelMap.addAttribute("estagiarios", estagiarios);

		return PAGINA_LISTAR_ESTAGIARIOS_PERIODO;
	}

	@RequestMapping(value = "/periodos", method = RequestMethod.GET)
	public String listarPeriodos(ModelMap model) {
		model.addAttribute("periodos", periodoService.find(Periodo.class));
		return PAGINA_LISTAR_PERIODOS;
	}

	@RequestMapping(value = "/periodo", method = RequestMethod.GET)
	public String novoPeriodo(ModelMap model) {
		model.addAttribute("periodo", new Periodo());

		return PAGINA_CADASTRAR_PERIODO;
	}

	@RequestMapping(value = "/{idPeriodo}/editar", method = RequestMethod.GET)
	public String editarPeriodo(@PathVariable("idPeriodo") Long idPeriodo, ModelMap model) {
		model.addAttribute("periodo", periodoService.find(Periodo.class, idPeriodo));

		return PAGINA_EDITAR_PERIODO;
	}

	@RequestMapping(value = "/periodo", method = RequestMethod.POST)
	public String adicionarPeriodo(ModelMap model, @Valid @ModelAttribute("periodo") Periodo periodo, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return PAGINA_CADASTRAR_PERIODO;
		}

		if (periodo.getId() == null) {
			
			try {
				periodoService.save(periodo);
			} catch (Exception e) {
				model.addAttribute("erro", "O periodo "  + periodo.getAno() + "." + periodo.getSemestre() + " já esta cadastrado.");
				return PAGINA_CADASTRAR_PERIODO;
			}
			
		} else {
			periodoService.update(periodo);
		}

		redirectAttributes.addAttribute("info", "Periodo " + periodo.getAno() + "." + periodo.getSemestre() + " cadastrado com sucesso.");
		return REDIRECT_PAGINA_LISTAR_PERIODOS;
	}

	@RequestMapping(value = "/{idPeriodo}/detalhes", method = RequestMethod.GET)
	public String detalhesPeriodo(@PathVariable("idPeriodo") Long idPeriodo, ModelMap model) {
		model.addAttribute("periodo", periodoService.find(Periodo.class, idPeriodo));

		return PAGINA_DETALHES_PERIODO;
	}

	@RequestMapping(value = "/{idPeriodo}/folga", method = RequestMethod.GET)
	public String novaFolgaPeriodo(@PathVariable("idPeriodo") Long idPeriodo, ModelMap model) {
		model.addAttribute("periodo", periodoService.find(Periodo.class, idPeriodo));
		model.addAttribute("folga", new Folga());

		return PAGINA_CADASTRAR_FOLGA;
	}

	@RequestMapping(value = "/{idPeriodo}/folgas", method = RequestMethod.GET)
	public String folgasPeriodo(@PathVariable("idPeriodo") Long idPeriodo, ModelMap model) {
		model.addAttribute("folgas", periodoService.find(Periodo.class, idPeriodo).getFolgas());

		return PAGINA_LISTAR_FOLGAS;
	}

	@RequestMapping(value = "/{idPeriodo}/folga", method = RequestMethod.POST)
	public String adicionarFolgaPeriodo(@PathVariable("idPeriodo") Long idPeriodo, @Valid @ModelAttribute("folga") Folga folga, BindingResult result, ModelMap model) {
		
		Periodo periodo = periodoService.find(Periodo.class, idPeriodo);
		
		if (result.hasErrors()) {
			model.addAttribute("periodo", periodo);
			return PAGINA_EDITAR_FOLGA;
		}

		folga.setPeriodo(periodo);

		folgaService.save(folga);
		return REDIRECT_PAGINA_LISTAR_PERIODOS;
	}

	private Projeto atualizarProjeto(Projeto projeto) {
		List<Estagiario> membros = new ArrayList<Estagiario>();
		if (projeto.getMembros() != null) {
			for (Estagiario membro : projeto.getMembros()) {
				if(membro.getId() != null){
					membro = estagiarioService.find(Estagiario.class, membro.getId());
					membros.add(membro);
				}
			}
		}
		projeto.setMembros(membros);
		return projeto;
	}
	
}


