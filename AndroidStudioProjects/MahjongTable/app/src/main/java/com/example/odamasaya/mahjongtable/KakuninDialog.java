package com.example.odamasaya.mahjongtable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class KakuninDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("リセットします");

        builder.setMessage("風以外がリセットされます");

        builder.setPositiveButton("OK", new DialogButtonClickListener());

        builder.setNegativeButton("キャンセル", new DialogButtonClickListener());

        AlertDialog dialog = builder.create();
        return dialog;
    }
    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            String msg = "";

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    msg = "リセットしました";
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.reset();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    msg = "キャンセルしました";
                    break;

            }

            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

        }
    }
}
