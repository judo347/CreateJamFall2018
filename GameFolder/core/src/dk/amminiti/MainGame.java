package dk.amminiti;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.amminiti.screens.GameScreen;

public class MainGame extends Game {
	SpriteBatch batch;
	Texture img;
	private GameScreen screen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		this.screen = new GameScreen();
		this.setScreen(screen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
