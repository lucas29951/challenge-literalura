package com.alura.literalura.model;

public class Libro {

    private String titulo;
    private String autores;
    private String idiomas;
    private Integer cantidadDeDescargas;

    public Libro(){}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getCantidadDeDescargas() {
        return cantidadDeDescargas;
    }

    public void setCantidadDeDescargas(Integer cantidadDeDescargas) {
        this.cantidadDeDescargas = cantidadDeDescargas;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", autores='" + autores + '\'' +
                ", idiomas='" + idiomas + '\'' +
                ", cantidadDeDescargas=" + cantidadDeDescargas + '\'';
    }
}
