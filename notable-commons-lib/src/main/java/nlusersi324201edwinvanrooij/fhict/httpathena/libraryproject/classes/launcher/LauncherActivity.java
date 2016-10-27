package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers.LocalHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocalHandler localHandler = new LocalHandler(this);
        if (localHandler.isLoggedIn()) {
            try {
                Intent intent = new Intent(this, Class.forName(getResources().getString(R.string.path_to_mainactivity)));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

//            GeneralHandler.ClearTopGo(this, MainActivity.class); //todo; find a way to call main activity
//            GeneralHandler.ClearTopGo(this, LoginContainerActivity.class);
        } else {
            GeneralHandler.ClearTopGo(this, LoginContainerActivity.class);
        }
        finish();
        super.onCreate(savedInstanceState);
    }

}
