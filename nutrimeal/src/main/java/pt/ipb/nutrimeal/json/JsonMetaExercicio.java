package pt.ipb.nutrimeal.json;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonMetaExercicio {

	long id, exercicio, perfil;
	float calorias;
	public JsonMetaExercicio(){
		
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
	public long getExercicio() {
		return exercicio;
	}

	public void setExercicio(long exercicio) {
		this.exercicio = exercicio;
	}

	public long getPerfil() {
		return perfil;
	}

	public void setPerfil(long perfil) {
		this.perfil = perfil;
	}
	
}
