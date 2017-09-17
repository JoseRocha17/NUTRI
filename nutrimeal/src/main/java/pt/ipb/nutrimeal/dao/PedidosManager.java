package pt.ipb.nutrimeal.dao;

import java.util.Date;
import java.util.List;

import pt.ipb.nutrimeal.entity.Pedidos;

public interface PedidosManager {
	Pedidos createPedido(String email, String nome, String contacto, String titulo, String assunto, Date data);

	void deletePedido(long id);

	Pedidos update(Pedidos pedido);

	List<Pedidos> getPedidos();

	Pedidos getPedido(long id);

}
