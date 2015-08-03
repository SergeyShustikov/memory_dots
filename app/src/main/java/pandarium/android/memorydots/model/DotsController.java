package pandarium.android.memorydots.model;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Stack;

import pandarium.android.memorydots.model.Circle;
import pandarium.android.memorydots.model.DotsModel;
import pandarium.android.memorydots.model.DotsView;
import pandarium.android.memorydots.RedrawListener;

/**
 * Created by Sergey Shustikov (sergey.shustikov@youshido.com) at 2015.
 */
public class DotsController extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
    private static final String TAG = DotsController.class.getSimpleName();
    private DotsModel dotsModel;
    private GestureDetector gestureDetector;
    private DotsView dotsView;
    private AnimationController animationController;

    public DotsController(Context context) {
        gestureDetector = new GestureDetector(context, this);
    }

    public void setModel(DotsModel model) {
        this.dotsModel = model;
    }

    public void setView(DotsView view) {
        dotsView = view;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        int tapX = (int) e.getX();
        int tapY = (int) e.getY();
        if (dotsModel.isTapLastAddedCircle(tapX, tapY)) {
            // Correct keep going
            dotsModel.addRandomCircle();
            Stack<Circle> circles = dotsModel.getCircles();
            dotsView.setCircles(circles);
            animationController.showLevel(circles.size(), new RedrawListener() {
                @Override
                public void onReadyToRedraw() {
                    dotsView.invalidate();
                }
            });
        } else if (dotsModel.isTapOnCircle(tapX, tapY)) {
            animationController.showGameOver(dotsModel.getCircles().size());
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
    }
}
