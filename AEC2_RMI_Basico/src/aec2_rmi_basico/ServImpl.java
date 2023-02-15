/*
AEC2_SSDD: Antonio Luis Ojeda Soto
*/
package aec2_rmi_basico;

/*
Clase que implementa los métodos de  ServInt
 */
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Date;

public class ServImpl extends UnicastRemoteObject implements ServInt {

    public static ArrayList <String> listaRegistrados=new ArrayList<>();
    
    public ServImpl() throws RemoteException {
        super();
    }
    
    // Este metodo registra el apodo en el servidor y devuelve la confirmación de que ha sido registrado
    @Override
    public boolean registro(String apodo) throws RemoteException {
            String registrado = "true";
            listaRegistrados.add(apodo);
            System.out.println("Registrado el nuevo apodo: " + apodo);
            boolean miregistro = Boolean.parseBoolean(registrado);
            return miregistro;
    }
    
    // Este metodo recibe devuelve la hora en la que fué mandado el tweet de un cliente
    @Override
    public String tweet(String apodo, String mensaje) throws RemoteException {
        String hora = new Date().toString();
        System.out.println(apodo + ":" + mensaje);
        return apodo + " tu mensaje: " + mensaje + " ,ha sido publicado a las-> "+ hora;
    }
}
