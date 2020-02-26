package data;

import static helpers.Artist.*;

import java.util.Random;

public class BotPlayer extends Player{
	
	private static float[] humanPos;
	
	public BotPlayer(float[] humanPos) {
		playerTurn = false;
		textureRight = quickLoad(TileType.MonsterRight.textureName);
		textureLeft = quickLoad(TileType.MonsterLeft.textureName);
		spawnPlayer(humanPos);
	}
	
	public void inputKey() {
		humanPos = players.get(0).getPlayerPos();
		int view = 3;
		
		Random randNum = new Random();
		int chance = randNum.nextInt(100);
	
		int dirNum = randNum.nextInt(4);
		int yDiff = (int) (humanPos[1] - playerPos[1]);
		int xDiff = (int) (humanPos[0] - playerPos[0]);
		int yLen = Math.abs(yDiff);
		int xLen = Math.abs(xDiff);
		
		if (yLen <= view * TILE_SIZE && xLen <= view * TILE_SIZE && chance < 95) {
			if (dirNum == 0 && yLen > Math.abs(yDiff + 1)) {
				processInput('W');
				playerTurn = false;
			}
			if (dirNum == 1 && yLen > Math.abs(yDiff - 1)) {
				processInput('S');
				playerTurn = false;
			}
			if (dirNum == 2 && xLen > Math.abs(xDiff - 1)) {
				processInput('D');
				playerTurn = false;
			}
			if (dirNum == 3 && xLen > Math.abs(xDiff + 1)) {
				processInput('A');
				playerTurn = false;
			}
		} else {
			if (dirNum == 0 && playerTurn) {
				processInput('W');
				playerTurn = false;
			}
			if (dirNum == 1 && playerTurn) {
				processInput('S');
				playerTurn = false;
			}
			if (dirNum == 2 && playerTurn) {
				processInput('D');
				playerTurn = false;
			}
			if (dirNum == 3 && playerTurn) {
				processInput('A');
				playerTurn = false;
			}
		}
	}
}
