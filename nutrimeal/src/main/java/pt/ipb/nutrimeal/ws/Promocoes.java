package pt.ipb.nutrimeal.ws;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import pt.ipb.nutrimeal.entity.*;


@Path("/promocoes")
public class Promocoes {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Promocao> getNovidadeList() {
		List<Promocao> lista_promocoes = new ArrayList<>();

		for (Promocao promocao : PersisterFactory.getInstance().getPromocaoManager().getPromocoes()) {
			lista_promocoes.add(new Promocao(promocao.getId(), promocao.getTitulo(), promocao.getBreveDesc(), promocao.getDescricao(), promocao.getPreco(), promocao.getDataInicio(), promocao.getDataFim()));
		}
		return lista_promocoes;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Promocao Promocao(@PathParam("id") long id) {

		Promocao promocao = PersisterFactory.getInstance().getPromocaoManager().getPromocao(id);
		
		Promocao p = new Promocao();
		p.setId(promocao.getId());
		p.setTitulo(promocao.getTitulo());
		p.setBreveDesc(promocao.getBreveDesc());
		p.setDescricao(promocao.getDescricao());
		p.setPreco(promocao.getPreco());
		p.setDataInicio(promocao.getDataInicio());
		p.setDataFim(promocao.getDataFim());
		p.setFoto(promocao.getFoto());
		
		return p;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPromocao(Promocao promocao) {
		Promocao p = PersisterFactory.getInstance().getPromocaoManager().createPromocao(promocao.getTitulo(), promocao.getBreveDesc(), promocao.getDescricao(), promocao.getPreco(), promocao.getDataInicio(), promocao.getDataFim());
		try {
			return Response.created(new URI("/promocoes?id=" + p.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deletePromocao(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getPromocaoManager().deletePromocao(id);
			return Response.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response apagarPromocao(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getPromocaoManager().deletePromocao(id);
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
	public Response updatePromocao(Promocao promocao) {
		PersisterFactory.getInstance().getPromocaoManager().update(promocao);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
