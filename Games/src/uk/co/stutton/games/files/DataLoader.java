package uk.co.stutton.games.files;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataLoader {
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public void writeToFile(String fileName, Object data ){
		String jsonOutput = gson.toJson(data);
		
		try {
			//write converted json data to a file named "file.json"
			FileWriter writer = new FileWriter("test/src/resources/" + fileName + ".json");
			writer.write(jsonOutput);
			writer.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
