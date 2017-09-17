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


@Path("/novidades")
public class Novidades {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Novidade> getNovidadeList() {
		List<Novidade> lista_novidades = new ArrayList<>();

		for (Novidade novidade : PersisterFactory.getInstance().getNovidadeManager().getNovidades()) {
			lista_novidades.add(new Novidade(novidade.getId(), novidade.getTitulo(), novidade.getBrevedesc(), novidade.getDescricao(), novidade.getData()));
		}
		return lista_novidades;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Novidade Novidade(@PathParam("id") long id) {

		Novidade novidade = PersisterFactory.getInstance().getNovidadeManager().getNovidade(id);
		
		Novidade n = new Novidade();
		n.setId(novidade.getId());
		n.setTitulo(novidade.getTitulo());
		n.setBrevedesc(novidade.getBrevedesc());
		n.setDescricao(novidade.getDescricao());
		n.setData(novidade.getData());
		n.setFoto(novidade.getFoto());
		
		return n;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNovidade(Novidade novidade) {
		Novidade n = PersisterFactory.getInstance().getNovidadeManager().createNovidade(novidade.getTitulo(), novidade.getBrevedesc(), novidade.getDescricao(), novidade.getData());
		try {
			return Response.created(new URI("/novidades?id=" + n.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteNovidade(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getNovidadeManager().deleteNovidade(id);
			return Response.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response apagarNovidade(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getNovidadeManager().deleteNovidade(id);
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
	public Response updateNovidade(Novidade novidade) {
		PersisterFactory.getInstance().getNovidadeManager().update(novidade);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
