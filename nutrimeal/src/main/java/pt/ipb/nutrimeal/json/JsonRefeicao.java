package pt.ipb.nutrimeal.json;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonRefeicao {
	
	long id, perfil;
	String nome;

	public JsonRefeicao() {
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

	public long getPerfil() {
		return perfil;
	}

	public void setPerfil(long perfil) {
		this.perfil = perfil;
	}





	



	

	
	
}