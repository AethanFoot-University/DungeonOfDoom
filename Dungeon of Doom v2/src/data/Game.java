package data;

import static helpers.MapLoader.*;
import static helpers.Artist.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import UI.UI;

public class Game {
	
	private TileGrid grid;
	private HumanPlayer player;
	private UI gameUI;
	private Texture menuBackground;
	private int currentLevel;
	private ArrayList<Player> players;
	
	public Game() {
		currentLevel = 1;
		players = new ArrayList<Player>();
		loadLevel();
	}
	
	private void setupUI() {
		gameUI = new UI();
	}
	
	private void updateUI() {
		gameUI.draw();
		gameUI.drawString(1310, 10, getMapName());
		gameUI.drawString(1310, 40, "Gold Required: " + getGoldRequired());
		gameUI.drawString(1310, 70, "Gold: " + player.getPlayerGold());
	}
	
	public void update() {
		player = (HumanPlayer) players.get(0);
		drawQuadTexRot(menuBackground, 1280, 0, 192, 960, 0);
		grid.draw();
		for (int i = 0; i < players.size(); i++) {
			players.get(i).draw();
		}
		updateUI();
		
		if (player.isPlayerTurn()) {
			player.update();
			players.get(1).setPlayerTurn(!player.isPlayerTurn());
		}
		for (int i = 1; i < players.size(); i++) {
			if (players.get(i).isPlayerTurn()) {
				players.get(i).update();
				if (i < players.size() - 1) {
					players.get(i + 1).setPlayerTurn(!players.get(i).isPlayerTurn());
				} else {
					players.get(0).setPlayerTurn(!players.get(i).isPlayerTurn());
				}
			}
		}
		
		if (player.isLevelFinished()) {
			currentLevel++;
			loadLevel();
			player.setLevelFinished(false);
		}
	}
	
	private void loadLevel() {
		String map = "test" + currentLevel;
		readMap(map);
		int num = 3;
		
		grid = new TileGrid(getMap());
		createPlayers(num);
		menuBackground = quickLoad("MenuBackground");
		setupUI();
	}
	
	private void createPlayers(int num) {
		players.add(new HumanPlayer());
		for(int i = 0; i < num; i++) {
			players.add(new BotPlayer(players.get(0).getPlayerPos()));
		}
		
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setPlayers(players);
		}
	}
}
