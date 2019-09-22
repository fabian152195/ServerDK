
import java.awt.*;

public class DKJr extends Entities{
	
	String state;
	int posX;
	int posY;
	
	
	
	
	
	
	
	
	public DKJr(int height, int tSize){
		this.rect.setRect(0, height - 4*tSize, 0, 0);	//Posición cada vez que se reinicie
		this.setSprite("dkjrIdle", "right");
	}
	
	boolean onVine = false;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean isOnVine() {
		return onVine;
	}

	public void setOnVine(boolean onVine) {
		this.onVine = onVine;
	}

	DKJr(int i) {
		this.state = "Initial";
		this.posX = 25;
		this.posY = 475;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
