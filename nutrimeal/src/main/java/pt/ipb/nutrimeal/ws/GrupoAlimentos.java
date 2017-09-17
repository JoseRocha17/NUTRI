package pt.ipb.nutrimeal.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.Alimento;


@Path("/grupo/alimentos")
public class GrupoAlimentos {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public List<Alimento> getAlimentoGroupList(@PathParam("id") long id) {
		List<Alimento> lista_alimentos = new ArrayList<>();

		for (Alimento alimento : PersisterFactory.getInstance().getAlimentoManager().getAlimentoGrupo(id)) {
			lista_alimentos.add(new Alimento(alimento.getId(), alimento.getNome(), alimento.grupo.getNome(), alimento.getCalorias(),
					alimento.getGordura(), alimento.getColestrol(), alimento.getSodio(), alimento.getPotassio(),
					alimento.getCarboidrato(), alimento.getFibra(), alimento.getAcucar(), alimento.getProteina()));
		}
		return lista_alimentos;
	}
}

