package data;

import static helpers.Artist.*;
import static helpers.MapLoader.*;
import helpers.StateManager;
import helpers.StateManager.GameState;

import java.util.*;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public abstract class Player {
	
	protected boolean playerTurn;
	private boolean spawning;
	protected float[] playerPos = {0, 0};
	private float speed, finalX, finalY;
	private Texture texture;
	protected Texture textureRight;
	protected Texture textureLeft;
	protected TileGrid grid;
	protected ArrayList<Player> players;
	
	protected abstract void inputKey();
  
	protected void spawnPlayer(float[] otherPos) {
		Random randNum = new Random();
		grid = new TileGrid(getMap());
		spawning = true;
		speed = 5;
		texture = textureRight;
		
		while (spawning) {
			//Create random spawn location.
			int x = randNum.nextInt(grid.getTilesWide());
			int y = randNum.nextInt(grid.getTilesHigh());
			
			//Check the spawn location is not in a wall or on gold or on the bot.
			if (grid.getTile(x, y).getType().canSpawnOn && x * TILE_SIZE != otherPos[0] && y * TILE_SIZE != otherPos[1]) {
				playerPos[0] = x * TILE_SIZE;
				playerPos[1] = y * TILE_SIZE;
				spawning = false;
			}
		 }
	}
	
	public void draw() {
		drawQuadTexRot(texture, playerPos[0], playerPos[1], TILE_SIZE, TILE_SIZE, 0);
	}
	
	public void update() {
		inputKey();
	}
	
	public void processInput(char input) {
		switch (input) {
			case 'W':
				movePlayer(input);
				break;
			case 'S':
				movePlayer(input);
				break;
			case 'D':
				texture = textureRight;
				movePlayer(input);
				break;
			case 'A':
				texture = textureLeft;
				movePlayer(input);
				break;
		}
	}
	
	public void movePlayer(char dir) {
		finalY = playerPos[1];
		finalX = playerPos[0];
		int x = (int) playerPos[0] / TILE_SIZE;
		int y = (int) playerPos[1] / TILE_SIZE;
		
		if (dir == 'W' && grid.getTile(x, y - 1).getType().canWalkOn) {
			finalY -= 64;
		}
		if (dir == 'S' && grid.getTile(x, y + 1).getType().canWalkOn) {
			finalY += 64;
		}
		if (dir == 'D' && grid.getTile(x + 1, y).getType().canWalkOn) {
			finalX += 64;
		}
		if (dir == 'A' && grid.getTile(x - 1, y).getType().canWalkOn) {
			finalX -= 64;
		}
		
		while (playerPos[1] > finalY) {
			playerPos[1] -= 1 * speed;
			updateScreen();
		}
		while (playerPos[1] < finalY) {
			playerPos[1] += 1 * speed;
			updateScreen();
		}
		while (playerPos[0] < finalX) {
			playerPos[0] += 1 * speed;
			updateScreen();
		}
		while (playerPos[0] > finalX) {
			playerPos[0] -= 1 * speed;
			updateScreen();
		}

		playerPos[1] = finalY;
		playerPos[0] = finalX;
		draw();
		
		checkLoss();
			
	}
	
	private void updateScreen() {
		grid.draw();
		for (int i = 0; i < players.size(); i++) {
			players.get(i).draw();
		}
		Display.update();
	}
	
	private void checkLoss() {
		if (checkCollision()) {
			clearScreen();
			StateManager.game = null;
			StateManager.setState(GameState.MAINMENU);
		}
	}
	
	private boolean checkCollision() {
		boolean temp = false;
		for (int i = 1; i < players.size(); i++) {
			temp = (players.get(0).getPlayerPos()[0] == players.get(i).getPlayerPos()[0] && players.get(0).getPlayerPos()[1] == players.get(i).getPlayerPos()[1]);
			if (temp) break;
		}
		return temp;
	}
	
	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}

	public float[] getPlayerPos() {
		return playerPos;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
