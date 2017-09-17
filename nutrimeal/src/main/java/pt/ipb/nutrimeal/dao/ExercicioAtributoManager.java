package pt.ipb.nutrimeal.dao;

import java.util.List;


import pt.ipb.nutrimeal.entity.ExercicioAtributo;


public interface ExercicioAtributoManager {

	ExercicioAtributo createExercicioAtributo( float valor, long idExercicio, long idAributo);

	void deleteExercicioAtributo(long id);

	ExercicioAtributo update(ExercicioAtributo exercicioAtributo);

	List<ExercicioAtributo> getExercicioAtributos();

	ExercicioAtributo getExercicioAtributo(long id);
	
	List<ExercicioAtributo> getExercicioAtributoUser(long id);
}
