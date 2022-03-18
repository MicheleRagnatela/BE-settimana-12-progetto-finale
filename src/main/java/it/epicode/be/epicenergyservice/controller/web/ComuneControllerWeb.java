package it.epicode.be.epicenergyservice.controller.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import it.epicode.be.epicenergyservice.model.Comune;
import it.epicode.be.epicenergyservice.service.ComuneService;
import it.epicode.be.epicenergyservice.service.ProvinciaService;

@Controller
@RequestMapping("/comuni")
public class ComuneControllerWeb {

	@Autowired
	ComuneService comuneService;
	
	@Autowired
	ProvinciaService provinciaService;
	
	@GetMapping("/mostraelenco")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ModelAndView mostraElenco(Pageable pageable) {
		return new ModelAndView("elencoComuni", "listaComuni", comuneService.findAll(pageable));
	}
	
	@GetMapping("/mostraformaggiungi")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String mostraFormAggiungi(Comune comune, Model model) {
//		log.info("aggiungi comuni");
		model.addAttribute("listaProvince", provinciaService.findAll());
		return "formComune";
	}
	
	@PostMapping("/aggiungicomune") //@Valid
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiungiComune( Comune comune, BindingResult result, Model model) {
//		log.info("aggiungi comune");
//		if(result.hasErrors()) {
//			model.addAttribute("listaProvince", provinciaService.findAll(pageable));
//			return "formComune";
//		}
		comuneService.saveComune(comune);
		
		return "redirect:/comuni/mostraelenco";
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
//		log.info("Test mostra form aggiorna comune");
		Optional <Comune> comune = comuneService.findById(id);
		if(comune.isPresent()) {
			ModelAndView view = new ModelAndView("aggiornaComune");
			view.addObject("comune", comune.get());
			view.addObject("listaProvince", provinciaService.findAll());
			return view;
		}
		return new ModelAndView("error").addObject("message", "id non trovato");
	}
	
	@PostMapping("/aggiornacomune/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiornaComune(@PathVariable Long id, Comune comune, Model model, BindingResult result) {
		comuneService.update( comune.getId(), comune);
//		log.info("Test aggiorna comune");
		return "redirect:/comuni/mostraelenco";
	}
	
	@GetMapping("/eliminacomune/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView eliminaComune(@PathVariable Long id, Model model, Pageable pageable) {
		Optional<Comune> comune = comuneService.findById(id);
		if(comune.isPresent()) {
			comuneService.deleteComune(id);
			ModelAndView view = new ModelAndView("elencoComuni");
			view.addObject("listaComuni", comuneService.findAll(pageable));
		
			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");

		}
		
	}
	
}
