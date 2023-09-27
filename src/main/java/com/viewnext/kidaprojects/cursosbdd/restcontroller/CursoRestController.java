package com.viewnext.kidaprojects.cursosbdd.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.viewnext.kidaprojects.cursosbdd.model.Curso;
import com.viewnext.kidaprojects.cursosbdd.service.CursoService;
import jakarta.persistence.EntityNotFoundException;

/**
 * Implementación del controlador REST para gestionar cursos en la aplicación.
 *
 * <p>
 * La clase {@code CursoRestController} implementa endpoints REST para interactuar con
 * la entidad {@code Curso} en la aplicación. Proporciona métodos para realizar operaciones
 * como la obtención de cursos, creación, actualización y eliminación de cursos en la base de datos.
 * </p>
 *
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 27 de septiembre de 2023
 */
@RestController
public class CursoRestController {

	@Autowired
	private CursoService cursoService;

	private static final String CURSO_NOT_FOUND = "Curso con los argumentos introducidos no encontrado";
	private static final String INVALID_ARGUMENT = "Formato de argumento inválido";

	/**
	 * Recupera y devuelve una lista de todos los cursos disponibles en la
	 * aplicación.
	 *
	 * @return Un objeto ResponseEntity con una respuesta HTTP que contiene la lista
	 *         de cursos si se encuentran disponibles.
	 */
	@GetMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarCursos() {
		List<Curso> listaCursos = cursoService.mostrarCursos();

		return ResponseEntity.ok(listaCursos);
	}

	/**
	 * Recupera y devuelve un curso específico de acuerdo con su código.
	 *
	 * @param codigo El código del curso que se desea recuperar.
	 * @return Un objeto ResponseEntity con una respuesta HTTP que contiene el curso
	 *         si se encuentra, o un mensaje de error si no se encuentra.
	 */
	@GetMapping(value = "curso/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarCursoByCodigo(@PathVariable("codigo") String codigo) {
		try {
			Curso curso = cursoService.mostrarCursoByCodigo(codigo);
			return ResponseEntity.ok(curso);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CURSO_NOT_FOUND);
		}
	}

	/**
	 * Recupera y devuelve una lista de cursos cuyos nombres comienzan con un
	 * prefijo específico.
	 *
	 * @param nombre El prefijo del nombre que se utilizará para buscar cursos.
	 * @return Un objeto ResponseEntity con una respuesta HTTP que contiene la lista
	 *         de cursos si se encuentran, o un mensaje de error si no se encuentran
	 *         cursos que coincidan con el prefijo.
	 */
	@GetMapping(value = "cursos/nombre/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarCursosLikeNombre(@PathVariable("nombre") String nombre) {
		try {
			List<Curso> listaCursos = cursoService.mostrarCursosLikeNombre(nombre);
			return ResponseEntity.ok(listaCursos);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CURSO_NOT_FOUND);
		}
	}

	/**
	 * Recupera y devuelve una lista de cursos cuyos precios están dentro de un
	 * rango específico.
	 *
	 * @param precioMinimo El precio mínimo del rango.
	 * @param precioMaximo El precio máximo del rango.
	 * @return Un objeto ResponseEntity con una respuesta HTTP que contiene la lista
	 *         de cursos si se encuentran dentro del rango especificado, o un
	 *         mensaje de error si no se encuentran cursos que coincidan con el
	 *         rango de precios.
	 */
	@GetMapping(value = "cursos/rango", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarCursosPorRangoPrecio(@RequestParam("precioMinimo") int precioMinimo,
			@RequestParam("precioMaximo") int precioMaximo) {
		try {
			List<Curso> listaCursos = cursoService.mostrarCursosPorRangoPrecio(precioMinimo, precioMaximo);
			return ResponseEntity.ok(listaCursos);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CURSO_NOT_FOUND);
		}

	}

	/**
	 * Crea un nuevo curso en la base de datos a partir de los datos proporcionados.
	 *
	 * @param cursoParaCrear El objeto {@code Curso} que se desea crear y guardar en
	 *                       la base de datos.
	 * @return Un objeto ResponseEntity con una respuesta HTTP que contiene el curso
	 *         creado si la operación tiene éxito, o un mensaje de error si se
	 *         proporcionan datos inválidos.
	 */
	@PostMapping(value = "curso", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> crearCurso(@RequestBody Curso cursoParaCrear) {
		try {
			Curso cursoCreado = cursoService.crearCurso(cursoParaCrear);
			return ResponseEntity.ok(cursoCreado);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_ARGUMENT);
		}
	}

	/**
	 * Crea varios cursos en la base de datos a partir de la lista de cursos
	 * proporcionada.
	 *
	 * @param listaCursosParaCrear La lista de objetos {@code Curso} que se desea
	 *                             crear y guardar en la base de datos.
	 * @return Un objeto ResponseEntity con una respuesta HTTP que contiene la lista
	 *         de cursos creados si la operación tiene éxito, o un mensaje de error
	 *         si se proporcionan datos inválidos.
	 */
	@PostMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> crearVariosCursos(@RequestBody List<Curso> listaCursosParaCrear) {
		try {
			List<Curso> listaCursoCreados = cursoService.crearVariosCursos(listaCursosParaCrear);
			return ResponseEntity.ok(listaCursoCreados);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_ARGUMENT);
		}
	}

	/**
	 * Actualiza la información de un curso en la base de datos.
	 *
	 * @param cursoParaActualizar El objeto {@code Curso} con la información
	 *                            actualizada del curso.
	 * @return Un objeto ResponseEntity con una respuesta HTTP que contiene el curso
	 *         actualizado si la operación tiene éxito, o un mensaje de error si el
	 *         curso no se encuentra o se proporcionan datos inválidos.
	 */
	@PutMapping(value = "curso", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizarCurso(@RequestBody Curso cursoParaActualizar) {

		try {
			Curso cursoActualizado = cursoService.actualizarCurso(cursoParaActualizar);
			return ResponseEntity.ok(cursoActualizado);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CURSO_NOT_FOUND);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_ARGUMENT);
		}

	}

	/**
	 * Borra un curso de la base de datos por su código.
	 *
	 * @param codigo El código del curso que se desea borrar.
	 * @return Un objeto ResponseEntity con una respuesta HTTP que indica el éxito
	 *         de la operación o un mensaje de error si el curso no se encuentra o
	 *         se proporcionan datos inválidos.
	 */
	@DeleteMapping(value = "curso/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> borrarCurso(@PathVariable("codigo") String codigo) {

		try {
			cursoService.borrarCurso(codigo);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CURSO_NOT_FOUND);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_ARGUMENT);
		}
	}
}
