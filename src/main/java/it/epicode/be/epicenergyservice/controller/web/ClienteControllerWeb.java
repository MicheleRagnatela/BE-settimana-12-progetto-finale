package it.epicode.be.epicenergyservice.controller.web;

import java.util.Optional;

import javax.validation.Valid;

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

import it.epicode.be.epicenergyservice.model.Cliente;
import it.epicode.be.epicenergyservice.service.ClienteService;
import it.epicode.be.epicenergyservice.service.ComuneService;
import it.epicode.be.epicenergyservice.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/clienti")
@Slf4j
public class ClienteControllerWeb {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	IndirizzoService indirizzoService;
	
	@Autowired
	ComuneService comuneService;

	@GetMapping("/mostraelenco")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ModelAndView mostraElenco(Pageable pageable) {
		return new ModelAndView("elencoClienti", "clienti", clienteService.findAll(pageable));
	}
	
	@GetMapping("/mostraformaggiungi")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String mostraFormAggiungi(Cliente cliente, Model model) {
		
		model.addAttribute("listaComuni", comuneService.findAll());
	
		return "formCliente";
	}
	
	@PostMapping("/addCliente") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiungiCliente(@Valid Cliente cliente, BindingResult result, Model model) {
		log.info("aggiungi cliente");
		if(result.hasErrors()) {

			return "error";
		}
		clienteService.saveCliente(cliente);
		
		return "redirect:/clienti/mostraelenco";
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
		Optional <Cliente> cliente = clienteService.findById(id);
		if(cliente.isPresent()) {
			ModelAndView view = new ModelAndView("aggiornaCliente");
			view.addObject("cliente", cliente.get());
			view.addObject("listaComuni", comuneService.findAll());
			return view;
		}
		return new ModelAndView("error").addObject("message", "id non trovato");
	}
	
	@PostMapping("/aggiornacliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String aggiornaCliente(@PathVariable Long id, Cliente cliente) {
		clienteService.update( cliente.getId(), cliente);
		log.info("Test aggiorna cliente");
		return "redirect:/clienti/mostraelenco";
	}
	
	@GetMapping("/eliminacliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView eliminaCliente(@PathVariable Long id, Pageable pageable) {
		Optional<Cliente> cliente = clienteService.findById(id);
		if(cliente.isPresent()) {
			clienteService.deleteCliente(id);
			log.info("Test elimina cliente");
			ModelAndView view = new ModelAndView("elencoClienti");
			view.addObject("listaClienti", clienteService.findAll(pageable));
			

			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");

		}
		
	}
}
