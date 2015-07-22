package ufc.quixada.npi.gp.controller;

import static ufc.quixada.npi.gp.utils.Constants.PAGINA_INICIAL_ESTAGIARIO;
import static ufc.quixada.npi.gp.utils.Constants.REDIRECT_PAGINA_INICIAL_ESTAGIARIO;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ufc.quixada.npi.gp.model.Estagiario;
import ufc.quixada.npi.gp.model.Pessoa;
import ufc.quixada.npi.gp.service.EstagiarioService;
import ufc.quixada.npi.gp.service.PessoaService;

@Component
@Controller
@RequestMapping("home")
public class HomeController {

	@Inject
	private EstagiarioService estagiarioService;

	@Inject
	private PessoaService pessoaService; 

	@RequestMapping(value = "/meu-cadastro-npi", method = RequestMethod.GET)
	public String inicial(ModelMap modelMap, HttpSession session) {

		modelMap.addAttribute("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		modelMap.addAttribute("estagiario", new Estagiario());

		return PAGINA_INICIAL_ESTAGIARIO;
	}

	@RequestMapping(value = "/meu-cadastro-npi", method = RequestMethod.POST)
	public String adicionarEstagiario( @Valid @ModelAttribute("estagiario") Estagiario estagiario, BindingResult result, HttpSession session, RedirectAttributes redirect, Model model) {

		if (result.hasErrors()) {
			return PAGINA_INICIAL_ESTAGIARIO;
		}

		SecurityContextHolder.getContext().getAuthentication().getName();

		Pessoa pessoa = null;
		
		estagiario.setPessoa(pessoa);

		estagiarioService.save(estagiario);

		redirect.addFlashAttribute("info", "Parabéns, " + pessoa.getNome()
				+ ", seu cadastro foi realizado com sucesso!");
		model.addAttribute("estagiarioCadastrado", true);
		model.addAttribute("estagiario", estagiario);
		return REDIRECT_PAGINA_INICIAL_ESTAGIARIO;
	}
	
	
}


