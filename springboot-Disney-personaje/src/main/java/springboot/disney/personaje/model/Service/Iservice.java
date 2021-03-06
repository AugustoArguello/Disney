package springboot.disney.personaje.model.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import springboot.disney.personaje.model.entity.Personajes;

public interface Iservice {
	
	public List<Personajes> findall();
	public void delete(Integer id);
	public Personajes edit(int id, Personajes person, MultipartFile foto);
	public Personajes create(Personajes person, MultipartFile foto); 
	public List<Personajes> findByEdad(Integer edad);
	public List<Personajes> findByNombre(String nombre);
	

}
