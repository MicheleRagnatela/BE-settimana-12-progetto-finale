package it.epicode.be.epicenergyservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.epicenergyservice.model.Indirizzo;
import it.epicode.be.epicenergyservice.service.IndirizzoService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class IndirizzoController {

	@Autowired
	IndirizzoService indirizzoService;
	
	@GetMapping("/findallindirizzi")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Indirizzo>> findAll(Pageable pageable) {
		Page<Indirizzo> findAll = indirizzoService.findAll(pageable);
		if (!findAll.isEmpty())
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/findbyidindirizzo/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Indirizzo> findById(@PathVariable(required = true) Long id) {
		Optional<Indirizzo> findById = indirizzoService.findById(id);

		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}


	@PostMapping("/indirizzo")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Indirizzo> save(@RequestBody Indirizzo indirizzo) {
		Indirizzo save = indirizzoService.saveIndirizzo(indirizzo);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping("/updateindirizzo/{id}") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Indirizzo> update(@PathVariable(required=true) Long id, @RequestBody Indirizzo indirizzo) {
		Indirizzo update = indirizzoService.update(id, indirizzo);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

	@DeleteMapping("/deleteindirizzo/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		indirizzoService.deleteIndirizzo(id);
		return new ResponseEntity<>("Indirizzo eliminato", HttpStatus.OK);

	}
}
 