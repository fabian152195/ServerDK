
public class Game {
	
	///Define si alguien esta jugando con esta instancia de Game
	boolean isPlaying;

	//Array Game
	String state;
	int score;
	int level;
	int lives;
	
	DKJr dkjr;
	Crocodile[] crocodilesList;
	Fruit[] fruitsList;
	
	//Constructor
	Game() {
		
		isPlaying = false;
		
	}

	
	///Getters & Setters
	
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
	
	public boolean isPlaying() {
		return isPlaying;
	}


	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	

}
