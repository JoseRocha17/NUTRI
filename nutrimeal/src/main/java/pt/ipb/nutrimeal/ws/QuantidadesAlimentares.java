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
import pt.ipb.nutrimeal.entity.QuantidadeAlimentar;
import pt.ipb.nutrimeal.entity.Refeicao;
import pt.ipb.nutrimeal.json.JsonQuantidadeAlimentar;
import pt.ipb.nutrimeal.json.JsonRefeicao;

@Path("/quantidadealimentares")
public class QuantidadesAlimentares {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuantidadeAlimentar> getQuantidadeAlimentarList() {
		List<QuantidadeAlimentar> lista_quantidadeAlimentar = new ArrayList<>();

		for (QuantidadeAlimentar quantidadeAlimentar : PersisterFactory.getInstance().getQuantidadeAlimentarManager().getQuantidadeAlimentares()) {
			lista_quantidadeAlimentar.add(new QuantidadeAlimentar(quantidadeAlimentar.getId(), quantidadeAlimentar.getQuantidade(),quantidadeAlimentar.getAtributo(), quantidadeAlimentar.refeicao.getNome(), quantidadeAlimentar.alimento.getId(),quantidadeAlimentar.alimento.getNome(), quantidadeAlimentar.refeicao.perfilalimentar.getData(), 
					quantidadeAlimentar.refeicao.perfilalimentar.getDia(), quantidadeAlimentar.alimento.getCalorias()));
		}
		return lista_quantidadeAlimentar;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public QuantidadeAlimentar getQuantidadeAlimentar(@PathParam("id") long id) {

		QuantidadeAlimentar quantidadeAlimentar = PersisterFactory.getInstance().getQuantidadeAlimentarManager().getQuantidadeAlimentar(id);
		
		QuantidadeAlimentar qa = new QuantidadeAlimentar();
		qa.setId(quantidadeAlimentar.getId());
		qa.setQuantidade(quantidadeAlimentar.getQuantidade());
		qa.setAtributo(quantidadeAlimentar.getAtributo());
		
		qa.refeicao.setId(quantidadeAlimentar.refeicao.getId());
		qa.refeicao.setNome(quantidadeAlimentar.refeicao.getNome());
		
		qa.alimento.setId(quantidadeAlimentar.alimento.getId());
		qa.alimento.setNome(quantidadeAlimentar.alimento.getNome());
		qa.alimento.setFoto(quantidadeAlimentar.alimento.getFoto());
		
		qa.refeicao.perfilalimentar.setNome(quantidadeAlimentar.refeicao.perfilalimentar.getNome());
		qa.refeicao.perfilalimentar.setData(quantidadeAlimentar.refeicao.perfilalimentar.getData());
		qa.refeicao.perfilalimentar.setDia(quantidadeAlimentar.refeicao.perfilalimentar.getDia());
		qa.refeicao.perfilalimentar.user.setEmail(quantidadeAlimentar.refeicao.perfilalimentar.user.getEmail());
		
		
		
		return qa;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuantidadeAlimentar(JsonQuantidadeAlimentar quantidadeAlimentar) {
		QuantidadeAlimentar qa = PersisterFactory.getInstance().getQuantidadeAlimentarManager().createQuantidadeAlimentar(quantidadeAlimentar.getQuantidade(), quantidadeAlimentar.getAtributo(),
				quantidadeAlimentar.getRefeicao(), quantidadeAlimentar.getAlimento());
		System.out.println("Atributo --" + qa.getAtributo());
		try {
			return Response.created(new URI("/quantidadealimentares?id=" + qa.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteQuantidadeAlimentar(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getQuantidadeAlimentarManager().deleteQuantidadeAlimentar(id);
			return Response.ok().build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response apagarQuantidadeAlimentar(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getQuantidadeAlimentarManager().deleteQuantidadeAlimentar(id);
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
	public Response updateQuantidadeAlimentar(QuantidadeAlimentar quantidadeAlimentar) {
		PersisterFactory.getInstance().getQuantidadeAlimentarManager().update(quantidadeAlimentar);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
