package pt.ipb.nutrimeal.dao;

import java.util.List;
import pt.ipb.nutrimeal.entity.Refeicao;

public interface RefeicaoManager {
	
	Refeicao createRefeicao(String nome, long idPerfil);

	void deleteRefeicao(long id);

	Refeicao update(Refeicao refeicao);

	List<Refeicao> getRefeicoes();

	Refeicao getRefeicao(long id);
		
	List<Refeicao> getPerfilRefeicao(long id);
	
}
