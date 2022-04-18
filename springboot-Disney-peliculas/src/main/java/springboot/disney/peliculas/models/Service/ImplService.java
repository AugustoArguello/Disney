package springboot.disney.peliculas.models.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import springboot.disney.peliculas.models.Dao.DaoPeliculas;
import springboot.disney.peliculas.models.Entity.Peliculas;

@Service
public class ImplService implements IntfService {
	
	@Autowired
	private DaoPeliculas dao;

	@Override
	@Transactional
	public List<Peliculas> findall() {
		return (List<Peliculas>) dao.findAll();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	@Transactional
	public Peliculas edit(int id, Peliculas pel, MultipartFile foto) {
		Peliculas peltemp = dao.findById(id).orElse(null);
		peltemp.setTitulo(pel.getTitulo());
		peltemp.setFechaCreacion(pel.getFechaCreacion());
		peltemp.setCalificacion(pel.getCalificacion());

		if (!foto.isEmpty()) {
			String rutaabsoluta = "C://productos//recursos";
			try {
				byte[] bytesfoto = foto.getBytes();
				Path rutacompleta = Paths.get(rutaabsoluta + "//" + foto.getOriginalFilename());
				Files.write(rutacompleta, bytesfoto);
				peltemp.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return dao.save(peltemp);
	}

	@Override
	@Transactional
	public Peliculas create(Peliculas pel, MultipartFile foto) {
		if (!foto.isEmpty()) {
			String rutaabsoluta = "C://productos//recursos";
			try {
				byte[] bytesfoto = foto.getBytes();
				Path rutacompleta = Paths.get(rutaabsoluta + "//" + foto.getOriginalFilename());
				Files.write(rutacompleta, bytesfoto);
				pel.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dao.save(pel);
	}

	@Override
	@Transactional
	public Peliculas findByTitulo(String titulo) {
		
		return dao.findByTitulo(titulo.toLowerCase());
	}

	@Override
	public List<Peliculas> findByCalificacion(Integer cal) {
		List<Peliculas> calificadas = (List<Peliculas>) dao.findAll();
		
		return calificadas.stream().filter(t -> t.getCalificacion() >= cal).collect(Collectors.toList());
	}

}
