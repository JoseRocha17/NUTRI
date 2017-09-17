package pt.ipb.nutrimeal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table
@NamedQueries({
		@NamedQuery(name="MetaExercicio.getAll", query = "SELECT me FROM MetaExercicio me"),
		@NamedQuery(name="MetaExercicio.getPerfilUser", query = "SELECT me FROM MetaExercicio me WHERE me.perfil.id=:id")
})
public class MetaExercicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	float calorias;
	@ManyToOne
	public PerfilFisico perfil = new PerfilFisico();
	
	@ManyToOne
	public Exercicio exercicio = new Exercicio();
	
	@OneToMany(mappedBy = "metaexercicio", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<ExercicioAtributo> exercicioatributo = new ArrayList<ExercicioAtributo>();
	
	public MetaExercicio(){
		super();
		
	}
	
	public  MetaExercicio(long id, float calorias, String exercicio, String perfil){
		this.id = id;
		this.calorias=calorias;
		this.exercicio.nome=exercicio;
		this.perfil.nome=perfil;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public float getCalorias() {
		return calorias;
	}

	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public PerfilFisico getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilFisico perfil) {
		this.perfil = perfil;
	}

	public List<ExercicioAtributo> getExercicioatributo() {
		return exercicioatributo;
	}

	public void setExercicioatributo(List<ExercicioAtributo> exercicioatributo) {
		this.exercicioatributo = exercicioatributo;
	}


	
	
}
