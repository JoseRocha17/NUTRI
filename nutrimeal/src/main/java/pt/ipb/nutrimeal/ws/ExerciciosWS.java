package pt.ipb.nutrimeal.ws;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.*;
import pt.ipb.nutrimeal.json.JsonExercicio;


@Path("/exercicios")
public class ExerciciosWS {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Exercicio> getExercicioList() {
		List<Exercicio> lista_exercicios = new ArrayList<>();

		for (Exercicio exercicio : PersisterFactory.getInstance().getExercicioManager().getExercicio()) {
			lista_exercicios.add(new Exercicio(exercicio.getId(), exercicio.getNome(), exercicio.getTipo(), exercicio.getDescricao()));
		}
		return lista_exercicios;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Exercicio Exercicio(@PathParam("id") long id) {

		Exercicio exercicio = PersisterFactory.getInstance().getExercicioManager().getExercicio(id);
		
		Exercicio e = new Exercicio();
		e.setId(exercicio.getId());
		e.setNome(exercicio.getNome());
		e.setTipo(exercicio.getTipo());
		e.setDescricao(exercicio.getDescricao());
		e.setFoto(exercicio.getFoto());

		
		return e;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addExercicio(JsonExercicio exercicio) {
		Exercicio e = PersisterFactory.getInstance().getExercicioManager().createExercicio(exercicio.getNome(), exercicio.getTipo(),
				exercicio.getDescricao());
		try {
			return Response.created(new URI("/exercicios?id=" + e.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteExercicio(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getExercicioManager().deleteExercicio(id);
			return Response.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response apagarExercicio(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getExercicioManager().deleteExercicio(id);
			return Response.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateExercicio(Exercicio exercicio) {
		PersisterFactory.getInstance().getExercicioManager().update(exercicio);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
