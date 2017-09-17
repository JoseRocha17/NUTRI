package pt.ipb.nutrimeal.dao;

import java.util.List;

import pt.ipb.nutrimeal.entity.Atributo;

public interface AtributoManager {
	
	Atributo createAtributo(String nome);

	void deleteAtributo(long id);

	Atributo update(Atributo atributo);

	List<Atributo> getAtributos();

	Atributo getAtributo(long id);
	
//	Atributo getAtributoNome(String nome);

}
