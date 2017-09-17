package pt.ipb.nutrimeal.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.User;
import pt.ipb.nutrimeal.json.JsonProfile;

@Path("/auth")
public class AuthUser {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonProfile getUserAuthenticated(@Context SecurityContext sc) {
		
		
		String name;
		boolean isAdmin, isUser;
		JsonProfile p = new JsonProfile();
		try {
			name = sc.getUserPrincipal().getName();
			isAdmin = sc.isUserInRole("ADMIN");
			isUser = sc.isUserInRole("USER");
			
			User pessoa = PersisterFactory.getInstance().getUserManager().getUser(name);
			
			p.setEmail(name);
			p.setNome(pessoa.getName());
			p.setData_nasc(pessoa.getData_nasc());
			p.setContacto(pessoa.getContacto());
			p.setSexo(pessoa.getSexo());
			p.setLocalidade(pessoa.getLocalidade());
			p.setNacionalidade(pessoa.getNacionalidade());
			p.setBi(pessoa.getBi());
			p.setContribuinte(pessoa.getContribuinte());
			p.setPasswd(pessoa.getPasswd());
			p.setFoto(pessoa.getFoto());

	
			
			if(isAdmin == true)
				p.setIsAdmin("ADMIN");
			
			if(isUser == true)
				p.setIsUser("USER");
			
		

		} catch (Exception e) {
		}
		return p;
	}
	
	
	
	
	
}