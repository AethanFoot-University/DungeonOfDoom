package helpers;

import helpers.StateManager.GameState;

import java.io.*;
import java.util.*;

public class MapLoader {
	private static char[][] map;
	private static String mapName;
	private static int goldRequired;

    public static boolean readMap(String mapName) {
    	BufferedReader reader;
		try {
			//Reader to read from the file.
			reader = new BufferedReader(new FileReader(mapName));
			loadMap(reader);
		} catch (FileNotFoundException e) {
			//If the file is invalid.
			System.out.println("Invalid file.");
			StateManager.setState(GameState.MAINMENU);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return true;
    	
    }
    
    private static void loadMap(BufferedReader reader) throws IOException {
		//Temporary array list to store map lines.
    	ArrayList<char[]> tempList = new ArrayList<char[]>();
    	//Temporary value for length.
		int length = -1;
		
		//Check if the line starts with name.
		String line = reader.readLine();
		if (line.substring(0, 4).equals("name")) {
			mapName = line.substring(5);
			
		}
		
		//Checks if the line starts with win.
		line = reader.readLine();
		if (line.substring(0, 3).equals("win")) {
			goldRequired = Integer.parseInt(line.substring(4));
		}
		
		line = reader.readLine();
		//Set the actual value for length.
		length = line.trim().length();
		
		//Continue until end of the file.
		while (line != null) {
			//Temporary char array to hold the line.
			char[] x = new char[line.length()];
			
			//Copy the line to the char array.
			for (int i = 0; i < line.length(); i++) {
				x[i] = line.charAt(i);
			}
			//Copy the line in the char array to the list.
			tempList.add(x);

			line = reader.readLine();
		}
		
		//Create temporary map.
		char[][] newMap = new char[tempList.size()][length];
		
		//Copy the list to the the temporary map.
		for (int i = 0; i < tempList.size(); i++) {
			newMap[i] = tempList.get(i);
		}
		
		//Set the map.
		map = newMap;
	}

	public static char[][] getMap() {
		return map;
	}

	public static String getMapName() {
		return mapName;
	}

	public static int getGoldRequired() {
		return goldRequired;
	}
    
}
