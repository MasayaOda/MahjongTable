package com.example.odamasaya.mahjongtable;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class RyukyokuDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // タイトル設定
        builder.setTitle("聴牌者を選択してください");

        final String member_list[] = new String[] {"東", "南", "西", "北"};

        final boolean tempai[] = new boolean[] {false, false, false, false};

        builder.setMultiChoiceItems(member_list,
                tempai,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton,
                                        boolean isChecked) {
                        //⇒アイテムを選択した時のイベント処理
                        tempai[whichButton] = isChecked;
                    }
                });

        builder.setPositiveButton("決定", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // MainActivityのインスタンスを取得
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.ryukyokushori(tempai);
            }
        });

        builder.setNegativeButton("キャンセル", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 何もしないで閉じる
            }
        });




        return builder.create();
    }

}
