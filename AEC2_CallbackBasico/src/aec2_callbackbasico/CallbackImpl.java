/*
AEC2_SSDD: Antonio Luis Ojeda Soto
*/
package aec2_callbackbasico;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Date;

/*
Esta clase implementa el interfaz remoto IntCallbackBasico
 */
public class CallbackImpl extends UnicastRemoteObject implements CallbackInter {
    
    public CallbackImpl() throws RemoteException {
        super( );
    }
    
    // método que notifica a los demás usuarios el mensaje enviado y el apodo de quién lo envía.
    @Override
    public String notificaMe(String apodo, String mensaje) throws RemoteException {
        String hora = new Date().toString();
        String returnMensaje = apodo + " publica el tweet: " + mensaje + " a las "+hora;
        System.out.println(returnMensaje);
        return returnMensaje;
    }
    
    
}
