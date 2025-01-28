package me.hannsi.lfjg.render.openGL.renderers.polygon;

import me.hannsi.lfjg.utils.graphics.color.Color;
import me.hannsi.lfjg.utils.type.types.DrawType;
import org.joml.Vector2f;

public class GLCircle extends GLPolygon {
    public GLCircle(String name) {
        super(name);
    }

    public void circle(double xCenter, double yCenter, double radius, int segmentCount, Color... colors) {
        circle((float) xCenter, (float) yCenter, (float) radius, (float) radius, segmentCount, colors);
    }

    public void circle(float xCenter, float yCenter, float radius, int segmentCount, Color... colors) {
        circle(xCenter, yCenter, radius, radius, segmentCount, colors);
    }

    public void circle(double xCenter,double yCenter,double xRadius,double yRadius,int segmentCount,Color... colors){
        circle((float) xCenter,(float) yCenter,(float) xRadius,(float) yRadius,segmentCount,colors);
    }

    public void circle(float xCenter, float yCenter, float xRadius, float yRadius, int segmentCount, Color... colors) {
        int colorCount = colors.length;

        if (colorCount < 1) {
            return;
        }

        for (int i = 0; i <= segmentCount; i++) {
            float theta = (float) (2.0f * Math.PI * i / segmentCount);
            float x = xCenter + xRadius * (float) Math.cos(theta);
            float y = yCenter + yRadius * (float) Math.sin(theta);

            Color color;
            if (colorCount > 1) {
                float colorIndex = (float) i / segmentCount * (colorCount - 1);
                int startColorIndex = (int) colorIndex;
                int endColorIndex = Math.min(startColorIndex + 1, colorCount - 1);
                float factor = colorIndex - startColorIndex;

                Color startColor = colors[startColorIndex];
                Color endColor = colors[endColorIndex];

                float r = startColor.getRed() / 255.0f + factor * (endColor.getRed() / 255.0f - startColor.getRed() / 255.0f);
                float g = startColor.getGreen() / 255.0f + factor * (endColor.getGreen() / 255.0f - startColor.getGreen() / 255.0f);
                float b = startColor.getBlue() / 255.0f + factor * (endColor.getBlue() / 255.0f - startColor.getBlue() / 255.0f);
                float a = startColor.getAlpha() / 255.0f + factor * (endColor.getAlpha() / 255.0f - startColor.getAlpha() / 255.0f);

                color = new Color(r, g, b, a);
            } else {
                Color color2 = colors[0];
                color = new Color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
            }

            put().vertex(new Vector2f(x, y)).color(color).end();
        }

        setDrawType(DrawType.POLYGON);
        rendering();
    }

    public void circleOutLine(double xCenter, double yCenter, double radius, int segmentCount, double lineWidth, Color... colors) {
        circleOutLine((float) xCenter, (float) yCenter, (float) radius, (float) radius, segmentCount, (float) lineWidth, colors);
    }

    public void circleOutLine(float xCenter, float yCenter, float radius, int segmentCount, float lineWidth, Color... colors) {
        circleOutLine(xCenter, yCenter, radius, radius, segmentCount, lineWidth, colors);
    }

    public void circleOutLine(double xCenter, double yCenter, double xRadius, double yRadius, int segmentCount, double lineWidth, Color... colors) {
        circleOutLine((float) xCenter, (float) yCenter, (float) xRadius, (float) yRadius, segmentCount, (float) lineWidth, colors);
    }

    public void circleOutLine(float xCenter, float yCenter, float xRadius, float yRadius, int segmentCount, float lineWidth, Color... colors) {
        int colorCount = colors.length;

        if (colorCount < 1) {
            return;
        }

        for (int i = 0; i <= segmentCount; i++) {
            float theta = (float) (2.0f * Math.PI * i / segmentCount);
            float x = xCenter + xRadius * (float) Math.cos(theta);
            float y = yCenter + yRadius * (float) Math.sin(theta);

            Color color;
            if (colorCount > 1) {
                float colorIndex = (float) i / segmentCount * (colorCount - 1);
                int startColorIndex = (int) colorIndex;
                int endColorIndex = Math.min(startColorIndex + 1, colorCount - 1);
                float factor = colorIndex - startColorIndex;

                Color startColor = colors[startColorIndex];
                Color endColor = colors[endColorIndex];

                float r = startColor.getRed() / 255.0f + factor * (endColor.getRed() / 255.0f - startColor.getRed() / 255.0f);
                float g = startColor.getGreen() / 255.0f + factor * (endColor.getGreen() / 255.0f - startColor.getGreen() / 255.0f);
                float b = startColor.getBlue() / 255.0f + factor * (endColor.getBlue() / 255.0f - startColor.getBlue() / 255.0f);
                float a = startColor.getAlpha() / 255.0f + factor * (endColor.getAlpha() / 255.0f - startColor.getAlpha() / 255.0f);

                color = new Color(r, g, b, a);
            } else {
                Color color2 = colors[0];
                color = new Color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
            }

            put().vertex(new Vector2f(x, y)).color(color).end();
        }

        setDrawType(DrawType.LINE_LOOP).setLineWidth(lineWidth);
        rendering();
    }
}
