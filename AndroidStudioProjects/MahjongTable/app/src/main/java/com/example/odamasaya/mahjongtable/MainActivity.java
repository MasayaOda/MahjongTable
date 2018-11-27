package com.example.odamasaya.mahjongtable;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


class BackUp {
    int[] scores = new int[4];
    int[] playerwind = new int[4];
    int kyoku;
    int honba;
    int kyotakuscore;
    boolean[] reachflag = new boolean[4];
}


public class MainActivity extends AppCompatActivity {

    TextView scoreTexts[] = new TextView[4];
    TextView windTexts[] = new TextView[4];
    TextView playerNameTexts[] = new TextView[4];
    String[] playername = new String[] {"プレイヤー1", "プレイヤー2", "プレイヤー3", "プレイヤー4"};
    TextView kyotaku;
    TextView nanhonba;
    TextView nankyoku;

    int[] scoreids = {R.id.under, R.id.right, R.id.upper, R.id.left};
    int[] windids = {R.id.underwind, R.id.rightwind, R.id.upperwind, R.id.leftwind};
    int[] playerids = {R.id.player1, R.id.player2, R.id.player3, R.id.player4};
    int[] playerwind = {0, 1, 2, 3};
    int[] scores = {25000, 25000, 25000, 25000};
    int first = 25000;
    int kyotakuscore = 0;
    int honba = 0;
    int kyoku = 0;
    boolean reachflag[] = new boolean[] {false, false, false, false};

    int koscore[][] = new int[][] {   //はん．ふ
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {   0,    0, 1000, 1300, 1600, 2000, 2300, 2600, 2900, 3200, 3600},
            {1300, 1600, 2000, 2600, 3200, 3900, 4500, 5200, 5800, 6400, 7100},
            {2600, 3200, 3900, 5200, 6400, 7700, 8000, 8000, 8000, 8000, 8000},
            {5200, 6400, 7700, 8000, 8000, 8000, 8000, 8000, 8000, 8000, 8000}
    };

    int oyascore[][] = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {   0,    0, 1500, 2000, 2400, 2900, 3400, 3900, 4400, 4800, 5300},
            {   0, 2400, 2900, 3900, 4800, 5800, 6800, 7700, 8700, 9600, 10600},
            {   0, 4800, 5800, 7700, 9600, 11600, 12000, 12000, 12000, 12000, 12000},
            {   0, 9600, 11600, 12000, 12000, 12000, 12000, 12000, 12000, 12000, 12000}
    };

    int manganijo[] = new int[] {
        0, 0, 0, 0, 0, 8000, 12000, 12000, 16000, 16000, 16000, 24000, 24000, 32000
    };



    ArrayList<BackUp> backuplist = new ArrayList<BackUp>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i;

        backuplist.clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nankyoku = findViewById(R.id.nankyoku);
        nanhonba = findViewById(R.id.nanhonba);
        kyotaku = findViewById(R.id.kyotaku);
        kyotaku.setText(String.valueOf(kyotakuscore));
        for (i = 0; i < scoreTexts.length; i++) {
            final int finalI = i;
            scoreTexts[i] = findViewById(scoreids[i]);
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

            nankyoku.setText(kyokuprint(kyoku));
            nanhonba.setText(honbaprint(honba));
            windTexts[i] = findViewById(windids[i]);
            windTexts[i].setText(windprint(playerwind[i]));
            windTexts[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // ダイアログクラスをインスタンス化
                    Bundle bundle = new Bundle();

                    bundle.putInt("position", finalI);
                    AgariDialogFragment dialog = new AgariDialogFragment();
                    dialog.setArguments(bundle);
                    dialog.show(getSupportFragmentManager(), "AgariDialogFragment");
                }
            });

            playerNameTexts[i] = findViewById(playerids[i]);
            playerNameTexts[i].setText(playername[i]);
            playerNameTexts[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // ダイアログクラスをインスタンス化
                    Bundle bundle = new Bundle();

                    bundle.putInt("position", finalI);
                    NameInputDialogFragment dialog = new NameInputDialogFragment();
                    dialog.setArguments(bundle);
                    dialog.show(getSupportFragmentManager(), "NameInputDialogFragment");
                }
            });

        }


    }



    public void backup() {
        int i;
        BackUp backup = new BackUp();
        for (i = 0; i < 4; i++) {
            backup.scores[i] = scores[i];
            backup.playerwind[i] = playerwind[i];
            backup.reachflag[i] = reachflag[i];
        }
        backup.honba = honba;
        backup.kyoku = kyoku;
        backup.kyotakuscore = kyotakuscore;
        backuplist.add(backup);
    }


    public String kyokuprint(int kyoku) {
        String nankyoku = "";

        if (kyoku < 4) {
            nankyoku = nankyoku + "東";
        } else {
            nankyoku = nankyoku + "南";
        }

        nankyoku += String.valueOf(kyoku % 4 + 1);
        nankyoku += "局";

        return nankyoku;
    }

    public String honbaprint(int honba) {
       return String.valueOf(honba) + "本場";

    }


    public String windprint(int wind) {
        switch (wind) {
            case 0:
                return "東";
            case 1:
                return "南";
            case 2:
                return "西";
            case 3:
                return "北";
        }
        return "?";

    }


    public void hyojikoshin() {
        int i;
        for (i = 0; i < 4; i++) {
            scoreTexts[i].setText(String.valueOf(scores[i]));
            windTexts[i].setText(windprint(playerwind[i]));
            playerNameTexts[i].setText(playername[i]);
        }
        nankyoku.setText(kyokuprint(kyoku));
        nanhonba.setText(honbaprint(honba));
        kyotaku.setText(String.valueOf(kyotakuscore));
    }

    public void kyokuAdvance() {
        kyoku = (kyoku + 1) % 8;
    }

    public void windAdvance() {
        int i;
        for (i = 0;i < 4; i++) {
            playerwind[i] = (playerwind[i] + 3) % 4;
        //    windTexts[i].setText(windprint(playerwind[i]));
        }
    }

    public void plusScore(int plus, int position) {
        backup();
        scores[position] = scores[position] + plus;
       // scoreTexts[position].setText(String.valueOf(scores[position]));
        for (int i = 0; i < 4; i++) {
            reachflag[i] = false;
        }
        hyojikoshin();
    }

    public void minusScore(int minus, int position) {
        backup();
        scores[position] = scores[position] - minus;
      //  scoreTexts[position].setText(String.valueOf(scores[position]));
        for (int i = 0; i < 4; i++) {
            reachflag[i] = false;
        }
        hyojikoshin();
    }

    public void kyotakuReset(View view) {
        backup();
        kyotakuscore = 0;
        //kyotaku.setText(String.valueOf(kyotakuscore));
        hyojikoshin();
    }

    public void onResetButtonClick(View view) {

        KakuninDialog dialog = new KakuninDialog();

        dialog.show(getSupportFragmentManager(), "KakuninDialog");
    }

    public void onBackButtonClick(View view) {
        BackUp backup = new BackUp();
        int i;
        if (!backuplist.isEmpty()) {
            backup = backuplist.get(backuplist.size() - 1);
            for (i = 0; i < 4; i++) {
                scores[i] = backup.scores[i];
                playerwind[i] = backup.playerwind[i];
                reachflag[i] = backup.reachflag[i];
            }
            honba = backup.honba;
            kyoku = backup.kyoku;
            kyotakuscore = backup.kyotakuscore;
            backuplist.remove(backuplist.size() - 1);
        } else {
            Toast.makeText(this,"戻れません", Toast.LENGTH_SHORT).show();
        }
        hyojikoshin();
    }


    public void reset() {
        int i;

        backuplist.clear();
        backup();

        for (i = 0; i < 4; i++) {
            scores[i] = first;
            reachflag[i] = false;
        }
        kyoku = 0;
        honba = 0;
        kyotakuscore = 0;
        hyojikoshin();
    }

    public void reach(View view) {
        backup();
        int id = view.getId();

        switch(id) {
            case R.id.underreach:
                if (!reachflag[0]) {
                    scores[0] -= 1000;
                    kyotakuscore += 1000;
                    reachflag[0] = true;
                }
                break;

            case R.id.rightreach:
                if (!reachflag[1]) {
                    scores[1] -= 1000;
                    kyotakuscore += 1000;
                    reachflag[1] = true;
                }
                break;

            case R.id.upperreach:
                if (!reachflag[2]) {
                    scores[2] -= 1000;
                    kyotakuscore += 1000;
                    reachflag[2] = true;
                }
                break;

            case R.id.leftreach:
                if (!reachflag[3]) {
                    scores[3] -= 1000;
                    kyotakuscore += 1000;
                    reachflag[3] = true;
                }
                break;
        }
        int i;
        hyojikoshin();
    }

    public void ryukyoku(View view) {
        RyukyokuDialogFragment dialogFragment = new RyukyokuDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "RyukyokuDialogFragment");
    }

    public void ryukyokushori(boolean tempai[]) {
        backup();
        int i;
        int wind;
        int counter = 0;
        boolean flag = true;
        for (i = 0; i < 4; i++) {
            if (tempai[i]) {
                counter++;
            }
        }

        int[] idou = new int[] {0, 0, 0, 0};
        int[] maenoscore = new int[4];



        String msg = "流局\n";

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("点数移動");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });



        for (i = 0; i < 4; i++) {
            reachflag[i] = false;
            maenoscore[i] = scores[i];
        }


        if (counter != 0 && counter != 4) {
            for (i = 0; i < 4; i++) {
                wind = playerwind[i];
                if (tempai[wind]) {
                    scores[i] += 3000 / counter;
                    idou[i] += 3000 / counter;

                } else {
                    scores[i] -= 3000 / (4 - counter);
                    idou[i] -= 3000 / (4 - counter);
                }
            }
        }

        for (i = 0; i < 4; i++) {
            if (idou[i] < 0) {
                msg = msg + playername[i] + ": " + String.valueOf(maenoscore[i]) + "→" + String.valueOf(scores[i]) + "  (" + String.valueOf(idou[i]) + ")\n";
            } else {
                msg = msg + playername[i] + ": " + String.valueOf(maenoscore[i]) + "→" + String.valueOf(scores[i]) + "  (+" + String.valueOf(idou[i]) + ")\n";
            }
        }

        dialog.setMessage(msg);
        dialog.show();

        if (tempai[0] == true ) {
            flag = false;
        }
        if (flag) {
            kyokuAdvance();
            windAdvance();
        }
        honba++;
        hyojikoshin();
    }

    public int kiriage(int koscore, int warukazu) {
        int marumeyou;
        int marumego;

        marumeyou = koscore / 100;
        marumego = (marumeyou + warukazu - 1) / warukazu;

        return marumego * 100;
    }

    public void agarishori(int hansu, int husu, int type, int position) {
        backup();
        int i;
        int kofutan;
        int oyafutan;
        int futan;

        int[] idou = new int[] {0, 0, 0, 0};
        int[] maenoscore = new int[4];



        String msg = "";

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("点数移動");
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });



        for (i = 0; i < 4; i++) {
            reachflag[i] = false;
            maenoscore[i] = scores[i];
        }

        if (type == 0) {
            msg += "ツモ:";

            for (i = 0; i < 4; i++) {
                if (playerwind[position] == 0) {
                    if (hansu > 4) {
                        kofutan = kiriage(manganijo[hansu], 2);
                    } else {
                        kofutan = kiriage(koscore[hansu][husu], 2);
                    }
                    kofutan += honba * 100;
                    if (i != position) {
                        scores[i] -= kofutan;
                        idou[i] -= kofutan;
                        scores[position] += kofutan;
                        idou[position] += kofutan;
                    }
                } else {
                    if (hansu > 4) {
                        kofutan = kiriage(manganijo[hansu], 4);
                        oyafutan = kiriage(manganijo[hansu], 2);
                    } else {
                        kofutan = kiriage(koscore[hansu][husu], 4);
                        oyafutan = kiriage(koscore[hansu][husu], 2);
                    }
                    kofutan += honba * 100;
                    oyafutan += honba * 100;
                    if (i != position) {
                        if (playerwind[i] == 0) {
                            scores[i] -= oyafutan;
                            idou[i] -= oyafutan;
                            scores[position] += oyafutan;
                            idou[position] += oyafutan;
                        } else {
                            scores[i] -= kofutan;
                            idou[i] -= kofutan;
                            scores[position] += kofutan;
                            idou[position] += kofutan;
                        }
                    }
                }
            }

        } else {
            msg += "ロン:";
            if (playerwind[position] == 0) {
                if (hansu > 4) {
                    futan = manganijo[hansu] * 3 / 2;
                } else {
                    futan = oyascore[hansu][husu];
                }
            } else {
                if (hansu > 4) {
                    futan = manganijo[hansu];
                } else {
                    futan = koscore[hansu][husu];
                }
            }
            msg += String.valueOf(futan) + "\n\n";
            futan += honba * 300;
            scores[position] += futan;
            idou[position] += futan;
            scores[(position + 4 - type) % 4] -= futan;
            idou[(position + 4 - type) % 4] -= futan;
        }




        scores[position] += kyotakuscore;
        idou[position] += kyotakuscore;
        kyotakuscore = 0;

        for (i = 0; i < 4; i++) {
            if (idou[i] < 0) {
                msg = msg + playername[i] + ": " + String.valueOf(maenoscore[i]) + "→" + String.valueOf(scores[i]) + "  (" + String.valueOf(idou[i]) + ")\n";
            } else {
                msg = msg + playername[i] + ": " + String.valueOf(maenoscore[i]) + "→" + String.valueOf(scores[i]) + "  (+" + String.valueOf(idou[i]) + ")\n";
            }
        }

        dialog.setMessage(msg);
        dialog.show();

        if (playerwind[position] == 0) {
            honba++;
        } else {
            honba = 0;
            kyokuAdvance();
            windAdvance();
        }
        hyojikoshin();
    }



}
