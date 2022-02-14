/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package juego_ahorcado.instances;

/**
 *
 * @author gustavotorti
 */
public class JuegoAhorcado {

     private char[] palabra;
     private boolean[] encontradas;
     private int letrasEncontradas = 0, jugadas;


     @Override
     public String toString() {
          return "JuegoAhorcado{" + "palabra=" + palabra + ", letras=" + letrasEncontradas + ", jugadas=" + jugadas + '}';
     }

     public JuegoAhorcado() {
     }

     public JuegoAhorcado(char[] palabra, int jugadas) {
          this.palabra = palabra;
          this.jugadas = jugadas;
     }

     public void setPalabra(String palabra) {
          this.palabra = new char[palabra.length()];
          for (int i = 0; i < palabra.length(); i++) {

               this.palabra[i] = palabra.charAt(i);
          }

          this.encontradas = new boolean[palabra.length()];
     }

     public void setEncontradas(int i) {
          this.encontradas[i] = true;
     }

     public void setLetrasEncontradas() {
          this.letrasEncontradas ++;
     }

     public void setJugadas(int jugadas) {
          this.jugadas = jugadas;
     }

     public char[] getPalabra() {
          
          return this.palabra;
     }

     public int getLetrasEncontradas() {
          return letrasEncontradas;
     }
     public boolean[] getEncontradas(){
          return encontradas;
     }

     public int getJugadas() {
          return jugadas;
     }

}
