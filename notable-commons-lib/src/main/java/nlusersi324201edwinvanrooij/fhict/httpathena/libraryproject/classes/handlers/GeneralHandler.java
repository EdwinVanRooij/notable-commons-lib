package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Edwin on 2/19/2016
 */
public class GeneralHandler {
    private static final String TAG = "GeneralHandler";

    public static void ClearTopGo(Activity from, Class to, int StartAnimation, int ExitAnimation) {
        Intent i = new Intent(from, to);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d(TAG, "ClearTopGo: cleared top");
        from.startActivity(i);
        from.overridePendingTransition(StartAnimation, ExitAnimation);

        Log.d(TAG, "ClearTopGo: started activity");
    }

    public static void ClearTopGo(Activity from, Class to) {
        Intent i = new Intent(from, to);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d(TAG, "ClearTopGo: cleared top");
        from.startActivity(i);

        Log.d(TAG, "ClearTopGo: started activity");
    }
}
