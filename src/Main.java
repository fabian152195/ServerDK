
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
	
	public static void main (String [] args) throws JSONException
    {
        
    	String jsonString;
		
    	JSONObject obj = new JSONObject();
    	/*put(key,value);*/
    	obj.put("posx","350");
    	obj.put("posy","650");
    	
    	//List -> Array
    	JSONArray list = new JSONArray();
    	list.put("cocodrilos");
    	list.put("frutas");
    	list.put("dk");
    	list.put("test");
    	
    	obj.put("movibles",list);
    	
    	jsonString = obj.toString();
    	
    	System.out.println(obj);
    	
    	
    	
    	
    	//To JSON again
    	
    	String jsonFromClient = jsonString;
		
		JSONObject jObjtoSend = new JSONObject(jsonFromClient);
		
		System.out.println("\n\nPosx to send: " + jObjtoSend.get("posx"));
    	
    }

}
