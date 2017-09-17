package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="AuthUser")
@NamedQueries({
	@NamedQuery(name=User.ALL, query="SELECT o from User o"),
	@NamedQuery(name=User.COUNT_ALL, query="SELECT COUNT(o) from User o"),
	@NamedQuery(name=User.BY_VERIFICATIONKEY, query="SELECT o from User o WHERE o.verificationKey=:verificationKey"),
	@NamedQuery(name="User.getProfile", query = "Select p from User p WHERE p.email=:name")
})
public class User implements Serializable {
	public static final String ALL = "pt.ipb.ejetty.entity.User.ALL";
	public static final String BY_VERIFICATIONKEY = "pt.ipb.ejetty.entity.User.BY_VERIFICATIONKEY";
	public static final String COUNT_ALL = "pt.ipb.ejetty.entity.User.COUNT_ALL";

	@Id
	String email;
	
	String name;
	String lastName;
	String passwd;
	String verificationKey;
	
	Date data_nasc;
	String contacto;
	String sexo;
	String localidade;
	String nacionalidade;
	
	long bi;
	long contribuinte; 
	
	@Basic(fetch = FetchType.LAZY)
	@Lob @Column(name="PIC")
	private byte[] foto;

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	List<Group> groups = new ArrayList<Group>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<Objetivos> objetivos = new ArrayList<Objetivos>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<Medidas> medidas= new ArrayList<Medidas>();
		
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<PerfilAlimentar> perfilAlimentar= new ArrayList<PerfilAlimentar>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<PerfilFisico> perfilFisico= new ArrayList<PerfilFisico>();
	
	public static String getAll() {
		return ALL;
	}

	public static String getByVerificationkey() {
		return BY_VERIFICATIONKEY;
	}

	public static String getCountAll() {
		return COUNT_ALL;
	}


	public User() {
	}

	public User(String email, String name, String lastName) {
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}
	public User(String email, String name, String lastName, String passwd) {
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.passwd = passwd;
	}
	
	public User(String email, String name, String passwd, byte[] foto) {
		super();
		this.email = email;
		this.name = name;
		this.passwd = passwd;
		this.foto = foto;
	}
	
	public User(String email, String name, String lastName, Date data_nasc, String contacto, String sexo, String localidade,
			String nacionalidade, long bi, long contribuinte, String passwd) {
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.data_nasc = data_nasc;
		this.contacto = contacto;
		this.sexo = sexo;
		this.localidade = localidade;
		this.nacionalidade = nacionalidade;
		this.bi = bi;
		this.contribuinte = contribuinte;
		this.passwd = passwd;
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getVerificationKey() {
		return verificationKey;
	}

	public void setVerificationKey(String verificationKey) {
		this.verificationKey = verificationKey;
	}

	public List<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(List<Group> groups) {
		this.groups = groups;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String toString(){
		String nome = this.name + " " + this.lastName;
		String mail = this.email;
		return mail + nome;
	}

	public List<Objetivos> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<Objetivos> objetivos) {
		this.objetivos = objetivos;
	}

	public List<Medidas> getMedidas() {
		return medidas;
	}

	public void setMedidas(List<Medidas> medidas) {
		this.medidas = medidas;
	}


	public List<PerfilAlimentar> getPerfilAlimentar() {
		return perfilAlimentar;
	}

	public void setPerfilAlimentar(List<PerfilAlimentar> perfilAlimentar) {
		this.perfilAlimentar = perfilAlimentar;
	}

	public List<PerfilFisico> getPerfilFisico() {
		return perfilFisico;
	}

	public void setPerfilFisico(List<PerfilFisico> perfilFisico) {
		this.perfilFisico = perfilFisico;
	}
	
	
	
	
	
}