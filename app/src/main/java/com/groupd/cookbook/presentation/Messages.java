package com.groupd.cookbook.presentation;

/**
 * Created by wutao on 2017/6/4.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.groupd.cookbook.R;

public class Messages {
    public static void fatalError(final Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle("Fetal error");
        alertDialog.setMessage(message);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                owner.finish();
            }
        });

        alertDialog.show();
    }

    public static void warning(Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle("Warning");
        alertDialog.setMessage(message);

        alertDialog.show();
    }
}
