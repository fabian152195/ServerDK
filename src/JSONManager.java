
import org.json.*;

public class JSONManager {
	
	Game gameA;
	Game gameB;
	
	
	///Constructor
	
	JSONManager(Game gameA, Game gameB){
		this.gameA = gameA;
		this.gameB = gameB;
	}
	
	
	///Funciones
	
	/**
	 * Genera un codigo para el juego.
	 * @param game
	 */
	public void generateCode(Game game) {
		//Generar codigo aleatorio
		
		String code = "000" + game.getId();
		
		
		if (game.getId() == gameA.getId()) {
			this.gameA.setCode(code);
		} else if (game.getId() == gameB.getId()) {
			this.gameB.setCode(code);
		} else {
			System.out.println("Fail code generator");
		}
		
		
		
	}
	
	/**
	 * Devuelve el juego al cual el codigo le pertenece.
	 * @param code
	 * @return game
	 */
	public Game getGame(String code) {
		
		Game game;
		
		if (gameA.getCode() == code) {
			game = gameA;
			System.out.println("Got GameA");
		} else if (gameB.getCode() == code) {
			game = gameB;
			System.out.println("Got GameB");
		} else {
			game = null;
			System.out.println("Got null");
		}
		
		return game;
	}
	
	
	/**
	 * Maneja el JSON proveniente del Cliente cuando este quiere
	 * iniciar un nuevo juego.
	 * @param jObj
	 * @throws JSONException 
	 */
	public String managePlay(JSONObject jObj) throws JSONException {
		
		String permition = "0";
		
		if (gameA.isPlaying && gameB.isPlaying) {
			//Cuando los dos juegos estan ocupados
			permition = "-1";
		} else {
			if (gameA.isPlaying == false) {
				//Cuando el juego A esta desocupado
				
				//Se cambia el boolean del juego A
				gameA.setPlaying(true);
				//Se crea un codigo para este
				generateCode(gameA);
				//Se enviara este codigo
				permition = gameA.getCode();
			}
			else {
				//Cuando el juego B esta desocupado
				
				//Se cambia el boolean del juego B
				gameB.setPlaying(true);
				//Se crea un codigo para este
				generateCode(gameB);
				//Se enviara este codigo
				permition = gameB.getCode();
				
			}
		}
		
		//Se crea un objeto JSON
		JSONObject toClient = new JSONObject();
		//Se agrega el resultado con el key
		toClient.put("PLAY", permition);
		//Se convierte el nuevo JSON a String
		String toClientString = toClient.toString();
		
		System.out.println("[+] Server >> " + toClientString + "\n\n");
		
		return toClientString;
		
	}
	
	/**
	 * Maneja el JSON proveniente del Cliente cuando este quiere
	 * observar un juego existente.
	 * @param jObj
	 * @throws JSONException 
	 */
	public String manageObserve(JSONObject jObj) throws JSONException {
		
		String permition;
		
		//Codigo del juego al cual se quiere observar
		String code = jObj.getString("OBSERVE");
		
		String codeA = gameA.getCode();
		String codeB = gameB.getCode();
		
		System.out.println("CLIENTCODE: ." + code + ".");
		System.out.println("GAMEACODE: ." + codeA + ".");
		System.out.println("GAMEBCODE: ." + codeB + ".");
		
		//JSONObject jsonObject = new JSONObject(gameA.getCode());
		
		
		
		System.out.println(codeA == codeA);

			
		if (codeA.equals(code)) {
				
			if (gameA.isObservable()) {
				//Si se puede observar
				permition = "1";
				//Agrega un observador a la cantidad de observadores del juego A
				gameA.setObservers(gameA.getObservers() + 1);
			}
			else {
				//No se puede observar,
				//deben haber ya 2 observadores
				permition = "0";
			}	
				
		} else if (codeB.equals(code)) {
				
			if (gameB.isObservable()) {
				//Si se puede observar
				permition = "1";
				//Agrega un observador a la cantidad de observadores del juego B
				gameB.setObservers(gameB.getObservers() + 1);
			}
			else {
				//No se puede observar,
				//deben haber ya 2 observadores
				permition = "0";
			}	
			
		} else {
			permition = "-1";
		}
		
		//Se crea un objeto JSON
		JSONObject toClient = new JSONObject();
		//Se agrega el resultado con el key
		toClient.put("OBSERVE", permition);
		//Se convierte el nuevo JSON a String
		String toClientString = toClient.toString();
				
		System.out.println("[+] Server >> " + toClientString + "\n\n");
		
		return toClientString;

	}
	
	/**
	 * Maneja el JSON proveniente del Cliente cada vez que
	 * este lo solicie para poder graficar.
	 * @param jObj
	 * @throws JSONException 
	 */
	public String manageInput(JSONObject jObj) throws JSONException {
		
		String response = "|";
		
		System.out.println("\nCode: " + jObj.get("CODE") + "\n");
		System.out.println("\nInput: " + jObj.get("INPUT"));
		
		JSONArray jsonArray = (JSONArray) jObj.get("INPUT");
		
		//List<String> list = new ArrayList<String>();
		for (int i=0; i<jsonArray.length(); i++) {
		    response += " " + jsonArray.getString(i) + " ";
		}
		
		response += "|";
		
		
		
		return response;
		
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
