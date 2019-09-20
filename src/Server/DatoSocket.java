package Server;

import java.io.*; 

public class DatoSocket {

	public int c;
    public String d; 
    
    public DatoSocket() {
    	c = 0;
    	d = "";
    }
    
    public DatoSocket(String dato) {
    	c = dato.length();
    	d = dato;
		// TODO Auto-generated constructor stub
	}

	public void writeObject(java.io.DataOutputStream out)
            throws IOException
        {
            // Se envía la longitud de la cadena + 1 por el \0 necesario en C
            out.writeInt (c+1);
            // Se envía la cadena como bytes.
            out.writeBytes (d);

            // Se envía el \0 del final
            out.writeByte ('\0');
        }
    
    public String readObject(java.io.DataInputStream in)
    	     throws IOException
    	     {
    	         // Se lee la longitud de la cadena y se le resta 1 para eliminar el \0 que nos envía C.
    	         c = in.readInt() - 1;
    	         // Array de bytes auxiliar para la lectura de la cadena.
    	         byte [] aux = null;
    	         aux = new byte[c];  // Se le da el tamaño 

    	         in.read(aux, 0, c); // Se leen los bytes
    	         d = new String (aux); // Se convierten a String

    	        // Se lee el caracter nulo del final
    	         in.read(aux,0,1);
    	         return d;
    	     }
   
}

