package dk.amminiti;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputController extends InputMultiplexer {

    private PlayerInputProcessor playerOneProcessor = new PlayerInputProcessor(
            Input.Keys.UP,
            Input.Keys.DOWN,
            Input.Keys.LEFT,
            Input.Keys.RIGHT,
            Input.Keys.COMMA,
            Input.Keys.PERIOD
    );

    private PlayerInputProcessor playerTwoProcessor = new PlayerInputProcessor(
            Input.Keys.R,
            Input.Keys.F,
            Input.Keys.D,
            Input.Keys.G,
            Input.Keys.Q,
            Input.Keys.A
    );

    public InputController() {
        addProcessor(playerOneProcessor);
        addProcessor(playerTwoProcessor);
    }

    public PlayerInputProcessor getPlayerInput(int index) {
        if (index == 0)
            return playerOneProcessor;
        return playerTwoProcessor;
    }
}
