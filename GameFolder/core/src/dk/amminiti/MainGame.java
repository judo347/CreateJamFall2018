package dk.amminiti;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import dk.amminiti.screens.GameScreen;

public class MainGame extends Game {
	private GameScreen screen;

	@Override
	public void create () {
		this.screen = new GameScreen(this);
		this.setScreen(screen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
