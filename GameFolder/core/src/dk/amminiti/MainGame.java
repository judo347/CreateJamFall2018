package dk.amminiti;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.entity.Player;
import dk.amminiti.screens.GameScreen;

public class MainGame extends Game {
	private GameScreen screen;
	private Player p1;

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
