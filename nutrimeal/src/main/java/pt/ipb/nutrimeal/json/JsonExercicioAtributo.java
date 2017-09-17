package pt.ipb.nutrimeal.json;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonExercicioAtributo {

	long id;
	long metaexercicio, atributo;
	float valor;
	
	public JsonExercicioAtributo() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getMetaexercicio() {
		return metaexercicio;
	}

	public void setMetaexercicio(long metaexercicio) {
		this.metaexercicio = metaexercicio;
	}

	public long getAtributo() {
		return atributo;
	}

	public void setAtributo(long atributo) {
		this.atributo = atributo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	
	
}
