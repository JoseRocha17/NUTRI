package pt.ipb.nutrimeal.dao;

import java.util.List;

import pt.ipb.nutrimeal.entity.Alimento;

public interface AlimentoManager {
	
	Alimento createAlimento(String nome, long idGrupo, float calorias, float gordura, float colestrol, float sodio, float potassio, float carboidrato,
			float fibra, float acucar, float proteina);

	void deleteAlimento(long id);

	Alimento update(Alimento alimento);

	List<Alimento> getAlimentos();
	
	Alimento getAlimento(long id);
	
	Alimento getAlimentoNome(String nome);
	
	List<Alimento> getAlimentoGrupo(long id);

}
