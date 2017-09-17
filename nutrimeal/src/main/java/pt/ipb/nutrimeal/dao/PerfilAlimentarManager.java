package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;


import pt.ipb.nutrimeal.entity.PerfilAlimentar;

public interface PerfilAlimentarManager {
	
	PerfilAlimentar createPerfilAlimentar(String nome, String dia, Date data, String name);

	void deletePerfilAlimentar(long id);

	PerfilAlimentar update(PerfilAlimentar perfilAlimentar);

	List<PerfilAlimentar> getPerfilAlimentares();

	PerfilAlimentar getPerfilAlimentar(long id);
	
	List<PerfilAlimentar> getPerfilAlimentarNome(String email);

}
