package com.example.odamasaya.mahjongtable;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AgariDialogFragment extends DialogFragment {
    String[] husulist = new String[] {
            "20", "25", "30", "40", "50", "60", "70", "80", "90", "100", "110"
    };


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Activity activity = getActivity();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);

        final int position = getArguments().getInt("position");

        //カスタムビューを設定
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        //レイアウトのインフレーター

        final View view = inflater.inflate(R.layout.fragment_dialog_agari, null);

        TextView tvLabelInput = (TextView) view.findViewById(R.id.tvLabelInput);
        RadioButton tsumo = (RadioButton) view.findViewById(R.id.tsumo);
        RadioButton kamicha = (RadioButton) view.findViewById(R.id.kamicha);
        RadioButton toimen = (RadioButton) view.findViewById(R.id.toimen);
        RadioButton shimocha = (RadioButton) view.findViewById(R.id.shimocha);


        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        final NumberPicker hansuPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
        hansuPicker.setMaxValue(13);
        hansuPicker.setMinValue(1);

        final NumberPicker husuPicker = (NumberPicker) view.findViewById(R.id.husuPicker);
        husuPicker.setDisplayedValues(husulist);
        husuPicker.setMaxValue(husulist.length - 1);
        husuPicker.setMinValue(0);

        // タイトル設定
        dialogBuilder.setTitle("得点計算");


        dialogBuilder.setView(view);

        // 入力フィールド作成

        dialogBuilder.setPositiveButton("確定", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // editTextの内容を元画面に反映する
                // editTextから値を取得
                // MainActivityのインスタンスを取得

              MainActivity mainActivity = (MainActivity) getActivity();

                int checkedId = radioGroup.getCheckedRadioButtonId();



                int hansu = hansuPicker.getValue();
                int husu = husuPicker.getValue();
                int type = 0;
                if (checkedId != -1) {
                    // 選択されているラジオボタンの取得
                    RadioButton radioButton = (RadioButton) view.findViewById(checkedId);// (Fragmentの場合は「getActivity().findViewById」にする)


                    // ラジオボタンのテキストを取得
                    String text = radioButton.getText().toString();

                    switch (text) {
                        case "ツモ":
                            type = 0;
                            break;

                        case "上家":
                            type = 1;
                            break;

                        case "対面":
                            type = 2;
                            break;

                        case "下家":
                            type = 3;
                            break;
                    }

                }



                mainActivity.agarishori(hansu, husu, type, position);
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