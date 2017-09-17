package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import pt.ipb.nutrimeal.entity.Promocao;

public interface PromocaoManager {
	Promocao createPromocao(String titulo, String breveDesc, String descricao, float preco, Date dataInicio, Date dataFim);

	void deletePromocao(long id);

	Promocao update(Promocao promocao);

	List<Promocao> getPromocoes();

	Promocao getPromocao(long id);
}
