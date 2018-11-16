package dk.amminiti;

import com.badlogic.gdx.InputProcessor;

public class PlayerInputProcessor implements InputProcessor {

    private final int codeUp, codeDown, codeLeft, codeRight, codePrimary, codeSecondary;
    private boolean upPressed, downPressed, leftPressed, rightPressed, jumpPressed, primaryPressed, secondaryPressed;

    public PlayerInputProcessor(int codeUp, int codeDown, int codeLeft, int codeRight, int codePrimary, int codeSecondary) {
        this.codeUp = codeUp;
        this.codeDown = codeDown;
        this.codeLeft = codeLeft;
        this.codeRight = codeRight;
        this.codePrimary = codePrimary;
        this.codeSecondary = codeSecondary;
    }

    @Override
    public boolean keyDown(int keycode) {
        boolean keyProcessed = false;
        if (keycode == codeUp) upPressed = keyProcessed = true;
        else if (keycode == codeDown) downPressed = keyProcessed = true;
        else if (keycode == codeLeft) leftPressed = keyProcessed = true;
        else if (keycode == codeRight) rightPressed = keyProcessed = true;
        else if (keycode == codePrimary) primaryPressed = keyProcessed = true;
        else if (keycode == codeSecondary) secondaryPressed = keyProcessed = true;
        return keyProcessed;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean keyProcessed = false;
        if (keycode == codeUp) { upPressed = false; keyProcessed = true; }
        else if (keycode == codeDown) { downPressed = false; keyProcessed = true; }
        else if (keycode == codeLeft) { leftPressed = false; keyProcessed = true; }
        else if (keycode == codeRight) { rightPressed = false; keyProcessed = true; }
        else if (keycode == codePrimary) { primaryPressed = false; keyProcessed = true; }
        else if (keycode == codeSecondary) { secondaryPressed = false; keyProcessed = true; }
        return keyProcessed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isJumpPressed() {
        return jumpPressed;
    }

    public boolean isPrimaryPressed() {
        return primaryPressed;
    }

    public boolean isSecondaryPressed() {
        return secondaryPressed;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
