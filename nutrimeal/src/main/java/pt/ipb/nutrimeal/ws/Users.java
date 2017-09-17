package pt.ipb.nutrimeal.ws;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.*;
import pt.ipb.nutrimeal.entity.Group.ROLE;
import pt.ipb.nutrimeal.json.JsonPass;
import pt.ipb.nutrimeal.json.JsonPerson;
import pt.ipb.nutrimeal.json.JsonProfile;
import pt.ipb.nutrimeal.servlets.RegistoServlet;
@Path("/users")
public class Users {
	private Session mailSession;
	Properties props = new Properties();
	
	public void init() throws ServletException {
		try {
			props.load(RegistoServlet.class.getClassLoader().getResourceAsStream("nutrimeal.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Context initial = new InitialContext();
			mailSession = (Session) initial.lookup("java:comp/env/mail/nutrimeal");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserList() {
		List<User> l = new ArrayList<>();

		for (User user : PersisterFactory.getInstance().getUserManager().getAll()) {
			l.add(new User(user.getEmail(), user.getName(), user.getLastName()));
		}

		return l;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{email}")
	public JsonPerson getPerson(@PathParam("email") String email) {
		User user = PersisterFactory.getInstance().getUserManager().getUser(email);

		

		List<ROLE> role = PersisterFactory.getInstance().getUserManager().getRole(email);
//		int size = role.size();
		
		JsonPerson p = new JsonPerson();
		p.setEmail(user.getEmail());
		p.setName(user.getName());
		p.setLastName(user.getLastName());
		p.setNacionalidade(user.getNacionalidade());
		p.setData_nasc(user.getData_nasc());
		p.setContacto(user.getContacto());
		p.setLocalidade(user.getLocalidade());
		p.setBi(user.getBi());
		p.setContribuinte(user.getContribuinte());
		p.setFoto(user.getFoto());
		p.setSexo(user.getSexo());
		
	
		String roleOne = role.get(0).toString();
		p.setRole(roleOne);
		return p;
	}


	@DELETE
	public Response deletePerson(@QueryParam("email") String email) {
		try {
			PersisterFactory.getInstance().getUserManager().delete(email);

			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("{email}")
	public Response apagarPerson(@PathParam("email") String email) {
		try {
			PersisterFactory.getInstance().getUserManager().delete(email);
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
	public Response updatePerson(JsonProfile person) {
		
		User pessoa = PersisterFactory.getInstance().getUserManager().getUser(person.getEmail());

		pessoa.setData_nasc(person.getData_nasc());
		pessoa.setContacto(person.getContacto());
		pessoa.setSexo(person.getSexo());
		pessoa.setLocalidade(person.getLocalidade());
		pessoa.setNacionalidade(person.getNacionalidade());
		pessoa.setBi(person.getBi());
		pessoa.setContribuinte(person.getContribuinte());
		PersisterFactory.getInstance().getUserManager().update(pessoa);
		
		
		try {
			return Response.ok().build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/setAdmin")
	public Response setAdmin(JsonPerson person) {
		PersisterFactory.getInstance().getUserManager().setToAdmin(person.getEmail());
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/password")
	public Response updatePassword(JsonPass person) {
		PersisterFactory.getInstance().getUserManager().changePassword(person.getEmail(), person.getOldPass(), person.getNewPass());
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/recover")
	public Response recoverPassword(JsonPass person) {
		String pass = RandomPass.generateRandomString();
		User user = PersisterFactory.getInstance().getUserManager().getUser(person.getEmail());
		System.out.println("User1 : " + user.getEmail() + user.getName() + user.getLastName() + user.getPasswd());
		PersisterFactory.getInstance().getUserManager().changePassword(user.getEmail(), null, pass);
		System.out.println("User2 : " + user.getEmail() + user.getName() + user.getLastName() + user.getPasswd());
		try {
			sendRecoveringMessage(user.getEmail(), user.getName(), user.getLastName(), pass);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		try {
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	
	
	
	
	public void sendRecoveringMessage(String email, String name, String lastName, String passwd) throws MessagingException, UnsupportedEncodingException {
		try {
			init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		props.getProperty("realm.url");

		MimeMessage msg = new MimeMessage(mailSession);
		msg.setSubject("Recuperacao de password na Plataforma Budogym");
		msg.setFrom(new InternetAddress("josemrocha17@gmail.com", "BudoGym"));
		msg.setRecipient(RecipientType.TO, new InternetAddress(email, name));
		StringBuilder str = new StringBuilder("<html><body><p>Ola " + name + " " + lastName + "," + "</p>");
		str.append("<p>A sua password foi alterada com sucesso.<br/>");
		str.append("<p>Esta sera a sua password temporaria:<br/>");
		str.append("Password: " + passwd);
		str.append("<p>Budogym</p></body></html>");
		msg.setContent(str.toString(), "text/html; charset=utf-8");
		Transport.send(msg);
	}
}