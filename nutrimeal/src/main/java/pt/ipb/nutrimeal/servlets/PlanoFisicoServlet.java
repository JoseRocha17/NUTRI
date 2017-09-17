package pt.ipb.nutrimeal.servlets;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.ExercicioAtributo;
import pt.ipb.nutrimeal.entity.QuantidadeAlimentar;

@SuppressWarnings("serial")
public class PlanoFisicoServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment;filename=" + "planoAlimentar.pdf");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		ServletFileUpload sfu = new ServletFileUpload(factory);

		// parse request

		List<FileItem> items;
		String str1 = "";

		try {
			items = sfu.parseRequest(request);
			str1 = items.get(1).getString();

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		long id = Long.valueOf(str1).longValue();
		System.out.println("ID --- " + id);
		Font[] font = { new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD), 
				new Font(Font.FontFamily.TIMES_ROMAN, 12), new Font(Font.FontFamily.TIMES_ROMAN, 10), new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD) };

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream());
		} catch (DocumentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		document.open();

		Paragraph titulo = new Paragraph("\n\nPlano Físico", font[0]);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		try {
			document.add(titulo);

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		try {
			List<ExercicioAtributo> qa1 = null;

			qa1 = PersisterFactory.getInstance().getExercicioAtributoManager().getExercicioAtributoUser(id);
			Paragraph planoFisico = null;
			Paragraph user = null;
			Paragraph exercicio = null;
			Paragraph atributos = null;
			Paragraph dataPerfil = null;
			try {
				for (ExercicioAtributo q : qa1) {
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

					System.out.println("Quantidade alimentar -- " + q.getId());
					planoFisico = new Paragraph("\n\n\n\n Tipo de Plano - " + q.metaexercicio.perfil.getNome(), font[1]);
					user = new Paragraph("\n Utilizador - " + q.metaexercicio.perfil.user.getEmail() + " - "
							+ q.metaexercicio.perfil.user.getName() + " " + q.metaexercicio.perfil.user.getLastName(),
							font[1]);
					dataPerfil = new Paragraph(
							"\n Data do Plano - " + formatter.format(q.metaexercicio.perfil.getData()) + "  -  "
									+ q.metaexercicio.perfil.getDia(),
							font[1]);
					exercicio = new Paragraph("\n Exercicio -   " + q.metaexercicio.exercicio.getNome(), font[1]);
					atributos = new Paragraph("\n Atributos do Exercicio: ", font[3]);

				}
				document.add(planoFisico);
				document.add(user);
				document.add(dataPerfil);
				document.add(exercicio);
				document.add(atributos);

			} catch (Exception e1) {
				System.out.println("Lista Vazia");
				Paragraph erro = new Paragraph(
						"Não tem valores atribuidos ao exercicio, por favor crie o seu plano completo.", font[1]);
				document.add(erro);
			}

			try {
				List<ExercicioAtributo> qa = null;
				qa = PersisterFactory.getInstance().getExercicioAtributoManager().getExercicioAtributoUser(id);

				Paragraph exercicios_atributo = null;
				try {
					for (ExercicioAtributo q : qa) {

						exercicios_atributo = new Paragraph("         ->" + q.getValor()  + " " + q.atributo.getNome(), font[1]);

						document.add(exercicios_atributo);
					}

				} catch (Exception e1) {
					System.out.println("Lista Vazia");
					Paragraph erro = new Paragraph(
							"Não tem valores atribuidos ao exercicio, por favor crie o seu plano completo.", font[1]);
					document.add(erro);

				}

				Paragraph duvida = new Paragraph(
						"\n\n Se tiver alguma dúvida, nao exite em entrar em contacto connosco, via e-mail, telefone, ou através da nossa plataforma.",
						font[2]);
				duvida.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(duvida);

				Paragraph budoGym = new Paragraph("\nBudoGym", font[2]);
				budoGym.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(budoGym);

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.now();
				Paragraph data = new Paragraph(" " + dtf.format(localDate), font[2]);
				data.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(data);

				Paragraph mail = new Paragraph("budogym@gmail.com", font[2]);
				mail.setAlignment(Paragraph.ALIGN_RIGHT);
				document.add(mail);

				Paragraph local = new Paragraph("R. Miguel Torga 22, 5300-111 Bragança", font[2]);
				local.setAlignment(Paragraph.ALIGN_RIGHT);
				document.add(local);

				Paragraph contato = new Paragraph("+351 918 080 724", font[2]);
				contato.setAlignment(Paragraph.ALIGN_RIGHT);
				document.add(contato);

				// End

				document.close();

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
