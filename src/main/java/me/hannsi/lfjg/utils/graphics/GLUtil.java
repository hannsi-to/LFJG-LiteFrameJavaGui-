package me.hannsi.lfjg.utils.graphics;

import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

public class GLUtil {
    private final Map<Integer, Boolean> targets;

    public GLUtil() {
        this.targets = new HashMap<>();
    }

    public void addGLTarget(int target) {
        targets.put(target, false);
    }

    public void addGLTarget(int target, boolean disable) {
        targets.put(target, disable);
    }

    public void enableTargets() {
        GL11.glPushMatrix();

        for (Map.Entry<Integer, Boolean> entry : targets.entrySet()) {
            if (entry.getValue()) {
                disable(entry.getKey());
            } else {
                enable(entry.getKey());
            }
        }
    }

    public void disableTargets() {
        for (Map.Entry<Integer, Boolean> entry : targets.entrySet()) {
            if (entry.getValue()) {
                enable(entry.getKey());
            } else {
                disable(entry.getKey());
            }
        }

        GL11.glPopMatrix();
    }

    private void enable(int target) {
        GL11.glEnable(target);
    }

    private void disable(int target) {
        GL11.glDisable(target);
    }

    public void finish() {
        targets.clear();
    }
}
