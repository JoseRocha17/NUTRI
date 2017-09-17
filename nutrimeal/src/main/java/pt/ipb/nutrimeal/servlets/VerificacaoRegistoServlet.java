package pt.ipb.nutrimeal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pt.ipb.nutrimeal.dao.PersisterFactory;

/**
 * Servlet implementation class RealmVerificationServlet
 */
@WebServlet("/verify")
public class VerificacaoRegistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public VerificacaoRegistoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String verificationKey = request.getParameter("h");
	      if(PersisterFactory.getInstance().getUserManager().activate(verificationKey)) {
			  response.sendRedirect(request.getContextPath() + "#/success");
	      } else {
			  response.sendRedirect(request.getContextPath() + "#/verifyError");
	      } 
	}

}
