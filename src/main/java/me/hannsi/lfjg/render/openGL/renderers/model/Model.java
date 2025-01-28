package me.hannsi.lfjg.render.openGL.renderers.model;

import me.hannsi.lfjg.render.openGL.system.model.Material;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final String id;
    private final List<Entity> entitiesList;
    private final List<Material> materialList;

    public Model(String id, List<Material> materialList) {
        this.id = id;
        entitiesList = new ArrayList<>();
        this.materialList = materialList;
    }

    public void cleanup() {
        materialList.forEach(Material::cleanup);
    }

    public List<Entity> getEntitiesList() {
        return entitiesList;
    }

    public String getId() {
        return id;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }
}
