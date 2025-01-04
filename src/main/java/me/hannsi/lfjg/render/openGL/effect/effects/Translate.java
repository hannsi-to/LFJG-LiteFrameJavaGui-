package me.hannsi.lfjg.render.openGL.effect.effects;

import me.hannsi.lfjg.render.openGL.effect.system.EffectBase;
import me.hannsi.lfjg.render.openGL.renderers.GLObject;
import org.joml.Vector2f;

public class Translate extends EffectBase {
    private float x;
    private float y;
    private float z;

    public Translate(Vector2f resolution, float x, float y, float z) {
        super(resolution,2, "Translate", (Class<GLObject>) null);

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Translate(Vector2f resolution, double x, double y, double z) {
        this(resolution,(float) x, (float) y, (float) z);
    }

    public Translate(Vector2f resolution, float x, float y) {
        this(resolution,x, y, 0.0f);
    }

    public Translate(Vector2f resolution, double x, double y) {
        this(resolution,x, y, 0.0f);
    }

    @Override
    public void pop(GLObject baseGLObject) {
        baseGLObject.setModelMatrix(baseGLObject.getModelMatrix().translate(-x, -y, -z));

        super.pop(baseGLObject);
    }

    @Override
    public void push(GLObject baseGLObject) {
        baseGLObject.setModelMatrix(baseGLObject.getModelMatrix().translate(x, y, z));

        super.push(baseGLObject);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
