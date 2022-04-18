package springboot.disney.peliculas.models.Dao;

import org.springframework.data.repository.CrudRepository;
import springboot.disney.peliculas.models.Entity.Peliculas;

public interface DaoPeliculas extends CrudRepository<Peliculas, Integer>{

	public Peliculas findByTitulo(String titulo);
}
