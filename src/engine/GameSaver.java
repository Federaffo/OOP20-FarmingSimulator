package engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

import com.google.gson.*;

import entity.Player;
import gameMap.Block;
import gameMap.FactoryBlock;

public class GameSaver {

	private Game game;
	private Gson gson;

	public GameSaver() {
		 gson = new GsonBuilder().registerTypeAdapter(Block.class, new InterfaceAdapter()).setPrettyPrinting().create();
	}

	public void save(Game game) {
		String json = gson.toJson(game);

		Game object = gson.fromJson(json, Game.class);
		System.out.println(json);
		
		
		try (FileWriter writer = new FileWriter("C:\\Users\\feder\\Desktop\\SO\\test.txt")) {
			gson.toJson(game, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(object.getMap().getBlock(4, 10).getType());
//		System.out.println(object.getPlayer());

	}
	
	public Game load() {
		try {
			File myObj = new File("C:\\Users\\feder\\Desktop\\SO\\test.txt");
			String json = "";
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		         json += myReader.nextLine();
		      }
		      myReader.close();
			
		      return gson.fromJson(json, Game.class);
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public class InterfaceAdapter<T> implements JsonDeserializer<T>, JsonSerializer<T> {

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
