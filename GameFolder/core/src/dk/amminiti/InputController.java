package dk.amminiti;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputController implements InputProcessor{

        public boolean a, s, d, w, q, leftClick, rightClick, space, n1, n2, n3, n4, n5;


        @Override
        public boolean keyDown(int keycode) {

            boolean keyProcessed = false;
            switch (keycode) {
                case Input.Keys.SPACE:
                    space = true;
                    keyProcessed = true;
                    break;
                case Input.Keys.W:
                    w = true;
                    keyProcessed = true;
                    break;
                case Input.Keys.A:
                    a = true;
                    keyProcessed = true;
                    break;
                case Input.Keys.S:
                    s = true;
                    keyProcessed = true;
                    break;
                case Input.Keys.D:
                    d = true;
                    keyProcessed = true;
                    break;
                case Input.Keys.Q:
                    q = true;
            }
            return keyProcessed;
        }

        @Override
        public boolean keyUp(int keycode) {
            boolean keyProcessed = false;
            switch (keycode) {
                case Input.Keys.SPACE:
                    space = false;
                    keyProcessed = true;
                    break;
                case Input.Keys.W:
                    w = false;
                    keyProcessed = true;
                    break;
                case Input.Keys.A:
                    a = false;
                    keyProcessed = true;
                    break;
                case Input.Keys.D:
                    d = false;
                    keyProcessed = true;
                    break;
                case Input.Keys.S:
                    s = false;
                    keyProcessed = true;
                    break;
                case Input.Keys.Q:
                    q = false;
                    keyProcessed = true;
                    break;
            }
            return keyProcessed;
        }


        @Override
        public boolean keyTyped(char character) {
            return false;
        }
        //Mouse click

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            boolean clickProcessed = false;
            if (button == Input.Buttons.LEFT) {
                leftClick = true;
                clickProcessed = true;
            }
            return clickProcessed;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            boolean clickProcessed = false;
            if (button == Input.Buttons.LEFT) {
                leftClick = false;
                clickProcessed = true;
            }
            return clickProcessed;
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
            return true;
        }


    }
