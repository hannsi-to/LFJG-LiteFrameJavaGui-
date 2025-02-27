package me.hannsi.lfjg.render.openGL.animation.animations;

import me.hannsi.lfjg.render.openGL.animation.system.AnimationBase;
import me.hannsi.lfjg.render.openGL.renderers.GLObject;
import me.hannsi.lfjg.utils.math.animation.Easing;
import me.hannsi.lfjg.utils.math.animation.EasingUtil;

import static me.hannsi.lfjg.utils.math.MathHelper.*;

public class Bounce extends AnimationBase {
    protected EasingUtil easingUtil;
    protected float lastX;
    protected float lastY;

    private long millis;
    private float height;
    private float degrees;

    public Bounce(long pauseTime, long millis, float height, float degrees) {
        super("Bounce", 1, pauseTime);

        this.millis = millis;
        this.height = height;
        this.degrees = degrees;
    }

    @Override
    public void loop(long currentTime, GLObject glObject) {
        if (easingUtil == null) {
            easingUtil = new EasingUtil(Easing.easeLinear);
            easingUtil.reset();
        }

        float easeValue = easingUtil.get(millis);
        float distance = abs(sin(toRadians(easeValue * 180))) * height;
        float x = distance * cos(toRadians(degrees));
        float y = distance * sin(toRadians(degrees));

        glObject.getModelMatrix().translate(-lastX, -lastY, 0).translate(x, y, 0);

        if (easingUtil.done(easeValue)) {
            easingUtil.reset();
            easingUtil.setReverse(!easingUtil.isReverse());
        }

        lastX = x;
        lastY = y;

        super.loop(currentTime, glObject);
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getDegrees() {
        return degrees;
    }

    public void setDegrees(float degrees) {
        this.degrees = degrees;
    }
}
