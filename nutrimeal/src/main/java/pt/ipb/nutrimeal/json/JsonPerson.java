package pt.ipb.nutrimeal.json;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonPerson  {
	
	String email, name, lastName;
	
	String role, genero, nac, equipa;
	Date idade;
	
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
	
	public JsonPerson() {
	}

	public JsonPerson(String email, String name, String lastName) {
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}
	
	public JsonPerson(String email, String name, String lastName, Date data_nasc, String contacto, String sexo,
			String localidade, String nacionalidade, long bi, long contribuinte,
			String passwd, byte[] foto) {
		this.email = email;
		this.name = name;
		this.data_nasc = data_nasc;
		this.lastName=lastName;
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

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNac() {
		return nac;
	}

	public void setNac(String nac) {
		this.nac = nac;
	}

	public Date getIdade() {
		return idade;
	}

	public void setIdade(Date idade) {
		this.idade = idade;
	}

	public String getEquipa() {
		return equipa;
	}

	public void setEquipa(String equipa) {
		this.equipa = equipa;
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}


	
	
	

}
