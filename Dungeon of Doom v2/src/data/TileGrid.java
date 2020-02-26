package data;

import static helpers.Artist.*;

public class TileGrid {
	public static Tile[][] map;
	private int tilesWide, tilesHigh;
	
	public TileGrid() {
		tilesWide = 20;
		tilesHigh = 15;
		map = new Tile[20][15];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (i == 0 || i == map.length - 1 || j == 0 || j == map[i].length -1) {
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Rock);
				}
				else {
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Gravel);
				}
			}
		}
	}
	
	public TileGrid(char[][] newMap) {
		tilesWide = 20;
		tilesHigh = 15;
		map = new Tile[20][15];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				char mapPiece = newMap[j][i];
				
				switch (mapPiece) {
					case '.':
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Gravel);
						break;
					case '#':
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Rock);
						break;
					case 'G':
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Gold);
						break;
					case 'E':
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Exit);
						break;
					case 'P':
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.HumanRight);
						break;
					case 'B':
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.MonsterRight);
						break;
				}
			}
		}
	}
	
	public void draw() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Tile t = map[i][j];
				drawQuadTexRot(t.getTexture(), t.getX(), t.getY(), t.getWidth(), t.getHeight(), 0);
			}
		}
	}
	
	public void setTile(int x, int y, TileType type) {
		map[x][y] = new Tile(x * 64, y * 64, 64, 64, type);
	}
	
	public Tile getTile(int x, int y) {
		return map[x][y];
	}
	
	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}
	
}
