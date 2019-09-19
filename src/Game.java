import Environment.Tile;
import java.awt.*;

public class Game{	
	//Constantes
	public static final int UP 		= 1;
	public static final int RIGHT 	= 2;
	public static final int DOWN 	= 3;
	public static final int LEFT 	= 4;
	
	public static final int sizeMult	= 3;			//La escala de la imagen de fondo original, de 248x216
	public static final int Width 		= 248*sizeMult;
	public static final int Height 		= 216*sizeMult;
	public static int tSize 			= 8*sizeMult; 	//Tamaño de un tile, relativo al tamaño de la ventana del juego
	
	DKJr dkjr = new DKJr();
	Rectangle platforms[] 	= new Rectangle[11];	//11 plataformas sobre las que se puede caminar
	Rectangle vines[] 		= new Rectangle[10]; 	//10 lianas
	Croc crocs[] 	= new Croc[10];					//Max = 10
	Fruit fruits[] 	= new Fruit[10];				//Max = 10
	
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
}