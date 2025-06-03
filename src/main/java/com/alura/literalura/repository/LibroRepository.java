package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

    Optional<Libro> findByTitulo(String titulo);

    @Query(value = "SELECT DISTINCT(libros.idioma) FROM libros", nativeQuery = true)
    List<String> obtenerIdiomas();

    @Query(value = "SELECT l FROM Libro l WHERE l.idioma ILIKE %:idiomaBuscado%")
    List<Libro> obtenerLibrosPorIdioma(String idiomaBuscado);
}
