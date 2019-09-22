
public class Game {
	
	///Define si alguien esta jugando con esta instancia de Game
	boolean isPlaying;
	String code;
	String id;
	int observers;
	int[] keyInput;
	

	//Array Game
	String state;
	int score;
	int level;
	int lives;
	
	DKJr dkjr;
	Crocodile[] crocodilesList;
	Fruit[] fruitsList;
	
	//Constructor
	Game(String id) {
		
		this.isPlaying = false;
		this.code = "-1";
		this.id = id;
		this.observers = 0;
		//keyInput
		
		this.state = "Initial";
		this.score = 0;
		this.level = 1;
		this.lives = 3;
		
		dkjr = new DKJr();
		
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

	
	
	
	
	
	
	
	
	
	
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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
	
	
	
	

}
