package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;
    private List<Libro> librosRegistrados;
    private List<Autor> autoresRegistrados;
    private Scanner teclado = new Scanner(System.in);

    public Principal(LibroRepository repoLibro, AutorRepository repoAutor) {
        this.repositorioLibro = repoLibro;
        this.repositorioAutor = repoAutor;
    }

    public void menuDeOpciones() {
        var opc = -1;
        while (opc != 0) {
            var opciones = """
                    --------------------------------------
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Estadisticas de Libros por Idioma
                    0 - Salir
                    --------------------------------------
                    """;
            System.out.println(opciones);
            System.out.print("Ingrese su opcion: ");
            opc = teclado.nextInt();
            teclado.nextLine();

            switch (opc) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAño();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    listarLibrosPorIdiomaEstadistica();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private DatosLibro obtenerDatosLibro() {
        System.out.println("Ingrese el nombre del libro a buscar:");
        var tituloLibro = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        Datos datos = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        return libroBuscado.get();
    }

    private void mostrarDatosLibros() {
        for (Libro l : librosRegistrados) {
            System.out.println("###################################");
            System.out.println("Titulo: " + l.getTitulo());
            System.out.println("Autor: " + l.getAutor().getNombre());
            System.out.println("Idioma: " + l.getIdioma().toUpperCase());
            System.out.println("Cantidad de Descargas: " + l.getCantidadDeDescargas());
        }
        System.out.println("###################################");
    }

    private void buscarLibroPorTitulo() {
        DatosLibro datos = obtenerDatosLibro();

        if (datos != null) {
            System.out.println("LIBRO ENCONTRADO!");

            System.out.println("Titulo: " + datos.titulo());
            System.out.print("Autor: ");
            datos.autor().stream().map(a -> a.nombre()).forEach(System.out::println);
            System.out.print("Idioma: ");
            datos.idioma().stream().map(i -> i.toUpperCase()).forEach(System.out::println);
            System.out.println("Cantidad de Descargas: " + datos.cantidadDeDescargas());

            String nombreAutor = datos.autor().get(0).nombre();
            Autor autor = repositorioAutor.findByNombre(nombreAutor)
                    .orElseGet(() -> repositorioAutor.save(new Autor(datos.autor().get(0))));

            Libro libro = new Libro(datos);
            libro.setAutor(autor);

            repositorioLibro.findByTitulo(libro.getTitulo())
                    .orElseGet(() -> repositorioLibro.save(libro));
        } else {
            System.out.println("LIBRO NO ENCONTRADO.");
        }
    }

    private void listarLibrosRegistrados() {
        librosRegistrados = repositorioLibro.findAll();

        if (!librosRegistrados.isEmpty()) {
            mostrarDatosLibros();
        } else {
            System.out.println("AUN NO HAY LIBROS REGISTRADOS.");
        }
    }

    private void listarAutoresRegistrados() {
        autoresRegistrados = repositorioAutor.findAll();

        if (!autoresRegistrados.isEmpty()) {
            for (Autor a : autoresRegistrados) {
                System.out.println("=================================");
                System.out.println("Nombre: " + a.getNombre());
                System.out.println("Fecha de Nacimiento: " + a.getNacimiento());
                System.out.println("Fecha de Fallecimiento: " + a.getFallecimiento());
            }
            System.out.println("=================================");
        } else {
            System.out.println("AUN NO SE REGISTRARON AUTORES.");
        }
    }

    private void listarAutoresVivosPorAño() {
        var añoDeBusqueda = 0;
        System.out.println("Ingrese el año que desea buscar:");
        do {
            añoDeBusqueda = teclado.nextInt();

            if (añoDeBusqueda <= 0) {
                System.out.println("Año invalido. Intente nuevamente..");
            }
        } while (añoDeBusqueda <= 0);

        autoresRegistrados = repositorioAutor.autoresVivosHastaDeterminadoAño(añoDeBusqueda);

        if (!autoresRegistrados.isEmpty()) {
            for (Autor a : autoresRegistrados) {
                System.out.println("=================================");
                System.out.println("Nombre: " + a.getNombre());
                System.out.println("Fecha de Nacimiento: " + a.getNacimiento());
            }
            System.out.println("=================================");
        } else {
            System.out.println("NO EXISTEN AUTORES VIVOS HASTA EL AÑO INGRESADO.");
        }
    }

    private void mostrarIdiomas() {
        List<String> idiomasExistentes = repositorioLibro.obtenerIdiomas();

        for (String lan : idiomasExistentes) {
            System.out.print("| " + lan.toUpperCase() + " ");
        }
        System.out.println("|");
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma a buscar:");
        mostrarIdiomas();
        var idioma = teclado.nextLine();

        librosRegistrados = repositorioLibro.obtenerLibrosPorIdioma(idioma);

        if (!librosRegistrados.isEmpty()) {
            mostrarDatosLibros();
        } else {
            System.out.println("NO EXISTEN LIBROS CON EL IDIOMA INGRESADO.");
        }
    }

    private void listarLibrosPorIdiomaEstadistica() {
        System.out.println("Ingrese el idioma a buscar:");
        mostrarIdiomas();
        var idioma = teclado.nextLine();

        librosRegistrados = repositorioLibro.findAll();

        IntSummaryStatistics est = librosRegistrados.stream()
                .filter(l -> l.getIdioma().toUpperCase().compareToIgnoreCase(idioma.toUpperCase()) == 0)
                .collect(Collectors.summarizingInt(Libro::getCantidadDeDescargas));

        System.out.println("Cantidad de Libros del Idioma [" + idioma.toUpperCase() + "]: " + est.getCount());
    }
}
