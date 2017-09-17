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
import pt.ipb.nutrimeal.json.JsonObjetivos;

@Path("/objetivos")
public class ObjetivosWS {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Objetivos> getObjetivosList(@Context SecurityContext sc) {
		List<Objetivos> lista_objetivos = new ArrayList<>();
		String user;

		try {
			user = sc.getUserPrincipal().getName();
			List<ROLE> _role = PersisterFactory.getInstance().getUserManager().getRole(user);
			String role = _role.get(0).toString();
			System.out.println("Regra ==" + role);

			if (role == "USER") {

				for (Objetivos objetivos : PersisterFactory.getInstance().getObjetivosManager()
						.getObjetivosUser(user)) {
					lista_objetivos.add(new Objetivos(objetivos.getId(), objetivos.getPeso(), objetivos.getPescoco(),
							objetivos.getCintura(), objetivos.getQuadris(), objetivos.getData(),
							objetivos.user.getName()));
				}
			} else {
				for (Objetivos objetivos : PersisterFactory.getInstance().getObjetivosManager().getObjetivos()) {
					lista_objetivos.add(new Objetivos(objetivos.getId(), objetivos.getPeso(), objetivos.getPescoco(),
							objetivos.getCintura(), objetivos.getQuadris(), objetivos.getData(),
							objetivos.user.getName()));
				}
			}

		} catch (Exception e) {

			for (Objetivos objetivos : PersisterFactory.getInstance().getObjetivosManager().getObjetivos()) {
				lista_objetivos.add(new Objetivos(objetivos.getId(), objetivos.getPeso(), objetivos.getPescoco(),
						objetivos.getCintura(), objetivos.getQuadris(), objetivos.getData(), objetivos.user.getName()));
			}

		}

		return lista_objetivos;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Objetivos Objetivos(@PathParam("id") long id) {

		Objetivos objetivos = PersisterFactory.getInstance().getObjetivosManager().getObjetivo(id);

		Objetivos o = new Objetivos();
		o.setId(objetivos.getId());
		o.setPeso(objetivos.getPeso());
		o.setPescoco(objetivos.getPescoco());
		o.setCintura(objetivos.getCintura());
		o.setQuadris(objetivos.getQuadris());
		o.setData(objetivos.getData());
		o.user.setEmail(objetivos.user.getEmail());
		o.user.setName(objetivos.user.getName());

		return o;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addObjetivo(JsonObjetivos objetivos) {

		Objetivos o = PersisterFactory.getInstance().getObjetivosManager().createObjetivo(objetivos.getPeso(),
				objetivos.getPescoco(), objetivos.getCintura(), objetivos.getQuadris(), objetivos.getData(),
				objetivos.getUser());
		try {
			return Response.created(new URI("/objetivos?id=" + o.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteObjetivo(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getObjetivosManager().deleteObjetivo(id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response apagarObjetivo(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getObjetivosManager().deleteObjetivo(id);
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
	public Response updateObjetivo(Objetivos objetivos) {
		PersisterFactory.getInstance().getObjetivosManager().update(objetivos);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	// @POST
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	// @Path("/update")
	// public Response updateObjetivo(JsonObjetivos objetivos) {
	//
	// Objetivos o =
	// PersisterFactory.getInstance().getObjetivosManager().getObjetivo(objetivos.getId());
	//
	// Objetivos obj = new Objetivos();
	// o.setId(objetivos.getId());
	// o.setPeso(objetivos.getPeso());
	// o.setPescoco(objetivos.getPescoco());
	// o.setCintura(objetivos.getCintura());
	// o.setQuadris(objetivos.getQuadris());
	// o.setData(objetivos.getData());
	// o.pessoa.setEmail(obj.pessoa.getEmail());
	// o.pessoa.setNome(obj.pessoa.getNome());
	//
	// PersisterFactory.getInstance().getObjetivosManager().update(o);
	//
	//
	// try {
	// return Response.ok().build();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// return Response.serverError().build();
	// }
	// }

	// @POST
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	// @Path("/update")
	// public Response updateObjetivo(JsonObjetivos objetivo) {
	//// Objetivos user =
	// PersisterFactory.getInstance().getObjetivosManager().getObjetivo(objetivo.getId());
	//
	// PersisterFactory.getInstance().getObjetivosManager().update(objetivo.getPeso(),
	// objetivo.getPescoco(), objetivo.getCintura(),
	// objetivo.getQuadris(), objetivo.getData(), objetivo.getPessoa());
	// try {
	// return Response.ok().build();
	// } catch (Exception e) {
	// e.printStackTrace();
	// return Response.serverError().build();
	// }
	// }

}
