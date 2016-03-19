package net.exodiusmc.engine;

import java.util.HashSet;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class InputManager {
    private HashSet<KeyCode> keys;

    public InputManager(Stage window) {
        this.keys = new HashSet<KeyCode>();
        
        window.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            keys.add(e.getCode());
        });
        
        window.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            keys.remove(e.getCode());
        });
    }
    
    public boolean isKeyPressed(KeyCode k) {
        return this.keys.contains(k);
    }

}
