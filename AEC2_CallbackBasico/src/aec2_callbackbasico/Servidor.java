/*
AEC2_SSDD: Antonio Luis Ojeda Soto
*/
package aec2_callbackbasico;

/*
Se crea el servidor de objeto, es decir la capa de aplicación
en el lado servidor, donde se instanciará el objeto remoto y 
se regisgtrará
*/
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

public class Servidor {

    public static void main(String[] args) {
        
        InputStreamReader teclado = new InputStreamReader(System.in);
        BufferedReader mibuffer = new BufferedReader(teclado);
        
        try{
            System.out.println("Introduzca el numero de puerto de RMIregistry:");
            String puerto = (mibuffer.readLine()).trim();//leemos lo pasado por teclado en el buffer y lo metemos en la variable puerto
            if (puerto.length() == 0)
                puerto = "1099"; // puerto por defecto
            int puertoRMI = Integer.parseInt(puerto); // casteamos el puerto
            
            inicioRegistro(puertoRMI);// llamada al método que inicializa el registro
            ServidorImpl objetoRemoto = new ServidorImpl();
            String URLregistro = "rmi://localhost:" + puerto + "/alarma";// se le asigna una URL
            Naming.rebind(URLregistro, objetoRemoto); // registramos el objeto en el registro en la URL asignada
            System.out.println("Servidor registrado.");
            System.out.println("Servidor esta listo.");
            
        }// end try
        
        catch (Exception re) {
         System.out.println("Excepcion en Servidor: " + re);
        } // end catch
    }
    
    // Este metodo inicia un registro RMI en localhost, en el puerto dado, si no existe ya
    private static void inicioRegistro(int puertoRMI)throws RemoteException{
        try {
            Registry registro = LocateRegistry.getRegistry(puertoRMI);
            registro.list();  // Esta llamada lanza una excepcion si el registro no existe
	}
        
        catch (RemoteException e) {// No existe aun el registro en ese puerto
/**/        System.out.println("No hay RMI registry previo en el puerto " + puertoRMI);

            Registry registro=LocateRegistry.createRegistry(puertoRMI);
         
/**/        System.out.println("RMI registry creado en el puerto " + puertoRMI);
        }
    } // end startRegistry
    
    
    
}
