package me.hannsi.lfjg.render.openGL.effect.effects;

import me.hannsi.lfjg.render.openGL.effect.system.EffectBase;
import me.hannsi.lfjg.render.openGL.effect.system.EffectCache;
import me.hannsi.lfjg.render.openGL.renderers.GLObject;
import me.hannsi.lfjg.render.openGL.system.rendering.FrameBuffer;
import me.hannsi.lfjg.render.openGL.system.rendering.SplitFrameBuffer;

public class SplitObject extends EffectBase {
    private final EffectCache effectCache;
    private int rows;
    private int cols;
    private int offsetX;
    private int offsetY;
    private SplitFrameBuffer splitFrameBuffer;

    public SplitObject(int rows, int cols, int offsetX, int offsetY) {
        this(rows, cols, offsetX, offsetY, null);
    }

    public SplitObject(int rows, int cols, int offsetX, int offsetY, EffectCache effectCache) {
        super(26, "SplitObject");

        this.cols = cols;
        this.rows = rows;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.effectCache = effectCache;
        if (this.effectCache != null) {
            this.effectCache.createCache("DrawFrameBuffer1", new DrawFrameBuffer(null), 0);
        }
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
        if (splitFrameBuffer == null) {
            splitFrameBuffer = new SplitFrameBuffer(getFrameBuffer(), cols, rows, offsetX, offsetY);
            splitFrameBuffer.createSmallFrameBuffers();
        }

        splitFrameBuffer.setMainFrameBuffer(getFrameBuffer());
        splitFrameBuffer.blitToSmallFrameBuffers();

        FrameBuffer[][] smallFrameBuffers = splitFrameBuffer.getSmallFrameBuffers();
        int ox = 0;
        int oy = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                FrameBuffer smallFrameBuffer = smallFrameBuffers[y][x];

                if (effectCache == null) {
                    smallFrameBuffer.getModelMatrix().translate(ox, oy, 0);
                    smallFrameBuffer.drawFrameBuffer();
                    smallFrameBuffer.getModelMatrix().translate(-ox, -oy, 0);
                } else {
                    effectCache.updateFrameBufferSize(smallFrameBuffer);

                    DrawFrameBuffer drawFrameBuffer = (DrawFrameBuffer) effectCache.getEffectBase("DrawFrameBuffer1");
                    drawFrameBuffer.setFrameBuffer(smallFrameBuffer);
                    drawFrameBuffer.setTranslateX(ox);
                    drawFrameBuffer.setTranslateY(oy);
                    effectCache.frameBuffer(baseGLObject);
                }

                ox += offsetX;
            }

            ox = 0;
            oy += offsetY;
        }

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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public SplitFrameBuffer getSplitFrameBuffer() {
        return splitFrameBuffer;
    }

    public void setSplitFrameBuffer(SplitFrameBuffer splitFrameBuffer) {
        this.splitFrameBuffer = splitFrameBuffer;
    }

    public EffectCache getEffectCache() {
        return effectCache;
    }
}
