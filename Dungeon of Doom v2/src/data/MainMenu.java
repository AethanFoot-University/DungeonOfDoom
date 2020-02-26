package data;

import helpers.StateManager;
import helpers.StateManager.GameState;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;

import static helpers.Artist.*;

public class MainMenu {
	
	private Texture menuBackground;
	private Texture background;
	private UI menuUI;
	
	public MainMenu() {
		background = quickLoad("MainMenuBackground");
		menuBackground = quickLoad("MenuBackground");
		menuUI = new UI();
		menuUI.addButton("Play", "PlayButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
		menuUI.addButton("Editor", "EditorButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.55f));
		menuUI.addButton("Quit", "QuitButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f));
	}
	
	private void updateButtons() {
		if (Mouse.isButtonDown(0)) {
			if (menuUI.isButtonClicked("Play")) {
				StateManager.setState(GameState.GAME);
			}
			if (menuUI.isButtonClicked("Editor")) {
				StateManager.setState(GameState.EDITOR);
			}
			if (menuUI.isButtonClicked("Quit")) {
				System.exit(0);
			}
		}
	}

	public void update() {
		drawQuadTexRot(background, 0, 0, 2048, 1024, 0);
		drawQuadTexRot(menuBackground, 1280, 0, 192, 960, 0);
		menuUI.draw();
		updateButtons();
	}
}
