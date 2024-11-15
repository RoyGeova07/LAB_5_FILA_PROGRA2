/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5_file_progra2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author royum
 */
public class Funciones_Archivos {

    private File DireccionActual;

    public Funciones_Archivos(String direccion) {
        DireccionActual = new File(direccion);
        if (!DireccionActual.exists()) {
            DireccionActual.mkdirs();  // Crear directorio si no existe
        }
    }

    public String mkdir(String direccion) {
        File carpeta = new File(DireccionActual, direccion);
        if (carpeta.exists()) {
            return "Esta carpeta ya existe";
        } else {
            carpeta.mkdirs();
            return "Carpeta creada exitosamente";
        }
    }

    public String mfile(String direccion) {
        File archivo = new File(DireccionActual, direccion);
        try {
            if (archivo.createNewFile()) {
                return "Archivo creado exitosamente";
            } else {
                return "El archivo ya existe";
            }
        } catch (IOException e) {
            return "Error al crear el archivo: " + e.getMessage();
        }
    }

    public String rm(String direccion) {
        File archivoEliminar = new File(DireccionActual, direccion);
        if (archivoEliminar.exists()) {
            if (archivoEliminar.delete()) {
                return "Archivo o carpeta eliminada exitosamente";
            } else {
                return "Error al eliminar el archivo o carpeta";
            }
        } else {
            return "Error: el archivo o carpeta no existe";
        }
    }

    public String Cd(String direccion) {

        String mensaje;
        File nuevoDirectorio = new File(DireccionActual, direccion);

        if (nuevoDirectorio.exists() && nuevoDirectorio.isDirectory()) {
            DireccionActual = nuevoDirectorio;
            mensaje = "Directorio cambiado a: " + DireccionActual.getPath();
        } else {
            mensaje = "Directorio no encontrado!";
        }
        return mensaje;
    }

    public String RegresarDirectorio() {

        String mensaje;
        File regresar = DireccionActual.getParentFile();
        if (regresar != null) {
            DireccionActual = regresar;
            mensaje = "Regresando al directorio: " + DireccionActual.getPath();
        } else {
            mensaje = "ERROR no se puede regresar";
        }
        return mensaje;

    }

    public String Dir() {

        StringBuilder contenido = new StringBuilder();
        File[] archivosGeneral = DireccionActual.listFiles();
        if (archivosGeneral != null) {
            for (File archi : archivosGeneral) {
                contenido.append(archi.getName()).append(archi.isDirectory() ? " <DIR>\n" : "\n");
            }
        }
        return contenido.toString();
    }

    public String Date() {
        Calendar fecha = Calendar.getInstance();
        int year = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        return dia + "-" + mes + "-" + year;
    }

    public String time() {
        Calendar tiempo = Calendar.getInstance();
        int hora = tiempo.get(Calendar.HOUR_OF_DAY);
        int minuto = tiempo.get(Calendar.MINUTE);
        int segundo = tiempo.get(Calendar.SECOND);
        return hora + ":" + minuto + ":" + segundo;
    }

    public String Escribir(String direccion, String texto) throws IOException {

        String mensaje;
        File archivo = new File(DireccionActual, direccion);
        if (!archivo.exists()) {
            mensaje = "ERROR: archivo no encontrado";
        } else {
            try (FileWriter escribir = new FileWriter(archivo, true)) {
                escribir.write(texto + "\n");
                mensaje = "Texto escrito en el archivo exitosamente";
            } catch (IOException e) {
                mensaje = "Error al escribir en el archivo: " + e.getMessage();
            }

        }
        return mensaje;

    }

    public String leer(String direccion) throws IOException {

        StringBuilder contenido = new StringBuilder();
        File lee = new File(DireccionActual, direccion);
        if (!lee.exists()) {
            return "Error: Archivo no encotrado";
        }
        try (FileReader leer = new FileReader(lee)) {
            int caracter;
            while ((caracter = leer.read()) != -1) {
                contenido.append((char) caracter);
            }
        } catch (IOException e) {
            return "No se pudo leer el mensaje" + e.getMessage();
        }
        return contenido.toString();
    }

    public String getCarpetaActual() {
        return DireccionActual.getPath();
    }

}
