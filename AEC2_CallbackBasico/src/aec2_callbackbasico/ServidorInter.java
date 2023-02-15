/*
AEC2_SSDD: Antonio Luis Ojeda Soto
*/
package aec2_callbackbasico;
/*
Interfaz del servidor con dos métodos que luego deberán ser 
implementados.
 */

import java.rmi.*;

public interface ServidorInter extends Remote {
    
    // Este metodo remoto pide como argumento un apodo y un callback para poder registrarlos.
    public void registro(String apodo, CallbackInter receptor) 
        throws java.rmi.RemoteException;
    
    // Este método remoto pide como argumento un apodo y un callback para porder ser eliminado.
    public void eliminarReceptor(String apodo, CallbackInter receptor) 
        throws java.rmi.RemoteException;
    
    // Este metodo remoto pide como argumento un apodo y un mensaje del cliente para poder enviárselo
    // a los demás usuarios registrados llamando al método notificaMe de callbacks.
    public String tweet (String apodo, String mensaje)
        throws java.rmi.RemoteException;
}
