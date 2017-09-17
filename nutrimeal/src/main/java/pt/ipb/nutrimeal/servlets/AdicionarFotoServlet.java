package pt.ipb.nutrimeal.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.*;

@SuppressWarnings("serial")
public class AdicionarFotoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            

            if (! ServletFileUpload.isMultipartContent(request)) {
                System.out.println("Erro!");
                return;
            }
            
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload sfu  = new ServletFileUpload(factory);


            List<FileItem> items = sfu.parseRequest(request);
            
            
            String picInserir = items.get(0).getString();
       
            switch(picInserir){
            	case "perfil" :  		
            		String userEmail = items.get(1).getString();
                    FileItem  idUser = (FileItem) items.get(2);
                    InputStream input = idUser.getInputStream();
                    byte[] bytes = IOUtils.toByteArray(input);
                    
                    User p = PersisterFactory.getInstance().getUserManager().getUser(userEmail);
                    p.setFoto(bytes);
                    PersisterFactory.getInstance().getUserManager().update(p);
                    response.sendRedirect(request.getContextPath() + "#/profile");
                    if(p.getFoto()!=null) {
                    	System.out.println("Works");
                    }else {
                    	System.out.println("not work");
                    }
                    break;
            	case "alimento" :
            		String idAlimento = items.get(1).getString();
            		long unique = Long.valueOf(idAlimento).longValue();
                    FileItem  image = (FileItem) items.get(2);
                    InputStream inputAlimento = image.getInputStream();
                    byte[] img = IOUtils.toByteArray(inputAlimento);
                    
                    Alimento e = PersisterFactory.getInstance().getAlimentoManager().getAlimento(unique);
                    e.setFoto(img);
                    PersisterFactory.getInstance().getAlimentoManager().update(e);
                    response.sendRedirect(request.getContextPath() + "#/alimento/" + unique);
            		break;
            	case "exercicio" :
            		String idExercicio = items.get(1).getString();
            		long unique1 = Long.valueOf(idExercicio).longValue();
                    FileItem  imageExercicio = (FileItem) items.get(2);
                    InputStream inputExercicio = imageExercicio.getInputStream();
                    byte[] imgExercicio = IOUtils.toByteArray(inputExercicio);
                    
                    Exercicio ex = PersisterFactory.getInstance().getExercicioManager().getExercicio(unique1);
                    ex.setFoto(imgExercicio);
                    PersisterFactory.getInstance().getExercicioManager().update(ex);
                    response.sendRedirect(request.getContextPath() + "#/exerciciosAdmin/" + unique1);
            		break;
            		
            	case "novidade" :
            		String idNovidade = items.get(1).getString();
            		System.out.println("Estou aqui" + idNovidade);
            		long unique2 = Long.valueOf(idNovidade).longValue();
            		System.out.println("Estou aqui" + unique2);
                    FileItem  imageNovidade = (FileItem) items.get(2);
                    System.out.println("Estou aqui" + imageNovidade);
                    InputStream inputNovidade = imageNovidade.getInputStream();
                    byte[] imgNovidade = IOUtils.toByteArray(inputNovidade);
                    
                    Novidade n = PersisterFactory.getInstance().getNovidadeManager().getNovidade(unique2);
                    n.setFoto(imgNovidade);
                    if(n.getFoto()!=null) {
                    	System.out.println("Works");
                    }else {
                    	System.out.println("not work");
                    }
                    PersisterFactory.getInstance().getNovidadeManager().update(n);
                    response.sendRedirect(request.getContextPath() + "#/novidadesAdmin/" + unique2);
            		break;
            	case "promocao" :
            		String idPromocao = items.get(1).getString();
            		System.out.println("Estou aqui" + idPromocao);
            		long uniqueProm = Long.valueOf(idPromocao).longValue();
            		System.out.println("Estou aqui" + uniqueProm);
                    FileItem  imagePromocao = (FileItem) items.get(2);
                    System.out.println("Estou aqui" + imagePromocao);
                    InputStream inputPromocao = imagePromocao.getInputStream();
                    byte[] imgPromocao = IOUtils.toByteArray(inputPromocao);
                    
                    Promocao pro = PersisterFactory.getInstance().getPromocaoManager().getPromocao(uniqueProm);
                    pro.setFoto(imgPromocao);
                    if(pro.getFoto()!=null) {
                    	System.out.println("Works");
                    }else {
                    	System.out.println("not work");
                    }
                    PersisterFactory.getInstance().getPromocaoManager().update(pro);
                    response.sendRedirect(request.getContextPath() + "#/promocoesAdmin/" + uniqueProm);
            		break;
            		
            	case "remover" :  		
            		String emailR = items.get(1).getString();
            		User pr = PersisterFactory.getInstance().getUserManager().getUser(emailR);
                    pr.setFoto(null);
                    PersisterFactory.getInstance().getUserManager().update(pr);
                    response.sendRedirect(request.getContextPath() + "#/profile");
                    break;
            	case "removerAlimentoFoto" :  		
            		String idAli = items.get(1).getString();
            		long uniquee = Long.valueOf(idAli).longValue();
                    Alimento al = PersisterFactory.getInstance().getAlimentoManager().getAlimento(uniquee);
                    al.setFoto(null);
                    PersisterFactory.getInstance().getAlimentoManager().update(al);
                    response.sendRedirect(request.getContextPath() + "#/alimento/" + uniquee);
                    break;
            	case "removerExercicioFoto" :  		
            		String idExe = items.get(1).getString();
            		long uniqueEx = Long.valueOf(idExe).longValue();
            		Exercicio exe = PersisterFactory.getInstance().getExercicioManager().getExercicio(uniqueEx);
                    exe.setFoto(null);
                    PersisterFactory.getInstance().getExercicioManager().update(exe);
                    response.sendRedirect(request.getContextPath() + "#/exerciciosAdmin/" + uniqueEx);
                    break;
            	case "removerNovidadeFoto" :  		
            		String idNov = items.get(1).getString();
            		long uniqueNov = Long.valueOf(idNov).longValue();
            		Novidade nov = PersisterFactory.getInstance().getNovidadeManager().getNovidade(uniqueNov);
                    nov.setFoto(null);
                    PersisterFactory.getInstance().getNovidadeManager().update(nov);
                    response.sendRedirect(request.getContextPath() + "#/novidadesAdmin/" + uniqueNov);
                    break;   
            	case "removerPromocaoFoto" :  		
            		String idPro = items.get(1).getString();
            		long uniqueProRe = Long.valueOf(idPro).longValue();
            		Promocao prom = PersisterFactory.getInstance().getPromocaoManager().getPromocao(uniqueProRe);
            		prom.setFoto(null);
                    PersisterFactory.getInstance().getPromocaoManager().update(prom);
                    response.sendRedirect(request.getContextPath() + "#/promocoesAdmin/" + uniqueProRe);
                    break;   
            }
  
        }
        catch(Exception ex) {

        }
    }
}