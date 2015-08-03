package pandarium.android.memorydots.model;

import android.graphics.Color;

/**
 * Created by Sergey Shustikov (sergey.shustikov@youshido.com) at 2015.
 */
public final class Circle {

    private final int x;
    private final int y;
    private final int radius;
    private final static int color = Color.WHITE;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public static int getColor() {
        return color;
    }
}
