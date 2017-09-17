package pt.ipb.nutrimeal.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
@NamedQueries({
		@NamedQuery(name="QuantidadeAlimentar.getAll", query = "SELECT g FROM QuantidadeAlimentar g"),
		@NamedQuery(name="QuantidadeAlimentar.getQuantidadeRefeicao", query = "SELECT g FROM QuantidadeAlimentar g WHERE g.refeicao.id=:id")
})
public class QuantidadeAlimentar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	long quantidade;
	String atributo;

	
	@ManyToOne
	public Refeicao refeicao = new Refeicao();
	
	@ManyToOne
	public Alimento alimento = new Alimento();
	
	public QuantidadeAlimentar(){
		super();
		
	}
	
	public  QuantidadeAlimentar(long id, long quantidade, String atributo, String refeicao, String alimento){
		this.id = id;
		this.quantidade=quantidade;
		this.atributo = atributo;
		this.refeicao.nome = refeicao;
		this.alimento.nome = alimento;
	}
	
	public  QuantidadeAlimentar(long id, long quantidade, String atributo, String refeicao, String alimento, Date data, String dia){
		this.id = id;
		this.quantidade=quantidade;
		this.atributo = atributo;
		this.refeicao.nome = refeicao;
		this.alimento.nome=alimento;
		this.refeicao.perfilalimentar.data=data;
		this.refeicao.perfilalimentar.dia=dia;
	}
	public  QuantidadeAlimentar(long id, long quantidade, String atributo, String refeicao, long idAlimento, String alimento, Date data, String dia, float quantidades){
		this.id = id;
		this.quantidade=quantidade;
		this.atributo = atributo;
		this.refeicao.nome = refeicao;
		this.alimento.id=idAlimento;
		this.alimento.nome=alimento;
		this.refeicao.perfilalimentar.data=data;
		this.refeicao.perfilalimentar.dia=dia;
		this.alimento.calorias=quantidades;
	}

	public  QuantidadeAlimentar(long id, long quantidade, String atributo, String refeicao, long idAlimento, String alimento, Date data, String dia, float carboidrato, float proteina, float gordura){
		this.id = id;
		this.quantidade=quantidade;
		this.atributo = atributo;
		this.refeicao.nome = refeicao;
		this.alimento.id=idAlimento;
		this.alimento.nome=alimento;
		this.refeicao.perfilalimentar.data=data;
		this.refeicao.perfilalimentar.dia=dia;
		this.alimento.carboidrato=carboidrato;
		this.alimento.proteina=proteina;
		this.alimento.gordura=gordura;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	
	

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	
	
	
}
