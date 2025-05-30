package com.alura.literalura.principal;

import com.alura.literalura.model.Datos;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

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
                    //listarLibrosRegistrados();
                    break;
                case 3:
                    //listarAutoresRegistrados();
                    break;
                case 4:
                    //listarAutoresVivosPorAño();
                    break;
                case 5:
                    //listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro a buscar:");
        var tituloLibro = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                        .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                        .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado!");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
}
