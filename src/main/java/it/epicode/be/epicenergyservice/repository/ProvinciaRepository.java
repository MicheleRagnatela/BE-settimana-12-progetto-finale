package it.epicode.be.epicenergyservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.epicenergyservice.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

	public Optional<Provincia> findByNome(String nome);
	
}
