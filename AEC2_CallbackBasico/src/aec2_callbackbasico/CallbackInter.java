/*
AEC2_SSDD: Antonio Luis Ojeda Soto
*/
package aec2_callbackbasico;

// Este metodo remoto es invocado a traves de un callback del cliente.

public interface CallbackInter extends java.rmi.Remote {

    public String notificaMe(String apodo, String mensaje)
      throws java.rmi.RemoteException;
}
