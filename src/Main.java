
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
				
				
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 		
						
				//Crea un DatoSocket para manejar lo que llega del Cliente
				DatoSocket dato = new DatoSocket();
				//Obtiene el JSON entrante al Servidor
				String jsonIn = dato.readObject(dis);
				
				//Obtiene el JSON saliente al Cliente al haber manejado jsonIn
				String jsonOut = manageJSONFromClient(jsonIn);
						

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
	 * Maneja el JSON proveniente del Cliente y
	 * envia el json correspondiente devuelta al servidor
	 * para que este sea enviado al Cliente.
	 * @param buffer
	 * @return jsonString
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
							jObjFromClient.has("UPDATE") == true ) {
			response = jsonManager.manageInput(jObjFromClient);
		}
		
		else {
			jsonManager.manageException(jObjFromClient);
		}
		
		return response;
		
	}
	
	
	
	/**
	 * Main de DonCEy Kong Jr Server.
	 * @param args
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void main (String [] args) throws JSONException, IOException
    { 	
		/////TEST PURPOSE ONLY/////
		jsonManager.gameA.setState("StateTestA");
		jsonManager.gameA.setScore(6900);
		jsonManager.gameA.setLevel(17);
		jsonManager.gameA.setLives(5);
		
		jsonManager.gameB.setState("StateTestB");
		jsonManager.gameB.setScore(4200);
		jsonManager.gameB.setLevel(9);
		jsonManager.gameB.setLives(3);
		
		Crocodile c1 = new Crocodile("red",45,654);
		Crocodile c2 = new Crocodile("blue",510,52);
		Crocodile c3 = new Crocodile("red",342,354);
		Crocodile c4 = new Crocodile("blue",153,197);
		
		Fruit f1 = new Fruit("apple",1000,614,23);
		Fruit f2 = new Fruit("pear",3000,312,143);
		Fruit f3 = new Fruit("grape",6000,81,352);
		Fruit f4 = new Fruit("peach",10000,302,223);
		
		Crocodile[] cArray = {c1,c2,c3,c4};
		Fruit[] fArray = {f1,f2,f3,f4};
		
		gameA.setCrocodilesList(cArray);
		gameA.setFruitsList(fArray);
		
		gameB.setCrocodilesList(cArray);
		gameB.setFruitsList(fArray);
		/////TEST PURPOSE ONLY/////
		
		
		
		
		
    	//Corre el servidor
    	runServer();
    	
    }

}
