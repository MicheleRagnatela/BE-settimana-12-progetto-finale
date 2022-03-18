package it.epicode.be.epicenergyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.epicenergyservice.model.Comune;

public interface ComuneRepository extends JpaRepository<Comune, Long>{

}
