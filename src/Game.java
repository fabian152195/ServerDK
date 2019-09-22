
//import Environment.Tile;
import java.awt.*;

public class Game {
	
	///Define si alguien esta jugando con esta instancia de Game
	boolean isPlaying;
	String code;
	String id;
	int observers;
	int[] keyInput;
	

	//Array Game
	//String state;
	int score;
	int level;
	int lives;
	
	//DKJr dkjr;
	Crocodile[] crocodilesList;
	Fruit[] fruitsList;
	Fruit[] fruitPointsList;
	
	//Constructor
	Game(String id) {
		
		this.isPlaying = false;
		this.code = "-1";
		this.id = id;
		this.observers = 0;
		//keyInput
		
		this.state = 0;
		this.score = 0;
		this.level = 1;
		this.lives = 3;
		
		dkjr = new DKJr();
		
	}
	
	
	
	
	
	
	
	
	
	
	//Constantes
		public static final int UP 		= 1;
		public static final int RIGHT 	= 2;
		public static final int DOWN 	= 3;
		public static final int LEFT 	= 4;
		
		public static final int MENU 		= 0;
		public static final int PLAYER		= 1;
		public static final int OBSERVER	= 2; 
		public static final int DYING		= 3;
		
		public static final int sizeMult	= 3;			//La escala de la imagen de fondo original, de 248x216
		public static final int Width 		= 248*sizeMult;
		public static final int Height 		= 216*sizeMult;
		public static int tSize 			= 8*sizeMult; 	//Tamaño de un tile, relativo al tamaño de la ventana del juego
		
		private static int state = MENU;
		private static DKJr dkjr = new DKJr();
		private static Rectangle platforms[] 	= new Rectangle[11];	//11 plataformas sobre las que se puede caminar
		private static Rectangle vines[] 		= new Rectangle[10]; 	//10 lianas
		private static Crocodile crocs[] 	= new Crocodile[10];					//Max = 10
		private static Fruit fruits[] 	= new Fruit[10];				//Max = 10
		
		void initPlatforms(){
			for (int i = 0; i < platforms.length; i++){
				if (i == 0) {
					//No se le suma un 1 a la posición, para que ese pixel que queda por fuera sirva
					//para detectar la colisión con el jugador y evitar que se vea sobre la tectura
					//de la plataforma
					
					//A width y height sí se le suma los pixeles extra para colisiones
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}//////////////////////////////////////////////////////
				if (i == 0) {
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}
				if (i == 0) {
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}
				if (i == 0) {
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}
				if (i == 0) {
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}
				if (i == 0) {
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}
				if (i == 0) {
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}
				if (i == 0) {
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}
				if (i == 0) {
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}
				if (i == 0) {
					platforms[i].setBounds(8*tSize, 3*tSize, 3*tSize + 2*sizeMult, tSize + 2*sizeMult);
				}
			}
		}

		void initVines(){
			int vStart = 4*tSize; //El rectángulo de la liana empieza 4 pixeles más hacia la derecha en el tile
			
			/////////////////////////////////////////////////
			for (int i = 0; i < vines.length; i++) {
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
				if (i == 0){
					vines[i].setBounds( 1*tSize + vStart, 7*tSize + vStart, 2*tSize, 16*tSize);
				}
			}
		}
		
		void initCrocsAndFruits() {
			//Los null servirán para saber cuántos crocs o fruits hay en el juego
			for (int i = 0; i < crocs.length; i++){
				crocs[i] = null;
			}
			
			for (int i = 0; i < fruits.length; i++){
				fruits[i] = null;
			}
		}

		//Se recibe el JSON que contiene la información del input enviada desde C
		void receiveJSONInput(int[] input) {
			int up = 0;			//JSON
			int right = 0;		//JSON
			int down = 0;		//JSON
			int left = 0;		//JSON
			
			for (int i = 0; i < 4; i++) {
				if (i == 0 && input[i] == 1) {
					up = 1;
				}
				if (i == 1 && input[i] == 1) {
					right = 1;
				}
				if (i == 2 && input[i] == 1) {
					left = 1;
				}
				if (i == 3 && input[i] == 1) {
					left = 1;
				}
			}
			
			//dkjr.move(up, right, down, left);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		public void move(int up, int right, int down, int left){
			int newX = (int) dkjr.getRect().getX() + up - down;
			int newY = (int) dkjr.getRect().getY() + right - left;
			Rectangle newRect = dkjr.getRect();
			newRect.setLocation(newX, newY);
			
			//
			if (collideCrocs(newRect)){
				//
				//
				Game.setState(Game.DYING);
			}
			
			//Colisi[on con frutas//////////////
			
			if (dkjr.isOnVine()) {
				moveOnVine(newRect);
			}
			
			//Si no está en una liana, ni colisiona con cocodrilso o frutas, entonces puede moverse
			else {
				;
			}
		}
		
		
		boolean collideCrocs(Rectangle newRect) {
			
			for (int i = 0; i < crocs.length; i++) {
				if (dkjr.getRect().intersects(crocs[i].getRect())) {
					return true;
				}
			}
			
			return false;
		}
		
		
		public void moveOnVine(Rectangle newRect){
			int posDiffX = (int) (dkjr.getRect().getX() - newRect.getX());
			int posDiffY = (int) (dkjr.getRect().getY() - newRect.getY());
			
			////////////////////////////////////////////////////////////Revisar cuándo se está en el borde inferior de una liana
			//////////////////////////////////////////Falta cambiar la dirección de los sprites
			if (posDiffX != 0){
				//Si no hay plataformas alrederdors
				if (! (collideHorizontal( Game.getPlatforms() ) ) ) {
					
				}
					

				//Se revisa si hay otra liana cerca
				
			}
		}
		
		boolean collideHorizontal(Rectangle obj[]) {
			int dkjrRightBorder = (int) ((dkjr.getRect().x + dkjr.getRect().getWidth()));
			int dkjrLeftBorder = (int) dkjr.getRect().x;
			
			for (int i = 0; i < obj.length; i++) {
				int objRightBorder = (int) (obj[i].getX() + obj[i].getWidth());	//Para ver si colisiona
				int objLeftBorder = (int) (obj[i].getX());
				
				if (objRightBorder == dkjrLeftBorder || objLeftBorder == dkjrRightBorder) {
					return true;
				}
			}
			
			return false;
		}
		
		
		
		boolean collideVertical(Rectangle obj[]) {
			int dkjrTop = (int) ((dkjr.getRect().y));
			int dkjrBottom = (int) (dkjr.getRect().y + dkjr.getRect().getHeight());
			
			for (int i = 0; i < obj.length; i++) {
				int objTop = (int) (obj[i].getY());	//Para ver si colisiona
				int objBottom = (int) (obj[i].getY() + obj[i].getHeight());
				
				if (objTop == dkjrBottom || objBottom == dkjrTop){
					return true;
				}
			}
			
			return false;
		}
		
		//Principalmente para saber si DKJr está sobre una plataforma
		boolean collideDown(Rectangle obj[]) {
			int dkjrBottom = (int) (dkjr.getRect().y + dkjr.getRect().getHeight());
			
			for (int i = 0; i < obj.length; i++) {
				int objTop = (int) (obj[i].getY());
				
				if (dkjrBottom == objTop){
					return true;
				}
			}
			
			return false;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public int getState() {
			return state;
		}
		
		public static void setState(int newState) {
			state = newState;
		}
		
		public DKJr getDKJr(){
			return dkjr;
		}
		
		
		public static Rectangle[] getPlatforms() {
			return platforms;
		}
		
		public static Rectangle[] getVines() {
			return vines;
		}
		
		public static Crocodile[] getCrocs() {
			return crocs;
		}
		
		public static Fruit[] getFruits() {
			return fruits;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///Metodos
	
	/**
	 * Inicia el Juego
	 */
	public void startGame() {
		System.out.println("Game " + id + " (" + code + ") -> Started!");
	}
	
	/**
	 * Verifica si se puede observar este juego debido a la cantidad
	 * de observadores ya en el.
	 * @return boolean
	 */
	public boolean isObservable() {
		
		boolean observable;
		
		if (observers < 2) {
			//Solo pueden haber dos observadores por juego
			observable = true;
		} else {
			//Cuando ya hay dos jugadores observando,
			//no se pueden agregar mas.
			observable = false;
		}
		
		return observable;
		
	}
	
	public void update() {
		
	}

	
	///Getters & Setters
	
	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	public int getObservers() {
		return observers;
	}

	public void setObservers(int observers) {
		this.observers = observers;
	}

	public int[] getKeyInput() {
		return keyInput;
	}


	public void setKeyInput(int[] keyInput) {
		this.keyInput = keyInput;
	}

	
	
	
	
	
	
	
	
	
	
	
/*
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
*/
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public DKJr getDkjr() {
		return dkjr;
	}

	public void setDkjr(DKJr dkjr) {
		this.dkjr = dkjr;
	}

	public Crocodile[] getCrocodilesList() {
		return crocodilesList;
	}

	public void setCrocodilesList(Crocodile[] crocodilesList) {
		this.crocodilesList = crocodilesList;
	}

	public Fruit[] getFruitsList() {
		return fruitsList;
	}

	public void setFruitsList(Fruit[] fruitsList) {
		this.fruitsList = fruitsList;
	}


	public Fruit[] getFruitPointsList() {
		return fruitPointsList;
	}


	public void setFruitPointsList(Fruit[] fruitPointsList) {
		this.fruitPointsList = fruitPointsList;
	}
	
	
}
