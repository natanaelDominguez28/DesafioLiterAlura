package com.alura.literalura.principal;

import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.services.ConsumoAPI;
import com.alura.literalura.services.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private LibroRepository repositorio;
    private List<Libro> libros;
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private Optional<Libro> libroBuscado;
    private ConvierteDatos conversor = new ConvierteDatos();

    public Principal(LibroRepository libroRepository) {
        this.repositorio = libroRepository;
    }

    public void mostrarMenu(){
        var opcion = -1;
        while(opcion != 0){
            var menu = """
                    1- Buscar libro
                    2- Buscar autor
                    3- Mostrar libros
                    
                    0- Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch(opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    System.out.println("opcion 2");
                    break;
                case 3:
                    System.out.println("opcion 3");
                    break;
                case 4:
                    System.out.println("opcion 4");
                    break;
                case 5:
                    System.out.println("opcion 5");
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    public DatosLibro getDatosLibro(){
        System.out.println("Escribe el título del libro que quieres buscar: ");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"/?search="+tituloLibro.replace(" ","%20"));
        System.out.println(json);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        return datos;
    }

    public void buscarLibro(){
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);
        datosLibros.add(datos);
        System.out.println(datos);
    }
}
