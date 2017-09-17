package pt.ipb.nutrimeal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;




@SuppressWarnings("serial")
@Table
@NamedQueries({
		@NamedQuery(name="Refeicao.getAll", query = "SELECT r FROM Refeicao r"),
		@NamedQuery(name="Refeicao.getPerfil", query = "SELECT rp FROM Refeicao rp where rp.perfilalimentar.id=:id")
})
@Entity
public class Refeicao implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String nome;
	
	@OneToMany(mappedBy = "refeicao", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	List<QuantidadeAlimentar> quantidadealimentar = new ArrayList<QuantidadeAlimentar>();

	@ManyToOne
	public PerfilAlimentar perfilalimentar = new PerfilAlimentar();
	
	public Refeicao() {
		super();
	}
	
	public Refeicao (long id, String nome, String perfil){
		this.id=id;
		this.nome=nome;
		this.perfilalimentar.nome=perfil;	
	}

	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id=id;
	}
	
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome=nome;
	}



	public List<QuantidadeAlimentar> getQuantidadealimentar() {
		return quantidadealimentar;
	}

	public void setQuantidadealimentar(List<QuantidadeAlimentar> quantidadealimentar) {
		this.quantidadealimentar = quantidadealimentar;
	}

	public PerfilAlimentar getPerfilalimentar() {
		return perfilalimentar;
	}

	public void setPerfilalimentar(PerfilAlimentar perfilalimentar) {
		this.perfilalimentar = perfilalimentar;
	}

	public PerfilAlimentar getPerfilAlimentar() {
		return perfilalimentar;
	}

	public void setPerfilAlimentar(PerfilAlimentar perfilAlimentar) {
		this.perfilalimentar = perfilAlimentar;
	}




	
	


	
	
	


}
