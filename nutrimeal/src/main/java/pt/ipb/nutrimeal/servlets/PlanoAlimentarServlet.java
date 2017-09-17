package pt.ipb.nutrimeal.servlets;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.QuantidadeAlimentar;

@SuppressWarnings("serial")
public class PlanoAlimentarServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment;filename=" + "planoAlimentar.pdf");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		ServletFileUpload sfu = new ServletFileUpload(factory);

		List<FileItem> items;
		String str1 = "";

		try {
			items = sfu.parseRequest(request);
			str1 = items.get(1).getString();
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		long id = Long.valueOf(str1).longValue();
		Font[] font = { new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD), 
				new Font(Font.FontFamily.TIMES_ROMAN, 12), new Font(Font.FontFamily.TIMES_ROMAN, 10), new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD) };

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream());
		} catch (DocumentException e2) {
			e2.printStackTrace();
		}
		document.open();

		Paragraph titulo = new Paragraph("\n\nPlano Alimentar", font[0]);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		try {
			document.add(titulo);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		try {
			List<QuantidadeAlimentar> qa1 = null;
			qa1 = PersisterFactory.getInstance().getQuantidadeAlimentarManager().getQuantidadeRefeicao(id);
			Paragraph planoAlimentar = null;
			Paragraph user = null;
			Paragraph refeicao = null;
			Paragraph alimentos = null;
			Paragraph dataPerfil = null;
			try {
				for (QuantidadeAlimentar q : qa1) {
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

					System.out.println("Quantidade alimentar -- " + q.refeicao.getNome());
					planoAlimentar = new Paragraph("\n\n\n\n Tipo de Plano - " + q.refeicao.perfilalimentar.getNome(),
							font[1]);
					user = new Paragraph("\n Utilizador - " + q.refeicao.perfilalimentar.user.getEmail() + " - "
							+ q.refeicao.perfilalimentar.user.getName() + " "
							+ q.refeicao.perfilalimentar.user.getLastName(), font[1]);
					dataPerfil = new Paragraph(
							"\n Data do Plano - " + formatter.format(q.refeicao.perfilalimentar.getData()) + "  -  "
									+ q.refeicao.perfilalimentar.getDia(),
							font[1]);
					refeicao = new Paragraph("\n Refeição -   " + q.refeicao.getNome(), font[1]);
					alimentos = new Paragraph("\n Alimentos a Consumir: ", font[3]);

				}
				document.add(planoAlimentar);
				document.add(user);
				document.add(dataPerfil);
				document.add(refeicao);
				document.add(alimentos);

				// document.add(alimentos);
				// document.add(num3);
				// document.add(num2);
			} catch (Exception e1) {
				System.out.println("Erro");

			}

			try {
				List<QuantidadeAlimentar> qa = null;
				qa = PersisterFactory.getInstance().getQuantidadeAlimentarManager().getQuantidadeRefeicao(id);

				Paragraph num3 = null;
				try {
					for (QuantidadeAlimentar q : qa) {

						num3 = new Paragraph(
								"         ->" + q.getQuantidade() + " " + q.getAtributo() + " de " + q.alimento.getNome() , font[1]);

						document.add(num3);
					}

				} catch (Exception e1) {
					System.out.println("Lista Vazia");
					Paragraph erro = new Paragraph(
							"Não alimentos associados à refeição, por favor crie o seu plano completo.", font[1]);
					document.add(erro);
				}
				
				Paragraph duvida = new Paragraph("\n\n Se tiver alguma dúvida, nao exite em entrar em contacto connosco, via e-mail, telefone, ou através da nossa plataforma.", font[2]);
				duvida.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(duvida);

				Paragraph budoGym = new Paragraph("\nBudoGym", font[2]);
				budoGym.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(budoGym);
				
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.now();
				Paragraph data = new Paragraph(" " + dtf.format(localDate) , font[2]);
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

				document.close();

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
