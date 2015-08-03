package pandarium.android.memorydots.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import pandarium.android.memorydots.R;
import pandarium.android.memorydots.RedrawListener;

/**
 * Created by Sergey Shustikov (sergey.shustikov@youshido.com) at 2015.
 */
public class GameProcessFragment extends Fragment {

    private static final String TAG = GameProcessFragment.class.getSimpleName();
    private View rootView;
    private int size = -1;
    private int scores = -1;
    private RedrawListener redrawListener;
    private UserDecisionListener userDecisionListener;
    private FragmentStateListener fragmentStateListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.game_state_layout, container, false);
        if (size != -1) {
            ((TextView) rootView.findViewById(R.id.main_text)).setText("LEVEL " + size);
            ((TextView) rootView.findViewById(R.id.secondary_text)).setVisibility(View.GONE);
        } else if (scores != -1) {
            ((TextView) rootView.findViewById(R.id.main_text)).setText("GAME OVER");
            ((TextView) rootView.findViewById(R.id.secondary_text)).setVisibility(View.VISIBLE);
            ((TextView) rootView.findViewById(R.id.secondary_text)).setText("SCORES " + scores);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userDecisionListener.onNewGame();
                }
            });
        }
        return rootView;
    }

    public void setLevel(int size) {
        this.size = size;
    }

    public void setRedrawListener(RedrawListener redrawListener) {
        this.redrawListener = redrawListener;
    }

    public void showGameOver(int scores) {
        this.scores = scores;
    }

    @Override
    public Animation onCreateAnimation(int transit, final boolean enter, int nextAnim) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), nextAnim);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                fragmentStateListener.onAnimationStart();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (enter && scores == -1) {
                    if (redrawListener != null)
                        redrawListener.onReadyToRedraw();
                    GameProcessFragment fragment = GameProcessFragment.this;
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_right_to_center, R.anim.slide_center_to_left);
                    fragmentTransaction.remove(fragment).commit();
                }
                fragmentStateListener.onAnimationEnd();
            }
        });
        return animation;
    }

    public void setUserDecisionListener(UserDecisionListener userDecisionListener) {
        this.userDecisionListener = userDecisionListener;
    }

    public void setFragmentStateListener(FragmentStateListener fragmentStateListener) {
        this.fragmentStateListener = fragmentStateListener;
    }
}
