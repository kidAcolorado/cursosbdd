package com.viewnext.kidaprojects.cursosbdd.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.viewnext.kidaprojects.cursosbdd.model.Curso;
import com.viewnext.kidaprojects.cursosbdd.repository.CursoRepository;

import jakarta.persistence.EntityNotFoundException;


/**
 * Implementación del servicio de gestión de cursos en la aplicación.
 *
 * <p>
 * La clase {@code CursoServiceImpl} implementa la interfaz {@code CursoService} y
 * proporciona métodos para realizar operaciones relacionadas con la entidad {@code Curso}
 * en la aplicación. Estas operaciones incluyen la búsqueda de cursos, creación, actualización
 * y eliminación de cursos en la base de datos.
 * </p>
 *
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 27 de septiembre de 2023
 */
@Service
public class CursoServiceImpl implements CursoService, CursoRepository {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	
	//###########################################################
	//################### MÉTODOS DE BÚSQUEDA ###################
	//###########################################################
	
	
	/**
	 * Recupera una lista de todos los cursos en la base de datos.
	 *
	 * @return Una lista de objetos {@code Curso} que representan todos los cursos en la base de datos.
	 */
	@Override
	public List<Curso> findAll() {
	    return cursoRepository.findAll();
	}

	/**
	 * Recupera una lista de todos los cursos en la base de datos.
	 *
	 * @return Una lista de objetos {@code Curso} que representan todos los cursos en la base de datos.
	 */
	@Override
	public List<Curso> mostrarCursos() {
	    return cursoRepository.findAll();
	}

	
	/**
	 * Busca un curso en la base de datos por su código.
	 *
	 * @param id El código del curso que se desea buscar.
	 * @return Un objeto {@code Optional<Curso>} que contiene el curso si se encuentra, o un valor vacío si no se encuentra.
	 */
	@Override
	public Optional<Curso> findById(String id) {
	    return cursoRepository.findById(id);
	}

	/**
	 * Busca un curso en la base de datos por su código y lo devuelve.
	 *
	 * @param codigo El código del curso que se desea buscar.
	 * @return Un objeto {@code Curso} que representa el curso si se encuentra.
	 * @throws EntityNotFoundException Si no se encuentra ningún curso con el código especificado.
	 */
	@Override
	public Curso mostrarCursoByCodigo(String codigo) throws EntityNotFoundException {
	    Optional<Curso> optionalCurso = cursoRepository.findById(codigo);

	    if (optionalCurso.isPresent()) {
	        return optionalCurso.get();
	    } else {
	        throw new EntityNotFoundException();
	    }
	}

	
	/**
	 * Recupera una lista de cursos cuyos precios estén dentro de un rango específico.
	 *
	 * @param precioMinimo El precio mínimo del rango.
	 * @param precioMaximo El precio máximo del rango.
	 * @return Una lista de objetos {@code Curso} que representan los cursos dentro del rango de precios especificado.
	 */
	@Override
	public List<Curso> findByPrecioBetween(int precioMinimo, int precioMaximo) {
	    return cursoRepository.findByPrecioBetween(precioMinimo, precioMaximo);
	}

	/**
	 * Busca cursos cuyos precios estén dentro de un rango específico y devuelve una lista de los cursos que coinciden.
	 *
	 * @param precioMinimo El precio mínimo del rango.
	 * @param precioMaximo El precio máximo del rango.
	 * @return Una lista de objetos {@code Curso} que representan los cursos dentro del rango de precios especificado.
	 * @throws EntityNotFoundException Si no se encuentran cursos dentro del rango de precios especificado.
	 */
	@Override
	public List<Curso> mostrarCursosPorRangoPrecio(int precioMinimo, int precioMaximo) throws EntityNotFoundException {
	    List<Curso> listaCursosPorRangoPrecio = cursoRepository.findByPrecioBetween(precioMinimo, precioMaximo);

	    if (listaCursosPorRangoPrecio.isEmpty()) {
	        throw new EntityNotFoundException();
	    }

	    return listaCursosPorRangoPrecio;
	}

	/**
	 * Recupera una lista de cursos cuyos nombres comienzan con el prefijo especificado.
	 *
	 * @param nombrePrefix El prefijo de nombre que se utilizará para buscar cursos.
	 * @return Una lista de objetos {@code Curso} que representan los cursos cuyos nombres comienzan con el prefijo especificado.
	 */
	@Override
	public List<Curso> findCursosByNombreStartingWith(String nombrePrefix) {
	    return cursoRepository.findCursosByNombreStartingWith(nombrePrefix);
	}

	/**
	 * Busca cursos cuyos nombres comienzan con el nombre proporcionado y devuelve una lista de los cursos que coinciden.
	 *
	 * @param nombre El nombre o prefijo de los cursos que se desea buscar.
	 * @return Una lista de objetos {@code Curso} que representan los cursos cuyos nombres comienzan con el nombre especificado.
	 * @throws EntityNotFoundException Si no se encuentran cursos con el nombre especificado.
	 */
	@Override
	public List<Curso> mostrarCursosLikeNombre(String nombre) throws EntityNotFoundException {
	    List<Curso> listaCursosLikeNombre = cursoRepository.findCursosByNombreStartingWith(nombre);

	    if (listaCursosLikeNombre.isEmpty()) {
	        throw new EntityNotFoundException();
	    }
	    return listaCursosLikeNombre;
	}


	//###########################################################
	//################### MÉTODOS DE CREACIÓN ###################
	//###########################################################
	
	/**
	 * Guarda un curso en la base de datos.
	 *
	 * @param entity El objeto {@code Curso} que se desea guardar en la base de datos.
	 * @return El objeto {@code Curso} guardado en la base de datos.
	 */
	@Override
	public <S extends Curso> S save(S entity) {
	    return cursoRepository.save(entity);
	}

	/**
	 * Crea un nuevo curso en la base de datos a partir de los datos proporcionados.
	 *
	 * @param cursoParaCrear El objeto {@code Curso} que se desea crear y guardar en la base de datos.
	 * @return El curso creado y guardado en la base de datos.
	 */
	@Override
	public Curso crearCurso(Curso cursoParaCrear) {
	    return cursoRepository.save(cursoParaCrear);
	}
	
	
	/**
	 * Guarda una lista de cursos en la base de datos.
	 *
	 * @param entities La lista de objetos {@code Curso} que se desea guardar en la base de datos.
	 * @return La lista de objetos {@code Curso} guardados en la base de datos.
	 */
	@Override
	public <S extends Curso> List<S> saveAll(Iterable<S> entities) {
	    return cursoRepository.saveAll(entities);
	}

	/**
	 * Crea varios cursos en la base de datos a partir de la lista proporcionada.
	 *
	 * @param listaCursosParaCrear La lista de objetos {@code Curso} que se desea crear y guardar en la base de datos.
	 * @return La lista de objetos {@code Curso} creados y guardados en la base de datos.
	 * @throws EntityNotFoundException Si no se crean cursos en la base de datos a partir de la lista proporcionada.
	 */
	@Override
	public List<Curso> crearVariosCursos(List<Curso> listaCursosParaCrear) throws EntityNotFoundException {
	    List<Curso> listaCursosCreados = cursoRepository.saveAll(listaCursosParaCrear);

	    if (listaCursosCreados.isEmpty()) {
	        throw new EntityNotFoundException();
	    }
	    return listaCursosCreados;
	}

	
	//###########################################################
	//################### MÉTODOS DE BORRADO ####################
	//###########################################################
	
	/**
	 * Verifica si existe un curso en la base de datos con el código especificado.
	 *
	 * @param codigo El código del curso que se desea verificar si existe.
	 * @return {@code true} si existe un curso con el código especificado en la base de datos, {@code false} en caso contrario.
	 */
	@Override
	public boolean existsById(String codigo) {
		
		return cursoRepository.existsById(codigo);
	}
	
	/**
	 * Elimina un curso de la base de datos por su código.
	 *
	 * @param codigo El código del curso que se desea eliminar.
	 */
	@Override
	public void deleteById(String codigo) {
		cursoRepository.deleteById(codigo);
		
	}
		

	
	/**
	 * Borra un curso de la base de datos por su código, verificando previamente si existe.
	 *
	 * @param codigo El código del curso que se desea borrar.
	 * @throws EntityNotFoundException Si no se encuentra ningún curso con el código especificado.
	 */
	@Override
	public void borrarCurso(String codigo) throws EntityNotFoundException{
		if(!existsById(codigo)) {
			throw new EntityNotFoundException();
		}
		
		deleteById(codigo);
		
	}
	
	
	//###########################################################
	//#################### MÉTODOS DE UPDATE ####################
	//###########################################################
	
	
	/**
	 * Actualiza la información de un curso en la base de datos.
	 *
	 * @param cursoParaActualizar El objeto {@code Curso} con la información actualizada del curso.
	 * @return El objeto {@code Curso} actualizado.
	 * @throws EntityNotFoundException Si no se encuentra ningún curso con el código especificado.
	 */
	@Override
	public Curso actualizarCurso(Curso cursoParaActualizar) throws EntityNotFoundException{
		Optional<Curso> optionalCurso = cursoRepository.findById(cursoParaActualizar.getCodigo());
		
		if(optionalCurso.isEmpty()) {
			throw new EntityNotFoundException();
		}
		
		Curso cursoActualizado;
		
		cursoActualizado = cursoRepository.save(cursoParaActualizar);
		
		return cursoActualizado;
		
	}
	
	
	
	//###########################################################
	//########### MÉTODOS A IMPLEMENTAR EN UN FUTURO ############
	//###########################################################

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Curso> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Curso> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Curso> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Curso getOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curso getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curso getReferenceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Curso> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Curso> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	


	

	@Override
	public List<Curso> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public void delete(Curso entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Curso> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Curso> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Curso> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Curso> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends Curso> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Curso> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Curso> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Curso, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	
	

}
