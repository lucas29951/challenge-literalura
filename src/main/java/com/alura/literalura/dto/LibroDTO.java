package com.alura.literalura.dto;

import com.alura.literalura.model.Autor;

public record LibroDTO(
        Long id,
        String titulo,
        Autor autor,
        String idioma,
        Integer cantidadDeDescargas
){ }
