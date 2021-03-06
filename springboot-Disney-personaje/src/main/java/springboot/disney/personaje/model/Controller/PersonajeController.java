package springboot.disney.personaje.model.Controller;

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

import springboot.disney.personaje.model.Service.Iservice;
import springboot.disney.personaje.model.entity.Personajes;

@RestController
public class PersonajeController {
	
	@Autowired
	private Iservice servicio;

	@GetMapping("/characters")
	public List<Personajes> findall(){
	
		return servicio.findall();		
	}
	
	@GetMapping("/characters/edad/{edad}")
	public List<Personajes> buscarPorEdad(@PathVariable Integer edad){
	
		return servicio.findByEdad(edad);		
	}
	
	@GetMapping("/characters/nombre/{nombre}")
	public List<Personajes> buscarPorNombre(@PathVariable String nombre){
		System.out.println(nombre);
		return servicio.findByNombre(nombre);		
	}

	
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		servicio.delete(id);
	}
	
	@PutMapping("/edit/{id}")
	public Personajes edit(@PathVariable int id,@RequestBody Personajes person,@RequestParam MultipartFile foto) {
		
		return servicio.edit(id, person,foto);		
	}
	
	@PostMapping("/create")
	public Personajes create(@RequestBody Personajes person,@RequestParam("file") MultipartFile foto) {
		return servicio.create(person,foto);
		
	}
}
