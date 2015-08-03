package pandarium.android.memorydots.model;

import java.util.Stack;

/**
 * Created by Sergey Shustikov (sergey.shustikov@youshido.com) at 2015.
 */
public class DotsModel {
    private final CircleGenerator circleGenerator;
    private final Stack<Circle> circles;

    public DotsModel() {
        circleGenerator = new CircleGenerator();
        circles = new Stack<Circle>();
    }

    public void addRandomCircle() {
        Circle circle = circleGenerator.generateUniqueCircle(circles);
        circles.push(circle);
    }

    public Stack<Circle> getCircles() {
        return circles;
    }

    public void setScreenResolution(int width, int height) {
        circleGenerator.setMaxWidth(width);
        circleGenerator.setMaxHeight(height);
    }

    public boolean isTapLastAddedCircle(int tapX, int tapY) {
        if (circles.isEmpty())
            return true;
        return Math.pow((tapX - circles.peek().getX()), 2) + Math.pow((tapY - circles.peek().getY()), 2) < Math.pow(circles.peek().getRadius(), 2);
    }

    public boolean isGameOver() {
        return false;
    }

    public boolean isTapOnCircle(int tapX, int tapY) {
        boolean tapped = false;
        for (Circle circle : circles) {
            if(Math.pow((tapX - circle.getX()), 2) + Math.pow((tapY - circle.getY()), 2) < Math.pow(circle.getRadius(), 2))
                tapped = true;
        }
        return tapped;
    }
}
