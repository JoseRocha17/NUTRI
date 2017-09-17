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
import pt.ipb.nutrimeal.json.JsonAtributo;

@Path("/atributos")
public class Atributos {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Atributo> getAtributoList() {
		List<Atributo> lista_atributos = new ArrayList<>();

		for (Atributo atributo : PersisterFactory.getInstance().getAtributoManager().getAtributos()) {
			lista_atributos.add(new Atributo(atributo.getId(), atributo.getNome()));
		}
		return lista_atributos;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Atributo Atributo(@PathParam("id") long id) {

		Atributo atributo = PersisterFactory.getInstance().getAtributoManager().getAtributo(id);

		Atributo a = new Atributo();
		a.setId(atributo.getId());
		a.setNome(atributo.getNome());

		return a;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAtributo(JsonAtributo atributo) {
		Atributo e = PersisterFactory.getInstance().getAtributoManager().createAtributo(atributo.getNome());
		try {
			return Response.created(new URI("/atributos?id=" + e.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteAtributo(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getAtributoManager().deleteAtributo(id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateAtributo(Atributo atributo) {
		PersisterFactory.getInstance().getAtributoManager().update(atributo);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
