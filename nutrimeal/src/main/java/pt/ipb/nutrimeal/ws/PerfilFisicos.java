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
import pt.ipb.nutrimeal.json.JsonPerfilFisico;

@Path("/perfilfisicos")
public class PerfilFisicos {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PerfilFisico> getPerfilFisicoList(@Context SecurityContext sc) {
		List<PerfilFisico> lista_perfilfisicos = new ArrayList<>();
		String user;
		try {
			user = sc.getUserPrincipal().getName();
			List<ROLE> _role = PersisterFactory.getInstance().getUserManager().getRole(user);
			String role = _role.get(0).toString();
			System.out.println("Regra == " + role);
			System.out.println("User == " + user);
			if (role == "USER") {
				for (PerfilFisico perfilfisico : PersisterFactory.getInstance().getPerfilFisicoManager()
						.getPerfilFisicoUser(user)) {
					lista_perfilfisicos.add(new PerfilFisico(perfilfisico.getId(), perfilfisico.getNome(),
							perfilfisico.getDia(), perfilfisico.getData(), perfilfisico.user.getName(),
							perfilfisico.user.getEmail(), perfilfisico.user.getLastName()));
				}
			} else {
				for (PerfilFisico perfilfisico : PersisterFactory.getInstance().getPerfilFisicoManager()
						.getPerfilFisicos()) {
					lista_perfilfisicos.add(new PerfilFisico(perfilfisico.getId(), perfilfisico.getNome(),
							perfilfisico.getDia(), perfilfisico.getData(), perfilfisico.user.getName(),
							perfilfisico.user.getEmail(), perfilfisico.user.getLastName()));
				}
			}

		} catch (Exception e) {
			for (PerfilFisico perfilfisico : PersisterFactory.getInstance().getPerfilFisicoManager()
					.getPerfilFisicos()) {
				lista_perfilfisicos.add(new PerfilFisico(perfilfisico.getId(), perfilfisico.getNome(),
						perfilfisico.getDia(), perfilfisico.getData(), perfilfisico.user.getName(),
						perfilfisico.user.getEmail(), perfilfisico.user.getLastName()));
			}
		}
		return lista_perfilfisicos;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public PerfilFisico PerfilFisico(@PathParam("id") long id) {

		PerfilFisico perfilFisico = PersisterFactory.getInstance().getPerfilFisicoManager().getPerfilFisico(id);

		PerfilFisico pf = new PerfilFisico();
		pf.setId(perfilFisico.getId());
		pf.setNome(perfilFisico.getNome());
		pf.setDia(perfilFisico.getDia());
		pf.setData(perfilFisico.getData());
		pf.user.setEmail(perfilFisico.user.getEmail());
		pf.user.setName(perfilFisico.user.getName());
		pf.user.setLastName(perfilFisico.user.getLastName());

		return pf;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerfilFisico(JsonPerfilFisico perfilFisico) {
		PerfilFisico pf = PersisterFactory.getInstance().getPerfilFisicoManager().createPerfilFisico(
				perfilFisico.getNome(), perfilFisico.getDia(), perfilFisico.getData(), perfilFisico.getUser());
		try {
			return Response.created(new URI("/perfilfisicos?id=" + pf.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deletePerfilFisico(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getPerfilFisicoManager().deletePerfilFisico(id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response apagarPerfilFisico(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getPerfilFisicoManager().deletePerfilFisico(id);
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
	public Response updatePerfilFisico(PerfilFisico perfilFisico) {
		PersisterFactory.getInstance().getPerfilFisicoManager().update(perfilFisico);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
