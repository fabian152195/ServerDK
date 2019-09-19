import org.json.*;

public class JSONManager {
	
	public void runServer() throws JSONException {
		
		String jsonFromClient = "";
		
		JSONObject jObj = new JSONObject(jsonFromClient);
		
		//Imprimiria la posicion en x que viene con el key "posx"
		System.out.println(jObj.get("posx"));
				
	}

}
