
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Main {
	
	
	
	/**
	 * Corre el Servidor
	 * @throws JSONException
	 */
	public static void runServer(String jsonTest) throws JSONException {
		
		//To JSON again
    	
		/*TESTING*/
		//Esto seria el buffer del servidor
    	String jsonStringFromClient = jsonTest;
    	
    	
		JSONObject jObjFromClient = new JSONObject(jsonStringFromClient);
		
		if (jObjFromClient.get("POSX") != "" && jObjFromClient.get("POSY") != "") {
			System.out.println("\nPosx: " + jObjFromClient.get("POSX"));
			System.out.println("\nPosy: " + jObjFromClient.get("POSY"));
		}
		
				
	}
	
	
	
	
	public static void main (String [] args) throws JSONException
    {
    
		//Instancia JSONManager para obtener los JSON's
    	JSONManager jsonManager = new JSONManager();
    	
    	
    	//Representa el JSONProveniente del Cliente
    	JSONObject jsonFromClient = new JSONObject();
    	jsonFromClient.put("POSX","350");
    	jsonFromClient.put("POSY","650");
    	
    	String jsonFromClientString = jsonFromClient.toString();
    	
    	System.out.println(jsonFromClientString);
    	
    	//Se corre el servidor
    	runServer(jsonFromClientString);
    	
    	
    }

}
