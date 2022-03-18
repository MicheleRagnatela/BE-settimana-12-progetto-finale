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
import it.epicode.be.epicenergyservice.model.Comune;
import it.epicode.be.epicenergyservice.service.ComuneService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class ComuneController {

	@Autowired
	ComuneService comuneService;
	
	@GetMapping("/findallcomuni")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Comune>> findAll(Pageable pageable) {
		Page<Comune> findAll = comuneService.findAll(pageable);
		if (!findAll.isEmpty())
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/findbyidcomune/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Comune> findById(@PathVariable(required = true) Long id) {
		Optional<Comune> findById = comuneService.findById(id);

		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}


	@PostMapping("/comune")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Comune> save(@RequestBody Comune comune) {
		Comune save = comuneService.saveComune(comune);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping("/updatecomune/{id}") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Comune> update(@PathVariable(required=true) Long id, @RequestBody Comune comune) {
		Comune update = comuneService.update(id, comune);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

	@DeleteMapping("/deletecomune/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		comuneService.deleteComune(id);
		return new ResponseEntity<>("Comune eliminato", HttpStatus.OK);

	}
	
}
