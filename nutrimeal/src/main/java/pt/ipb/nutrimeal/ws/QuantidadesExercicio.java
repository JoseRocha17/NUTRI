package pt.ipb.nutrimeal.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.ExercicioAtributo;
import pt.ipb.nutrimeal.entity.QuantidadeAlimentar;

@Path("/exercicio/quantidades")
public class QuantidadesExercicio {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public List<ExercicioAtributo> getQuantidadeExerciciosUser(@PathParam("id") long id) {
		List<ExercicioAtributo> lista_exercicioAtributos = new ArrayList<>();
		System.out.println("id" + id);
		for (ExercicioAtributo exercicioAtributo : PersisterFactory.getInstance().getExercicioAtributoManager().getExercicioAtributoUser(id)) {
			lista_exercicioAtributos.add(new ExercicioAtributo(exercicioAtributo.getId(), exercicioAtributo.getValor(), exercicioAtributo.metaexercicio.exercicio.getNome(), exercicioAtributo.atributo.getNome()));
		}
		return lista_exercicioAtributos;
	}
}
