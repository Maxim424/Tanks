import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyState implements KeyListener {
        private boolean [] states = new boolean[256];
        private boolean [] oldStates = new boolean[256];


        private static KeyState instance = null;

        public static KeyState getInstance() {
            if (instance == null) {
                instance = new KeyState();
            }

            return  instance;
        }


        public void setKeyState (int keyCode, boolean state) {
            states[keyCode] = state;
        }

        public void update() {
            oldStates = (boolean []) states.clone();
        }

        public boolean keyDown(int keyCode) {
            return  states[keyCode];
        }



        public boolean press(int keyCode) {
            return   !oldStates[keyCode]  && states[keyCode]  ;
        }
        public boolean realise(int keyCode) {
            return   oldStates[keyCode]  && !states[keyCode]  ;
        }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        setKeyState(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setKeyState(e.getKeyCode(), false);
    }
}

