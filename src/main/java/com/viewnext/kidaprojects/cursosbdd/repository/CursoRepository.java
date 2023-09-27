package com.viewnext.kidaprojects.cursosbdd.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.viewnext.kidaprojects.cursosbdd.model.Curso;

/**
 * Repositorio para la gestión de la entidad {@code Curso}.
 *
 * <p>
 * La interfaz {@code CursoRepository} extiende {@code JpaRepository} y proporciona
 * métodos para realizar operaciones de acceso a la base de datos relacionadas con
 * la entidad {@code Curso}. Estos métodos incluyen operaciones como guardar,
 * recuperar, actualizar y eliminar cursos en la base de datos.
 * </p>
 *
 * <p>
 * El autor de esta interfaz es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 27 de septiembre de 2023
 */
public interface CursoRepository extends JpaRepository<Curso, String> {

	
	/**
	 * Busca cursos cuyo precio se encuentra en el rango especificado, incluyendo los extremos.
	 *
	 * @param precioMinimo El precio mínimo del rango.
	 * @param precioMaximo El precio máximo del rango.
	 * @return Una lista de objetos {@code Curso} cuyos precios están dentro del rango especificado.
	 */
	@Query("SELECT c FROM Curso c WHERE c.precio >= :precioMinimo AND c.precio <= :precioMaximo")
	List<Curso> findByPrecioBetween(int precioMinimo, int precioMaximo);

	/**
	 * Busca cursos cuyos nombres comienzan con el prefijo especificado.
	 *
	 * @param nombrePrefix El prefijo que se utiliza para buscar cursos.
	 * @return Una lista de objetos {@code Curso} cuyos nombres coinciden con el patrón especificado.
	 */
	@Query("SELECT c FROM Curso c WHERE c.nombre LIKE :nombrePrefix%")
	List<Curso> findCursosByNombreStartingWith(@Param("nombrePrefix") String nombrePrefix);


    
}
