package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import entity.Direction;
import gameMap.BlockType;
import item.FoodType;
import item.Texturable;
import item.SeedType;
import entity.AnimalType;

public class Resources {
	private static final String FILE_SEPARATOR = File.separator;
	private static final String TEXTURE_FOLDER = "/texture";

	private static Map<Texturable, BufferedImage> textures = new HashMap<>();
	private static InputStream mainTheme;
	private static int animationDelay = 0;
	private static final int ANIMATION_SPEED = 500;

	public void load() {
		try {
			//seed
			textures.put(SeedType.CARROT_SEED, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/carrot2.png")));
			textures.put(SeedType.WHEAT_SEED, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/weat.png")));
			textures.put(SeedType.POTATO_SEED, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/potato2.png")));
			textures.put(SeedType.TOMATO_SEED, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/tomato2.png")));
			textures.put(SeedType.ORANGE_SEED, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/orange.png")));
			textures.put(SeedType.CHERRY_SEED, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/cherry.png")));
			textures.put(SeedType.APPLE_SEED, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/apple.png")));
			
			//food

			textures.put(FoodType.PORK_MEAT, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/carrot.png")));
			textures.put(FoodType.EGG, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/carrot.png")));
			textures.put(FoodType.MILK, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/carrot.png")));
			textures.put(FoodType.CARROT, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/carrot2.png")));
			textures.put(FoodType.POTATO, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/potato2.png")));
			textures.put(FoodType.TOMATO, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/tomato2.png")));
			textures.put(FoodType.APPLE, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/appletree.png")));
			textures.put(FoodType.ORANGE, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/orangetree.png")));
			textures.put(FoodType.CHERRY, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/cherrytree.png")));
			textures.put(FoodType.WHEAT, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/weat.png")));

			//block
			textures.put(BlockType.TERRAIN, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/grass.png")));
			textures.put(BlockType.FIELD, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/farmland.png")));
			textures.put(BlockType.WALL, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/wall.png")));
			textures.put(BlockType.LOCKED, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/lock3.png")));
			textures.put(BlockType.WATER, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/water.png")));
			textures.put(BlockType.STALL, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/stall.png")));
			
			textures.put(texture.LOCK, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/lucchetto.png")));
			textures.put(texture.TREE, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/tree.png")));
			//textures.put(texture.LABELHUD, ImageIO.read(getClass().getResourceAsStream("/labelHUD.png")));

			textures.put(texture.MONEY, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/moneyIcon.png")));
			textures.put(texture.SEED, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/pianta.png")));
			textures.put(texture.TIME, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/timeIcon.png")));
			textures.put(texture.INFO, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/infoIcon.png")));
			textures.put(texture.EMPTY, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/empty.png")));

			
			//player
			textures.put(texture.PLAYER_RIGHT, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/right0.png")));
			textures.put(texture.PLAYER_RIGHT2, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/right1.png")));
			textures.put(texture.PLAYER_LEFT, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/left0.png")));
			textures.put(texture.PLAYER_LEFT2, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/left1.png")));
			textures.put(texture.PLAYER_DOWN, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/down0.png")));
			textures.put(texture.PLAYER_DOWN2, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/down1.png")));
			textures.put(texture.PLAYER_UP, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/up0.png")));
			textures.put(texture.PLAYER_UP2, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/up1.png")));
			textures.put(texture.PLAYER, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/stop.png")));


			//animals
			textures.put(AnimalType.COW, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/cow.png")));
			textures.put(AnimalType.PIG, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/pig.png")));
			textures.put(AnimalType.CHICKEN, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/chicken.png")));
			
			//keyboard commands
			textures.put(texture.WASD, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/wasd.png")));
			textures.put(texture.E_KEY, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/e.png")));
			textures.put(texture.F_KEY, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/f.png")));
			textures.put(texture.MOUSE_WHEEL, ImageIO.read(getClass().getResourceAsStream(TEXTURE_FOLDER+"/mouse.png")));
			
			mainTheme = getClass().getResourceAsStream("/hd.wav"); 

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


	public static enum texture implements Texturable {
		PLAYER, PLAYER_LEFT, PLAYER_LEFT2, PLAYER_RIGHT, PLAYER_RIGHT2, PLAYER_UP, PLAYER_UP2, PLAYER_DOWN,
		PLAYER_DOWN2,
		
		WASD, E_KEY,F_KEY, MOUSE_WHEEL,

		LABELHUD, MONEY, SEED, TIME, INFO, EMPTY, LOCK, TREE;

	}

	public static InputStream getMainTheme() {
		return mainTheme;
	}
}
