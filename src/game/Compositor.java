package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Compositor {
    public interface Layer {
        void render(Graphics graphics);
        default boolean collidesWith(Rectangle other) {
            return false;
        }
    }

    List<Layer> layers;

    public Compositor() {
        layers = new ArrayList<>();
    }

    public void insertLayer(int index, Layer layer) {
        layers.add(index, layer);
    }

    public void pushLayer(Layer layer) {
        layers.add(layer);
    }

    public Layer collidesWith(Rectangle other) {
        for (Layer layer : layers) {
            if (layer.collidesWith(other)) {
                return layer;
            }
        }
        return null;
    }

    public void render(Graphics graphics) {
        for (Layer layer : layers) {
            layer.render(graphics);
        }
    }
}
