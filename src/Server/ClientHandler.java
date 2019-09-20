package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;


//ClientHandler class 
public class ClientHandler extends Thread 
{ 
	DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
	DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
	final DataInputStream dis; 
	final DataOutputStream dos; 
	final Socket s; 
	
	

	// Constructor 
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
	{ 
		this.s = s; 
		this.dis = dis; 
		this.dos = dos; 
	} 

	public String readMessage() throws IOException {
		DatoSocket dato = new DatoSocket();
		String respuesta = dato.readObject(dis);
		return respuesta;
	}
	
	public void sendMessage(String message) throws IOException {
		DatoSocket dato = new DatoSocket(message);
		dato.writeObject(dos);
	}
	
	@Override
	public void run() 
	{ 
		String received; 
		String toreturn; 
		Scanner input = new Scanner(System.in);
		
		while (true) 
		{ 
			try { 
				System.out.print("[Server] >> ");
				String message = input.next();
				// El dato a enviar
				
				if (message.equals("bye"))
				{
					break;
				}
				else {
					
					sendMessage(message);
					//waits for an answer:
					String resp = readMessage();
					System.out.println("\n[Cliente] >> " + resp);
					
					}
				}
				 // Se envia el dato
				
			 catch (IOException e) { 
				e.printStackTrace(); 
			} 
		} 
		
		try
		{ 
			// closing resources 
			this.dis.close(); 
			this.dos.close(); 
			
		}catch(IOException e){ 
			e.printStackTrace(); 
		} 
	} 


}
