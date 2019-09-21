
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
	static Game gameA = new Game();
	static Game gameB = new Game();
	
	//Instancia JSONManager para obtener los JSON's
	static JSONManager jsonManagerA = new JSONManager(gameA);
	static JSONManager jsonManagerB = new JSONManager(gameB);
	
	
	public static void manageInput(String code, int x, int y) {
		
	}
	
	
	/**
	 * Corre el Servidor.
	 * @throws JSONException
	 */
	public static void runServer(String jsonTest) throws JSONException {
		
		JSONObject jsonForClient = new JSONObject();
    	
		/*TESTING*/
		//Esto seria el buffer del servidor que recibe el json del Cliente
    	String buffer = jsonTest;
    	
    	
		JSONObject jObjFromClient = new JSONObject(buffer);
		
		//jObjFromClient.has("PLAY");
		
		//Para iniciar a jugar
		if (jObjFromClient.has("PLAY") == true) {
			jsonManagerA.managePlay(jObjFromClient);
		}
		
		//Para observar un juego
		else if (jObjFromClient.has("OBSERVE") == true) {
			jsonManagerA.manageObserve(jObjFromClient);
		}
		
		//Para actualizar el cliente
		else if (jObjFromClient.has("CODE") == true && jObjFromClient.has("POSX") == true && jObjFromClient.has("POSY") == true) {
			jsonManagerA.manageInput(jObjFromClient);
		}
		
		else {
			jsonManagerA.manageException(jObjFromClient);
		}
		
		
		
		//Vuelve el JSON un String (Asi llegara al servidor)
    	String jsonForClientString = jsonForClient.toString();
    	
    	System.out.println("JSON proveniente del servidor:\n" + jsonForClientString);
    	
    	//Crea el TopJSON con toda la informacion del juego
    	//jsonForClient = jsonManager.generateJSON();
		
				
	}
	
	
	static void runServer() throws IOException {
		
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
		
		
		System.out.println("\n\nJSON from Client: " + buffer);
		
		String response = "notjson";
		
  	
		JSONObject jObjFromClient = new JSONObject(buffer);
		
		//Para iniciar a jugar
		if (jObjFromClient.has("PLAY") == true) {
			response = jsonManagerA.managePlay(jObjFromClient);
		}
		
		//Para observar un juego
		else if (jObjFromClient.has("OBSERVE") == true) {
			response = jsonManagerA.manageObserve(jObjFromClient);
		}
		
		//Para actualizar el cliente
		else if (jObjFromClient.has("CODE") == true && 
							jObjFromClient.has("INPUT") == true ) {
			response = jsonManagerA.manageInput(jObjFromClient);
		}
				
		else {
			jsonManagerA.manageException(jObjFromClient);
		}
				
		
		
		return response;
		
		
	}
	
	
	
	
	public static void main (String [] args) throws JSONException, IOException
    { 	
    	
    	   	
    	runServer();
    	
    }

}
