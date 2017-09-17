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
import pt.ipb.nutrimeal.json.JsonRefeicao;

@Path("/refeicoes")
public class RefeicoesWS {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Refeicao> getRefeicoesList() {
		List<Refeicao> lista_refeicoes = new ArrayList<>();

		for (Refeicao refeicao : PersisterFactory.getInstance().getRefeicaoManager().getRefeicoes()) {
			lista_refeicoes.add(new Refeicao(refeicao.getId(), refeicao.getNome(), refeicao.perfilalimentar.getNome()));
		}
		return lista_refeicoes;
	}



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Refeicao Refeicao(@PathParam("id") long id) {

		Refeicao refeicao = PersisterFactory.getInstance().getRefeicaoManager().getRefeicao(id);

		Refeicao r = new Refeicao();
		r.setId(refeicao.getId());
		r.setNome(refeicao.getNome());

		r.perfilalimentar.setId(refeicao.perfilalimentar.getId());
		r.perfilalimentar.setNome(refeicao.perfilalimentar.getNome());
		r.perfilalimentar.setData(refeicao.perfilalimentar.getData());
		r.perfilalimentar.setDia(refeicao.perfilalimentar.getDia());
		r.perfilalimentar.user.setEmail(refeicao.perfilalimentar.user.getEmail());
		r.perfilalimentar.user.setName(refeicao.perfilalimentar.user.getName());
		r.perfilalimentar.user.setLastName(refeicao.perfilalimentar.user.getLastName());

		return r;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRefeicao(JsonRefeicao refeicao) {
		Refeicao r = PersisterFactory.getInstance().getRefeicaoManager().createRefeicao(refeicao.getNome(),
				refeicao.getPerfil());
		try {
			return Response.created(new URI("/refeicao?id=" + r.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteRefeicao(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getRefeicaoManager().deleteRefeicao(id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response apagarRefeicao(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getRefeicaoManager().deleteRefeicao(id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateRefeicao(Refeicao refeicao) {
		PersisterFactory.getInstance().getRefeicaoManager().update(refeicao);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
