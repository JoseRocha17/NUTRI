package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
@NamedQueries(@NamedQuery(name = "Pedidos.getAll", query = "SELECT p FROM Pedidos p"))
public class Pedidos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	String email, nome, contacto, titulo;
	Date data;

	@Lob
	@Column(name = "CONTENT", length = 2046)
	String assunto;

	public Pedidos() {

	}

	public Pedidos(long id, String email, String nome, String contacto, String titulo, String assunto, Date data) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.contacto = contacto;
		this.titulo = titulo;
		this.assunto = assunto;
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

}