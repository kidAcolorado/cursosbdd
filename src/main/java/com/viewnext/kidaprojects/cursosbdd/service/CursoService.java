package com.viewnext.kidaprojects.cursosbdd.service;

import java.util.List;

import com.viewnext.kidaprojects.cursosbdd.model.Curso;

/**
 * Servicio de gestión de cursos en la aplicación.
 *
 * <p>
 * La interfaz {@code CursoService} proporciona métodos para realizar operaciones
 * relacionadas con la entidad {@code Curso} en la aplicación. Estas operaciones
 * incluyen la búsqueda de cursos por código, nombre o rango de precio, la
 * creación, actualización y eliminación de cursos.
 * </p>
 *
 * <p>
 * El autor de esta interfaz es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since @since 27 de septiembre de 2023
 */
public interface CursoService {

    /**
     * Recupera una lista de todos los cursos disponibles.
     *
     * @return Una lista de objetos {@code Curso} que representan todos los cursos
     *         en la aplicación.
     */
    public List<Curso> mostrarCursos();

    /**
     * Busca un curso por su código único.
     *
     * @param codigo El código único del curso que se desea buscar.
     * @return El objeto {@code Curso} que coincide con el código especificado.
     */
    public Curso mostrarCursoByCodigo(String codigo);

    /**
     * Busca cursos cuyos nombres contienen una cadena específica.
     *
     * @param nombre La cadena que se utiliza para buscar cursos por nombre.
     * @return Una lista de objetos {@code Curso} que contienen la cadena de nombre
     *         especificada.
     */
    public List<Curso> mostrarCursosLikeNombre(String nombre);

    /**
     * Busca cursos dentro de un rango de precio especificado.
     *
     * @param precioMinimo El precio mínimo del rango de precio.
     * @param precioMaximo El precio máximo del rango de precio.
     * @return Una lista de objetos {@code Curso} que se encuentran dentro del rango
     *         de precio especificado.
     */
    public List<Curso> mostrarCursosPorRangoPrecio(int precioMinimo, int precioMaximo);

    /**
     * Crea un nuevo curso en la aplicación.
     *
     * @param cursoParaCrear El objeto {@code Curso} que se desea crear y guardar.
     * @return El curso creado y guardado en la aplicación.
     */
    public Curso crearCurso(Curso cursoParaCrear);

    /**
     * Crea varios cursos a la vez en la aplicación.
     *
     * @param listaCursosParaCrear Una lista de objetos {@code Curso} que se
     *                             desean crear y guardar.
     * @return Una lista de los cursos creados y guardados en la aplicación.
     */
    public List<Curso> crearVariosCursos(List<Curso> listaCursosParaCrear);

    /**
     * Actualiza la información de un curso existente en la aplicación.
     *
     * @param cursoParaActualizar El objeto {@code Curso} con la información
     *                            actualizada del curso.
     * @return El objeto {@code Curso} actualizado.
     */
    public Curso actualizarCurso(Curso cursoParaActualizar);

    /**
     * Elimina un curso de la aplicación por su código.
     *
     * @param codigo El código del curso que se desea eliminar.
     */
    public void borrarCurso(String codigo);
}

