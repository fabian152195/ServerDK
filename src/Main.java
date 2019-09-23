
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

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
	
	
	public static Runnable runManager() {
	
		while(true) {
		
			LinkedList<String> dataList = menu();
			
			String gameCode = dataList.get(0);
			String listId = dataList.get(1);
			
			//Codigos de los juegos actuales
			String codeA = jsonManager.gameA.getId();
			String codeB = jsonManager.gameB.getId();
			
			String color;
			String liana;
			String type;
			String points;
			String x;
			String y;
			
			
			if (listId != "ER") {
				
				//Verifica con el codigo cual es el juego del cual debe
				//obtener la informacion
				if (codeA.equals(gameCode)) {
									
					if (listId != "CA") {
						
						//Agregar Cocodrilo Azul
						System.out.println("Agregar Cocodrilo Azul");
						
						color = dataList.get(2);
						liana = dataList.get(3);
						
						//jsonManager.gameA.addCrocodile(color,liana);
						
					} else if (listId != "CR") {
						
						//Agregar Cocodrilo Rojo
						System.out.println("Agregar Cocodrilo Rojo");
						
						color = dataList.get(2);
						liana = dataList.get(3);
						
						//jsonManager.gameA.addCrocodile(color,liana);
						
					} else if (listId != "AF") {
						
						//Agregar Fruta
						System.out.println("Agregar Fruta");
						
						type = dataList.get(2);
						points = dataList.get(3);
						x = dataList.get(4);
						y = dataList.get(5);
						
						//jsonManager.gameA.addFruit(type,points,x,y);
						
					} else if (listId != "EF") {
						
						//Eliminar Fruta
						System.out.println("Eliminar Fruta");

						x = dataList.get(2);
						y = dataList.get(3);
						
						//jsonManager.gameA.deleteFruit(x,y);
						
					} else {
						
						//ERROR
						System.out.println("Error");
						
					}
																		
				} else { //(codeB.equals(code)) {
					
					if (listId != "CA") {
						
						//Agregar Cocodrilo Azul
						System.out.println("Agregar Cocodrilo Azul");
						
						color = dataList.get(2);
						liana = dataList.get(3);
						
						//jsonManager.gameB.addCrocodile(color,liana);
						
					} else if (listId != "CR") {
						
						//Agregar Cocodrilo Rojo
						System.out.println("Agregar Cocodrilo Rojo");
						
						color = dataList.get(2);
						liana = dataList.get(3);
						
						//jsonManager.gameB.addCrocodile(color,liana);
						
					} else if (listId != "AF") {
						
						//Agregar Fruta
						System.out.println("Agregar Fruta");

						x = dataList.get(2);
						y = dataList.get(3);
						
						//jsonManager.gameB.addFruit(type,points,x,y);
						
					} else if (listId != "EF") {
						
						//Eliminar Fruta
						System.out.println("Eliminar Fruta");
						
						type = dataList.get(2);
						points = dataList.get(3);
						x = dataList.get(4);
						y = dataList.get(5);
						
						//jsonManager.gameB.deleteFruit(x,y);
						
					} else {
						
						//ERROR
						System.out.println("Error");
						
					}																		
					
				}
				
			}

		}
		
	}
		
	
	
	public static LinkedList<String> menu()
	{
		Scanner scan = new Scanner(System.in);
		LinkedList<String> output = new LinkedList<>();
		
		// Ingresa el numero de sala:
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
		System.out.println("Bienvenido al Generador de objetos v1.0 DKServer");
		System.out.print("Ingrese el codigo del juego al que quiere modificar: \n>> ");
		String s = scan.nextLine();
		Boolean bool=true;
		
		if(s.equals(gameA.getCode()) || s.equals(gameB.getCode())){
			
			//Agrega a lista
			output.add(s);
			System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - -\n");
			System.out.print("\nIngrese el digito correspondiente a la operacion deseada: " + "\n"
					+ "1: Agregar un cocodrilo \n"
					+ "2: Agregar una fruta \n"
					+ "3: Eliminar un fruta \n>> ");
			
			String s2 = scan.nextLine();
			
			switch(s2) {
				case "1":
					System.out.print("\nIngrese id de tipo de cocodrilo que desea ingresar: "
							+ "\n1: Rojo"
							+ "\n2: Azul"
							+ "\n>> ");
					String s3 = scan.nextLine();
					switch(s3) {
					case "1": //Agrega cocodrilo Rojo
						output.add("CR");
						output.add("cocodriloRojo");
						System.out.print("\nIngrese identificador de liana (1 al 9) \n>> ");
						int idLiana = scan.nextInt();
						if(idLiana > 0 && idLiana < 10) {
							//Agrega identificador de liana
							output.add(Integer.toString(idLiana)); //ID LIANA
							break;
						}
					case "2": //Agrega cocodrilo Azul
						output.add("CA");
						output.add("cocodriloAzul");
						output.add("0"); //Identificador cero
						break;
						
					default:
						System.out.print("\nIngrese un identificador valido");
						break;
					}
					break;
				
				case "2":
					System.out.print("\nIngrese el valor que tendra esta fruta (1 - 999): "
							+ "\n>> ");
					int valorFruta = scan.nextInt();
					
					if (valorFruta>999 || valorFruta<1) {
						System.out.print("\nValor de fruta no valida, digite valores entre 1 y 999");
						break;
					}
					else {
						System.out.print("\nIngrese coordenada en X: ");
						int posX = scan.nextInt();
						
						System.out.print("\nIngrese coordenada en Y: ");
						int posY = scan.nextInt();
						
						//Valida posicion en X de la fruta
						if(posX >= 0 && posX <= 744) {
							
							//Valida posicion en Y de la fruta
							if(posY >= 0 && posY <= 648) {
								
								//Clasificacion de fruta a graficar segun valor indicado
								
								String valorFrutaS = Integer.toString(valorFruta);
								String posXS = Integer.toString(posX);
								String posYS = Integer.toString(posY);
								
								output.add("AF");
								
								if(valorFruta<=333) {
									output.add("1");
									output.add(valorFrutaS);
									output.add(posXS);
									output.add(posYS);
									break;
								}
								
								else if(valorFruta > 333 && valorFruta <= 666) {
									output.add("2");
									output.add(valorFrutaS);
									output.add(posXS);
									output.add(posYS);
									break;
								}
								else{
									output.add("3");
									output.add(valorFrutaS);
									output.add(posXS);
									output.add(posYS);
									break;
								}
							}
							else {
								System.out.println("Posicion en Y no valida, solo entre 0 y 648");
								break;
							}
						}
						else {
						System.out.println("Posicion en X no valida, solo entre 0 y 744");
						break;
						}
					}
				case "3":
					System.out.print("Ingrese coordenada en X: ");
					int posX = scan.nextInt();
					
					System.out.print("Ingrese coordenada en Y: ");
					int posY = scan.nextInt();
					
					//Valida posicion en X de la fruta
					if(posX >= 0 && posX <= 744) {
						
						//Valida posicion en Y de la fruta
						if(posY >= 0 && posY <= 648) {
							
							//Clasificacion de fruta a graficar segun valor indicado
							
							String posXS = Integer.toString(posX);
							String posYS = Integer.toString(posY);
		
							output.add("EF");
							output.add(posXS);
							output.add(posYS);
							
						}
						else {
							System.out.println("Posicion en Y no valida, solo entre 0 y 648");
						}
					}
					else {
					System.out.println("Posicion en X no valida, solo entre 0 y 744");
					break;
					}
					
			}
			
			//IMPRESION DE LISTA
			System.out.println("Impresion de lista: ");
			int c = output.size();
			int cont = 0;
			
			while(cont<c) {
				System.out.println(output.get(cont));
				cont++;
			}
			return output;
		}
		else {
			
			//Si no hay juegos disponibles.
			if (jsonManager.gameA.isPlaying() == false && jsonManager.gameB.isPlaying() == false) {
				
				System.out.println("No hay juegos activos.");
				
			}
			//Si el codigo esta mal y si hay juegos disponibles.
			else {
				
				System.out.println("Ingrese un código de juego valido.");
				
			}
			
			output.add("-1");
			output.add("ER");
			
			//IMPRESION DE LISTA
			System.out.println("Impresion de lista: ");
			int c = output.size();
			int cont = 0;
			
			while(cont<c) {
				System.out.println(output.get(cont));
				cont++;
			}
			
			return output;
		
		}
		
		
		
	}
	
	
	
	
	
	
	/**
	 * Corre el Servidor.
	 * @return 
	 * @throws IOException
	 */
	static Runnable runServer() throws IOException {
		
		// server is listening on port 5056 
		ServerSocket ss = new ServerSocket(5056);
		
		System.out.println("DonCEy Kong Jr. Server Running!\n\n"); 
		
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
	 * @throws InterruptedException 
	 */
	public static void main (String [] args) throws JSONException, IOException, InterruptedException
    { 	
		/////TEST PURPOSE ONLY/////
		jsonManager.gameA.setState(1);
		jsonManager.gameA.setScore(6900);
		jsonManager.gameA.setLevel(17);
		jsonManager.gameA.setLives(5);
		
		jsonManager.gameB.setState(1);
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
		Fruit[] fArray = {f1,f2};
		Fruit[] fpArray = {f3,f4};
		
		gameA.setCrocs(cArray);
		gameA.setFruits(fArray);
		gameA.setFruitPointsList(fpArray);
		
		gameB.setCrocs(cArray);
		gameB.setFruits(fArray);
		gameB.setFruitPointsList(fpArray);
		/////TEST PURPOSE ONLY/////
		
		
		
		
		 
    	//Corre el servidor
    	//runServer();
    	
		//runManager();
		
	
		
		
		
		//Thread para el manager
		Thread thread1 = new Thread() {
		    public void run() {
		    	runManager();
		    }
		};

		//Thread para el servidor
		Thread thread2 = new Thread() {
		    public void run() {
		    	try {
					runServer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		};

		// Start them
		thread1.start();
		thread2.start();

		// Wait for them both to finish
		thread1.join();
		thread2.join();
		
    }

}
