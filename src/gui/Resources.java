package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Resources {

	private static final String RES_FOLDER = "res";
	private static List<BufferedImage> textures = new ArrayList<>();
	private static File mainTheme;

	public void load() {
		try {

			textures.add(ImageIO.read(new File(RES_FOLDER + File.separator + "stop.png")));
			textures.add(ImageIO.read(new File(RES_FOLDER + File.separator + "left0.png")));
			textures.add(ImageIO.read(new File(RES_FOLDER + File.separator + "left1.png")));
			textures.add(ImageIO.read(new File(RES_FOLDER + File.separator + "right0.png")));
			textures.add(ImageIO.read(new File(RES_FOLDER + File.separator + "right1.png")));
			textures.add(ImageIO.read(new File(RES_FOLDER + File.separator + "hd.wav")));

			mainTheme = new File("res/hd.wav");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getAtIndx(int indx) {
		return textures.get(indx);
	}

	public static enum textures {
		PLAYER(0), PLAYER_LEFT(1), PLAYER_LEFT2(2), PLAYER_RIGHT(3), PLAYER_RIGHT2(4), GRASS(5);

		private int indx;

		private textures(int n) {
			this.indx = n;
		}

		public int getIndx() {
			return this.indx;
		}

	}

	public static File getMainTheme() {
		return mainTheme;
	}
}
