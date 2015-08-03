package pandarium.android.memorydots.model;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import pandarium.android.memorydots.R;
import pandarium.android.memorydots.RedrawListener;

/**
 * Created by Sergey Shustikov (sergey.shustikov@youshido.com) at 2015.
 */
public class AnimationController implements UserDecisionListener, FragmentStateListener {
    public static final int WITHOUT_ANIMATION = -1;
    private FragmentManager supportFragmentManager;
    private int rootContainer;
    private boolean isAnimating;

    public AnimationController(FragmentManager supportFragmentManager, int rootContainer) {
        this.supportFragmentManager = supportFragmentManager;
        this.rootContainer = rootContainer;
    }

    public void showLevel(int size, RedrawListener redrawListener) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_right_to_center, R.anim.slide_center_to_left);
        GameProcessFragment gameProcessFragment = new GameProcessFragment();
        gameProcessFragment.setLevel(size);
        gameProcessFragment.setRedrawListener(redrawListener);
        gameProcessFragment.setFragmentStateListener(this);
        fragmentTransaction.add(rootContainer, gameProcessFragment);
        fragmentTransaction.commit();
    }

    public void showGameOver(int scores) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_right_to_center, WITHOUT_ANIMATION);
        GameProcessFragment gameProcessFragment = new GameProcessFragment();
        gameProcessFragment.setFragmentStateListener(this);
        gameProcessFragment.showGameOver(scores);
        fragmentTransaction.add(rootContainer, gameProcessFragment);
        gameProcessFragment.setUserDecisionListener(this);
        fragmentTransaction.commit();
    }

    @Override
    public void onNewGame() {
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    @Override
    public void onAnimationStart() {
        isAnimating = true;
    }

    @Override
    public void onAnimationEnd() {
        isAnimating = false;
    }
}
