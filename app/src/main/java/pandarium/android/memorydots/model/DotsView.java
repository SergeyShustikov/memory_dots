package pandarium.android.memorydots.model;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.Stack;

/**
 * Created by Sergey Shustikov (sergey.shustikov@youshido.com) at 2015.
 */
public final class DotsView extends View {
    private Stack<Circle> mStackCircle;
    private Paint paint;

    public DotsView(Context context) {
        super(context);
        initialize();
    }

    public DotsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();

    }

    public DotsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DotsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {
        paint = new Paint();
        paint.setAntiAlias(true);
        mStackCircle = new Stack<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        for (Circle circle : mStackCircle) {
            paint.setColor(Circle.getColor());
            canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
        }
    }

    public void setCircles(Stack<Circle> circles) {
        this.mStackCircle = circles;
    }
}
