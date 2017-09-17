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
import pt.ipb.nutrimeal.entity.User;
import pt.ipb.nutrimeal.json.JsonProfile;

@Path("/pessoas")
public class Pessoas {


	@DELETE
	public Response deletePessoa(@QueryParam("email") String email) {
		try {
			PersisterFactory.getInstance().getUserManager().delete(email);
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
	public Response updatePerson(JsonProfile profile) {
		
		User pessoa = PersisterFactory.getInstance().getUserManager().getUser(profile.getEmail());

		pessoa.setData_nasc(profile.getData_nasc());
		pessoa.setContacto(profile.getContacto());
		pessoa.setSexo(profile.getSexo());
		pessoa.setLocalidade(profile.getLocalidade());
		pessoa.setNacionalidade(profile.getNacionalidade());
		pessoa.setBi(profile.getBi());
		pessoa.setContribuinte(profile.getContribuinte());
		PersisterFactory.getInstance().getUserManager().update(pessoa);
		
		
		try {
			return Response.ok().build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}
	
}
