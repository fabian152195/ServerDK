
import java.awt.*;

public class Crocodile extends Entities{
	
	String color;
	int posX;
	int posY;
	
	/**
	 * Azul
	 */
	public Crocodile() {
		this.rect.setRect(10*8*Game.sizeMult, (5*8+1) * Game.sizeMult, 0, 0);
		this.setSprite("blue1", "");
		
	}
	
	/**
	 * Rojo
	 * @param vine
	 * @param vines
	 */
	public Crocodile(int vine, Rectangle vines[]){
		int newCrocPosX = ((int) vines[vine].getX() - 3) * Game.sizeMult;
		int newCrocPosY = (int) vines[vine].getY() * Game.sizeMult;
		
		this.rect.setRect(newCrocPosX, newCrocPosY, 0, 0);

		this.setSprite("red1", "");
	}
	
	void move(){
		
	}
	
	
	
	
	
	
	Crocodile(String color, int posX, int posY) {
		this.color = color;
		this.posX = posX;
		this.posY = posY;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
