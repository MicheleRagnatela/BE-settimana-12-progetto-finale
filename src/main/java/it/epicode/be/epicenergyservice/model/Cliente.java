package it.epicode.be.epicenergyservice.model;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "pecificare la ragione sociale")
	private String ragioneSociale;
	@NotEmpty(message = "Specificare la partitaIva")
	private String partitaIva;
	@NotEmpty(message = "Specificare l'email")
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Specificare la data di inserimento")
	private LocalDate dataInserimento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Specificare la la data dell'ultimo contatto")
	private LocalDate dataUltimoContatto;
	@NotNull(message = "Specificare il fatturato annuale")
	private int fatturatoAnnuale;
	@NotEmpty(message = "Specificare la pec")
	private String pec;
	@NotEmpty(message = "Specificare il numero di telefono")
	private String telefono;
	@NotEmpty(message = "Specificare l'email del contatto")
	private String emailContatto;
	@NotEmpty(message = "Specificare il nome del contatto")
	private String nomeContatto;
	@NotEmpty(message = "Specificare il cognome del contatto")
	private String cognomeContatto;
	@NotEmpty(message = "Specificare il numero di telefono del contatto")
	private String telefonoContatto;
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	private Indirizzo sedeLegale;
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private Indirizzo sedeOperativa;
	@OneToMany(mappedBy = "cliente")
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<Fattura> fatture;
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", ragioneSociale=" + ragioneSociale + ", partitaIva=" + partitaIva + ", email="
				+ email + ", dataInserimento=" + dataInserimento + ", dataUltimoContatto=" + dataUltimoContatto
				+ ", fatturatoAnnuale=" + fatturatoAnnuale + ", pec=" + pec + ", telefono=" + telefono
				+ ", emailContatto=" + emailContatto + ", nomeContatto=" + nomeContatto + ", cognomeContatto="
				+ cognomeContatto + ", telefonoContatto=" + telefonoContatto + ", tipoCliente=" + tipoCliente
				+ ", sedeLegale=" + sedeLegale + ", sedeOperativa=" + sedeOperativa + "]";
	}
	
	
}

