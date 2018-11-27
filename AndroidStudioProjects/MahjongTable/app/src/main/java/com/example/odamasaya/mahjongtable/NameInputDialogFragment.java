package com.example.odamasaya.mahjongtable;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

public class NameInputDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        // タイトル設定
        dialogBuilder.setTitle("名前");
        // 表示する文章設定
        dialogBuilder.setMessage("名前を入力してください");

        final int position = getArguments().getInt("position");

        // 入力フィールド作成
        final EditText editText = new EditText(getActivity());
        dialogBuilder.setView(editText);

        dialogBuilder.setPositiveButton("決定", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // editTextの内容を元画面に反映する
                // editTextから値を取得
                String returnValue = editText.getText().toString();
                // MainActivityのインスタンスを取得
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.playername[position] = returnValue;
                mainActivity.hyojikoshin();
            }
        });

        // NGボタン作成
        dialogBuilder.setNegativeButton("キャンセル" , new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 何もしないで閉じる
            }
        });

        return dialogBuilder.create();

    }
}
