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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Specificare il fatturato annuale")
	private String sigla;
	@NotEmpty(message = "Specificare il fatturato annuale")
	private String nome;
	@NotEmpty(message = "Specificare il fatturato annuale")
	private String regione;
	@OneToMany(mappedBy = "provincia")
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIgnore
	private List<Comune> comuni;
	@Override
	public String toString() {
		return "Provincia [id=" + id + ", sigla=" + sigla + ", nome=" + nome + ", regione=" + regione + "]";
	}
	

	
	
}
