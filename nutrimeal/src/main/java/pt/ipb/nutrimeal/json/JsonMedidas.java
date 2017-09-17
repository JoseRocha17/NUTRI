package pt.ipb.nutrimeal.json;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonMedidas {
	
	long id;
	String user;
	float peso, altura, pescoco, cintura, quadris;
	Date data;
	
	public JsonMedidas() {
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPeso() {
		return peso;
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
		return pescoco;
	}

	public void setPescoco(float pescoco) {
		this.pescoco = pescoco;
	}

	public float getCintura() {
		return cintura;
	}

	public void setCintura(float cintura) {
		this.cintura = cintura;
	}

	public float getQuadris() {
		return quadris;
	}

	public void setQuadris(float quadris) {
		this.quadris = quadris;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	

	
	
}