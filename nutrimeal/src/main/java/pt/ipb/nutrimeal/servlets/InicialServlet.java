package pt.ipb.nutrimeal.servlets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import pt.ipb.nutrimeal.dao.AlimentoManager;
import pt.ipb.nutrimeal.dao.ObjetivosManager;
import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.dao.RefeicaoManager;
import pt.ipb.nutrimeal.dao.UserManager;
import pt.ipb.nutrimeal.entity.Refeicao;
import pt.ipb.nutrimeal.entity.User;

@WebServlet(name = "init", urlPatterns = { "/init" })
public class InicialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager;
	AlimentoManager alimentoManager;
	ObjetivosManager objetivosManager;
	RefeicaoManager refeicaoManager;

	public InicialServlet() {
		userManager = PersisterFactory.getInstance().getUserManager();
		alimentoManager = PersisterFactory.getInstance().getAlimentoManager();
	}

	@Override
	public void init() throws ServletException {
		super.init();

		// Criação de um utilizador e de um admin
		User p = userManager.getUser("rlopes@ipb.pt");
		if (p == null) {
			p = userManager.createAdmin("rlopes@ipb.pt", "Rui", "Lopes", "1234");
			userManager.activate(p.getVerificationKey());
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dn = "02/06/1994";
		Date dataNasc = null;
		try {
			dataNasc = formatter.parse(dn);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		User user = userManager.getUser("a29345@ipb.pt");
		if (user == null) {
			user = userManager.create("a29345@ipb.pt", "José", "Rocha", "1234");
			userManager.activate(user.getVerificationKey());
		}
	}

	@Override
	public void destroy() {
		System.out.println("releasing resources");
		PersisterFactory.getInstance().close();
	}
}
