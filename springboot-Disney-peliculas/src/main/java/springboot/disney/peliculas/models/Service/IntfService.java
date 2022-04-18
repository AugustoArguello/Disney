package springboot.disney.peliculas.models.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import springboot.disney.peliculas.models.Entity.Peliculas;

public interface IntfService {

	

	public List<Peliculas> findall();
	public void delete(Integer id);
	public Peliculas edit(int id, Peliculas pel, MultipartFile foto);
	public Peliculas create(Peliculas pel, MultipartFile foto); 
	public Peliculas findByTitulo(String titulo);
	public List<Peliculas> findByCalificacion(Integer cal);
	
}
