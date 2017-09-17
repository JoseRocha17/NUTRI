package pt.ipb.nutrimeal.dao;

import java.util.List;

import pt.ipb.nutrimeal.entity.QuantidadeAlimentar;


public interface QuantidadeAlimentarManager {
	
	QuantidadeAlimentar createQuantidadeAlimentar(long quantidade, String atributo, long idRefeicao, long idAlimento);

	void deleteQuantidadeAlimentar(long id);

	QuantidadeAlimentar update(QuantidadeAlimentar quantidadeAlimentar);

	List<QuantidadeAlimentar> getQuantidadeAlimentares();

	QuantidadeAlimentar getQuantidadeAlimentar(long id);
	
	List<QuantidadeAlimentar> getQuantidadeRefeicao(long id);


}
