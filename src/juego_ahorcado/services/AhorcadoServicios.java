/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package juego_ahorcado.services;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import juego_ahorcado.instances.JuegoAhorcado;

/**
 *
 * @author gustavotorti
 */
public class AhorcadoServicios {

     Scanner read = new Scanner(System.in).useDelimiter("\n");
     private final String[] dibujo = {"_____\n|   \ud83d\ude35\n|  / \\\n|   |\n|  / \\\n|_________",
          "_____\n|   \ud83d\ude16\n|  / \\\n|   |\n|  / \\ \n|_________",
          "_____\n|   \ud83d\ude15\n|  / \\\n|   |\n|  /    \n|_________",
          "_____\n|   \ud83d\ude15\n|  / \\\n|   |\n|       \n|_________",
          "_____\n|   \ud83d\ude1b\n|  / \\\n|    \n|       \n|_________",
          "_____\n|   \ud83d\ude1b\n|  /   \n|    \n|       \n|_________",
          "_____\n|   \ud83d\ude0e\n|      \n|    \n|       \n|_________",
          "_____\n|    \n|      \n|    \n|       \n|_________"};

     public JuegoAhorcado crearJuego() {
          JuegoAhorcado j = new JuegoAhorcado();
          System.out.println("un jugador inicialice el juego (sin que vea el otro)");
          System.out.println("Ingrese la palabra a encontrar");
          String palabra = read.next().toLowerCase();
          j.setPalabra(palabra);
          String intentos = "";
          boolean error = true;
          Pattern patron = Pattern.compile("^[0-9]");

          error = true;
          System.out.println("ingrese la cantidad de jugadas máximas");
          do {
               intentos = read.next();
               if (!patron.matcher(intentos).matches()) {
                    System.out.println("debe ingresar un número entero");
               }
          } while (!patron.matcher(intentos).matches());

          j.setJugadas(Integer.parseInt(intentos));
          clearConsole();
          return j;
     }

     public int longitud(JuegoAhorcado j) {
          return j.getPalabra().length;
     }

     public boolean buscarLetra(JuegoAhorcado j, char L) {
          boolean salida = false;
          for (int i = 0; i < j.getPalabra().length; i++) {
               if (L == j.getPalabra()[i]) {
                    j.setEncontradas(i);
                    j.setLetrasEncontradas();
                    salida = true;
               }
               //intentos(j);
          }
          return salida;
     }

     public void intentos(JuegoAhorcado j, boolean contiene) {
          
          clearConsole();
          if (contiene) {
               System.out.println("la letra está contenida en la palabra");
          } else {
               j.setJugadas(j.getJugadas() - 1);
               System.out.println("la letra no está en la palabra");
          }
          
     }

     public void mostrarPalabra(JuegoAhorcado j) {
         
          System.out.println(dibujo[Math.min(j.getJugadas(), 7)] + "Longitud de la palabra: " + longitud(j));

          for (int i = 0; i < longitud(j); i++) {

               if (j.getEncontradas()[i]) {
                    System.out.print(" " + j.getPalabra()[i]);
               } else {
                    System.out.print(" _");
               }

          }
          System.out.println(String.format("\nNúmero de letras (encontradas/faltantes): (%d/%d) ", 
                  j.getLetrasEncontradas(), longitud(j) - j.getLetrasEncontradas()));
          System.out.println((j.getJugadas() == 0) ? "no le quedan más oportunidades"
                  + " \nFIN DEL JUEGO" : "le quedan " + j.getJugadas() + " oportunidades");
     }

     public boolean encontradas(JuegoAhorcado j, char L) {

          return buscarLetra(j, L);
     }

     public void juego() {
          //limpio la pantalla
          clearConsole();
          //iniciamos el juego
          JuegoAhorcado j = crearJuego();
          Pattern letras = Pattern.compile("[a-z|A-Z|á-ñ|Ñ|é]");
          System.out.println("el \"OTRO\" jugador intente resolverlo");
          
          while (j.getLetrasEncontradas() != longitud(j) && j.getJugadas() > 0) {
               if (j.getLetrasEncontradas() != longitud(j)) {
                    mostrarPalabra(j);
                    System.out.println("Ingrese una letra:");
                    char caracter = '_';
                    String eval = "";
                    do {  
                         
                         while (!read.hasNext(letras)){
                         String input = read.next();
                              System.out.printf("\"%s\" no es una letra evaluable .%n", input);
                         }
                         caracter = read.next().toLowerCase().charAt(0);
                         
                         eval = String.valueOf(caracter);
                         
                    } while (!letras.matcher(eval).matches());
                    intentos(j, (encontradas(j, caracter)));
                    
               }

          }
          
          mostrarPalabra(j);
          System.out.println((j.getLetrasEncontradas() != longitud(j) ? "\ud83d\ude30" : "usted ha ganado, la palabra es: " + Arrays.toString(j.getPalabra())+"\ud83d\ude1c")+"\n\n\n");

     }

     public void clearConsole() {
          try {
               final String os = System.getProperty("os.name");
               if (os.contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
               } else {
                    //Runtime.getRuntime().exec("clear");
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
               }
          } catch (Exception e) {
               System.out.println(e);
          }
     }

}
