package springboot.disney.peliculas.models.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springboot.disney.peliculas.models.Entity.Peliculas;
import springboot.disney.peliculas.models.Service.ImplService;

@RestController
public class ControladorPeliculas {
	
	@Autowired
	private ImplService service;
	
	@GetMapping("/movies")
	public List<Peliculas> findall(){
		return service.findall();		
	}
	
	@GetMapping("/movies/titulo/{titulo}")
	public Peliculas buscarPorTitulo(@PathVariable String titulo){
	
		return service.findByTitulo(titulo);		
	}
	
	@GetMapping("/movies/calificacion/{calificacion}")
	public List <Peliculas> buscarPorCalificacion(@PathVariable Integer calificacion){
	
		return service.findByCalificacion(calificacion);		
	}
	
		
	@DeleteMapping("/movies/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		service.delete(id);
	}
	
	@PutMapping("/movies/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Peliculas edit(@PathVariable int id,@RequestBody Peliculas pel,@RequestParam MultipartFile foto) {
		
		return service.edit(id, pel,foto);		
	}
	
	@PostMapping("/movies/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Peliculas create(@RequestBody Peliculas pel,@RequestParam("file") MultipartFile foto) {
		return service.create(pel,foto);
		
	}

}
