package pt.ipb.nutrimeal.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.QuantidadeAlimentar;
import pt.ipb.nutrimeal.entity.Refeicao;

@Path("/refeicao/quantidades")
public class QuantidadeRefeicoes {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public List<QuantidadeAlimentar> getQuantidadeAlimentaresUser(@PathParam("id") long id) {
		List<QuantidadeAlimentar> lista_quantidadeAlimentar = new ArrayList<>();
		float quanti = (float) 0.0;
		System.out.println("id" + id);
		for (QuantidadeAlimentar quantidadeAlimentar : PersisterFactory.getInstance().getQuantidadeAlimentarManager().getQuantidadeRefeicao(id)) {
			lista_quantidadeAlimentar.add(new QuantidadeAlimentar(quantidadeAlimentar.getId(), quantidadeAlimentar.getQuantidade(), quantidadeAlimentar.getAtributo(), quantidadeAlimentar.refeicao.getNome(), quantidadeAlimentar.alimento.getId(),quantidadeAlimentar.alimento.getNome(), 
					quantidadeAlimentar.refeicao.perfilalimentar.getData(), quantidadeAlimentar.refeicao.perfilalimentar.getDia(), quantidadeAlimentar.alimento.getCarboidrato(),
					quantidadeAlimentar.alimento.getProteina(), quantidadeAlimentar.alimento.getGordura()));
		}
		for(QuantidadeAlimentar lista : lista_quantidadeAlimentar) {
//			quanti += lista.alimento.getCalorias();
//			System.out.println("Calorias total dos Alimentos: " + quanti);
			
		}
		
		return lista_quantidadeAlimentar;
	}
}
