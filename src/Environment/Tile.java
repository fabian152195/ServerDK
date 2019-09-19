package Environment;

import java.awt.*;

//Sólo se almacenan las plataformas, las lianas, agua
//y el tile de King Kong (cuando sea adyacente, se gana)
//Cada tile mide 8x8 pixeles
public class Tile extends Rectangle{
	
	private String type;		//platform, vine, water, KK
	
	public void setType(String string){
		this.type = string;
	}
	
}

/*
 * 	/*
	void initGrid(){
		int i = 0;
		int j = 0;
		for (; i < 31; i++){
			for (; j < 27; i++){
				if (i == 2 && j == 7){	//Se le resta 1 a la posición real del Tile
					gameGrid[i][j].setType("finish");
				}
				if (i == 12 && (j == 2 || j == 3) ||
				   (i == 18 && j == 2) ||
				   ((i == 25 || i == 28) && (j > 4 || j < 19)) ||
				   ((i == 7 || i == 13) && j > 1)){///////////////////////////////////////////
					/////////////////////////////////////////////////////
					gameGrid[i][j].setType("vine");
				}
				if (i == 3 && (j > 8 && j < 11) ||
				   (i == 6 && j < 18) ||
				   (i == 7 && (j > 16 && j < 23 )) ||
				   (i == 12 && (j > 5 && j < 9)) ||
				   (i == 15 && (j > 22 && j < 30) ||
				   (i == 17 && (j > 5 && j < 11)))){
					gameGrid[i][j].setType("platform");
					//gameGrid[i][j].
				}
			}
		}
 * */
 */