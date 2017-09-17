package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Table
@NamedQueries({ @NamedQuery(name = "Novidade.getAll", query = "SELECT n FROM Novidade n") })
@Entity
public class Novidade implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String titulo;
	String brevedesc;

	@Lob
	@Column(name = "CONTENT", length = 2200)
	String descricao;
	Date data;

	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "PIC")
	private byte[] foto;

	public Novidade() {
		super();
	}

	public Novidade(long id, String titulo, String brevedesc, String descricao, Date data) {
		this.id = id;
		this.titulo = titulo;
		this.brevedesc = brevedesc;
		this.descricao = descricao;
		this.data = data;
	}

	public Novidade(String titulo, String brevedesc, String descricao, Date data) {
		this.titulo = titulo;
		this.brevedesc = brevedesc;
		this.descricao = descricao;
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getBrevedesc() {
		return brevedesc;
	}

	public void setBrevedesc(String brevedesc) {
		this.brevedesc = brevedesc;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}
