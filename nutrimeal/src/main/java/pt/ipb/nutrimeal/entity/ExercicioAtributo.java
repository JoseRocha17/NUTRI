package pt.ipb.nutrimeal.entity;

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

		@NamedQuery(name = "ExercicioAtributo.getAll", query = "SELECT ea FROM ExercicioAtributo ea"),
		@NamedQuery(name = "ExercicioAtributo.getExercicioUser", query = "SELECT ea FROM ExercicioAtributo ea WHERE ea.metaexercicio.id=:id") })
public class ExercicioAtributo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	float valor;

	@ManyToOne
	public Atributo atributo = new Atributo();

	@ManyToOne
	public MetaExercicio metaexercicio = new MetaExercicio();

	public ExercicioAtributo() {
		super();

	}

	public ExercicioAtributo(long id, float valor, String exercicio, String atributo) {
		this.id = id;
		this.valor = valor;
		this.metaexercicio.exercicio.nome=exercicio;
		this.atributo.nome = atributo;

	}

	public ExercicioAtributo(float valor, String exercicio, String atributo) {
		this.valor = valor;
		this.metaexercicio.exercicio.nome=exercicio;
		this.atributo.nome = atributo;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public MetaExercicio getMetaexercicio() {
		return metaexercicio;
	}

	public void setMetaexercicio(MetaExercicio metaexercicio) {
		this.metaexercicio = metaexercicio;
	}



}
