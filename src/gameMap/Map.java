package gameMap;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.gson.annotations.Expose;

import entity.Pair;

public class Map {
	private Block[][] mappa;
	int row = 0;//
	int col = 0;//
	private FactoryBlock factory = new FactoryBlock();
	private int xDim;
	private int yDim;

	public Map(int x, int y) {
		xDim = x;
		yDim = y;
		mappa = new Block[x][y];

		for (int i = 0; i < y; i++) {
			mappa[0][i] = factory.getObstacleBlock(0, i);
			mappa[x - 1][i] = factory.getObstacleBlock(x - 1, i);
		}
		for (int i = 0; i < x; i++) {
			mappa[i][0] = factory.getObstacleBlock(i, 0);
			mappa[i][y - 1] = factory.getObstacleBlock(i, y - 1);
		}

		for (int i=1; i<x-1; i++) {
			for (int j = 1; j < y - 1; j++) {
				if (i > 1 && j > 1) {
					if (i < x / 4 && j < y / 2) {
						mappa[i][j] = factory.getFieldBlock(i, j);
					} else if (i < (x / 2)-2 && j < y - 4) {
						mappa[i][j] = factory.getLockedBlock(i, j);
					}else {
						mappa[i][j] = factory.getTerrainBlock(i, j);
					}
				} else {
					mappa[i][j] = factory.getTerrainBlock(i, j);
				}
			}
		}
	}

//		for(int i=0; i<x; i++) {
//			for(int j=0; j<y; j++) {
//				if(new Random().nextInt(100) >50) {
//					mappa[i][j] = factory.getTerrainBlock(i,j);					
//				}else if( new Random().nextInt(100) < 40) {
//					mappa[i][j] = factory.getObstacleBlock(i,j);					
//				}else if( new Random().nextInt(100) <50) {
//					mappa[i][j] = factory.getFieldBlock(i,j);					
//				}else {
//					mappa[i][j] = factory.getLockedBlock(i,j);
//				}
//			}
//		}
//	}

	// return block in the position indicated
	public Block getBlock(int x, int y) {
		return mappa[x][y];
	}

	// return set of the map
	public Set<Block> getMapSet() {
		Set<Block> mapSet = new HashSet<>();

		int row = mappa.length;
		int col = mappa[0].length;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				mapSet.add(mappa[i][j]);
			}
		}

		return mapSet;
	}

	public Pair<Integer, Integer> getBlockPosition(Block b) {
		for (int i = 0; i < xDim; i++) {
			for (int j = 0; j < yDim; j++) {
				if (mappa[i][j] == b)
					return new Pair<>(i, j);
			}
		}
		return null;
	}

	public void setBlock(int x, int y, BlockType bt) {
		if (bt == BlockType.FIELD) {
			mappa[x][y] = factory.getFieldBlock(x, y);
		}
	}
}
