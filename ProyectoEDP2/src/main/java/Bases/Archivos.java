package Bases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Archivos {

    private static final String direc = "temas/";
    //guardamos en archivos binarios para que la lectura de los objetos sea más sencilla
    
    public static Tema leerTema(String nombre) {
        File archivo = new File(direc, nombre + ".dat"); 
        if (!archivo.exists()) {
            System.err.println("ARCHIVOS: El archivo del tema " + nombre + " no existe.");
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            Tema tema = (Tema) in.readObject();
            System.out.println("ARCHIVOS: Tema leído con éxito: " + nombre);
            return tema;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ARCHIVOS: Error al leer el archivo de tema " + nombre + ": " + e.getMessage());
            return null;
        }
    }

        public static List<Tema> leerTemasDesdeDirectorio() {
        List<Tema> temas = new ArrayList<>();
        File directorio = new File(direc);
        File[] archivos = directorio.listFiles((dir, name) -> name.endsWith(".dat"));

        if (archivos != null) {
            for (File archivo : archivos) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
                    Tema tema = (Tema) in.readObject();
                    temas.add(tema);
                    System.out.println("ARCHIVOS: Tema leído con éxito: " + archivo.getName());
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("ARCHIVOS: Error al leer el archivo de tema " + archivo.getName() + ": " + e.getMessage());
                }
            }
        } else {
            System.err.println("ARCHIVOS: No se pudo encontrar el directorio de temas.");
        }

        return temas;
    }
        
    public static void guardarTema(String nombre, Tema tema) {
        File directorio = new File(direc);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        File archivo = new File(directorio, nombre + ".dat");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(tema);
            System.out.println("ARCHIVOS: Tema guardado con éxito: " + nombre);
        } catch (IOException e) {
            System.err.println("ARCHIVOS: Error al guardar el tema " + nombre + ": " + e.getMessage());
        }
    }
        
    public static void eliminarTema(String nombre) {
        File directorio = new File(direc);
        File archivo = new File(directorio, nombre + ".dat");

        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("ARCHIVOS: Tema eliminado con éxito: " + nombre);
            } else {
                System.err.println("ARCHIVOS: No se pudo eliminar el tema: " + nombre);
            }
        } else {
            System.err.println("ARCHIVOS: No se encontró el tema: " + nombre);
        }
    }

    public static Tema leerSeleccionado() {
        String linea = "";
        File archivo = new File("seleccionado.txt");
        try {
            // Si el archivo no existe, lo crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            // Lee la primera línea del archivo
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                linea = br.readLine();
                if (linea == null) {
                    linea = ""; // El archivo está vacío
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        
        return leerTema(linea);
    }
    
    public static void escribirSeleccionado(String nombreTema) {
        File archivo = new File("seleccionado.txt");
        try {
            // Si el archivo no existe, lo crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            // Escribe el nombre del tema en el archivo, sobrescribiendo el contenido existente
            try (FileWriter fw = new FileWriter(archivo)) {
                fw.write(nombreTema);  // Sobrescribe el archivo con el nuevo nombre del tema
            }

        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

}
