package gui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Direction;
import gameMap.BlockType;
import item.Food;
import item.Texturable;
import item.SeedType;

public class Resources {
	private static final String RES_FOLDER = "res";

	private static Map<Texturable, BufferedImage> textures = new HashMap<>();
	private static File mainTheme;
	private static int animationDelay = 0;
	private static final int ANIMATION_SPEED = 500;

	public void load() {
		try {
			textures.put(SeedType.CARROT_SEED, ImageIO.read(getClass().getResourceAsStream("/carrot.png")));

			//textures.put(Food.PORK_MEET, new BufferedReader(new InputStreamReader("carrot.png")));
			textures.put(Food.PORK_MEET, ImageIO.read(new File(RES_FOLDER + File.separator + "carrot.png")));
			textures.put(Food.COW_MEET, ImageIO.read(new File(RES_FOLDER + File.separator + "carrot.png")));
			textures.put(Food.EGG, ImageIO.read(new File(RES_FOLDER + File.separator + "carrot.png")));
			textures.put(Food.MILK, ImageIO.read(new File(RES_FOLDER + File.separator + "carrot.png")));
			textures.put(Food.CARROT, ImageIO.read(new File(RES_FOLDER + File.separator + "carrot.png")));
			textures.put(Food.POTATO, ImageIO.read(new File(RES_FOLDER + File.separator + "potato.png")));
			textures.put(Food.TOMATO, ImageIO.read(new File(RES_FOLDER + File.separator + "tomato.png")));
			textures.put(Food.APPLE, ImageIO.read(new File(RES_FOLDER + File.separator + "carrot.png")));
			textures.put(Food.ORANGE, ImageIO.read(new File(RES_FOLDER + File.separator + "carrot.png")));
			textures.put(Food.CHERRY, ImageIO.read(new File(RES_FOLDER + File.separator + "carrot.png")));
			textures.put(Food.WHEAT, ImageIO.read(new File(RES_FOLDER + File.separator + "weat.png")));

			textures.put(SeedType.WHEAT_SEED, ImageIO.read(new File(RES_FOLDER + File.separator + "weat.png")));
			//textures.put(SeedType.CARROT_SEED, ImageIO.read(new File(RES_FOLDER + File.separator + "carrot.png")));
			textures.put(SeedType.POTATO_SEED, ImageIO.read(new File(RES_FOLDER + File.separator + "potato.png")));
			textures.put(SeedType.TOMATO_SEED, ImageIO.read(new File(RES_FOLDER + File.separator + "tomato.png")));
			textures.put(SeedType.APPLE_SEED, ImageIO.read(new File(RES_FOLDER + File.separator + "stop.png")));
			textures.put(SeedType.ORANGE_SEED, ImageIO.read(new File(RES_FOLDER + File.separator + "stop.png")));
			textures.put(SeedType.CHERRY_SEED, ImageIO.read(new File(RES_FOLDER + File.separator + "stop.png")));

			textures.put(BlockType.TERRAIN, ImageIO.read(new File(RES_FOLDER + File.separator + "grass.png")));
			textures.put(BlockType.FIELD, ImageIO.read(new File(RES_FOLDER + File.separator + "farmland.png")));
			textures.put(BlockType.WALL, ImageIO.read(new File(RES_FOLDER + File.separator + "wall.png")));
			textures.put(BlockType.LOCKED, ImageIO.read(new File(RES_FOLDER + File.separator + "lock3.png")));
			textures.put(BlockType.WATER, ImageIO.read(new File(RES_FOLDER + File.separator + "water.png")));
			
			textures.put(texture.LABELHUD, ImageIO.read(new File(RES_FOLDER + File.separator + "labelHUD.png")));
			textures.put(texture.MONEY, ImageIO.read(new File(RES_FOLDER + File.separator + "moneyIcon.png")));
			textures.put(texture.SEED, ImageIO.read(new File(RES_FOLDER + File.separator + "pianta.png")));
			textures.put(texture.TIME, ImageIO.read(new File(RES_FOLDER + File.separator + "timeIcon.png")));
			textures.put(texture.INFO, ImageIO.read(new File(RES_FOLDER + File.separator + "infoIcon.png")));
			textures.put(texture.EMPTY, ImageIO.read(new File(RES_FOLDER + File.separator + "empty.png")));

			
			textures.put(texture.PLAYER_RIGHT, ImageIO.read(new File(RES_FOLDER + File.separator + "right0.png")));
			textures.put(texture.PLAYER_RIGHT2, ImageIO.read(new File(RES_FOLDER + File.separator + "right1.png")));
			textures.put(texture.PLAYER_LEFT, ImageIO.read(new File(RES_FOLDER + File.separator + "left0.png")));
			textures.put(texture.PLAYER_LEFT2, ImageIO.read(new File(RES_FOLDER + File.separator + "left1.png")));
			textures.put(texture.PLAYER_DOWN, ImageIO.read(new File(RES_FOLDER + File.separator + "down0.png")));
			textures.put(texture.PLAYER_DOWN2, ImageIO.read(new File(RES_FOLDER + File.separator + "down1.png")));
			textures.put(texture.PLAYER_UP, ImageIO.read(new File(RES_FOLDER + File.separator + "up0.png")));
			textures.put(texture.PLAYER_UP2, ImageIO.read(new File(RES_FOLDER + File.separator + "up1.png")));
			textures.put(texture.PLAYER, ImageIO.read(new File(RES_FOLDER + File.separator + "stop.png")));

			mainTheme = new File(RES_FOLDER + File.separator + "hd.wav");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getPlayerInDirection(Direction dir) {
		animationDelay++;
		animationDelay %= ANIMATION_SPEED;
		boolean isFirstFrame = animationDelay > (ANIMATION_SPEED / 2);

		if (dir.isRight()) {
			if (isFirstFrame) {
				return textures.get(texture.PLAYER_RIGHT);
			} else {
				return textures.get(texture.PLAYER_RIGHT2);
			}
		} else if (dir.isLeft()) {
			if (isFirstFrame) {
				return textures.get(texture.PLAYER_LEFT);
			} else {
				return textures.get(texture.PLAYER_LEFT2);
			}
		} else if (dir.isDown()) {
			if (isFirstFrame) {
				return textures.get(texture.PLAYER_DOWN);
			} else {
				return textures.get(texture.PLAYER_DOWN2);
			}
		} else if (dir.isUp()) {
			if (isFirstFrame) {
				return textures.get(texture.PLAYER_UP);
			} else {
				return textures.get(texture.PLAYER_UP2);
			}
		} else {
			return textures.get(texture.PLAYER);
		}
	}

	public static BufferedImage getTextures(Texturable item) {
		return Resources.textures.get(item);
	}

//	public  <X> BufferedImage getSeedTextures(X x) {
//		if (x instanceof SeedType) {
//			SeedType new_name = (SeedType) x;
//			
//		}
//	}

//	public static BufferedImage getAtIndx(int indx) {
//		return textures.get(indx);
//	}

	public static enum texture implements Texturable {
		PLAYER, PLAYER_LEFT, PLAYER_LEFT2, PLAYER_RIGHT, PLAYER_RIGHT2, PLAYER_UP, PLAYER_UP2, PLAYER_DOWN,
		PLAYER_DOWN2,

		LABELHUD, MONEY, SEED, TIME, INFO, EMPTY;

	}

	public static File getMainTheme() {
		return mainTheme;
	}
}
