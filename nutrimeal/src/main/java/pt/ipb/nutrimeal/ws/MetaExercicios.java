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
import pt.ipb.nutrimeal.entity.MetaExercicio;
import pt.ipb.nutrimeal.json.JsonMetaExercicio;

@Path("/metaexercicios")
public class MetaExercicios {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MetaExercicio> getMetaExercicioList() {
		List<MetaExercicio> lista_metaExercicio = new ArrayList<>();

		for (MetaExercicio metaExercicio : PersisterFactory.getInstance().getMetaExercicioManager().getMetaExercicios()) {
			lista_metaExercicio.add(new MetaExercicio(metaExercicio.getId(), metaExercicio.getCalorias(), metaExercicio.exercicio.getNome(), metaExercicio.perfil.getNome()));
		}
		return lista_metaExercicio;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public MetaExercicio getMetaExercicio(@PathParam("id") long id) {

		MetaExercicio metaExercicio = PersisterFactory.getInstance().getMetaExercicioManager().getMetaExercicio(id);
		
		MetaExercicio me = new MetaExercicio();
		me.setId(metaExercicio.getId());
		
		me.setCalorias(metaExercicio.getCalorias());
		me.exercicio.setId(metaExercicio.exercicio.getId());
		me.exercicio.setNome(metaExercicio.exercicio.getNome());
		me.exercicio.setTipo(metaExercicio.exercicio.getTipo());
		me.exercicio.setDescricao(metaExercicio.exercicio.getDescricao());
		
		me.perfil.setId(metaExercicio.perfil.getId());
		me.perfil.setNome(metaExercicio.perfil.getNome());
		me.perfil.user.setEmail(metaExercicio.perfil.user.getEmail());
		me.perfil.user.setName(metaExercicio.perfil.user.getName());
		me.perfil.user.setLastName(metaExercicio.perfil.user.getLastName());
		me.perfil.setData(metaExercicio.perfil.getData());
		me.perfil.setDia(metaExercicio.perfil.getDia());
		

		return me;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMetaExercicio(JsonMetaExercicio metaExercicio) {
		System.out.println("-------------------------------" + metaExercicio.getExercicio());
		System.out.println("-------------------------------" + metaExercicio.getPerfil());

		MetaExercicio me = PersisterFactory.getInstance().getMetaExercicioManager().createMetaExercicio(metaExercicio.getCalorias(), metaExercicio.getExercicio(), metaExercicio.getPerfil());
		try {
			return Response.created(new URI("/metaexercicios?id=" + me.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteMetaExercicio(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getMetaExercicioManager().deleteMetaExercicio(id);
			return Response.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response apagarMetaExercicio(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getMetaExercicioManager().deleteMetaExercicio(id);
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
	public Response updateMetaExercicio(MetaExercicio metaExercicio) {
		PersisterFactory.getInstance().getMetaExercicioManager().update(metaExercicio);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
