package springboot.disney.peliculas.Cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import springboot.disney.peliculas.models.Entity.Personajes;

@FeignClient(name = "personajes", url = "localhost:9001")
public interface ClientePersonajes {
	
	public List<Personajes> findAll();

}
