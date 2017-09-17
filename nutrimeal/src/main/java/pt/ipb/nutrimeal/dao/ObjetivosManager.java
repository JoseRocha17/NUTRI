package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;


import pt.ipb.nutrimeal.entity.Objetivos;

public interface ObjetivosManager {

	Objetivos createObjetivo(float peso, float pescoco, float cintura, float quadris, Date data, String nome);

	void deleteObjetivo(long id);

//	Objetivos update(float peso, float pescoco, float cintura, float quadris, Date data, String nome);
	
	Objetivos update(Objetivos objetivos);

	List<Objetivos> getObjetivos();

	Objetivos getObjetivo(long id);
		
	List<Objetivos> getObjetivosUser(String email);
}
