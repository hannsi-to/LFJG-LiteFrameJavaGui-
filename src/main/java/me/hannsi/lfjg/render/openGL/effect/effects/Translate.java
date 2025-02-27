package me.hannsi.lfjg.render.openGL.effect.effects;

import me.hannsi.lfjg.render.openGL.effect.system.EffectBase;
import me.hannsi.lfjg.render.openGL.renderers.GLObject;

/**
 * Class representing a Translate effect in OpenGL.
 */
public class Translate extends EffectBase {
    private float x;
    private float y;
    private float z;

    /**
     * Constructs a new Translate effect with the specified parameters.
     *
     * @param x the translation distance along the X axis
     * @param y the translation distance along the Y axis
     * @param z the translation distance along the Z axis
     */
    public Translate(float x, float y, float z) {
        super(2, "Translate", (Class<GLObject>) null);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a new Translate effect with the specified parameters.
     *
     * @param x the translation distance along the X axis
     * @param y the translation distance along the Y axis
     * @param z the translation distance along the Z axis
     */
    public Translate(double x, double y, double z) {
        this((float) x, (float) y, (float) z);
    }

    /**
     * Constructs a new Translate effect with the specified parameters.
     *
     * @param x the translation distance along the X axis
     * @param y the translation distance along the Y axis
     */
    public Translate(float x, float y) {
        this(x, y, 0.0f);
    }

    /**
     * Constructs a new Translate effect with the specified parameters.
     *
     * @param x the translation distance along the X axis
     * @param y the translation distance along the Y axis
     */
    public Translate(double x, double y) {
        this(x, y, 0.0f);
    }

    /**
     * Pops the transformation from the stack and applies the inverse translation to the base GL object.
     *
     * @param baseGLObject the base GL object
     */
    @Override
    public void pop(GLObject baseGLObject) {
        super.pop(baseGLObject);
    }

    /**
     * Pushes the transformation onto the stack and applies the translation to the base GL object.
     *
     * @param baseGLObject the base GL object
     */
    @Override
    public void push(GLObject baseGLObject) {
        super.push(baseGLObject);
    }

    /**
     * Pushes the frame buffer for the base GL object.
     *
     * @param baseGLObject the base GL object
     */
    @Override
    public void frameBufferPush(GLObject baseGLObject) {
        getFrameBuffer().bindFrameBuffer();
        super.frameBufferPush(baseGLObject);
    }

    /**
     * Pops the frame buffer for the base GL object.
     *
     * @param baseGLObject the base GL object
     */
    @Override
    public void frameBufferPop(GLObject baseGLObject) {
        getFrameBuffer().unbindFrameBuffer();
        super.frameBufferPop(baseGLObject);
    }

    /**
     * Draws the frame buffer for the base GL object.
     *
     * @param baseGLObject the base GL object
     */
    @Override
    public void frameBuffer(GLObject baseGLObject) {
        getFrameBuffer().getModelMatrix().translate(x, y, z);
        getFrameBuffer().drawFrameBuffer();
        getFrameBuffer().getModelMatrix().translate(-x, -y, -z);
        super.frameBuffer(baseGLObject);
    }

    /**
     * Sets the uniform variables for the shader program.
     *
     * @param baseGLObject the base GL object
     */
    @Override
    public void setUniform(GLObject baseGLObject) {
        super.setUniform(baseGLObject);
    }

    /**
     * Gets the translation distance along the X axis.
     *
     * @return the translation distance along the X axis
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the translation distance along the X axis.
     *
     * @param x the translation distance along the X axis
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Gets the translation distance along the Y axis.
     *
     * @return the translation distance along the Y axis
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the translation distance along the Y axis.
     *
     * @param y the translation distance along the Y axis
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Gets the translation distance along the Z axis.
     *
     * @return the translation distance along the Z axis
     */
    public float getZ() {
        return z;
    }

    /**
     * Sets the translation distance along the Z axis.
     *
     * @param z the translation distance along the Z axis
     */
    public void setZ(float z) {
        this.z = z;
    }
}