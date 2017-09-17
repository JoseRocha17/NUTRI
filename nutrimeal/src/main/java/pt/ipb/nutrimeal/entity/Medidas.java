package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Table
@NamedQueries({
		@NamedQuery(name="Medidas.getAll", query = "SELECT m FROM Medidas m"),
		@NamedQuery(name="Medidas.getPerfil", query="SELECT m FROM Medidas m WHERE m.user.email=:email")
})
@Entity
public class Medidas implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	float peso;
	float altura;
	float pescoco;
	float cintura;
	float quadris;
	Date data;
	
	@ManyToOne
	public User user = new User();

	public Medidas() {
		super();
	}

	public Medidas(long id, float peso, float altura, float pescoco, float cintura, float quadris, Date data, String pessoa, String email, String lastName) {
		this.id = id;
		this.peso = peso;
		this.altura=altura;
		this.pescoco = pescoco;
		this.cintura = cintura;
		this.quadris = quadris;
		this.data = data;
		this.user.name=pessoa;
		this.user.email=email;
		this.user.lastName=lastName;
	}

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

	
	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
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
