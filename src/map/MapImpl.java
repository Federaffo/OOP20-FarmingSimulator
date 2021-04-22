package map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

import block.Block;
import block.BlockType;
import block.FactoryBlock;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import entity.Pair;

public final class MapImpl implements Map {
    private static final String MAP_PATH = "/map/map.txt";
    private static final Integer ROW = 18;
    private static final Integer COLUMN = 32;

    private Block[][] mappa;
    private FactoryBlock factory;

    public MapImpl() {
        mappa = new Block[COLUMN][ROW];
        factory = new FactoryBlock();

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
                case 5:
                    mappa[x][y] = factory.getStallBlock(x, y);
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(mappa);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MapImpl other = (MapImpl) obj;
        if (!Arrays.deepEquals(mappa, other.mappa)) {
            return false;
        }
        return true;
    }

    public Block getBlock(final Pair<Integer, Integer> pos) {
        return mappa[pos.getX()][pos.getY()];
    }

    public void setBlock(final Pair<Integer, Integer> pos, final BlockType bt) {
        Integer x = pos.getX();
        Integer y = pos.getY();

        if (bt == BlockType.FIELD) {
            mappa[x][y] = factory.getFieldBlock(x, y);
        }
    }

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

    public Pair<Integer, Integer> getBlockCoordinates(final Block b) {
        for (int i = 0; i < COLUMN; i++) {
            for (int j = 0; j < ROW; j++) {
                if (mappa[i][j].equals(b)) {
                    return new Pair<>(i, j);
                }
            }
        }
        return null;
    }

    public Block getRandomFilterBlock(final Predicate<Block> blockFilter) {
        Random rnd = new Random();
        Block b;
        do {
            b = mappa[rnd.nextInt(COLUMN)][rnd.nextInt(ROW)];
        } while (!blockFilter.test(b));

        return b;
    }

    @Override
    public int getRows() {
        return ROW;
    }

    @Override
    public int getColumns() {
        return COLUMN;
    }
}
