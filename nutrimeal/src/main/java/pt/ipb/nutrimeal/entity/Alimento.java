package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Table
@NamedQueries({ @NamedQuery(name = "Alimento.getAll", query = "SELECT a FROM Alimento a"),
		@NamedQuery(name = "Alimento.getGrupo", query = "SELECT a FROM Alimento a WHERE a.grupo.id=:id") })
@Entity
public class Alimento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String nome;
	float calorias;
	float gordura;
	float colestrol;
	float sodio;
	float potassio;
	float carboidrato;
	float fibra;
	float acucar;
	float proteina;

	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "PIC")
	private byte[] foto;

	public Alimento() {
		super();
	}

	@OneToMany(mappedBy = "alimento", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<QuantidadeAlimentar> quantidadealimentar = new ArrayList<QuantidadeAlimentar>();

	@ManyToOne
	public Grupo grupo = new Grupo();

	public Alimento(long id, String nome, String grupo, float calorias, float gordura, float colestrol, float sodio,
			float potassio, float carboidrato, float fibra, float acucar, float proteina) {
		this.id = id;
		this.nome = nome;
		this.grupo.nome = grupo;
		this.calorias = calorias;
		this.gordura = gordura;
		this.colestrol = colestrol;
		this.sodio = sodio;
		this.potassio = potassio;
		this.carboidrato = carboidrato;
		this.fibra = fibra;
		this.acucar = acucar;
		this.proteina = proteina;
	}

	public Alimento(long id, String nome, float calorias, float gordura, float colestrol, float sodio, float potassio,
			float carboidrato, float fibra, float acucar, float proteina) {
		this.id = id;
		this.nome = nome;
		this.calorias = calorias;
		this.gordura = gordura;
		this.colestrol = colestrol;
		this.sodio = sodio;
		this.potassio = potassio;
		this.carboidrato = carboidrato;
		this.fibra = fibra;
		this.acucar = acucar;
		this.proteina = proteina;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getCalorias() {
		return this.calorias;
	}

	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}

	public float getGordura() {
		return this.gordura;
	}

	public void setGordura(float gordura) {
		this.gordura = gordura;
	}

	public float getColestrol() {
		return this.colestrol;
	}

	public void setColestrol(float colestrol) {
		this.colestrol = colestrol;
	}

	public float getSodio() {
		return this.sodio;
	}

	public void setSodio(float sodio) {
		this.sodio = sodio;
	}

	public float getPotassio() {
		return this.potassio;
	}

	public void setPotassio(float potassio) {
		this.potassio = potassio;
	}

	public float getCarboidrato() {
		return this.carboidrato;
	}

	public void setCarboidrato(float carboidrato) {
		this.carboidrato = carboidrato;
	}

	public float getFibra() {
		return this.fibra;
	}

	public void setFibra(float fibra) {
		this.fibra = fibra;
	}

	public float getAcucar() {
		return this.acucar;
	}

	public void setAcucar(float acucar) {
		this.acucar = acucar;
	}

	public float getProteina() {
		return this.proteina;
	}

	public void setProteina(float proteina) {
		this.proteina = proteina;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<QuantidadeAlimentar> getQuantidadealimentar() {
		return quantidadealimentar;
	}

	public void setQuantidadealimentar(List<QuantidadeAlimentar> quantidadealimentar) {
		this.quantidadealimentar = quantidadealimentar;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}


}
