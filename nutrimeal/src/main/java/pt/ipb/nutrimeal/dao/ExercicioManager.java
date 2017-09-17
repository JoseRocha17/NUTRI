package pt.ipb.nutrimeal.dao;


import java.util.List;

import pt.ipb.nutrimeal.entity.Exercicio;



public interface ExercicioManager  {
	
	Exercicio createExercicio(String nome, String tipo, String descricao);

	void deleteExercicio(long id);

	Exercicio update(Exercicio exercicio);

	List<Exercicio> getExercicio();

	Exercicio getExercicio(long id);
	
	Exercicio getExercicioNome(String nome);

}
