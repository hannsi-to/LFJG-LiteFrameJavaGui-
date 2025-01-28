package me.hannsi.lfjg.render.openGL.renderers.polygon;

import me.hannsi.lfjg.utils.graphics.color.Color;
import me.hannsi.lfjg.utils.type.types.DrawType;
import org.joml.Vector2f;

public class GLRoundedRect extends GLPolygon {
    public static final int defaultSegmentCount = 16;

    public GLRoundedRect(String name) {
        super(name);
    }

    public void roundedRect(float x, float y, float x1, float y1, boolean leftTop, boolean rightTop, boolean rightBottom, boolean leftBottom, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        float i;

        if (leftBottom) {
            for (i = 0; i <= 90; i += segmentCount) {
                put().vertex(new Vector2f((float) (x + leftBottomRadius + Math.sin(i * Math.PI / 180.0D) * leftBottomRadius * -1.0D), (float) (y + leftBottomRadius + Math.cos(i * Math.PI / 180.0D) * leftBottomRadius * -1.0D))).color(bottomLeftColor).end();
            }
        } else {
            put().vertex(new Vector2f(x, y)).color(bottomLeftColor).end();
        }

        if (leftTop) {
            for (i = 90; i <= 180; i += segmentCount) {
                put().vertex(new Vector2f((float) (x + leftTopRadius + Math.sin(i * Math.PI / 180.0D) * leftTopRadius * -1.0D), (float) (y1 - leftTopRadius + Math.cos(i * Math.PI / 180.0D) * leftTopRadius * -1.0D))).color(topLeftColor).end();
            }
        } else {
            put().vertex(new Vector2f(x, y1)).color(topLeftColor).end();
        }

        if (rightTop) {
            for (i = 0; i <= 90; i += segmentCount) {
                put().vertex(new Vector2f((float) (x1 - rightTopRadius + Math.sin(i * Math.PI / 180.0D) * rightTopRadius), (float) (y1 - rightTopRadius + Math.cos(i * Math.PI / 180.0D) * rightTopRadius))).color(topRightColor).end();
            }
        } else {
            put().vertex(new Vector2f(x1, y1)).color(topRightColor).end();
        }

        if (rightBottom) {
            for (i = 90; i <= 180; i += segmentCount) {
                put().vertex(new Vector2f((float) (x1 - rightBottomRadius + Math.sin(i * Math.PI / 180.0D) * rightBottomRadius), (float) (y + rightBottomRadius + Math.cos(i * Math.PI / 180.0D) * rightBottomRadius))).color(bottomRightColor).end();
            }
        } else {
            put().vertex(new Vector2f(x1, y)).color(bottomRightColor).end();
        }

        setDrawType(DrawType.POLYGON);
        rendering();
    }

    public void roundedRect(float x, float y, float x1, float y1, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x1, y1, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, segmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRect(float x, float y, float x1, float y1, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x1, y1, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRect(float x, float y, float x1, float y1, float radius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x1, y1, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRect(float x, float y, float x1, float y1, float radius, Color color) {
        roundedRect(x, y, x1, y1, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, color, color, color, color);
    }

    public void roundedRectWH(float x, float y, float width, float height, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x + width, y + height, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, segmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWH(float x, float y, float width, float height, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x + width, y + height, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWH(float x, float y, float width, float height, float radius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x + width, y + height, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWH(float x, float y, float width, float height, float radius, Color color) {
        roundedRect(x, y, x + width, y + height, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, color, color, color, color);
    }

    public void roundedRectOutLine(float x, float y, float x1, float y1, float lineWidth, boolean leftTop, boolean rightTop, boolean rightBottom, boolean leftBottom, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        float i;

        if (leftBottom) {
            for (i = 0; i <= 90; i += segmentCount) {
                put().vertex(new Vector2f((float) (x + leftBottomRadius + Math.sin(i * Math.PI / 180.0D) * leftBottomRadius * -1.0D), (float) (y + leftBottomRadius + Math.cos(i * Math.PI / 180.0D) * leftBottomRadius * -1.0D))).color(bottomLeftColor).end();
            }
        } else {
            put().vertex(new Vector2f(x, y)).color(bottomLeftColor).end();
        }

        if (leftTop) {
            for (i = 90; i <= 180; i += segmentCount) {
                put().vertex(new Vector2f((float) (x + leftTopRadius + Math.sin(i * Math.PI / 180.0D) * leftTopRadius * -1.0D), (float) (y1 - leftTopRadius + Math.cos(i * Math.PI / 180.0D) * leftTopRadius * -1.0D))).color(topLeftColor).end();
            }
        } else {
            put().vertex(new Vector2f(x, y1)).color(topLeftColor).end();
        }

        if (rightTop) {
            for (i = 0; i <= 90; i += segmentCount) {
                put().vertex(new Vector2f((float) (x1 - rightTopRadius + Math.sin(i * Math.PI / 180.0D) * rightTopRadius), (float) (y1 - rightTopRadius + Math.cos(i * Math.PI / 180.0D) * rightTopRadius))).color(topRightColor).end();
            }
        } else {
            put().vertex(new Vector2f(x1, y1)).color(topRightColor).end();
        }

        if (rightBottom) {
            for (i = 90; i <= 180; i += segmentCount) {
                put().vertex(new Vector2f((float) (x1 - rightBottomRadius + Math.sin(i * Math.PI / 180.0D) * rightBottomRadius), (float) (y + rightBottomRadius + Math.cos(i * Math.PI / 180.0D) * rightBottomRadius))).color(bottomRightColor).end();
            }
        } else {
            put().vertex(new Vector2f(x1, y)).color(bottomRightColor).end();
        }

        setDrawType(DrawType.LINE_LOOP).setLineWidth(lineWidth);
        rendering();
    }

    public void roundedRectOutLine(float x, float y, float x1, float y1, float lineWidth, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x1, y1, lineWidth, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, segmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectOutLine(float x, float y, float x1, float y1, float lineWidth, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x1, y1, lineWidth, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectOutLine(float x, float y, float x1, float y1, float lineWidth, float radius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x1, y1, lineWidth, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectOutLine(float x, float y, float x1, float y1, float lineWidth, float radius, Color color) {
        roundedRectOutLine(x, y, x1, y1, lineWidth, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, color, color, color, color);
    }

    public void roundedRectWHOutLine(float x, float y, float width, float height, float lineWidth, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x + width, y + height, lineWidth, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, segmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWHOutLine(float x, float y, float width, float height, float lineWidth, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x + width, y + height, lineWidth, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWHOutLine(float x, float y, float width, float height, float lineWidth, float radius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x + width, y + height, lineWidth, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWHOutLine(float x, float y, float width, float height, float lineWidth, float radius, Color color) {
        roundedRectOutLine(x, y, x + width, y + height, lineWidth, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, color, color, color, color);
    }

    public void roundedRect(double x, double y, double x1, double y1, boolean leftTop, boolean rightTop, boolean rightBottom, boolean leftBottom, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        double i;

        if (leftBottom) {
            for (i = 0; i <= 90; i += segmentCount) {
                put().vertex(new Vector2f((float) (x + leftBottomRadius + Math.sin(i * Math.PI / 180.0) * leftBottomRadius * -1.0), (float) (y + leftBottomRadius + Math.cos(i * Math.PI / 180.0) * leftBottomRadius * -1.0))).color(bottomLeftColor).end();
            }
        } else {
            put().vertex(new Vector2f((float) x, (float) y)).color(bottomLeftColor).end();
        }

        if (leftTop) {
            for (i = 90; i <= 180; i += segmentCount) {
                put().vertex(new Vector2f((float) (x + leftTopRadius + Math.sin(i * Math.PI / 180.0) * leftTopRadius * -1.0), (float) (y1 - leftTopRadius + Math.cos(i * Math.PI / 180.0) * leftTopRadius * -1.0))).color(topLeftColor).end();
            }
        } else {
            put().vertex(new Vector2f((float) x, (float) y1)).color(topLeftColor).end();
        }

        if (rightTop) {
            for (i = 0; i <= 90; i += segmentCount) {
                put().vertex(new Vector2f((float) (x1 - rightTopRadius + Math.sin(i * Math.PI / 180.0) * rightTopRadius), (float) (y1 - rightTopRadius + Math.cos(i * Math.PI / 180.0) * rightTopRadius))).color(topRightColor).end();
            }
        } else {
            put().vertex(new Vector2f((float) x1, (float) y1)).color(topRightColor).end();
        }

        if (rightBottom) {
            for (i = 90; i <= 180; i += segmentCount) {
                put().vertex(new Vector2f((float) (x1 - rightBottomRadius + Math.sin(i * Math.PI / 180.0) * rightBottomRadius), (float) (y + rightBottomRadius + Math.cos(i * Math.PI / 180.0) * rightBottomRadius))).color(bottomRightColor).end();
            }
        } else {
            put().vertex(new Vector2f((float) x1, (float) y)).color(bottomRightColor).end();
        }

        setDrawType(DrawType.POLYGON);
        rendering();
    }

    public void roundedRect(double x, double y, double x1, double y1, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x1, y1, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, segmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRect(double x, double y, double x1, double y1, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x1, y1, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRect(double x, double y, double x1, double y1, double radius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x1, y1, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRect(double x, double y, double x1, double y1, double radius, Color color) {
        roundedRect(x, y, x1, y1, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, color, color, color, color);
    }

    public void roundedRectWH(double x, double y, double width, double height, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x + width, y + height, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, segmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWH(double x, double y, double width, double height, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x + width, y + height, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWH(double x, double y, double width, double height, double radius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRect(x, y, x + width, y + height, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWH(double x, double y, double width, double height, double radius, Color color) {
        roundedRect(x, y, x + width, y + height, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, color, color, color, color);
    }

    public void roundedRectOutLine(double x, double y, double x1, double y1, double lineWidth, boolean leftTop, boolean rightTop, boolean rightBottom, boolean leftBottom, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        double i;

        if (leftBottom) {
            for (i = 0; i <= 90; i += segmentCount) {
                put().vertex(new Vector2f((float) (x + leftBottomRadius + Math.sin(i * Math.PI / 180.0D) * leftBottomRadius * -1.0D), (float) (y + leftBottomRadius + Math.cos(i * Math.PI / 180.0D) * leftBottomRadius * -1.0D))).color(bottomLeftColor).end();
            }
        } else {
            put().vertex(new Vector2f((float) x, (float) y)).color(bottomLeftColor).end();
        }

        if (leftTop) {
            for (i = 90; i <= 180; i += segmentCount) {
                put().vertex(new Vector2f((float) (x + leftTopRadius + Math.sin(i * Math.PI / 180.0D) * leftTopRadius * -1.0D), (float) (y1 - leftTopRadius + Math.cos(i * Math.PI / 180.0D) * leftTopRadius * -1.0D))).color(topLeftColor).end();
            }
        } else {
            put().vertex(new Vector2f((float) x, (float) y1)).color(topLeftColor).end();
        }

        if (rightTop) {
            for (i = 0; i <= 90; i += segmentCount) {
                put().vertex(new Vector2f((float) (x1 - rightTopRadius + Math.sin(i * Math.PI / 180.0D) * rightTopRadius), (float) (y1 - rightTopRadius + Math.cos(i * Math.PI / 180.0D) * rightTopRadius))).color(topRightColor).end();
            }
        } else {
            put().vertex(new Vector2f((float) x1, (float) y1)).color(topRightColor).end();
        }

        if (rightBottom) {
            for (i = 90; i <= 180; i += segmentCount) {
                put().vertex(new Vector2f((float) (x1 - rightBottomRadius + Math.sin(i * Math.PI / 180.0D) * rightBottomRadius), (float) (y + rightBottomRadius + Math.cos(i * Math.PI / 180.0D) * rightBottomRadius))).color(bottomRightColor).end();
            }
        } else {
            put().vertex(new Vector2f((float) x1, (float) y)).color(bottomRightColor).end();
        }

        setDrawType(DrawType.LINE_LOOP).setLineWidth((float) lineWidth);
        rendering();
    }

    public void roundedRectOutLine(double x, double y, double x1, double y1, double lineWidth, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x1, y1, lineWidth, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, segmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectOutLine(double x, double y, double x1, double y1, double lineWidth, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x1, y1, lineWidth, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectOutLine(double x, double y, double x1, double y1, double lineWidth, double radius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x1, y1, lineWidth, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectOutLine(double x, double y, double x1, double y1, double lineWidth, double radius, Color color) {
        roundedRectOutLine(x, y, x1, y1, lineWidth, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, color, color, color, color);
    }

    public void roundedRectWHOutLine(double x, double y, double width, double height, double lineWidth, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, int segmentCount, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x + width, y + height, lineWidth, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, segmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWHOutLine(double x, double y, double width, double height, double lineWidth, double leftTopRadius, double rightTopRadius, double rightBottomRadius, double leftBottomRadius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x + width, y + height, lineWidth, true, true, true, true, leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWHOutLine(double x, double y, double width, double height, double lineWidth, double radius, Color topLeftColor, Color topRightColor, Color bottomRightColor, Color bottomLeftColor) {
        roundedRectOutLine(x, y, x + width, y + height, lineWidth, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor);
    }

    public void roundedRectWHOutLine(double x, double y, double width, double height, double lineWidth, double radius, Color color) {
        roundedRectOutLine(x, y, x + width, y + height, lineWidth, true, true, true, true, radius, radius, radius, radius, defaultSegmentCount, color, color, color, color);
    }
}
