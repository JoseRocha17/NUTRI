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
import pt.ipb.nutrimeal.json.JsonAlimento;


@Path("/alimentos")
public class Alimentos {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alimento> getAlimentoList() {
		List<Alimento> lista_alimentos = new ArrayList<>();

		for (Alimento alimento : PersisterFactory.getInstance().getAlimentoManager().getAlimentos()) {
			lista_alimentos.add(new Alimento(alimento.getId(), alimento.getNome(), alimento.grupo.getNome(), alimento.getCalorias(),
					alimento.getGordura(), alimento.getColestrol(), alimento.getSodio(), alimento.getPotassio(),
					alimento.getCarboidrato(), alimento.getFibra(), alimento.getAcucar(), alimento.getProteina()));
		}
		return lista_alimentos;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Alimento Alimento(@PathParam("id") long id) {

		Alimento alimento = PersisterFactory.getInstance().getAlimentoManager().getAlimento(id);
		
		Alimento e = new Alimento();
		e.setId(alimento.getId());
		e.setNome(alimento.getNome());
		
		e.grupo.setNome(alimento.grupo.getNome());
		
		e.setCalorias(alimento.getCalorias());
		e.setGordura(alimento.getGordura());
		e.setColestrol(alimento.getColestrol());
		e.setSodio(alimento.getSodio());
		e.setPotassio(alimento.getPotassio());
		e.setCarboidrato(alimento.getCarboidrato());
		e.setFibra(alimento.getFibra());
		e.setAcucar(alimento.getAcucar());
		e.setProteina(alimento.getProteina());
		e.setFoto(alimento.getFoto());
		
		return e;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAlimento(JsonAlimento alimento) {
		Alimento e = PersisterFactory.getInstance().getAlimentoManager().createAlimento(alimento.getNome(), alimento.getGrupo(), alimento.getCalorias(),
				alimento.getGordura(), alimento.getColestrol(), alimento.getSodio(), alimento.getPotassio(),
				alimento.getCarboidrato(), alimento.getFibra(), alimento.getAcucar(), alimento.getProteina());
		try {
			return Response.created(new URI("/alimentos?id=" + e.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteAlimento(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getAlimentoManager().deleteAlimento(id);
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
	public Response updateAlimento(Alimento alimento) {
		PersisterFactory.getInstance().getAlimentoManager().update(alimento);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
