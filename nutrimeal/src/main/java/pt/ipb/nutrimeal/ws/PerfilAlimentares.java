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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.*;
import pt.ipb.nutrimeal.entity.Group.ROLE;
import pt.ipb.nutrimeal.json.JsonPerfilAlimentar;

@Path("/perfilalimentares")
public class PerfilAlimentares {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PerfilAlimentar> getPerfilAlimentarList(@Context SecurityContext sc) {
		List<PerfilAlimentar> lista_perfilalimentares = new ArrayList<>();
		String user;
		try {
			user = sc.getUserPrincipal().getName();
			List<ROLE> _role = PersisterFactory.getInstance().getUserManager().getRole(user);
			String role = _role.get(0).toString();
			System.out.println("Regra == " + role);
			if (role == "USER") {
				for (PerfilAlimentar perfilalimentar : PersisterFactory.getInstance().getPerfilAlimentarManager()
						.getPerfilAlimentarNome(user)) {
					lista_perfilalimentares.add(new PerfilAlimentar(perfilalimentar.getId(), perfilalimentar.getNome(),
							perfilalimentar.getDia(), perfilalimentar.getData(), perfilalimentar.user.getEmail(),
							perfilalimentar.user.getName(), perfilalimentar.user.getLastName()));
				}
			} else {
				for (PerfilAlimentar perfilalimentar : PersisterFactory.getInstance().getPerfilAlimentarManager()
						.getPerfilAlimentares()) {
					lista_perfilalimentares.add(new PerfilAlimentar(perfilalimentar.getId(), perfilalimentar.getNome(),
							perfilalimentar.getDia(), perfilalimentar.getData(), perfilalimentar.user.getEmail(),
							perfilalimentar.user.getName(), perfilalimentar.user.getLastName()));
				}
			}
		} catch (Exception e) {
			for (PerfilAlimentar perfilalimentar : PersisterFactory.getInstance().getPerfilAlimentarManager()
					.getPerfilAlimentares()) {
				lista_perfilalimentares.add(new PerfilAlimentar(perfilalimentar.getId(), perfilalimentar.getNome(),
						perfilalimentar.getDia(), perfilalimentar.getData(), perfilalimentar.user.getEmail(),
						perfilalimentar.user.getName(), perfilalimentar.user.getLastName()));
			}
		}
		return lista_perfilalimentares;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public PerfilAlimentar PerfilAlimentar(@PathParam("id") long id) {

		PerfilAlimentar perfilalimentar = PersisterFactory.getInstance().getPerfilAlimentarManager()
				.getPerfilAlimentar(id);

		PerfilAlimentar pa = new PerfilAlimentar();
		pa.setId(perfilalimentar.getId());
		pa.setNome(perfilalimentar.getNome());
		pa.setData(perfilalimentar.getData());
		pa.setDia(perfilalimentar.getDia());
		pa.user.setEmail(perfilalimentar.user.getEmail());
		pa.user.setName(perfilalimentar.user.getName());
		pa.user.setLastName(perfilalimentar.getUser().getLastName());

		return pa;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerfilAlimentar(JsonPerfilAlimentar perfilalimentar) {
		System.out.println("-------------------------------" + perfilalimentar.getUser());
		System.out.println("-------------------------------" + perfilalimentar.getEmail());
		System.out.println("-------------------------------" + perfilalimentar.getDia());
		PerfilAlimentar pa = PersisterFactory.getInstance().getPerfilAlimentarManager().createPerfilAlimentar(
				perfilalimentar.getNome(), perfilalimentar.getDia(), perfilalimentar.getData(),
				perfilalimentar.getUser());
		try {

			return Response.created(new URI("/perfilalimentares?id=" + pa.getId())).build();

		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deletePerfilAlimentar(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getPerfilAlimentarManager().deletePerfilAlimentar(id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response apagarPerfilAlimentar(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getPerfilAlimentarManager().deletePerfilAlimentar(id);
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
	public Response updatePerfilAlimentar(PerfilAlimentar perfilalimentar) {
		PersisterFactory.getInstance().getPerfilAlimentarManager().update(perfilalimentar);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
