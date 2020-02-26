package data;

import helpers.StateManager;
import helpers.StateManager.GameState;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;
import UI.UI.Menu;

import static helpers.Artist.*;
import static helpers.MapSaver.*;

public class Editor {
	
	private TileGrid grid;
	private int index;
	private TileType[] types;
	private UI editorUI;
	private Menu tilePickerMenu;
	private Texture menuBackground;
	
	public Editor() {
		grid = new TileGrid();
		index = 0;
		types = new TileType[4];
		types[0] = TileType.Gravel;
		types[1] = TileType.Rock;
		types[2] = TileType.Gold;
		types[3] = TileType.Exit;
		menuBackground = quickLoad("MenuBackground");
		setupUI();
	}
	
	private void setupUI() {
		editorUI = new UI();
		editorUI.createMenu("TilePicker", 1280, 0, 192, 960, 2, 0);
		tilePickerMenu = editorUI.getMenu("TilePicker");
		tilePickerMenu.quickAdd("Gravel", "Gravel");
		tilePickerMenu.quickAdd("Rock", "Rock");
		tilePickerMenu.quickAdd("Gold", "Gold");
		tilePickerMenu.quickAdd("Exit", "Exit");
		tilePickerMenu.quickAdd("Save", "SaveButton");
		tilePickerMenu.quickAdd("ExitButton", "ExitButton");
	}

	public void update() {
		draw();
		
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (tilePickerMenu.isButtonClicked("Gravel")) {
					index = 0;
				}
				else if (tilePickerMenu.isButtonClicked("Rock")) {
					index = 1;
				}
				else if (tilePickerMenu.isButtonClicked("Gold")) {
					index = 2;
				}
				else if (tilePickerMenu.isButtonClicked("Exit")) {
					index = 3;
				}
				else if (tilePickerMenu.isButtonClicked("Save")) {
					saveMap("test5" ,  7, grid);
				}
				else if (tilePickerMenu.isButtonClicked("ExitButton")) {
					clearScreen();
					StateManager.editor = null;
					StateManager.setState(GameState.MAINMENU);
				}
				else {
					setTile();
				}
			}
		}
	}
	
	private void draw() {
		grid.draw();
		drawQuadTexRot(menuBackground, 1280, 0, 192, 960, 0);
		editorUI.draw();
	}
	
	private void setTile() {
		grid.setTile((int) Math.floor(Mouse.getX() / 64), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64), types[index]);
	}
}
