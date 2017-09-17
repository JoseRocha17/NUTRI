package pt.ipb.nutrimeal.json;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonGrupo {
	
	long id;
	String nome;
	

	public JsonGrupo() {
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
	
	
	

}
