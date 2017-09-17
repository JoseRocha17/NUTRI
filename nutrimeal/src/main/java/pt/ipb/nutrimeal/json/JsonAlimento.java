package pt.ipb.nutrimeal.json;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonAlimento  {
	
	long id;
	String nome;
	long grupo;
	long calorias;
	long gordura;
	long colestrol;
	long sodio;
	long potassio;
	long carboidrato;
	long fibra;
	long acucar;
	long proteina;
	byte[] photo;

	
	public JsonAlimento() {
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

	
	public long getGrupo() {
		return grupo;
	}


	public void setGrupo(long grupo) {
		this.grupo = grupo;
	}


	public long getCalorias() {
		return calorias;
	}


	public void setCalorias(long calorias) {
		this.calorias = calorias;
	}


	public long getGordura() {
		return gordura;
	}


	public void setGordura(long gordura) {
		this.gordura = gordura;
	}


	public long getColestrol() {
		return colestrol;
	}


	public void setColestrol(long colestrol) {
		this.colestrol = colestrol;
	}


	public long getSodio() {
		return sodio;
	}


	public void setSodio(long sodio) {
		this.sodio = sodio;
	}


	public long getPotassio() {
		return potassio;
	}


	public void setPotassio(long potassio) {
		this.potassio = potassio;
	}


	public long getCarboidrato() {
		return carboidrato;
	}


	public void setCarboidrato(long carboidrato) {
		this.carboidrato = carboidrato;
	}


	public long getFibra() {
		return fibra;
	}


	public void setFibra(long fibra) {
		this.fibra = fibra;
	}


	public long getAcucar() {
		return acucar;
	}


	public void setAcucar(long acucar) {
		this.acucar = acucar;
	}


	public long getProteina() {
		return proteina;
	}


	public void setProteina(long proteina) {
		this.proteina = proteina;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	

	
	
	
}