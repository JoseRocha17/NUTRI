package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import pt.ipb.nutrimeal.entity.Medidas;


public interface MedidasManager {

	Medidas createMedida(float peso, float altura, float pescoco, float cintura, float quadris, Date data, String nome);

	void deleteMedida(long id);

	Medidas update(Medidas medidas);

	List<Medidas> getMedidas();

	Medidas getMedida(long id);

	List<Medidas> getMedidasUser(String email);

}
