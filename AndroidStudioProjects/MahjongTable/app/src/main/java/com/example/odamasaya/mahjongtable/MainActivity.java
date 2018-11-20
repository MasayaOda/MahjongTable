package com.example.odamasaya.mahjongtable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    TextView scoreTexts[] = new TextView[4];
    TextView kyotaku;
    int[] ids = {R.id.under, R.id.right, R.id.upper, R.id.left};
    int[] scores = {25000, 25000, 25000, 25000};
    int first = 25000;
    int kyotakuscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i;




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kyotaku = findViewById(R.id.kyotaku);

        kyotaku.setText(String.valueOf(kyotakuscore));
        for (i = 0; i < scoreTexts.length; i++) {
            final int finalI = i;
            scoreTexts[i] = findViewById(ids[i]);
            scoreTexts[i].setText(String.valueOf(scores[i]));
            scoreTexts[i].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // ダイアログクラスをインスタンス化
                Bundle bundle = new Bundle();

                bundle.putInt("position", finalI);

                InputDialogFragment dialog = new InputDialogFragment();
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "InputDialogFragment");
            }
        });

        }


    }


    public void plusScore(int plus, int position) {
        scores[position] = scores[position] + plus;
        scoreTexts[position].setText(String.valueOf(scores[position]));
    }

    public void minusScore(int minus, int position) {
        scores[position] = scores[position] - minus;
        scoreTexts[position].setText(String.valueOf(scores[position]));
    }

    public void kyotakuReset(View view) {
        kyotakuscore = 0;
        kyotaku.setText(String.valueOf(kyotakuscore));
    }

    public void onResetButtonClick(View view) {
        int i;
        for (i = 0; i < 4; i++) {
            scores[i] = first;
            scoreTexts[i].setText(String.valueOf(scores[i]));
        }
        kyotakuscore = 0;
        kyotaku.setText(String.valueOf(kyotakuscore));
    }

    public void reach(View view) {
        int id = view.getId();

        switch(id) {
            case R.id.underreach:
                scores[0] -= 1000;
                kyotakuscore += 1000;
                break;

            case R.id.rightreach:
                scores[1] -= 1000;
                kyotakuscore += 1000;
                break;

            case R.id.upperreach:
                scores[2] -= 1000;
                kyotakuscore += 1000;
                break;

            case R.id.leftreach:
                scores[3] -= 1000;
                kyotakuscore += 1000;
                break;
        }
        int i;
        for (i = 0; i < 4; i++) {
            scoreTexts[i].setText(String.valueOf(scores[i]));
        }
        kyotaku.setText(String.valueOf(kyotakuscore));

    }

    public void ryukyoku(View view) {
        RyukyokuDialogFragment dialogFragment = new RyukyokuDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "RyukyokuDialogFragment");
    }

    public void ryukyokushori(boolean tempai[]) {
        int i;
        int counter = 0;
        for (i = 0; i < 4; i++) {
            if (tempai[i]) {
                counter++;
            }
        }
        if (counter == 0 || counter == 4) return;
        for (i = 0; i < 4; i++) {
            if (tempai[i]) {
                scores[i] += 3000 / counter;
            } else {
                scores[i] -= 3000 / (4 - counter);
            }
            scoreTexts[i].setText(String.valueOf(scores[i]));
        }
    }
}
