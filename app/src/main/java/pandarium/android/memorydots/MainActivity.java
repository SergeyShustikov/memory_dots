package pandarium.android.memorydots;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends FragmentActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mNewGame = (Button) findViewById(R.id.new_game_btn);
        Button mHighScores = (Button) findViewById(R.id.highscores_btn);
        Button mAbout = (Button) findViewById(R.id.about_btn);

        mNewGame.setOnTouchListener(this);
        mHighScores.setOnTouchListener(this);
        mAbout.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ((Button) v).setTextSize(18);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                ((Button) v).setTextSize(14);
        }
        return false;
    }

    public void onAboutButtonClick(View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void onHighScoresButtonClick(View view) {
        //TODO: need implementation
    }

    public void onNewGameClick(View view) {
        startActivity(new Intent(this, DotsGameActivity.class));
    }
}
