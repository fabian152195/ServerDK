
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
	 *//*
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
	}*/
	
	
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
		
		System.out.println("\nCode: " + jObj.get("CODE"));
		System.out.println("\nInput: " + jObj.get("UPDATE"));
		
		//Contendra la respuesta o el JSON saliente hacia el Cliente
		String updateArray;
		//Contendra el JSONArray proveniente del manager
		JSONObject updateArrayJSON  = new JSONObject();
		//Inicializacion de keyInputArray temporal
		int[] keyInputTemp = {0,0,0,0};
		//Contendra el keyPress Actual que se estara leyendo del JSON
		String actualKey;
		
		//Obtiene el JSONArray proveniente del Cliente con el input
		//de los 4 keyPress
		JSONArray jsonArray = (JSONArray) jObj.get("UPDATE");
		
		//Recorre jsonArray obteniendo todas las posiciones de este
		//Para guardalos en keyInputTemp
		for (int i=0; i<jsonArray.length(); i++) {
			//Guarda un keyPress como String
			actualKey = jsonArray.getString(i);
			//Convierte el keyPress en un int y lo guarda en el array temporal
			keyInputTemp[i] = Integer.parseInt(actualKey);
		}
		
		//Codigo del juego al cual se quiere observar
		String code = jObj.getString("CODE");
		
		//Codigos de los juegos actuales
		String codeA = gameA.getCode();
		String codeB = gameB.getCode();
		
		
		if (codeA.equals(code)) {
			
			gameA.setKeyInput(keyInputTemp);
			gameA.update();
			updateArrayJSON = generateUpdateJSON(code);
				
		} else if (codeB.equals(code)) {
				
			gameB.setKeyInput(keyInputTemp);
			gameB.update();
			updateArrayJSON = generateUpdateJSON(code);
			
		} else {
			
			//Se genera un array de Error
			JSONArray errorArray = new JSONArray();
			//Se ingresa un "-1" que sera interpretado por el cliente
			errorArray.put("-1");
			//Se ingresa el JSONarray al JSON saliente
			updateArrayJSON.put("ERROR", errorArray);
		}
		
		
		//Se crea un objeto JSON
		JSONObject toClient = new JSONObject();
		
		//Se agrega updateArrayJSON con el key
		toClient.put("UPDATE", updateArrayJSON);
		//Se convierte el nuevo JSON a String
		String toClientString = toClient.toString();
		

		System.out.println("[+] Server >> " + toClientString + "\n\n");
				
		return toClientString;
		
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
	public JSONObject generateUpdateJSON(String code) throws JSONException {
		
		//UpdateJSON
    	JSONObject updateJSON = new JSONObject();		
		
    	//GameArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray gameArray = generateGameArray(code);
    	updateJSON.put("GAME",gameArray);
    	
    	//DKJrArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray dKJrArray = generateDKJrArray(code);
    	updateJSON.put("DKJR",dKJrArray);
    	
    	//CrocodilesArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray crocodilesArray = generateCrocodilesArray(code);
    	updateJSON.put("CROCODILES",crocodilesArray);
    	
    	//FruitsArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray fruitsArray = generateFruitsArray(code);
    	updateJSON.put("FRUITS",fruitsArray);
    	
    	//FruitPointsArray
    	//Crea e ingresa el Array al TopJSON
    	JSONArray fruitPointsArray = generateFruitPointsArray(code);
    	updateJSON.put("FRUITPOINTS",fruitPointsArray);
    	
    	
    	//Retorna UpdateJSON ya listo para enviar
    	return updateJSON;
		
	}
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return gameArray
	 */
	public JSONArray generateGameArray(String code) {
		
		//Datos necesarios para GameArray
		String state;
		String score;
		String level;
		String lives;
		
		//Codigos de los juegos actuales
		String codeA = gameA.getCode();
		String codeB = gameB.getCode();
				
		//Verifica con el codigo cual es el juego del cual debe
		//obtener la informacion
		if (codeA.equals(code)) {
			
			//Se obtienen las datos necesarios de gameA
			state = gameA.getState();
			score = Integer.toString(gameA.getScore());
			level = Integer.toString(gameA.getLevel());
			lives = Integer.toString(gameA.getLives());
								
		} else { //(codeB.equals(code)) {
								
			//Se obtienen las datos necesarios de gameB
			state = gameB.getState();
			score = Integer.toString(gameB.getScore());
			level = Integer.toString(gameB.getLevel());
			lives = Integer.toString(gameB.getLives());	
			
		}
		
		//GameArray
    	JSONArray gameArray = new JSONArray();
		
    	//Se agregan los datos necesarios a gameArray
		gameArray.put(state);
		gameArray.put(score);
		gameArray.put(level);
		gameArray.put(lives);
		
		return gameArray;
		
	}
	
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return dKJrArray
	 * @throws JSONException 
	 */
	public JSONArray generateDKJrArray(String code) throws JSONException {
		
		//Datos necesarios para DKJrArray
		String state;
		String posX;
		String posY;
				
		//Codigos de los juegos actuales
		String codeA = gameA.getCode();
		String codeB = gameB.getCode();
						
		//Verifica con el codigo cual es el juego del cual debe
		//obtener la informacion
		if (codeA.equals(code)) {
					
			//Se obtienen las datos necesarios de DKJr de gameA
			state = gameA.getDkjr().getState();
			posX = Integer.toString(gameA.getDkjr().getPosX());
			posY = Integer.toString(gameA.getDkjr().getPosY());
										
		} else { //(codeB.equals(code)) {
										
			//Se obtienen las datos necesarios de DKJr de gameB
			state = gameB.getDkjr().getState();
			posX = Integer.toString(gameB.getDkjr().getPosX());
			posY = Integer.toString(gameB.getDkjr().getPosY());
					
		}
				
		//GameArray
		JSONArray gameArray = new JSONArray();
				
		//Se agregan los datos necesarios a gameArray
		gameArray.put(state);
		gameArray.put(posX);
		gameArray.put(posY);
				
		return gameArray;
		
	}
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return crocodilesArray
	 */
	public JSONArray generateCrocodilesArray(String code) {
		
		//Lista de Cocodrilos Temporal
		Crocodile[] tempCrocList;
		
		//CrocodilesArray
		JSONArray crocodilesArray = new JSONArray();
		
		//Datos necesarios para CrocodilesArray
		String color;
		String posX;
		String posY;
		
		//Codigos de los juegos actuales
		String codeA = gameA.getCode();
		String codeB = gameB.getCode();
				
		//Verifica con el codigo cual es el juego del cual debe
		//obtener la informacion
		if (codeA.equals(code)) {
			
			//Se obtiene la lista de Cocodrlos de gameA
			tempCrocList = gameA.getCrocodilesList();
												
		} else { //(codeB.equals(code)) {
												
			//Se obtiene la lista de Cocodrlos de gameB
			tempCrocList = gameB.getCrocodilesList();
		}
		
		//Se itera por la lista de cocodrilos para guardar cada uno de ellos
		for (int i = 0; i < tempCrocList.length;i++) {
			
			//tempArray
			JSONArray tempArray = new JSONArray();
			
			//Se obtienen los atributos del cocodrilo necesarios
			color = tempCrocList[i].getColor();
			posX = Integer.toString(tempCrocList[i].getPosX());
			posY = Integer.toString(tempCrocList[i].getPosY());
			
			//Se agregan los datos necesarios a tempArray
			tempArray.put(color);
			tempArray.put(posX);
			tempArray.put(posY);
			
			//TempArray es ingresado al crocodilesArray
			crocodilesArray.put(tempArray);
		}
		
		return crocodilesArray;
		
	}
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return fruitsArray
	 */
	public JSONArray generateFruitsArray(String code) {
		
		//Lista de Frutas Temporal
		Fruit[] tempFruitList;
				
		//FruitArray
		JSONArray fruitArray = new JSONArray();
				
		//Datos necesarios para CrocodilesArray
		String type;
		String posX;
		String posY;
			
		//Codigos de los juegos actuales
		String codeA = gameA.getCode();
		String codeB = gameB.getCode();
		
		//Verifica con el codigo cual es el juego del cual debe
		//obtener la informacion
		if (codeA.equals(code)) {
					
			//Se obtiene la lista de Cocodrlos de gameA
			tempFruitList = gameA.getFruitsList();
														
		} else { //(codeB.equals(code)) {
														
			//Se obtiene la lista de Cocodrlos de gameB
			tempFruitList = gameB.getFruitsList();
		}
		
		//Se itera por la lista de frutas para guardar cada una de ellas
		for (int i = 0; i < tempFruitList.length;i++) {
					
			//tempArray
			JSONArray tempArray = new JSONArray();
					
			//Se obtienen los atributos del cocodrilo necesarios
			type = tempFruitList[i].getType();
			posX = Integer.toString(tempFruitList[i].getPosX());
			posY = Integer.toString(tempFruitList[i].getPosY());
					
			//Se agregan los datos necesarios a tempArray
			tempArray.put(type);
			tempArray.put(posX);
			tempArray.put(posY);
					
			//TempArray es ingresado al fruitArray
			fruitArray.put(tempArray);
		}
				
				return fruitArray;
		
	}
	
	/**
	 * Genera el array de _ para agregarse al TopJSON 
	 * @return fruitPointsArray
	 */
	public JSONArray generateFruitPointsArray(String code) {
		
		//Codigos de los juegos actuales
				String codeA = gameA.getCode();
				String codeB = gameB.getCode();
						
				
								
				/*
				if (codeA.equals(code)) {
									
										
				} else { //(codeB.equals(code)) {
										
									
				}
				*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		//FruitPointsArray
    	JSONArray fruitPointsArray = new JSONArray();
		
		return fruitPointsArray;
		
	}
	
	
	

}
