package com.example.odamasaya.mahjongtable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    TextView scoreTexts[] = new TextView[4];
    int[] ids = {R.id.under, R.id.right, R.id.upper, R.id.left};
    int[] scores = {25000, 25000, 25000, 25000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

}
