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

import it.epicode.be.epicenergyservice.model.Fattura;
import it.epicode.be.epicenergyservice.service.ClienteService;
import it.epicode.be.epicenergyservice.service.FatturaService;
import it.epicode.be.epicenergyservice.service.StatoFatturaService;


@Controller
@RequestMapping("/fatture")
//@Slf4j
public class FatturaControllerWeb {

	@Autowired
	FatturaService fatturaService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	StatoFatturaService statoFatturaService;
	
	@GetMapping("/mostraelenco")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ModelAndView mostraElenco(Pageable pageable) {
		return new ModelAndView("elencoFatture", "listaFatture", fatturaService.findAll(pageable));
	}
	
	@GetMapping("/mostraformaggiungi")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String mostraFormAggiungi(Fattura fattura, Model model) {
//		log.info("aggiungi fattura");
		model.addAttribute("listaClienti", clienteService.findAll());
		model.addAttribute("listaStati", statoFatturaService.findAll());

		return "formFattura";
	}
	
	@PostMapping("/aggiungifattura") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiungiFattura(Fattura fattura, BindingResult result) {
//		log.info("aggiungi fattura");
		if(result.hasErrors()) {
			return "error";
		}
		fatturaService.saveFattura(fattura);
		
		return "redirect:/fatture/mostraelenco";
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id) {
//		log.info("Test mostra form aggiorna fattura");
		Optional <Fattura> fattura = fatturaService.findById(id);
		if(fattura.isPresent()) {
			ModelAndView view = new ModelAndView("aggiornaFattura");
			view.addObject("fattura", fattura.get());
			view.addObject("listaClienti", clienteService.findAll());
			view.addObject("listaStati", statoFatturaService.findAll());
			return view;
		}
		return new ModelAndView("error").addObject("message", "id non trovato");
	}
	
	@PostMapping("/aggiornafattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiornaFattura(@PathVariable Long id, Fattura fattura, BindingResult result) {
		fatturaService.update(fattura.getId(), fattura);
//		log.info("Test aggiorna fattura");
		return "redirect:/fatture/mostraelenco";
	}
	
	@GetMapping("/eliminafattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView eliminaFattura(@PathVariable Long id, Pageable pageable) {
		Optional<Fattura> fattura = fatturaService.findById(id);
		if(fattura.isPresent()) {
			fatturaService.deleteFattura(id, pageable);
			ModelAndView view = new ModelAndView("elencoFatture");
			view.addObject("listaFatture", fatturaService.findAll(pageable));
		
			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");

		}
		
	}
	
}
