/*
AEC2_SSDD: Antonio Luis Ojeda Soto
*/
package aec2_callbackbasico;
/*
Implementaciòn de la interfaz del ServidorInter, 
donde los métodos quedarán registrados para que el servidor pueda mandar mensajes
al cliente.
 */

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;


public class ServidorImpl extends UnicastRemoteObject implements ServidorInter  {
    
    public static ArrayList <String> listaApodos=new ArrayList<>(); // Estructura de datos para almacenar los apodos
    public static ArrayList <CallbackInter> listaReceptores=new ArrayList<>(); // ED para almacenar los clientes recpetores
    
        public ServidorImpl() throws RemoteException{
            super();
        }
    
    // en este método registramos el apodo y el callback
    @Override
    public synchronized void registro(String apodo, CallbackInter receptor) throws RemoteException {
        listaApodos.add(apodo);
        System.out.println("Registrado el nuevo apodo: " + apodo);
        if (!(listaReceptores.contains(receptor))) {
            listaReceptores.add(receptor);
        }
    }
    
    // metodo que permite a un cliente de objeto cancelar el registro de su callback y su apodo
    // eliminándose de ambos Arraylist
    public synchronized void eliminarReceptor(String apodo, CallbackInter receptor)throws java.rmi.RemoteException{
        listaApodos.remove(apodo);
        System.out.println("Eliminado el nuevo apodo: " + apodo);    
        if (listaReceptores.contains(receptor)) {
            listaReceptores.remove(receptor);
        } 
    }
    
    // metodo que llama al método de callback "notificaMe" para que este envíelos tweets publicados por un usuario
    // a los demás usuarios que estén registrados.
    @Override
    public String tweet(String apodo, String mensaje) throws RemoteException {
        System.out.println("**************************************\n" + "Callbacks iniciados ---\n");
            for (int i = 0; i < listaReceptores.size(); i++){
                System.out.println("haciendo callback numero "+ i +"\n");
                // convertir objeto del Arraylist en objeto callback
                CallbackInter siguienteReceptor = (CallbackInter)listaReceptores.get(i);
                // invocar metodo de callback
                siguienteReceptor.notificaMe(apodo,mensaje);
            }// end for
        System.out.println("********\n" + "El Servidor ha completado los callbacks *****\n");
        return "LLegó correctamente";
    }
    
}
