package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.*;

import block.Block;
import control.Game;
import control.GameImpl;
import entity.Animal;
import entity.Entity;
import entity.player.Inventory;
import entity.player.Player;
import gameMap.Map;
import gameShop.Shop;

public class GameSaver {
	private final String dot = ".";
	private final String fileName = "saves.txt";
	private Gson gson;

	public GameSaver() {
		InterfaceAdapter interfaceAdapter = new InterfaceAdapter();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		 gson = new GsonBuilder().registerTypeAdapter(Block.class,
				 interfaceAdapter).registerTypeAdapter(Game.class, interfaceAdapter)
				 .registerTypeAdapter(Interaction.class, interfaceAdapter)
				 .registerTypeAdapter(Player.class,	interfaceAdapter)
				 .registerTypeAdapter(Inventory.class, interfaceAdapter)
				 .registerTypeAdapter(Map.class, interfaceAdapter)
				 .registerTypeAdapter(Animal.class, interfaceAdapter)
				 .registerTypeAdapter(Shop.class, interfaceAdapter).setPrettyPrinting().create();

		
		
//		Stream.of(Package.getPackages()).forEach(x -> {
//			try {
//				InterfaceLoader.getInterfaces(x.getName())
//						.forEach(y -> gsonBuilder.registerTypeAdapter(y, interfaceAdapter));
//			} catch (ClassNotFoundException | IOException e) {
//				e.printStackTrace();
//			}
//		});
//		gson = gsonBuilder.create();
	}

	public boolean isSavingPresent() {
		return new File(dot + File.separator + fileName).exists();
	}

	public void save(Game game) {
		String json = gson.toJson(game);
		try (FileWriter writer = new FileWriter(dot + File.separator + fileName)) {
			gson.toJson(game, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GameImpl load() {
		try {
			File myObj = new File(dot + File.separator + fileName);
			
			InputStream in = getClass().getResourceAsStream("/saves.txt"); 
			BufferedReader saves = new BufferedReader(new InputStreamReader(in));

			
			String json = "";
			Scanner myReader = new Scanner(saves);
			while (myReader.hasNextLine()) {
				json += myReader.nextLine();
			}
			myReader.close();

			return gson.fromJson(json, GameImpl.class);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private class InterfaceAdapter<T> implements JsonDeserializer<T>, JsonSerializer<T> {

		private static final String CLASSNAME = "CLASSNAME";
		private static final String DATA = "DATA";

		public JsonElement serialize(T jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
			jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
			return jsonObject;
		}

		public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
				throws JsonParseException {

			JsonObject jsonObject = jsonElement.getAsJsonObject();
			JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
			String className = prim.getAsString();
			Class klass = getObjectClass(className);
			return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
		}

		public Class getObjectClass(String className) {
			try {
				return Class.forName(className);
			} catch (ClassNotFoundException e) {
				// e.printStackTrace();
				throw new JsonParseException(e.getMessage());
			}
		}

	}

}
