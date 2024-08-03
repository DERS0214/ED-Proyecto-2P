package Bases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Archivos {

    public static void guardarTemas(Map<String, Tema> usuarios) {
        File archivo = new File("Temas.txt");

        try {
            // Crear el archivo si no existe
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("ARCHIVOS: El archivo \"Temas\" fue creado con exito");
            }

            // Escribir el mapa de usuarios en el archivo
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
                out.writeObject(usuarios);
                System.out.println("ARCHIVOS: Temas guardados con exito");
            } catch (IOException e) {
                System.err.println("ARCHIVOS: Error al guardar Mapa Temas " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("ARCHIVOS: El archivo \"Temas\" NO fue creado: " + e.getMessage());
        }
    }

    public static Map<String, Tema> leerTemas() {
        Map<String, Tema> temas = null;
        File archivo = new File("Temas.dat");

        try {
            // Leer el mapa de usuarios del archivo
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
                temas = (Map<String, Tema>) in.readObject();
                System.out.println("ARCHIVOS: Mapa Temas leido con exito");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("ARCHIVOS: Error al leer Mapa Temas: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("ARCHIVOS: Error al leer Mapa Temas: " + e.getMessage());
        }

        if (temas == null) {
            temas = new HashMap<>(); // Si no se pudo leer, retornar mapa vacío
        }
        return temas;
    }
    
    public static String leerTema() {
        String titulo = null;
        File archivo = new File("Tema.txt");

        try {
            // Leer el mapa de usuarios del archivo
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
                titulo = (String) in.readObject();
                System.out.println("ARCHIVOS: Mapa Temas leido con exito");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("ARCHIVOS: Error al leer Mapa Temas: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("ARCHIVOS: Error al leer Mapa Temas: " + e.getMessage());
        }

        
        return titulo;
    }
    
    public static void guardarTema(String titulo) {
        File archivo = new File("Tema.txt");

        try {
            // Crear el archivo si no existe
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("ARCHIVOS: El archivo \"Tema\" fue creado con exito");
            }

            // Escribir el mapa de usuarios en el archivo
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
                out.writeObject(titulo);
                System.out.println("ARCHIVOS: Tema guardado con exito");
            } catch (IOException e) {
                System.err.println("ARCHIVOS: Error al guardar String Tema " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("ARCHIVOS: El archivo \"Tema\" NO fue creado: " + e.getMessage());
        }
    }

}
