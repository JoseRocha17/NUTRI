package pt.ipb.nutrimeal.dao;


import java.util.List;

import pt.ipb.nutrimeal.entity.Grupo;


public interface GrupoManager {
	Grupo createGrupo(String nome);

	void deleteGrupo(long id);

	Grupo update(Grupo grupo);

	List<Grupo> getGrupos();

	Grupo getGrupo(long id);
}
