package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Table
@NamedQueries({
		@NamedQuery(name="Grupo.getAll", query = "SELECT g FROM Grupo g")
})
@Entity
public class Grupo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	String nome;

	@OneToMany(mappedBy = "grupo", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<Alimento> alimento = new ArrayList<Alimento>();
	
	public Grupo() {
		super();
	}

	public Grupo(long id, String nome) {
		this.id=id;
		this.nome=nome;

	}
	
	public Grupo(String nome) {
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

	public List<Alimento> getAlimento() {
		return alimento;
	}

	public void setAlimento(List<Alimento> alimento) {
		this.alimento = alimento;
	}


	
	

}
