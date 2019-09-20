
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
	
	
	
	//Instancia JSONManager para obtener los JSON's
	static JSONManager jsonManager = new JSONManager();
	
	
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
			jsonManager.managePlay(jObjFromClient);
		}
		
		//Para observar un juego
		else if (jObjFromClient.has("OBSERVE") == true) {
			jsonManager.manageObserve(jObjFromClient);
		}
		
		//Para actualizar el cliente
		else if (jObjFromClient.has("CODE") == true && jObjFromClient.has("POSX") == true && jObjFromClient.has("POSY") == true) {
			jsonManager.manageInput(jObjFromClient);
		}
		
		else {
			jsonManager.manageException(jObjFromClient);
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
	
	
	
	
	public static void main (String [] args) throws JSONException, IOException
    {
    	/*TEST */
    	//Representa el JSONProveniente del Cliente para Iniciar un Juego
    	JSONObject jsonFromClientPlay = new JSONObject();
    	//Ingresa las posiciones que vendran desde el Cliente
    	jsonFromClientPlay.put("PLAY","Request");
    	//Vuelve el JSON un String (Asi llegara al servidor)
    	String jsonFromClientStringPlay = jsonFromClientPlay.toString();
    	
    	//Representa el JSONProveniente del Cliente para Observar un juego
    	JSONObject jsonFromClientObserve = new JSONObject();
    	//Ingresa las posiciones que vendran desde el Cliente
    	jsonFromClientObserve.put("OBSERVE","A1B2C3");
    	//Vuelve el JSON un String (Asi llegara al servidor)
    	String jsonFromClientStringObserve = jsonFromClientObserve.toString();
    	
    	//Representa el JSONProveniente del Cliente para Actualizar el cliente
    	JSONObject jsonFromClientUpdate = new JSONObject();
    	//Ingresa las posiciones que vendran desde el Cliente
    	jsonFromClientUpdate.put("CODE","A1B2C3");
    	jsonFromClientUpdate.put("POSX","350");
    	jsonFromClientUpdate.put("POSY","650");
    	//Vuelve el JSON un String (Asi llegara al servidor)
    	String jsonFromClientStringUpdate = jsonFromClientUpdate.toString();
    	
    	System.out.println("JSON from Client -> Play:\n" + jsonFromClientStringPlay);
    	//Se corre el servidor
    	runServer(jsonFromClientStringPlay);
    	
    	System.out.println("JSON from Client -> Observe:\n" + jsonFromClientStringObserve);
    	//Se corre el servidor
    	runServer(jsonFromClientStringObserve);
    	
    	System.out.println("JSON from Client -> Update:\n" + jsonFromClientStringUpdate);
    	//Se corre el servidor
    	runServer(jsonFromClientStringUpdate);

    	
    	
    	
    	
    	
    	
    	runServer();
    	
    }

}
