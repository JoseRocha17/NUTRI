package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;


import pt.ipb.nutrimeal.entity.PerfilFisico;

public interface PerfilFisicoManager {

	PerfilFisico createPerfilFisico(String nome, String dia, Date data, String name);

	void deletePerfilFisico(long id);


	PerfilFisico update(PerfilFisico perfilFisico);

	List<PerfilFisico> getPerfilFisicos();

	PerfilFisico getPerfilFisico(long id);
		
	List<PerfilFisico> getPerfilFisicoUser(String email);
	
}
