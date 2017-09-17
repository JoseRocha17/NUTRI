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
@NamedQueries({
		@NamedQuery(name="Promocao.getAll", query = "SELECT p FROM Promocao p")
})
@Entity
public class Promocao implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String titulo;
	String breveDesc;
	
	@Lob 
	@Column(name="CONTENT", length=2200)
	String descricao;
	
	float preco;
	Date dataInicio;
	Date dataFim;

	
	@Basic(fetch = FetchType.LAZY)
	@Lob @Column(name="PIC")
	private byte[] foto;
	
	public Promocao() {
		super();
	}
	
	public Promocao(long id, String titulo, String breveDesc, String descricao, float preco, Date dataInicio, Date dataFim) {
		this.id=id;
		this.titulo=titulo;
		this.breveDesc=breveDesc;
		this.descricao=descricao;
		this.preco=preco;
		this.dataInicio=dataInicio;
		this.dataFim=dataFim;
		
	}
	
	public Promocao(long id, String titulo, String descricao, float preco, Date dataInicio, Date dataFim) {
		this.id=id;
		this.titulo=titulo;
		this.descricao=descricao;
		this.preco=preco;
		this.dataInicio=dataInicio;
		this.dataFim=dataFim;	
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

	public String getBreveDesc() {
		return breveDesc;
	}

	public void setBreveDesc(String breveDesc) {
		this.breveDesc = breveDesc;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	
}
