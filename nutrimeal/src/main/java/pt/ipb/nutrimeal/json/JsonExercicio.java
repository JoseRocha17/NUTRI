package pt.ipb.nutrimeal.json;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonExercicio {
	
	long id;
	String nome, tipo, descricao;
	byte[] photo;

	
	public JsonExercicio() {
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


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	

}