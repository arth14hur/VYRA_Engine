package com.vyra.engine.scene;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private List<Entity> entities = new ArrayList<>();

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
