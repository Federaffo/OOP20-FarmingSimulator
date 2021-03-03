package gameMap;


public class Map {
	
	private Block[][] mappa;
	
	public Map(int x, int y) {

		mappa = new Block[x][y];
		FactoryBlock factory = new FactoryBlock();
		
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				mappa[i][j] = factory.getTerrainBlock();
			}
		}
	}
	
	//return block in the position indicated
	public Block getBlock(int x, int y) {
		return mappa[x][y];
	}
	
	
}
