package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Table
@NamedQueries({
		@NamedQuery(name="Exercicio.getAll", query = "SELECT e FROM Exercicio e")
})
@Entity
public class Exercicio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String nome, tipo;
	
	
	@Lob 
	@Column(name="CONTENT", length=300)
	String descricao;
	
	@Basic(fetch = FetchType.LAZY)
	@Lob @Column(name="PIC")
	private byte[] foto;
	
	@OneToMany(mappedBy = "exercicio", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<MetaExercicio> metaexercicio = new ArrayList<MetaExercicio>();
	
		
	public Exercicio() {
		super();
	}

	public Exercicio(long id, String nome, String tipo, String descricao) {
		this.id=id;
		this.nome = nome;
		this.tipo=tipo;
		this.descricao=descricao;
	}
	
	public Exercicio(String nome, String tipo, String descricao) {
		this.nome = nome;
		this.tipo=tipo;
		this.descricao=descricao;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<MetaExercicio> getMetaexercicio() {
		return metaexercicio;
	}

	public void setMetaexercicio(List<MetaExercicio> metaexercicio) {
		this.metaexercicio = metaexercicio;
	}


}
