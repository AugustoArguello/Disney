package springboot.disney.personaje.model.Service;

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

import springboot.disney.personaje.model.Dao.PersonajeDao;
import springboot.disney.personaje.model.entity.Personajes;

@Service
public class Serviceimpl implements Iservice {

	@Autowired
	private PersonajeDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<Personajes> findall() {
		List<Personajes> person = (List<Personajes>) dao.findAll();
		return person;
	}

	@Override
	@Transactional
	public void delete(Integer id) {
	dao.deleteById(id);
	}

	@Override
	@Transactional
	public Personajes edit(int id, Personajes person, MultipartFile foto) {

		Personajes personajetemp = dao.findById(id).orElse(null);
		personajetemp.setNombre(person.getNombre());
		personajetemp.setEdad(person.getEdad());
		personajetemp.setPeso(person.getPeso());
		personajetemp.setHistoria(person.getHistoria());

		if (!foto.isEmpty()) {
			String rutaabsoluta = "C://productos//recursos";
			try {
				byte[] bytesfoto = foto.getBytes();
				Path rutacompleta = Paths.get(rutaabsoluta + "//" + foto.getOriginalFilename());
				Files.write(rutacompleta, bytesfoto);
				personajetemp.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return dao.save(personajetemp);
	}

	@Override
	@Transactional
	public Personajes create(Personajes person, MultipartFile foto) {

		if (!foto.isEmpty()) {
			String rutaabsoluta = "C://productos//recursos";
			try {
				byte[] bytesfoto = foto.getBytes();
				Path rutacompleta = Paths.get(rutaabsoluta + "//" + foto.getOriginalFilename());
				Files.write(rutacompleta, bytesfoto);
				person.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return dao.save(person);
	}

	@Override
	@Transactional
	public List<Personajes> findByEdad(Integer edad) {
	List<Personajes> lista = (List<Personajes>) dao.findAll();
	return lista.stream().filter(t-> t.getEdad()==edad).collect(Collectors.toList());

	}

	
	@Override
	@Transactional
	public List<Personajes> findByNombre(String nombre) {
		return dao.findByNombre(nombre);
	}

}
