package pandarium.android.memorydots.model;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Sergey Shustikov (sergey.shustikov@youshido.com) at 2015.
 */
public class CircleGenerator {
    private final int[] RADIUSES = {50, 75, 100, 125};
    int MIN_RADIUS = 50;
    int MAX_RADIUS = 150;
    private int height;
    private int width;

    public Circle generateUniqueCircle(Stack<Circle> circles) {
        while (true) {
            int x = new Random().nextInt(width);
            int y = new Random().nextInt(height);
            int r = RADIUSES[new Random().nextInt(RADIUSES.length)];
            if (x + r > width || y + r > height || x - r < 0 || y - r < 0)
                continue;
            Circle c1 = new Circle(x, y, r);
            if (circles.isEmpty())
                return c1;
            boolean unique = true;
            for (Circle c2 : circles) {
                if (isInside(c1, c2) || isCircleIntersect(c1.getX(), c1.getY(), c1.getRadius(), c2.getX(), c2.getY(), c2.getRadius()) || isInside(c2, c1)) {
                    unique = false;
                    break;
                }
            }
            if (unique)
                return c1;
        }
    }

    public static boolean isCircleIntersect(double... values) {
        /*
         * check using mathematical relation: ABS(R0-R1) <=
         * SQRT((x0-x1)^2+(y0-y1)^2) <= (R0+R1)
         */
        if (values.length == 6) {
            /* get values from first circle */
            double x0 = values[0];
            double y0 = values[1];
            double r0 = values[2];
            /* get values from second circle */
            double x1 = values[3];
            double y1 = values[4];
            double r1 = values[5];

            boolean b = (Math.abs(r0 - r1) <= Math.sqrt(Math.pow((x0 - x1), 2) + Math.pow((y0 - y1), 2)))
                    && (Math.sqrt(Math.pow((x0 - x1), 2) + Math.pow((y0 - y1), 2)) <= (r0 + r1));
            return b;
        } else {
            return false;
        }
    }

    public void setMaxWidth(int width) {
        this.width = width;
    }

    public void setMaxHeight(int height) {
        this.height = height;
    }

    public boolean isInside(Circle c1, Circle c2) {
        // get the distance between the two center points
        double distance = Math.sqrt((c1.getX() - c2.getX()) * (c1.getX() - c2.getX()) + (c1.getY() - c2.getY()) * (c1.getY() - c2.getY()));
        // check to see if we are inside the first circle, if so then return
        // true, otherwise return false.
        if (distance + c1.getRadius() <= (c2.getRadius())) {
            System.out.println(distance);
            return true;
        } else {
            return false;
        }
    }

    public boolean isOutside(Circle c1, Circle c2) {
        double distance = Math.sqrt((c1.getX() - c2.getX()) * (c1.getX() - c2.getX()) + (c1.getY() - c2.getY()) * (c1.getY() - c2.getY()));
        if (distance > ((c1.getRadius()) + (c2.getRadius()))) {
            System.out.println(distance);
            return true;
        } else {
            return false;
        }

    }

    public boolean isIntersecting(Circle c1, Circle c2) {
        double distance = Math.sqrt((c1.getX() - c2.getX()) * (c1.getX() - c2.getX() + (c1.getY() - c2.getY()) * (c1.getY() - c2.getY())));
        if (Math.abs((c1.getRadius() - c2.getRadius())) <= distance && distance <= (c1.getRadius() + c2.getRadius())) {
            System.out.println(distance);
            return true;
        } else {
            return false;
        }
    }
}

