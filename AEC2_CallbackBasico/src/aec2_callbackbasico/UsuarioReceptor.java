/*
AEC2_SSDD: Antonio Luis Ojeda Soto
*/
package aec2_callbackbasico;

import java.io.*;
import java.rmi.*;

public class UsuarioReceptor {

    public static void main(String[] args) {
        try {
            InputStreamReader teclado = new InputStreamReader(System.in);
            BufferedReader mibuffer = new BufferedReader(teclado);
            
            System.out.println("Introduzca la direccion de RMIRegistry:");
            String nombreServidor = mibuffer.readLine();
            if (nombreServidor.length()==0)
                nombreServidor="localhost"; // dirección por defecto
         
            System.out.println("Introduzca el numero de puerto de RMIregistry:");
            String puerto = mibuffer.readLine();
            if (puerto.length() == 0)
                puerto = "1099"; // puerto por defecto
            int puertoRMI = Integer.parseInt(puerto);
         
            String URLregistro ="rmi://" + nombreServidor + ":" + puertoRMI + "/alarma";
            ServidorInter objetoRemoto =(ServidorInter)Naming.lookup(URLregistro);
            System.out.println("Busqueda completada " );
            
            CallbackInter callback = new CallbackImpl(); // creación de instancia callback
            
            System.out.println("Introduzca su apodo:");
            String apodo = mibuffer.readLine();
            
            // registra el apodo y el callback en los ArrayList invocando el método remoto de "registro"
            objetoRemoto.registro(apodo,callback);           
            System.out.println("Está registrado. Ya puede twittear");
            
            String mensaje1 = "";
            // mientras el usuario no ponga por consola el mensaje "fin" seguirá pudiendo introducir tweets
            while(!mensaje1.equals("fin")){
                System.out.println("Introduzca su mensaje:");
                mensaje1 = mibuffer.readLine();
                String mensaje2 = objetoRemoto.tweet(apodo,mensaje1);
                System.out.println(mensaje2);
            }
            
            // eliminación del usuario registrado para poder recibir tweets cuando este usuario quiera
            objetoRemoto.eliminarReceptor(apodo,callback);
            System.out.println("Eliminado registro de callback.");
            
        }
        
        catch (Exception e) {
            System.out.println("Excepcion en Receptor: " + e);
        } // end catch
    }
}
