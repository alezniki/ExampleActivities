package com.nikola.exampleactivities.dialogs;

import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.nikola.exampleactivities.R;

/**
 * Created by Dzoni on 5/18/2017.
 */

public class AboutDialog extends AlertDialog.Builder {
    // Create constructor matching super
    public AboutDialog(Context context) {
        super(context);
        // Code here

        // From AlertDialog Class
        setTitle(R.string.dialog_about_title);
        setMessage(R.string.dialog_about_message);
        setCancelable(false);

        setPositiveButton(R.string.dialog_about_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        setNegativeButton(R.string.dialog_about_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    } // AboutDialog Constuctor

    public android.support.v7.app.AlertDialog prepareDialog(){
        AlertDialog alertDialog = create();
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }

}
