/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5_file_progra2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author royum
 */
public class Consola extends JFrame {

    private final JTextArea areaEntrada;
    private final Funciones_Archivos funcionesArchivos;
    private String prompt;

    public Consola() {                              //AQUI PONER SU DIRECTORIO INGENIERO ERICK.
       
        funcionesArchivos = new Funciones_Archivos("C:\\Users\\royum\\OneDrive\\Documentos\\NetBeansProjects\\Lab5_File_Progra2");
        prompt = funcionesArchivos.getCarpetaActual() + "> ";

        setTitle("Simulador CMD");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //aqui area de entrada (con prompt y colores similares al CMD)
        areaEntrada = new JTextArea();
        areaEntrada.setEditable(true);
        areaEntrada.setBackground(Color.BLACK);
        areaEntrada.setForeground(Color.LIGHT_GRAY);
        areaEntrada.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaEntrada.append("Microsoft Windows [Versión 10.0.22621.521]\n");
        areaEntrada.append("(c) Microsoft Corporation. All rights reserved. MADE FOR Roy Umaña and Wilmer Colindres\n\n");
        areaEntrada.append(prompt);

        JScrollPane scrollEntrada = new JScrollPane(areaEntrada);
        add(scrollEntrada, BorderLayout.CENTER);

        //aqui detecta la tecla Enter para ejecutar comandos
        areaEntrada.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();  // Previene salto de linea adicional
                    ejecutarComando();
                }
            }
        });

        setVisible(true);

    }

    private void ejecutarComando() {
        try {
            // Obtener el comando introducido después del prompt
            int promptPosition = areaEntrada.getText().lastIndexOf(prompt) + prompt.length();
            String comando = areaEntrada.getText().substring(promptPosition).trim();

            String respuesta = "";

            // Comandos específicos
            if (comando.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if (comando.toLowerCase().startsWith("cd")) {
                respuesta = funcionesArchivos.Cd(comando.substring(2).trim());
            } else if (comando.equalsIgnoreCase("dir")) {
                respuesta = funcionesArchivos.Dir();
            } else if (comando.equalsIgnoreCase("date")) {
                respuesta = funcionesArchivos.Date();
            } else if (comando.equalsIgnoreCase("time")) {
                respuesta = funcionesArchivos.time();
            } else if (comando.toLowerCase().startsWith("mkdir")) {
                respuesta = funcionesArchivos.mkdir(comando.substring(5).trim());
            } else if (comando.toLowerCase().startsWith("mfile")) {
                respuesta = funcionesArchivos.mfile(comando.substring(5).trim());
            } else if (comando.toLowerCase().startsWith("rm")) {
                respuesta = funcionesArchivos.rm(comando.substring(2).trim());
            } else if (comando.toLowerCase().startsWith("wr")) {
                String[] partes = comando.substring(2).split(" ", 2);
                if (partes.length == 2) {
                    String archivo = partes[0].trim(); // Nombre del archivo
                    String texto = partes[1].trim();   // Texto a escribir
                    respuesta = funcionesArchivos.Escribir(archivo, texto);
                } else {
                    respuesta = "Comando incorrecto. Uso: wr <archivo> <texto>";
                }
            } else if (comando.toLowerCase().startsWith("rd")) {
                respuesta = funcionesArchivos.leer(comando.substring(2).trim());
            } else if (comando.equalsIgnoreCase("...")) {
                respuesta = funcionesArchivos.RegresarDirectorio();
            } else {
                respuesta = "'" + comando + "' no se reconoce como un comando interno o externo.";
            }

            // Mostrar la respuesta del comando
            areaEntrada.append("\n" + respuesta + "\n");

            // Actualizar el prompt
            prompt = funcionesArchivos.getCarpetaActual() + "> ";
            areaEntrada.append(prompt);

            // Asegura que el cursor siempre esté al final
            areaEntrada.setCaretPosition(areaEntrada.getDocument().getLength());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
