package pt.ipb.nutrimeal.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pt.ipb.nutrimeal.dao.PersisterFactory;
import pt.ipb.nutrimeal.entity.MetaExercicio;

@Path("/perfis/exercicios")
public class ExerciciosPerfil {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public List<MetaExercicio> getExerciciosUserList(@PathParam("id") long id) {
		List<MetaExercicio> lista_metaExercicio = new ArrayList<>();

		for (MetaExercicio metaExercicio : PersisterFactory.getInstance().getMetaExercicioManager().getPerfilUser(id)) {
			lista_metaExercicio.add(new MetaExercicio(metaExercicio.getId(), metaExercicio.getCalorias(),
					metaExercicio.exercicio.getNome(), metaExercicio.perfil.getNome()));
		}
		return lista_metaExercicio;
	}
}
