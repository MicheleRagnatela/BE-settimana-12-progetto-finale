package it.epicode.be.epicenergyservice.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import it.epicode.be.epicenergyservice.model.Fattura;
import it.epicode.be.epicenergyservice.service.FatturaService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class FatturaController {

	@Autowired
	FatturaService fatturaService;

	@GetMapping("/findallfatture")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findAll(Pageable pageable) {
		Page<Fattura> findAll = fatturaService.findAll(pageable);
		if (!findAll.isEmpty())
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/findByidfattura/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Fattura> findById(@PathVariable(required = true) Long id) {
		Optional<Fattura> findById = fatturaService.findById(id);

		return new ResponseEntity<>(findById.get(), HttpStatus.OK);

	}

	@GetMapping("/findbycliente/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findByClienteId(@PathVariable(required = true) Long id, Pageable pageable) {
		Page<Fattura> findBy = fatturaService.findByClienteId(id, pageable);
		return new ResponseEntity<>(findBy, HttpStatus.OK);

	}

	@GetMapping("/findbystato/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findByStatoFattura(@PathVariable(required = true) Long id, Pageable pageable) {
		Page<Fattura> findBy = fatturaService.findByStatoFattura(id, pageable);
		return new ResponseEntity<>(findBy, HttpStatus.OK);

	}

	@GetMapping("/findbydata/{data}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findByData(
			@PathVariable(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
			Pageable pageable) {
		Page<Fattura> findBy = fatturaService.findByData(data, pageable);
		return new ResponseEntity<>(findBy, HttpStatus.OK);

	}

	@GetMapping("/findbyanno/{anno}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findByAnno(@PathVariable(required = true) Integer anno, Pageable pageable) {
		Page<Fattura> findBy = fatturaService.findByAnno(anno, pageable);
		return new ResponseEntity<>(findBy, HttpStatus.OK);

	}

	@GetMapping("/findbyimporto/{importo}/{importo2}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findByImportoBetween(@PathVariable(required = true) BigDecimal importo,
			@PathVariable(required = true) BigDecimal importo2, Pageable pageable) {
		Page<Fattura> findBy = fatturaService.findByImportoBetween(importo, importo2, pageable);
		return new ResponseEntity<>(findBy, HttpStatus.OK);

	}

	@PostMapping("/fattura")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Fattura> save(@RequestBody Fattura fattura) {
		Fattura save = fatturaService.saveFattura(fattura);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping("/updatefattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Fattura> update(@PathVariable(required = true) Long id, @RequestBody Fattura fattura) {
		Fattura save = fatturaService.update(id, fattura);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping("/deletefattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id, Pageable pageable) {
		fatturaService.deleteFattura(id, pageable);
		return new ResponseEntity<>("Fattura eliminata", HttpStatus.OK);

	}

}
