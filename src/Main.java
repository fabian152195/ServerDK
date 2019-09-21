
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Server.*;



public class Main {
	
	//Unicas dos instancias posibles de un Game
	static Game gameA = new Game("A");
	static Game gameB = new Game("B");
	
	//Instancia JSONManager para obtener los JSON's
	static JSONManager jsonManager = new JSONManager(gameA,gameB);
	
	
	/**
	 * Corre el Servidor.
	 * @throws IOException
	 */
	static void runServer() throws IOException {
		
		// server is listening on port 5056 
		ServerSocket ss = new ServerSocket(5056);
		
		System.out.println("DonCEy Kong Jr. Server Running!\n"); 
		
		// running infinite loop for getting 
		// client request 
		while (true) 
		{ 
			Socket s = null;

			try
			{
				
				// socket object to receive incoming client requests 
				s = ss.accept(); 
						
				//System.out.println("A new client is connected : " + s); 
				
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
						
				//System.out.println("Assigning new thread for this client");
						
						
				DatoSocket dato = new DatoSocket();
				String res = dato.readObject(dis);
				String jsonOut = manageJSONFromClient(res);
						

				// create a new thread object 
				Thread t = new ClientHandler(s, dis, dos, jsonOut);
								

				// Invoking the start() method 
				t.start();
						
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			}
				
		} 
		
	}
	
	/**
	 * Maneja el JSON proveniente del CLiente
	 * Envia el json correspondiente devuelta al servidor
	 * para que este sea enviado al Cliente.
	 * @param buffer
	 * @return
	 * @throws JSONException
	 */
	public static String manageJSONFromClient(String buffer) throws JSONException {
		
		
		System.out.println("[+] Client >> " + buffer + "\n");
		
		String response = "notjson";
		
  	
		JSONObject jObjFromClient = new JSONObject(buffer);
		
		//Para iniciar a jugar
		if (jObjFromClient.has("PLAY") == true) {
			response = jsonManager.managePlay(jObjFromClient);
		}
		
		//Para observar un juego
		else if (jObjFromClient.has("OBSERVE") == true) {
			response = jsonManager.manageObserve(jObjFromClient);
		}
		
		//Para actualizar el cliente
		else if (jObjFromClient.has("CODE") == true && 
							jObjFromClient.has("INPUT") == true ) {
			response = jsonManager.manageInput(jObjFromClient);
		}
				
		else {
			jsonManager.manageException(jObjFromClient);
		}
				
		
		
		return response;
		
		
	}
	
	
	
	
	public static void main (String [] args) throws JSONException, IOException
    { 	
    	
    	//Corre el servidor
    	runServer();
    	
    }

}
