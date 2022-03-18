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
import it.epicode.be.epicenergyservice.model.Provincia;
import it.epicode.be.epicenergyservice.service.ProvinciaService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;
	
	@GetMapping("/findallprovince")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Provincia>> findAll(Pageable pageable) {
		Page<Provincia> findAll = provinciaService.findAll(pageable);
		if (!findAll.isEmpty())
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/findByidprovincia/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Provincia> findById(@PathVariable(required = true) Long id) {
		Optional<Provincia> findById = provinciaService.findById(id);

		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	

	@PostMapping("/provincia")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Provincia> save(@RequestBody Provincia provincia) {
		Provincia save = provinciaService.saveProvincia(provincia);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping("/updateprovincia/{id}") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Provincia> update(@PathVariable(required=true) Long id, @RequestBody Provincia provincia) {
		Provincia save = provinciaService.update(id, provincia);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping("/deleteprovincia/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		provinciaService.deleteProvincia(id);
		return new ResponseEntity<>("Provincia eliminata", HttpStatus.OK);

	}
	
}
