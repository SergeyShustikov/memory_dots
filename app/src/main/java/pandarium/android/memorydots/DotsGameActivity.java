package pandarium.android.memorydots;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.ViewGroup;

import pandarium.android.memorydots.model.AnimationController;
import pandarium.android.memorydots.model.DotsController;
import pandarium.android.memorydots.model.DotsModel;
import pandarium.android.memorydots.model.DotsView;

/**
 * Created by Sergey Shustikov (sergey.shustikov@youshido.com) at 2015.
 */
public class DotsGameActivity extends FragmentActivity {
    private DotsController mDotsController;
    private DotsView mDotsView;
    private DotsModel mDotsModel;
    private AnimationController animationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        Display display = getWindowManager().getDefaultDisplay();
        Point outSize = new Point();
        display.getSize(outSize);
        mDotsModel = new DotsModel();
        animationController = new AnimationController(getSupportFragmentManager(), R.id.root);
        mDotsModel.setScreenResolution(outSize.x, outSize.y);
        mDotsView = new DotsView(this);
        mDotsController = new DotsController(this);
        mDotsController.setView(mDotsView);
        mDotsController.setModel(mDotsModel);
        mDotsController.setAnimationController(animationController);
        ((ViewGroup) findViewById(R.id.root)).addView(mDotsView);
        mDotsView.setOnTouchListener(mDotsController);
    }

    @Override
    public void onBackPressed() {
        if (animationController.isAnimating())
            return;

        if (mDotsModel.isGameOver()) {
            super.onBackPressed();
        } else {
            showLeaveDialog();
        }
    }

    private void showLeaveDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("EXIT").setMessage("Are you sure to leave from game?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();
        dialog.show();
    }
}
