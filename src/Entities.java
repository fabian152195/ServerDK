//import Environment.Tile;
import java.awt.*;

public abstract class Entities{
	protected Rectangle rect;
	private static String sprite;
	private static String spriteDirection;
	
	public void setSprite(String newSprite, String direction){
		if (spriteDirection != direction){
			sprite = newSprite;
			
			//SDL_RendererFlip flip = SDL_FLIP_HORIZONTAL | SDL_FLIP_VERTICAL;
			//SDL_RenderCopyEx(Game.renderer, texture, &srcrect, &dstrect, angle, &center, flip);
		}
		updateRect();
	}
	
	//Cuando se cambia el sprite, se actualiza las dimensiones del rectángulo
	public void updateRect(){
		if (sprite == "dkjrIdle"){
			rect.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}/////////////////////////////////////////////
		if (sprite == "dkjrIdle"){
			rect.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (sprite == "dkjrIdle"){
			rect.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (sprite == "dkjrIdle"){
			rect.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (sprite == "dkjrIdle"){
			rect.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (sprite == "dkjrIdle"){
			rect.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
		if (sprite == "dkjrIdle"){
			rect.setSize(25*Game.sizeMult, 16*Game.sizeMult);
		}
	}
	
	public static String getSprite(){
		return sprite;
	}
	
	public static String getSpriteDirect() {
		return spriteDirection;
	}
	
	public Rectangle getRect() {
		return rect;
	}
}