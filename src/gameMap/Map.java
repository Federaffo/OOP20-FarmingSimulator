package gameMap;

import java.util.Random;

public class Map {
	
	private Block[][] mappa;
	
	public Map(int x, int y) {
	
		mappa = new Block[x][y];
		FactoryBlock factory = new FactoryBlock();
		
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				if(new Random().nextInt(100) < 20)
					mappa[i][j] = factory.getTerrainBlock();
				else
					mappa[i][j] = factory.getFieldBlock();
			}
		}
	}
	
	//return block in the position indicated
	public Block getBlock(int x, int y) {
		return mappa[x][y];
	}
	
	
}
