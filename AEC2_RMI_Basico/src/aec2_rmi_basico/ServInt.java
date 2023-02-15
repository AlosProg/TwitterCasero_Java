/*
AEC2_SSDD: Antonio Luis Ojeda Soto
*/
package aec2_rmi_basico;
/*
Interfaz del servidor con dos métodos que luego deberán ser 
implementados.
 */
import java.rmi.*;

public interface ServInt extends Remote {
    // Este metodo remoto pide como argumento un apodo para poder registrarse en el servidor
    public boolean registro(String apodo) 
        throws java.rmi.RemoteException;
    
    // Este metodo remoto pide como argumento un apodo y un mensaje del cliente 
    public String tweet (String apodo, String mensaje)
        throws java.rmi.RemoteException;
}
