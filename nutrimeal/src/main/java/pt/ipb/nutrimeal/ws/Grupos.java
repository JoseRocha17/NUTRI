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
import pt.ipb.nutrimeal.entity.Atributo;
import pt.ipb.nutrimeal.entity.Grupo;
import pt.ipb.nutrimeal.json.JsonAtributo;
import pt.ipb.nutrimeal.json.JsonGrupo;

@Path("/grupos")
public class Grupos {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Grupo> getGrupoList() {
		List<Grupo> lista_grupos = new ArrayList<>();

		for (Grupo grupo : PersisterFactory.getInstance().getGrupoManager().getGrupos()) {
			lista_grupos.add(new Grupo(grupo.getId(), grupo.getNome()));
		}
		return lista_grupos;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Grupo Grupo(@PathParam("id") long id) {

		Grupo grupo = PersisterFactory.getInstance().getGrupoManager().getGrupo(id);

		Grupo a = new Grupo();
		a.setId(grupo.getId());
		a.setNome(grupo.getNome());

		return a;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGrupo(JsonGrupo grupo) {
		Grupo e = PersisterFactory.getInstance().getGrupoManager().createGrupo(grupo.getNome());
		try {
			return Response.created(new URI("/grupos?id=" + e.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteGrupo(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getGrupoManager().deleteGrupo(id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response apagarGrupo(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getGrupoManager().deleteGrupo(id);
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
	public Response updateGrupo(Grupo grupo) {
		PersisterFactory.getInstance().getGrupoManager().update(grupo);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
