import Environment.Tile;
import java.awt.*;

public abstract class Entities extends Rectangle{
	private String texture;
	
	public void setTexture(String textureChange){
		texture = textureChange;
		updateRect();
	}
	
	//Cuando se cambia el sprite, se actualiza las dimensiones del rectángulo
	public void updateRect(){
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}/////////////////////////////////////////////
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		
		if (texture == "dkjrIdle"){
			this.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
	}
	
	public String getTexture(){
		return texture;
	}
}