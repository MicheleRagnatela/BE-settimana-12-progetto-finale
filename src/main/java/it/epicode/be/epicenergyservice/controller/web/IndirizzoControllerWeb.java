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


import it.epicode.be.epicenergyservice.model.Indirizzo;
import it.epicode.be.epicenergyservice.service.ComuneService;
import it.epicode.be.epicenergyservice.service.IndirizzoService;

@Controller
@RequestMapping("/indirizzi")
public class IndirizzoControllerWeb {

	@Autowired
	IndirizzoService indirizzoService;
	
	@Autowired
	ComuneService comuneService;
	
	
	@GetMapping("/mostraelenco")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ModelAndView mostraElenco(Pageable pageable) {
		return new ModelAndView("elencoIndirizzi", "listaIndirizzi", indirizzoService.findAll(pageable));
	}
	
	@GetMapping("/mostraformaggiungi")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String mostraFormAggiungi(Indirizzo indirizzo, Model model) {
//		log.info("aggiungi indirizzo");
		model.addAttribute("listaComuni", comuneService.findAll());
		return "formIndirizzo";
	}
	
	@PostMapping("/aggiungiindirizzo") //@Valid
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiungiIndirizzo(Indirizzo indirizzo, BindingResult result, Model model) {
//		log.info("aggiungi indirizzo");
//		if(result.hasErrors()) {
//			model.addAttribute("listaComuni", comuneService.findAll(pageable));
//			return "formIndirizzo";
//		}
		indirizzoService.saveIndirizzo(indirizzo);
		
		return "redirect:/indirizzi/mostraelenco";
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
//		log.info("Test mostra form aggiorna indirizzo");
		Optional <Indirizzo> indirizzo = indirizzoService.findById(id);
		if(indirizzo.isPresent()) {
			ModelAndView view = new ModelAndView("aggiornaIndirizzo");
			view.addObject("indirizzo", indirizzo.get());
			view.addObject("listaComuni", comuneService.findAll());
			return view;
		}
		return new ModelAndView("error").addObject("message", "id non trovato");
	}
	
	@PostMapping("/aggiornaindirizzo/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiornaIndirizzo(@PathVariable Long id, Indirizzo indirizzo, Model model, BindingResult result) {
		indirizzoService.update( indirizzo.getId(), indirizzo);
//		log.info("Test aggiorna indirizzo");
		return "redirect:/indirizzi/mostraelenco";
	}
	
	@GetMapping("/eliminaindirizzo/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView eliminaIndirizzo(@PathVariable Long id, Model model) {
		Optional<Indirizzo> indirizzo = indirizzoService.findById(id);
		if(indirizzo.isPresent()) {
			indirizzoService.deleteIndirizzo(id);
			ModelAndView view = new ModelAndView("elencoIndirizzi");
			view.addObject("listaIndirizzi", indirizzoService.findAll());
		
			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");

		}
		
	}
	
}
