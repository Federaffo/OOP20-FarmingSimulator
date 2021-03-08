package gameMap;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Map {
	
	private Block[][] mappa;
	int row = 0;//
	int col = 0;//
	
	public Map(int x, int y) {
	
		mappa = new Block[x][y];
		FactoryBlock factory = new FactoryBlock();
		
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				if(new Random().nextInt(100) > 20) {
					mappa[i][j] = factory.getTerrainBlock(i,j);					
				}else if( new Random().nextInt(100) < 50) {
					mappa[i][j] = factory.getFieldBlock(i,j);					
				}else {
					mappa[i][j] = factory.getObstacleBlock(i,j);					
				}
			}
		}
	}
	
	//return block in the position indicated
	public Block getBlock(int x, int y) {
		return mappa[x][y];
	}
	
	
	//return set of the map
	public Set<Block> getMapSet(){
		Set<Block> mapSet = new HashSet<>();
		
		int row = mappa.length;
		int col = mappa[0].length;
		
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				mapSet.add(mappa[i][j]);
			}
		}
		
		return mapSet;
	}
	
}
