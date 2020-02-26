package data;

import helpers.Clock;
import helpers.StateManager;

import org.lwjgl.opengl.Display;

import static helpers.Artist.*;

public class Boot {
	
	public Boot() {
		//Draws the screen
		beginSession();
		
		//Continue until screen is closed
		while (!Display.isCloseRequested()) {
			//Update everything
			Clock.update();
			StateManager.update();
			Display.update();
			
			//Sync screen to 60 fps
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new Boot();
	}
}
