package it.epicode.be.epicenergyservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.epicenergyservice.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public List<Cliente> findByFattureId(Long id);
	
	public Page<Cliente> findAllByOrderByFatturatoAnnuale( Pageable pageable);
	
	public Page<Cliente> findAllByOrderByDataInserimento(Pageable pageable);

	public Page<Cliente> findAllByOrderByDataUltimoContatto(Pageable pageable);

	public Page<Cliente> findAllByOrderBySedeLegaleComuneProvinciaId(Pageable pageable);

	public Page<Cliente> findByFatturatoAnnualeBetween(int fatturatoAnnuale, int fatturatoAnnuale2, Pageable pageable);
	
	public Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);
	
	public Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);
	
	public Page<Cliente> findByRagioneSocialeContaining(String parteNome, Pageable pageable);


}
