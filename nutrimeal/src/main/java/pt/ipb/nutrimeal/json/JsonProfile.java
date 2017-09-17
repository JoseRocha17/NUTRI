package pt.ipb.nutrimeal.json;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonProfile {

	String email;
	String nome;
	Date data_nasc;
	String contacto;
	String sexo;
	String localidade;
	String nacionalidade;
	long bi;
	long contribuinte; 
	String isAdmin, isUser;
	String passwd;
	byte[] foto;

	public JsonProfile() {
	}

	public JsonProfile(String email, String nome, Date data_nasc, String contacto, String sexo,
			String localidade, String nacionalidade, long bi, long contribuinte, String passwd) {
		this.email = email;
		this.nome = nome;
		this.data_nasc = data_nasc;
		this.contacto = contacto;
		this.sexo = sexo;
		this.localidade = localidade;
		this.nacionalidade = nacionalidade;
		this.bi = bi;
		this.contribuinte = contribuinte;
		this.passwd = passwd;
	}
	
	public JsonProfile(String email, String nome, Date data_nasc, String contacto, String sexo,
			String localidade, String nacionalidade, long bi, long contribuinte,
			String passwd, byte[] foto) {
		this.email = email;
		this.nome = nome;
		this.data_nasc = data_nasc;
		this.contacto = contacto;
		this.sexo = sexo;
		this.localidade = localidade;
		this.nacionalidade = nacionalidade;
		this.bi = bi;
		this.contribuinte = contribuinte;
		this.passwd = passwd;
		this.foto = foto;
	}
	
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public long getBi() {
		return bi;
	}

	public void setBi(long bi) {
		this.bi = bi;
	}

	public long getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(long contribuinte) {
		this.contribuinte = contribuinte;
	}
	
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String getIsUser() {
		return isUser;
	}

	public void setIsUser(String isUser) {
		this.isUser = isUser;
	}



	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	
	public String toString(){
		return "Email:" + email + '\n'
		+ "Nome:" + nome + '\n'
		+ "DataNasc:" + data_nasc + '\n'
		+ "Contacto:" + contacto + '\n'
		+ "Sexo:" + sexo + '\n'
		+ "Localidade:" + localidade + '\n'
		+ "Nacionalidade:" + nacionalidade + '\n'
		+ "BI:" + bi + '\n'
		+ "Contribuinte:" + contribuinte + '\n'; 
	}



}
