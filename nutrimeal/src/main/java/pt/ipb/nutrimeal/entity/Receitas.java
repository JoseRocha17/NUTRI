package pt.ipb.nutrimeal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Receitas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private int porcoes;
	
	@OneToMany(mappedBy="recipes", cascade=CascadeType.PERSIST)
	List<Alimento> foods = new ArrayList<Alimento>();

	
	public Receitas (){
		super ();
	}
	
	public Receitas(String nome, int porcoes){
		this.nome=nome;
		this.porcoes=porcoes;
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
	
	public int getPorcoes(){
		return this.porcoes;
	}
	
	public void setPorcoes(int porcoes){
		this.porcoes=porcoes;
	}
	
	public List<Alimento> getFood(){
		return foods;
	}
	
	public void setFood(List<Alimento> foods){
		this.foods=foods;
	}



}
