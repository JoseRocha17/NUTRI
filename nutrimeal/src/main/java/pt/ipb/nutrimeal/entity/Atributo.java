package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Table
@NamedQueries({
		@NamedQuery(name="Atributo.getAll", query = "SELECT a FROM Atributo a")
})
@Entity
public class Atributo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	String nome;

	@OneToMany(mappedBy = "atributo", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<ExercicioAtributo> exercicioatributo = new ArrayList<ExercicioAtributo>();
	
	public Atributo() {
		super();
	}

	public Atributo(long id, String nome) {
		this.id=id;
		this.nome=nome;

	}
	
	public Atributo(String nome) {
		this.nome=nome;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ExercicioAtributo> getExercicioatributo() {
		return exercicioatributo;
	}

	public void setExercicioatributo(List<ExercicioAtributo> exercicioatributo) {
		this.exercicioatributo = exercicioatributo;
	}

	

}
