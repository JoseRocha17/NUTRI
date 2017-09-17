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

@Path("/perfis/refeicoes")
public class RefeicoesPerfil {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public List<Refeicao> getRefeicoesUserList(@PathParam("id") long id) {
		List<Refeicao> lista_refeicoes = new ArrayList<>();
		System.out.println("id" + id);

		for (Refeicao refeicao : PersisterFactory.getInstance().getRefeicaoManager().getPerfilRefeicao(id)) {
			lista_refeicoes.add(new Refeicao(refeicao.getId(), refeicao.getNome(), refeicao.perfilalimentar.getNome()));
		}
		return lista_refeicoes;
	}
}