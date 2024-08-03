package Bases;

import Bases.DoublyCircularList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Archivos {

    public static void guardarUsuarios(Map<String, Usuario> usuarios) {
        File archivo = new File("Usuarios.dat");

        try {
            // Crear el archivo si no existe
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("ARCHIVOS: El archivo \"Usuarios\" fue creado con exito");
            }

            // Escribir el mapa de usuarios en el archivo
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
                out.writeObject(usuarios);
                System.out.println("ARCHIVOS: Usuario guardados con exito");
            } catch (IOException e) {
                System.err.println("ARCHIVOS: Error al guardar Mapa Usuarios " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("ARCHIVOS: El archivo \"Usuarios\" NO fue creado: " + e.getMessage());
        }
    }

    public static Map<String, Usuario> leerUsuarios() {
        Map<String, Usuario> usuarios = null;
        File archivo = new File("Usuarios.dat");

        try {
            // Leer el mapa de usuarios del archivo
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
                usuarios = (Map<String, Usuario>) in.readObject();
                System.out.println("ARCHIVOS: Mapa Usuarios leido con exito");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("ARCHIVOS: Error al leer Mapa Usuarios: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("ARCHIVOS: Error al leer Mapa Usuarios: " + e.getMessage());
        }

        if (usuarios == null) {
            usuarios = new HashMap<>(); // Si no se pudo leer, retornar mapa vacío
        }
        return usuarios;
    }

    public static void guardarAutos(DoublyCircularList<Auto> autos) {
        File archivo = new File("Autos.dat");

        try {
            // Crear el archivo si no existe
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("ARCHIVOS: El archivo \"Autos\" fue creado con exito");
            }

            // Escribir el mapa de Autos en el archivo
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
                out.writeObject(autos);
                System.out.println("ARCHIVOS: Autos guardados con exito");
            } catch (IOException e) {
                System.err.println("ARCHIVOS: Error al guardar DCLL Autos: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("ARCHIVOS: El archivo \"Usuarios\" NO fue creado: " + e.getMessage());
        }
    }

    public static DoublyCircularList<Auto> leerAutos() {
        DoublyCircularList<Auto> autos = null;
        File archivo = new File("Autos.dat");

        try {
            // Leer el mapa de usuarios del archivo
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
                autos = (DoublyCircularList<Auto>) in.readObject();
                System.out.println("ARCHIVOS: DCLL Autos leida con exito");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("ARCHIVOS: Error al leer DCLL Autos: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("ARCHIVOS: Error al leer DCLL Autos: " + e.getMessage());
        }

        if (autos == null) {
            autos = new DoublyCircularList<>(); // Si no se pudo leer, retornar circularlist vacío
        }

        return autos;
    }

}
