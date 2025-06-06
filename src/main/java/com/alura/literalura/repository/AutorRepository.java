package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);

    @Query(value = "SELECT a FROM Autor a WHERE a.fallecimiento > :añoBuscado AND a.nacimiento < :añoBuscado")
    List<Autor> autoresVivosHastaDeterminadoAño(int añoBuscado);
}
