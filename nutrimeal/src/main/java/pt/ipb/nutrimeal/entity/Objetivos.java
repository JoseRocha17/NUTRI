package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.*;


@SuppressWarnings("serial")
@Table
@NamedQueries({
		@NamedQuery(name="Objetivos.getAll", query = "SELECT o FROM Objetivos o"),
		@NamedQuery(name="Objetivos.getPerfil", query="SELECT o FROM Objetivos o WHERE o.user.email=:email")
})
@Entity
public class Objetivos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	float peso;
	float pescoco;
	float cintura;
	float quadris;
	Date data;

	@ManyToOne
	public User user = new User();
	
	public Objetivos() {
		super();
	}

	public Objetivos(long id, float peso, float pescoco, float cintura, float quadris, Date data, String pessoa) {
		this.id=id;
		this.peso = peso;
		this.pescoco = pescoco;
		this.cintura = cintura;
		this.quadris = quadris;
		this.data = data;
		this.user.name = pessoa;
	}
	
//	public Objetivos(float peso, float pescoco, float cintura, float quadris, Date data, String pessoa) {
//		this.peso = peso;
//		this.pescoco = pescoco;
//		this.cintura = cintura;
//		this.quadris = quadris;
//		this.data = data;
//		this.pessoa.nome = pessoa;
//	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPeso() {
		return this.peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getPescoco() {
		return this.pescoco;
	}

	public void setPescoco(float pescoco) {
		this.pescoco = pescoco;
	}

	public float getCintura() {
		return this.cintura;
	}

	public void setCintura(float cintura) {
		this.cintura = cintura;
	}

	public float getQuadris() {
		return this.quadris;
	}

	public void setQuadris(float quadris) {
		this.quadris = quadris;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
