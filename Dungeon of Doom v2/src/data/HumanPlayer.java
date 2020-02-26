package data;

import helpers.MapLoader;

import org.lwjgl.input.Keyboard;

import static helpers.Artist.*;

public class HumanPlayer extends Player {
	private int playerGold;
	private boolean hasMoved;
	private boolean levelFinished;
	
	public HumanPlayer() {
		
		playerTurn = true;
		hasMoved = false;
		levelFinished = false;
		playerGold = 0;
		textureRight = quickLoad(TileType.HumanRight.textureName);
		textureLeft = quickLoad(TileType.HumanLeft.textureName);
		
		float[] nul = {0 , 0};
		spawnPlayer(nul);
	}
	
	public void inputKey() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W) && !hasMoved) {
			processInput('W');
			hasMoved = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S) && !hasMoved) {
			processInput('S');
			hasMoved = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) && !hasMoved) {
			processInput('D');
			hasMoved = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A) && !hasMoved) {
			processInput('A');
			hasMoved = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E) && !hasMoved) {
			use();
			hasMoved = true;
		}
		if (hasMoved && !levelFinished) {
			hasMoved = false;
			playerTurn = false;
		}
	}
	
	private void use() {
		int x = (int) playerPos[0] / TILE_SIZE;
		int y = (int) playerPos[1] / TILE_SIZE;
		
		if (grid.getTile(x, y).getType() == TileType.Gold) {
			playerGold++;
			grid.setTile(x, y, TileType.Gravel);
		}
		if (grid.getTile(x, y).getType() == TileType.Exit && playerGold >= MapLoader.getGoldRequired()) {
			levelFinished = true;
		}
	}
	
	public int getPlayerGold() {
		return playerGold;
	}

	public boolean isLevelFinished() {
		return levelFinished;
	}

	public void setLevelFinished(boolean levelFinished) {
		this.levelFinished = levelFinished;
	}
}
