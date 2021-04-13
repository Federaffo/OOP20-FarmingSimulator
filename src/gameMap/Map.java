package gameMap;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import entity.Pair;

public class Map {
	private final static String MAP_PATH ="/map/map.txt";
	
	private Block[][] mappa;
	private final static Integer ROW = 18;
	private final static Integer COLUMN = 32;
	private FactoryBlock factory = new FactoryBlock();

	public Map() {
		mappa = new Block[COLUMN][ROW];

		int x = 0;
		int y = 0;

		InputStream in = getClass().getResourceAsStream(MAP_PATH); 
		BufferedReader mapFile = new BufferedReader(new InputStreamReader(in));

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
		for (int i = 0; i < COLUMN; i++) {
			for (int j = 0; j < ROW; j++) {
				if (mappa[i][j].equals(b))
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
