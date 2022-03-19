package it.epicode.be.epicenergyservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;


@Entity
@Data
public class StatoFattura {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Specificare il fatturato annuale")
	private String nome;
	@OneToMany(mappedBy= "statoFattura")
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIgnore
	List<Fattura> fatture;
	
	
}
