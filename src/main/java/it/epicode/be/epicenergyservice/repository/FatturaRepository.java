package it.epicode.be.epicenergyservice.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.epicenergyservice.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long>{

	public Page<Fattura> findByClienteId(Long id, Pageable pageable);
	
	public Page<Fattura> findByStatoFatturaId(Long id, Pageable pageable);
	
	public Page<Fattura> findByData(LocalDate data, Pageable pageable);

	public Page<Fattura> findByAnno(Integer anno, Pageable pageable);
	
	public Page<Fattura> findByImportoBetween(BigDecimal importo, BigDecimal importo2, Pageable pageable);


}
