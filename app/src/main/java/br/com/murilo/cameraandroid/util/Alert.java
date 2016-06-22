package br.com.murilo.cameraandroid.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by murilocosta on 6/21/16.
 */
public class Alert {

    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
