package pt.ipb.nutrimeal.ws;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.Pedidos;
import pt.ipb.nutrimeal.json.JsonPedido;

@Path("/pedidos")
public class PedidosWS {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedidos> getNovidadeList() {
		List<Pedidos> lista_pedidos = new ArrayList<>();

		for (Pedidos pedido : PersisterFactory.getInstance().getPedidosManager().getPedidos()) {
			lista_pedidos.add(new Pedidos(pedido.getId(), pedido.getEmail(), pedido.getNome(), pedido.getContacto(), pedido.getTitulo(), pedido.getAssunto(), pedido.getData()));
		}
		return lista_pedidos;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Pedidos Pedidos(@PathParam("id") long id) {

		Pedidos pedido = PersisterFactory.getInstance().getPedidosManager().getPedido(id);
		
		Pedidos p = new Pedidos();
		p.setId(pedido.getId());
		p.setEmail(pedido.getEmail());
		p.setNome(pedido.getNome());
		p.setContacto(pedido.getContacto());
		p.setTitulo(pedido.getTitulo());
		p.setAssunto(pedido.getAssunto());
		p.setData(pedido.getData());

		
		return p;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPromocao(JsonPedido pedido) {
		Date data = new Date();
		System.out.println("Email ---" + pedido.getData());
		Pedidos p = PersisterFactory.getInstance().getPedidosManager().createPedido(pedido.getEmail(), pedido.getNome(), pedido.getContacto(), pedido.getTitulo(), pedido.getAssunto(), data);
		
		try {
			return Response.created(new URI("/pedidos?id=" + p.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deletePedidos(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getPedidosManager().deletePedido(id);
			return Response.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response apagarPedido(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getPedidosManager().deletePedido(id);
			return Response.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updatePedidos(Pedidos pedido) {
		PersisterFactory.getInstance().getPedidosManager().update(pedido);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
