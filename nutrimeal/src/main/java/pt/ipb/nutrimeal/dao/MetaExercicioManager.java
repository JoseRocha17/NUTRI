package pt.ipb.nutrimeal.dao;

import java.util.List;

import pt.ipb.nutrimeal.entity.MetaExercicio;

public interface MetaExercicioManager {
	MetaExercicio createMetaExercicio(float calorias, long idExercicio, long idPerfil);

	void deleteMetaExercicio(long id);

	MetaExercicio update(MetaExercicio metaExercicio);

	List<MetaExercicio> getMetaExercicios();

	MetaExercicio getMetaExercicio(long id);
	
	List<MetaExercicio> getPerfilUser(long id);

}
