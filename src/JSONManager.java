import org.json.*;

public class JSONManager {
	
	
	
	/**
	 * Maneja el JSON proveniente del Cliente cuando este quiere
	 * iniciar un nuevo juego.
	 * @param jObj
	 * @throws JSONException 
	 */
	public void managePlay(JSONObject jObj) throws JSONException {
		
		System.out.println("\nPlay: " + jObj.get("PLAY"));
		
	}
	
	/**
	 * Maneja el JSON proveniente del Cliente cuando este quiere
	 * observar un juego existente.
	 * @param jObj
	 * @throws JSONException 
	 */
	public void manageObserve(JSONObject jObj) throws JSONException {
		
		System.out.println("\nObserve: " + jObj.get("OBSERVE"));
	}
	
	/**
	 * Maneja el JSON proveniente del Cliente cada vez que
	 * este lo solicie para poder graficar.
	 * @param jObj
	 * @throws JSONException 
	 */
	public void manageInput(JSONObject jObj) throws JSONException {
		
		System.out.println("\nCode: " + jObj.get("CODE"));
		System.out.println("\nPosx: " + jObj.get("POSX"));
		System.out.println("\nPosy: " + jObj.get("POSY"));
		
		//Array input serian las teclas de up, right, down, left (horario)
		//int[] input = {0,1,0,1}
		//juego1.recieveJSONInput(int[] input);
		
	}
	
	/**
	 * Maneja el JSON proveniente del Cliente cuando no llega con los Keys preestablecidos.
	 * @param jObj
	 * @throws JSONException
	 */
	public void manageException(JSONObject jObj) throws JSONException {
		
		//Vuelve el JSON un String (Asi llegara al servidor)
    	String jsonException = jObj.toString();
    	
    	System.out.println("jsonException:\n" + jsonException);
		
	}
	
	/**
	 * Genera el topJSON
	 * @return topJSON
	 * @throws JSONException
	 */
	public JSONObject generateJSON() throws JSONException {
		
		//TopJSON
    	JSONObject topJSON = new JSONObject();
		
    	//GameArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray gameArray = generateGameArray();
    	topJSON.put("GAME",gameArray);
    	
    	//DKJrArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray dKJrArray = generateDKJrArray();
    	topJSON.put("DKJR",dKJrArray);
    	
    	//CrocodilesArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray crocodilesArray = generateCrocodilesArray();
    	topJSON.put("CROCODILES",crocodilesArray);
    	
    	//FruitsArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray fruitsArray = generateFruitsArray();
    	topJSON.put("FRUITS",fruitsArray);
    	
    	//FruitPointsArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray fruitPointsArray = generateFruitPointsArray();
    	topJSON.put("FRUITPOINTS",fruitPointsArray);
    	
    	
    	//Retorna el topJSON ya listo para enviar
    	return topJSON;
		
	}
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return gameArray
	 */
	public JSONArray generateGameArray() {
		
		//GameArray
    	JSONArray gameArray = new JSONArray();
		
		return gameArray;
		
	}
	
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return dKJrArray
	 * @throws JSONException 
	 */
	public JSONArray generateDKJrArray() throws JSONException {
		
		//DKJrArray
    	JSONArray dKJrArray = new JSONArray();
    	
    	//TopJSON
    	JSONObject jObj = new JSONObject();
    	
    	
    	//Test
    	jObj.put("frutas","1");
    	jObj.put("dk","2");
    	
    	dKJrArray.put(jObj);
    	
		
		return dKJrArray;
		
	}
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return crocodilesArray
	 */
	public JSONArray generateCrocodilesArray() {
		
		//CrocodilesArray
    	JSONArray crocodilesArray = new JSONArray();
		
		return crocodilesArray;
		
	}
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return fruitsArray
	 */
	public JSONArray generateFruitsArray() {
		
		//FruitsArray
    	JSONArray fruitsArray = new JSONArray();
    	
    	//juego1.getListaF
		
		return fruitsArray;
		
	}
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return fruitPointsArray
	 */
	public JSONArray generateFruitPointsArray() {
		
		//FruitPointsArray
    	JSONArray fruitPointsArray = new JSONArray();
		
		return fruitPointsArray;
		
	}
	
	
	

}
