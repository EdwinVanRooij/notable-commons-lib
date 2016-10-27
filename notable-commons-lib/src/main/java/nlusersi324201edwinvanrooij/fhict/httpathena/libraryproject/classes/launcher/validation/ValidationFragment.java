package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.launcher.validation;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.launcher.LauncherActivity;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers.LocalHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.physical.Account;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class ValidationFragment extends Fragment {

    static Class loginSuccessActivity;

    LocalHandler localHandler;

    private static final String TAG = "ValidationFragment";

    protected abstract void initialize();

    protected abstract void initViews(ViewGroup rootView);

    protected abstract void initButtons();

    protected abstract void initEditTexts();

    ValidationObject getDefaultUsernameValidation(String username) {
        if (username.length() < 5) {
            return new ValidationObject("Moet meer dan 5 karakters bevatten.");
        }

        if (username.length() > 15) {
            return new ValidationObject("Mag niet meer dan 15 karakters bevatten.");
        }

        return new ValidationObject();
    }

    ValidationObject getDefaultEmailValidation(String email) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return new ValidationObject("Vul een geldig e-mail adres in.");
        }

        return new ValidationObject();
    }

    ValidationObject getDefaultPasswordValidation(String password) {
        if (password.length() < 5) {
            return new ValidationObject("Moet meer dan 5 karakters bevatten.");
        }

        if (password.length() > 20) {
            return new ValidationObject("Mag niet meer dan 20 karakters bevatten.");
        }

        if(!password.matches(".*\\d.*")){
            return new ValidationObject("Moet minimaal één cijfer bevatten.");
        }

        return new ValidationObject();
    }

    void checkCredentials(String username, String password) throws UnsupportedEncodingException, MalformedURLException {
    }

    private void onCorrectCredentials(Account a) {

        localHandler.ExecuteLogin(a);

        if (loginSuccessActivity != null) {
            startActivity(new Intent(getActivity(), loginSuccessActivity));
        }

        GeneralHandler.ClearTopGo(getActivity(), LauncherActivity.class, R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

    private void onIncorrectCredentials() {
        Toast.makeText(getActivity(), R.string.exception_login_credentials, Toast.LENGTH_LONG).show();
    }
}
