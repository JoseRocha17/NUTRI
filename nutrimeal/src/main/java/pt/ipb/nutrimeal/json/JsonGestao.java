package pt.ipb.nutrimeal.json;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonGestao  {
	
	long idGestao;
	String idUser, idPessoa, nome, equipa;
	
	public JsonGestao() {
	}

	public JsonGestao(long idGestao, String idPessoa, String nome,  String equipa) {
		this.idGestao = idGestao;
		this.nome = nome;
		this.idPessoa = idPessoa;
		this.equipa = equipa;
	}

	public String getEquipa() {
		return equipa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEquipa(String equipa) {
		this.equipa = equipa;
	}

	public long getIdGestao() {
		return idGestao;
	}

	public void setIdGestao(long idGestao) {
		this.idGestao = idGestao;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
	}


}
