package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import data.Tile;
import data.TileGrid;

public class MapSaver {
	
	public static void saveMap(String mapName, int goldRequired, TileGrid grid) {
		String mapData = "";
		File file = new File(mapName);
		
		mapData += "name " + mapName + "\n";
		mapData += "win " + goldRequired + "\n";
		for (int i = 0; i < grid.getTilesHigh(); i++) {
			for (int j = 0; j < grid.getTilesWide(); j++) {
				mapData += getTileID(grid.getTile(j, i));
			}
			mapData += "\n";
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(mapData);
			bw.close();
		} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	public static String getTileID(Tile t) {
		String ID = "K";
		
		switch(t.getType()) {
			case Gravel:
				ID = ".";
				break;
			case Rock:
				ID = "#";
				break;
			case Gold:
				ID = "G";
				break;
			case Exit:
				ID = "E";
				break;
			default:
				break;
		}
		
		return ID;
	}
}
