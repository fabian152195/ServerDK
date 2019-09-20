package dkServer;

//Java implementation of Server side 
//It contains two classes : Server and ClientHandler 
//Save file as Server.java 

import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

//Server class 
public class Server 
{ 
	
	
	public static void main(String[] args) throws IOException 
	{ 
		// server is listening on port 5056 
		ServerSocket ss = new ServerSocket(5056); 
		
		// running infinite loop for getting 
		// client request 
		while (true) 
		{ 
			Socket s = null; 
			
			try
			{
				System.out.println("Server listening..."); 
				// socket object to receive incoming client requests 
				s = ss.accept(); 
				
				System.out.println("A new client is connected : " + s); 
				
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
				
				System.out.println("Assigning new thread for this client"); 

				// create a new thread object 
				Thread t = new ClientHandler(s, dis, dos); 

				// Invoking the start() method 
				t.start(); 
				
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
		} 
	} 
} 

//ClientHandler class 
class ClientHandler extends Thread 
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
