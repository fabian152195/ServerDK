
public class Fruit {
	
	String type;
	int points;
	int posX;
	int posY;
	
	
	
	
	private int puntaje;
	
	public int getPuntaje() {
		return puntaje;
	}
	
	
	
	
	
	
	Fruit(String type, int points, int posX, int posY) {
		this.type = type;
		this.points = points;
		this.posX = posX;
		this.posY = posY;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	

}
