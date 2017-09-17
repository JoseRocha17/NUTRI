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
import pt.ipb.nutrimeal.json.JsonMedidas;

@Path("/medidas")
public class MedidasWS {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Medidas> getMedidasList(@Context SecurityContext sc) {
		List<Medidas> lista_medidas = new ArrayList<>();
		String user;
		try {
			user = sc.getUserPrincipal().getName();
			List<ROLE> _role = PersisterFactory.getInstance().getUserManager().getRole(user);
			String role = _role.get(0).toString();
			System.out.println("Regra :" + role);

			if (role == "USER") {
				for (Medidas medidas : PersisterFactory.getInstance().getMedidasManager().getMedidasUser(user)) {
					lista_medidas.add(new Medidas(medidas.getId(), medidas.getPeso(), medidas.getAltura(),
							medidas.getPescoco(), medidas.getCintura(), medidas.getQuadris(), medidas.getData(),
							medidas.user.getName(), medidas.user.getEmail(), medidas.user.getLastName()));
				}
			} else {
				for (Medidas medidas : PersisterFactory.getInstance().getMedidasManager().getMedidas()) {
					lista_medidas.add(new Medidas(medidas.getId(), medidas.getPeso(), medidas.getAltura(),
							medidas.getPescoco(), medidas.getCintura(), medidas.getQuadris(), medidas.getData(),
							medidas.user.getName(), medidas.user.getEmail(), medidas.user.getLastName()));
				}
			}

		} catch (Exception e) {
			for (Medidas medidas : PersisterFactory.getInstance().getMedidasManager().getMedidas()) {
				lista_medidas
						.add(new Medidas(medidas.getId(), medidas.getPeso(), medidas.getAltura(), medidas.getPescoco(),
								medidas.getCintura(), medidas.getQuadris(), medidas.getData(), medidas.user.getName(), medidas.user.getEmail(), medidas.user.getLastName()));
			}
		}

		return lista_medidas;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Medidas Medidas(@PathParam("id") long id) {

		Medidas medidas = PersisterFactory.getInstance().getMedidasManager().getMedida(id);

		Medidas m = new Medidas();
		m.setId(medidas.getId());
		m.setPeso(medidas.getPeso());
		m.setAltura(medidas.getAltura());
		m.setPescoco(medidas.getPescoco());
		m.setCintura(medidas.getCintura());
		m.setQuadris(medidas.getQuadris());
		m.setData(medidas.getData());
		m.user.setEmail(medidas.user.getEmail());
		m.user.setName(medidas.user.getName());

		return m;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMedida(JsonMedidas medidas) {
		Medidas m = PersisterFactory.getInstance().getMedidasManager().createMedida(medidas.getPeso(),
				medidas.getAltura(), medidas.getPescoco(), medidas.getCintura(), medidas.getQuadris(),
				medidas.getData(), medidas.getUser());
		try {
			return Response.created(new URI("/medidas?id=" + m.getId())).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	public Response deleteMedida(@QueryParam("id") long id) {
		try {
			PersisterFactory.getInstance().getMedidasManager().deleteMedida(id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response apagarMedida(@PathParam("id") long id) {
		try {
			PersisterFactory.getInstance().getMedidasManager().deleteMedida(id);
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
	public Response updateMedida(Medidas medidas) {
		PersisterFactory.getInstance().getMedidasManager().update(medidas);
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
