package springboot.disney.personaje.model.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springboot.disney.personaje.model.entity.Personajes;

public interface PersonajeDao extends CrudRepository<Personajes, Integer> {
	
	public List <Personajes> findByNombre(String nombre);
	
}
