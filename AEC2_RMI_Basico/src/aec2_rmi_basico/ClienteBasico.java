/*
AEC2_SSDD: Antonio Luis Ojeda Soto
*/
package aec2_rmi_basico;

/*
Invoca a métodos remotos. 1º Busca el objeto remoto y 2º invoca métodos remotos
como si se tratara de un objeto local. Esta invocación provocará envío 
de mensajes y ejecución de métodos en el lado servidor.
*/
import java.io.*;
import java.rmi.*;

public class ClienteBasico {

    public static void main(String[] args) {
        
        InputStreamReader teclado = new InputStreamReader(System.in);
        BufferedReader mibuffer = new BufferedReader(teclado);
        
        try{
            System.out.println("Introduzca la direccion de RMIRegistry:");
            String nombreServidor = mibuffer.readLine();
            if (nombreServidor.length()==0)
                nombreServidor="localhost";// dirección por defecto si no se introduce nada
            
            System.out.println("Introduzca el numero de puerto de RMIregistry:");
            String puerto = mibuffer.readLine();
            if (puerto.length() == 0)
                puerto = "1099";// puerto por defecto si no se introduce nada
            int puertoRMI = Integer.parseInt(puerto);
            
            String URLregistro ="rmi://" + nombreServidor + ":" + puertoRMI + "/hora";
            
            // Busca el objeto remoto (Naming.lookup) y lo envía a un objeto interfaz
            ServInt objetoRemoto = (ServInt)Naming.lookup(URLregistro);
            System.out.println("Busqueda completada");
            
            // pedimos al usuario que introduzca por consola su apodo
            System.out.println("Introduzca su apodo:");
            String apodo = mibuffer.readLine();
            // registra el apodo en el ArrayList invocando el método remoto de "registro"
            System.out.println("Registro del apodo es " + objetoRemoto.registro(apodo)); 
            
            String mensaje1 ="";
            // mientras el usuario no ponga por consola el mensaje "fin" seguirá pudiendo introducir mensajes
            while(!mensaje1.equals("fin")){
                System.out.println("Introduzca su mensaje:");
                mensaje1 = mibuffer.readLine();
                String mensaje2 = objetoRemoto.tweet(apodo,mensaje1);
                System.out.println(mensaje2);
            }
            // nos salimos del bucle cuando ponemosla palabra "fin"
            System.out.println("El cliente se ha salido");
        }
        catch (Exception e) {
            System.out.println("Excepcion en cliente: " + e);
        }
    }
}
