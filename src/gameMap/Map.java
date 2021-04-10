package gameMap;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

import com.google.gson.annotations.Expose;

import entity.Pair;

public class Map {
	private Block[][] mappa;
	private final static Integer ROW = 18;
	private final static Integer COLUMN = 32;
	private FactoryBlock factory = new FactoryBlock();
	private int xDim;
	private int yDim;

	public Map() {
		xDim = ROW;
		yDim = COLUMN;
		mappa = new Block[COLUMN][ROW];

		int x = 0;
		int y = 0;

		try {
			File mapFile = new File("res/map/map.txt");
			Scanner myReader = new Scanner(mapFile);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				String[] rowNumbers = data.split(" ");
				for (String number : rowNumbers) {
					Integer num = Integer.parseInt(number);

					switch (num) {
					case 0:
						mappa[x][y] = factory.getObstacleBlock(x, y);
						break;
					case 1:
						mappa[x][y] = factory.getTerrainBlock(x, y);
						break;
					case 2:
						mappa[x][y] = factory.getFieldBlock(x, y);
						break;
					case 3:
						mappa[x][y] = factory.getLockedBlock(x, y);
						break;
					case 4:
						mappa[x][y] = factory.getWaterBlock(x, y);
						break;

					default:
						break;
					}
					x++;
				}
				y++;
				x = 0;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("file map error");
			e.printStackTrace();
		}
	}

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
