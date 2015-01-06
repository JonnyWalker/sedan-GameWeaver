package com.johnnyworks.mytetris;

import java.util.ArrayList;
import java.util.Random;
import android.graphics.Color;
import android.graphics.Point;


public class GameState {
    public final int bgColor = Color.BLUE;
	public final int width = 10;
	public final int height = 20;
	public int blockSizeX = 40;
	public int blockSizeY = 40;
	public float touchX, touchY;
	public int[][] array; 
	public int points = 0;
	public boolean gameover=false;
	public int x0; // first touch location, tile x movement will be computed reletive to this position
	public boolean  action_move, action_rotate;
	public int rotation_index;
	
	private int tileX =7;
	private int tileY =0;
	private int tileType=0;
	private int tileColor= Color.BLUE;
	private Random rand;
	private Tiles tiles;
	private ArrayList<Point> currentTile;


	
	public GameState(int display_width,int display_height)
	{
		blockSizeX =  display_width/width;
		blockSizeY =  display_height/ height-6;// status bar 4 blocks height
		tileX      = width / 2;
		rand=new Random();
		array = new int[width][height];
		for (int x=0; x< this.width ; x++)
			for (int y=0; y<this.height ; y++ )
				array[x][y] = bgColor;
		tiles = new Tiles();
		createNewTile();
	}
	
	public boolean touchedCurrentTile() {
		int tx = ((int) touchX/ blockSizeX);
		int ty = ((int) touchY/ blockSizeY);
		for (int i=0; i<4; i++)
		{
			Point p1 = currentTile.get(i+rotation_index*4);
			if(tileX+p1.x==tx && tileY+p1.y==ty)
				return true;
		}	
		return false;
	}

	public void rotateCurrentTile() {
			changeArray(bgColor);
			int new_rotation_index = (rotation_index+1)%4;
			if (canMoveTile(0,0,new_rotation_index)) // TODO: Racecondtion if y chances 
				rotation_index = new_rotation_index;
			changeArray(tileColor);
	}
	
	public boolean canMoveTile(int incX, int incY, int rotation_index) {					
		for (int i=0; i<4; i++)
		{
			Point p1 = currentTile.get(i+rotation_index*4);
			if(tileY+p1.y+incY==height)
				return false;
			if(tileX+p1.x+incX==width)
				return false;
			if(tileX+p1.x+incX<0)
				return false;
			if(array[tileX+p1.x+incX][tileY+p1.y+incY] != bgColor)
			{
				// found blocked square
				boolean selfCollide = false;
				for (int j=0; j<4; j++)
				{
					// check for self collide
					Point p2 = currentTile.get(j+rotation_index*4);
					if(p2.x==p1.x+incX && p2.y==p1.y+incY)
						selfCollide = true;
				}
				
				if (!selfCollide)
					return false;
			}
		}

		return true;
	}
	public void createNewTile() {
		// choose tile
		tileType = rand.nextInt(7);
		tileX = width / 2;
		tileY = 0;
		rotation_index = 0;
		// switch color 
		switch(tileType)
		{
		case 0:tileColor= Color.MAGENTA;break;
		case 1:tileColor= Color.YELLOW;break;
		case 2:tileColor= Color.GREEN; break;
		case 3:tileColor= Color.CYAN; break;
		case 4:tileColor= Color.DKGRAY; break;
		case 5:tileColor= Color.WHITE; break;
		case 6:tileColor= Color.LTGRAY; break;
		}
		
		// and set tile
		currentTile = tiles.tilemap.get(tileType);
		
		// check if game over
		for (int i=0; i<4; i++)
		{
			Point p1 = currentTile.get(i+rotation_index*4);
			if(array[tileX+p1.x][tileY+p1.y] != bgColor)
				gameover = true;
		}			
		
		// draw (indirect)
		changeArray(tileColor);

		
	}
	
	private void changeArray(int color)
	{
		for (int i=0; i<4; i++)
		{
			Point p = currentTile.get(i+rotation_index*4);
			array[tileX+p.x][tileY+p.y] = color;
		}
	}
	
	public void moveTileY() {
		changeArray(bgColor);
		tileY= tileY+1;
		changeArray(tileColor);
	}
	
	public void moveTileX() {
		// calc square
		int incX = 0;
		if(((int) touchX/ blockSizeX)< tileX)
			incX = -1;
		if(((int) touchX/ blockSizeX)> tileX)
			incX = +1;
		// check if ok
		if(!canMoveTile(incX, 0, rotation_index))
			return;

		// move
		changeArray(bgColor);
		tileX = tileX + incX;
		changeArray(tileColor);
	}
	
	public void checkAndRemoveFullRow()
	{
		int y;
		for (y=this.height-1; y>0 ; y-- )
		{
			boolean fullRow = true;
			for (int x=0; x< this.width ; x++)
				if(array[x][y] ==bgColor)
					fullRow = false;
			if(fullRow)
			{
				removeRow(y);
				y=this.height-1;
			}
		}
	}
	
	private void removeRow(int n) {
		for (int y=n; y!=0 ; y-- )
			for (int x=0; x< this.width ; x++)
				array[x][y]=array[x][y-1];
		for (int x=0; x< this.width ; x++)
			array[x][0]=bgColor;
		points += 10;
	}

}
