package pt.ipb.nutrimeal.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.*;


@WebServlet(urlPatterns = { "/registar" })
public class RegistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Session mailSession;
	Properties props = new Properties();

	public RegistoServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("password_confirm");
		

		if (!password.equals(passwordConfirm)) {
			response.sendRedirect(request.getContextPath() + "#/passwdError");
		} else {
			User p = PersisterFactory.getInstance().getUserManager().getUser(email);
			if(p == null){
				p = PersisterFactory.getInstance().getUserManager().create(email, name, lastName, password);
				try {
					sendMessage(email, name, p.getVerificationKey());
					response.sendRedirect("#/registered");
				} catch (MessagingException e) {
					response.sendRedirect(request.getContextPath() + "#/authError");
				}
			}else{
				response.sendRedirect(request.getContextPath() + "#/registerError");
			}
					
		}
	}

	public void sendMessage(String email, String name, String verificationKey) throws MessagingException, UnsupportedEncodingException {
		String host = props.getProperty("realm.url");

		MimeMessage msg = new MimeMessage(mailSession);
		msg.setSubject("Confirmacao do registo no BudoGym");
		msg.setFrom(new InternetAddress("josemrocha17@gmail.com", "BudoGym"));
		msg.setRecipient(RecipientType.TO, new InternetAddress(email, name));
		StringBuilder str = new StringBuilder("<html><body><p> Olá " + name + " " + "," + "</p>");
		str.append("<p>Agradecemos o seu registo na plataforma.<br/>");
		str.append("<p>Para podermos confirmar a sua conta, clique no link que segue neste email. <br/>");
		str.append("<p>Obrigado pela compreensão.<br/>");
		String link = host + "verify?h=" + verificationKey;
		str.append("<a href='" + link + "'>" + link + "</a></p>");
		str.append("<p>Gerencia do BudoGym</p></body></html>");
		msg.setContent(str.toString(), "text/html; charset=utf-8");
		Transport.send(msg);
	}

}