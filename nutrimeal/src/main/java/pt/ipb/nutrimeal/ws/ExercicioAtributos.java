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
import pt.ipb.nutrimeal.entity.ExercicioAtributo;
import pt.ipb.nutrimeal.json.JsonExercicioAtributo;


@Path("/exercicioatributos")
public class ExercicioAtributos {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExercicioAtributo> getExercicioAtributoList() {
		List<ExercicioAtributo> lista_exercicioAtributos = new ArrayList<>();

		for (ExercicioAtributo exercicioAtributo : PersisterFactory.getInstance().getExercicioAtributoManager().getExercicioAtributos()) {
			lista_exercicioAtributos.add(new ExercicioAtributo(exercicioAtributo.getId(), exercicioAtributo.getValor(), exercicioAtributo.metaexercicio.exercicio.getNome(), exercicioAtributo.atributo.getNome()));
		}
		return lista_exercicioAtributos;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ExercicioAtributo getExercicioAtributo(@PathParam("id") long id) {

		ExercicioAtributo exercicioAtributo = PersisterFactory.getInstance().getExercicioAtributoManager().getExercicioAtributo(id);
		
		ExercicioAtributo ea = new ExercicioAtributo();
		
		ea.setId(exercicioAtributo.getId());
		ea.setValor(exercicioAtributo.getValor());
		
	
		ea.metaexercicio.setId(exercicioAtributo.metaexercicio.getId());
		ea.metaexercicio.exercicio.setNome(exercicioAtributo.metaexercicio.exercicio.getNome());
		ea.metaexercicio.perfil.setId(exercicioAtributo.metaexercicio.perfil.getId());
		
		ea.atributo.setId(exercicioAtributo.atributo.getId());
		ea.atributo.setNome(exercicioAtributo.atributo.getNome());

		
		return ea;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addExercicioAtributo(JsonExercicioAtributo exercicioAtributo) {
		ExercicioAtributo ea = PersisterFactory.getInstance().getExercicioAtributoManager().createExercicioAtributo(exercicioAtributo.getValor(), exercicioAtributo.getMetaexercicio(), 
				exercicioAtributo.getAtributo());
		try {
			return Response.created(new URI("/exercicioatributos?id=" + ea.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteExercicioAtributo(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getExercicioAtributoManager().deleteExercicioAtributo(id);
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
	public Response updateExercicioAtributo(ExercicioAtributo exercicioAtributo) {
		PersisterFactory.getInstance().getExercicioAtributoManager().update(exercicioAtributo);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
