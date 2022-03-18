package it.epicode.be.epicenergyservice.controller.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.epicenergyservice.model.Provincia;
import it.epicode.be.epicenergyservice.service.ProvinciaService;

@Controller
@RequestMapping("/province")
public class ProvinciaControllerWeb {

	@Autowired
	ProvinciaService provinciaService;
	
	@GetMapping("/mostraelenco")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ModelAndView mostraElenco(Pageable pageable) {
		return new ModelAndView("elencoProvince", "listaProvince", provinciaService.findAll(pageable));
	}
	
	@GetMapping("/mostraformaggiungi")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String mostraFormAggiungi(Model model, Pageable pageable) {
//		log.info("aggiungi provincia");
		model.addAttribute("provincia", new Provincia());
		return "formProvincia";
	}
	
	@PostMapping("/aggiungiprovincia") //@Valid
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiungiProvincia(@ModelAttribute("provincia") Provincia provincia, BindingResult result, Model model) {
//		log.info("aggiungi provincia");
//		if(result.hasErrors()) {
//			model.addAttribute("provincia", new Provincia());
//			return "formProvincia";
//		}
		provinciaService.saveProvincia(provincia);
		
		return "redirect:/province/mostraelenco";
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model, Pageable pageable) {
//		log.info("Test mostra form aggiorna provincia");
		Optional <Provincia> provincia = provinciaService.findById(id);
		if(provincia.isPresent()) {
			ModelAndView view = new ModelAndView("aggiornaProvincia");
			view.addObject("provincia", provincia.get());
			return view;
		}
		return new ModelAndView("error").addObject("message", "id non trovato");
	}
	
	@PostMapping("/aggiornaprovincia/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiornaProvincia(@PathVariable Long id, Provincia provincia, Model model, BindingResult result) {
		provinciaService.update(provincia.getId(), provincia);
//		log.info("Test aggiorna provincia");
		return "redirect:/province/mostraelenco";
	}
	
	@GetMapping("/eliminaprovincia/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView eliminaProvincia(@PathVariable Long id, Model model, Pageable pageable) {
		Optional<Provincia> provincia = provinciaService.findById(id);
		if(provincia.isPresent()) {
			provinciaService.deleteProvincia(id);
			ModelAndView view = new ModelAndView("elencoProvince");
			view.addObject("listaProvince", provinciaService.findAll(pageable));
		
			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");

		}
		
	}
}
