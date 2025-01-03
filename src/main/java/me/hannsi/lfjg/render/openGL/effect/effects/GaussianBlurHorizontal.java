package me.hannsi.lfjg.render.openGL.effect.effects;

import me.hannsi.lfjg.render.openGL.effect.system.EffectBase;
import me.hannsi.lfjg.render.openGL.renderers.GLObject;
import me.hannsi.lfjg.utils.math.MathUtil;
import me.hannsi.lfjg.utils.reflection.ResourcesLocation;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class GaussianBlurHorizontal extends EffectBase {
    private final Vector2f resolution;
    private final float radiusX;

    public GaussianBlurHorizontal(Vector2f resolution, float radiusX) {
        super(resolution, new ResourcesLocation("shader/frameBuffer/filter/GaussianBlur.fsh"), true, 6, "GaussianBlurHorizontal", (Class<GLObject>) null);

        this.resolution = resolution;
        this.radiusX = radiusX;
    }

    @Override
    public void frameBufferPush(GLObject baseGLObject) {
        getFrameBuffer().bindFrameBuffer();

        super.frameBufferPush(baseGLObject);
    }

    @Override
    public void frameBufferPop(GLObject baseGLObject) {
        getFrameBuffer().unbindFrameBuffer();

        super.frameBufferPop(baseGLObject);
    }

    @Override
    public void frameBuffer(GLObject baseGLObject) {
        getFrameBuffer().drawFrameBuffer();

        super.frameBuffer(baseGLObject);
    }

    @Override
    public void setUniform(GLObject baseGLObject) {
        getFrameBuffer().getShaderProgramFBO().bind();

        getFrameBuffer().getShaderProgramFBO().setUniform2f("direction", new Vector2f(1, 0));
        getFrameBuffer().getShaderProgramFBO().setUniform1f("radius", radiusX);
        getFrameBuffer().getShaderProgramFBO().setUniform2f("texelSize", new Vector2f(1.0f / baseGLObject.getResolution().x(), 1.0f / baseGLObject.getResolution().y()));

        final FloatBuffer weightBuffer = BufferUtils.createFloatBuffer(256);
        for (int i = 0; i < radiusX; i++) {
            weightBuffer.put(MathUtil.calculateGaussianValue(i, radiusX / 2));
        }
        weightBuffer.rewind();
        getFrameBuffer().getShaderProgramFBO().setUniform1fv("values", weightBuffer);

        getFrameBuffer().getShaderProgramFBO().unbind();

        super.setUniform(baseGLObject);
    }
}
