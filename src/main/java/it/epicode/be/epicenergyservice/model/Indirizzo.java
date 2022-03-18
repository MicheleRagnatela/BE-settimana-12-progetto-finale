package it.epicode.be.epicenergyservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Indirizzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Specificare la via")
	private String via;
	@NotNull(message = "Specificare il numero civico")
	private int civico;
	@NotNull(message = "Specificare il cap")
	private int cap;
	private String localita;
	@ManyToOne
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Comune comune;
	@Override
	public String toString() {
		return  via + " " + civico + ", " + comune.getNome() ;
	}
	
	
	
}
