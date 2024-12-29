// Benjamin Duncan
// Scene.java

import javax.swing.*;

public abstract class Scene extends JPanel implements Tickable {
    protected SceneManager sceneManager;

    public Scene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public interface SceneManager {
        public void LoadScene(Scene scene);
    }
}
