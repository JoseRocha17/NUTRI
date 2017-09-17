package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import pt.ipb.nutrimeal.entity.Novidade;

public interface NovidadeManager {
	Novidade createNovidade(String titulo, String brevedesc, String descricao, Date data);

	void deleteNovidade(long id);

	Novidade update(Novidade novidade);

	List<Novidade> getNovidades();

	Novidade getNovidade(long id);

}
