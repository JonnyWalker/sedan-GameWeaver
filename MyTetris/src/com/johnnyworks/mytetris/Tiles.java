package com.johnnyworks.mytetris;

import java.util.ArrayList;
import java.util.HashMap;


import android.graphics.Point;

public class Tiles {
	HashMap<Integer, ArrayList<Point>> tilemap;
	public Tiles()
	{ 
		tilemap = new HashMap<Integer, ArrayList<Point>>();
		
		ArrayList<Point> bar = new ArrayList<Point>();
		bar.add(new Point(0,0)); // *
		bar.add(new Point(0,1)); // *
		bar.add(new Point(0,2)); // *
		bar.add(new Point(0,3)); // *
		
		bar.add(new Point(0,0)); // * * * *
		bar.add(new Point(1,0)); // 
		bar.add(new Point(2,0)); // 
		bar.add(new Point(3,0)); // 
		
		bar.add(new Point(0,0)); // *
		bar.add(new Point(0,1)); // *
		bar.add(new Point(0,2)); // *
		bar.add(new Point(0,3)); // *
		
		bar.add(new Point(0,0)); // * * * *
		bar.add(new Point(1,0)); // 
		bar.add(new Point(2,0)); // 
		bar.add(new Point(3,0)); // 
		tilemap.put(0, bar);
		
		ArrayList<Point> tile = new ArrayList<Point>();
		tile.add(new Point(0,0)); //  ***
		tile.add(new Point(1,0)); //   *
		tile.add(new Point(1,1));
		tile.add(new Point(2,0));

		tile.add(new Point(0,0)); //  *
		tile.add(new Point(0,1)); //  * *
		tile.add(new Point(0,2)); //  *
		tile.add(new Point(1,1));
		
		tile.add(new Point(1,0)); //    *
		tile.add(new Point(0,1)); //  * * *
		tile.add(new Point(1,1)); //  
		tile.add(new Point(2,1));
		
		tile.add(new Point(1,0)); //    *
		tile.add(new Point(0,1)); //  * * 
		tile.add(new Point(1,1)); //    *
		tile.add(new Point(1,2));
		tilemap.put(1, tile);
		
		ArrayList<Point> tile2 = new ArrayList<Point>();
		tile2.add(new Point(0,0)); // **
		tile2.add(new Point(1,0)); //  **
		tile2.add(new Point(1,1));
		tile2.add(new Point(2,1));

		tile2.add(new Point(1,0)); //   *
		tile2.add(new Point(0,1)); //  **
		tile2.add(new Point(1,1)); //  *
		tile2.add(new Point(0,2));
		
		tile2.add(new Point(0,0)); //  **
		tile2.add(new Point(1,0)); //   **
		tile2.add(new Point(1,1)); //  
		tile2.add(new Point(2,1));
		
		tile2.add(new Point(1,0)); //   *
		tile2.add(new Point(0,1)); //  **
		tile2.add(new Point(1,1)); //  *
		tile2.add(new Point(0,2));
		tilemap.put(2, tile2);
		
		ArrayList<Point> tile3 = new ArrayList<Point>();
		tile3.add(new Point(1,0)); //  **
		tile3.add(new Point(2,0)); // **
		tile3.add(new Point(0,1));
		tile3.add(new Point(1,1));

		tile3.add(new Point(0,0)); // *
		tile3.add(new Point(0,1)); // **
		tile3.add(new Point(1,1)); //  *
		tile3.add(new Point(1,2));
		
		tile3.add(new Point(1,0)); //  **
		tile3.add(new Point(2,0)); // **
		tile3.add(new Point(0,1));
		tile3.add(new Point(1,1));
		
		tile3.add(new Point(0,0)); // *
		tile3.add(new Point(0,1)); // **
		tile3.add(new Point(1,1)); //  *
		tile3.add(new Point(1,2));		
		tilemap.put(3, tile3);
		
		ArrayList<Point> tile4 = new ArrayList<Point>();
		tile4.add(new Point(0,0)); // * 
		tile4.add(new Point(0,1)); // *
		tile4.add(new Point(0,2)); // **
		tile4.add(new Point(1,2));

		tile4.add(new Point(2,0)); //   *
		tile4.add(new Point(0,1)); // ***
		tile4.add(new Point(1,1)); // 
		tile4.add(new Point(2,1));
		
		tile4.add(new Point(0,0)); // ** 
		tile4.add(new Point(1,0)); //  *
		tile4.add(new Point(1,1)); //  *
		tile4.add(new Point(1,2));
		
		tile4.add(new Point(0,0)); // *** 
		tile4.add(new Point(1,0)); // * 
		tile4.add(new Point(2,0)); //  
		tile4.add(new Point(0,1));
		tilemap.put(4, tile4);
		
		ArrayList<Point> tile5 = new ArrayList<Point>();
		tile5.add(new Point(1,0)); //  * 
		tile5.add(new Point(1,1)); //  *
		tile5.add(new Point(1,2)); // **
		tile5.add(new Point(0,2));

		tile5.add(new Point(0,0)); // *** 
		tile5.add(new Point(1,0)); //   *
		tile5.add(new Point(2,0)); // 
		tile5.add(new Point(2,1));
		
		tile5.add(new Point(0,0)); // ** 
		tile5.add(new Point(1,0)); // *
		tile5.add(new Point(0,1)); // *
		tile5.add(new Point(0,2));
		
		tile5.add(new Point(0,0)); // * 
		tile5.add(new Point(0,1)); // ***
		tile5.add(new Point(1,1)); // 
		tile5.add(new Point(2,1));
		tilemap.put(5, tile5);
		
		ArrayList<Point> block = new ArrayList<Point>();
		block.add(new Point(0,0)); // **
		block.add(new Point(1,0)); // **
		block.add(new Point(0,1));
		block.add(new Point(1,1));
		
		block.add(new Point(0,0)); // **
		block.add(new Point(1,0)); // **
		block.add(new Point(0,1));
		block.add(new Point(1,1));	
		
		block.add(new Point(0,0)); // **
		block.add(new Point(1,0)); // **
		block.add(new Point(0,1));
		block.add(new Point(1,1));
		
		block.add(new Point(0,0)); // **
		block.add(new Point(1,0)); // **
		block.add(new Point(0,1));
		block.add(new Point(1,1));
		tilemap.put(6, block);
	}

}
