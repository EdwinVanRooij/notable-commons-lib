package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.launcher.validation;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers.LocalHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends ValidationFragment {

    protected EditText etUsername;
    protected EditText etPassword;
    protected TextInputLayout containerUsername;
    protected TextInputLayout containerPassword;
    protected Button btnLogin;

    private static final String TAG = "LoginFragment";

    private boolean usernameCorrect;
    private boolean passwordCorrect;
    private String username;
    private String password;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initialize() {
        localHandler = new LocalHandler(getActivity());

        initEditTexts();
        initButtons();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_launcher_login, container, false);
        initViews(rootView);

        Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        a.setStartOffset(getResources().getInteger(R.integer.slide_duration_horizontal));
        containerUsername.startAnimation(a);
        containerPassword.startAnimation(a);

        Animation a2 = AnimationUtils.loadAnimation(getActivity(), R.anim.open_to_sides);
        a2.setStartOffset(getResources().getInteger(R.integer.default_animation_duration) + getResources().getInteger(R.integer.slide_duration_horizontal));
        etUsername.startAnimation(a2);
        etPassword.startAnimation(a2);

        initialize();

        return rootView;
    }

    @Override
    protected void initViews(ViewGroup rootView) {
        etUsername = (EditText) rootView.findViewById(R.id.etUsername);
        etPassword = (EditText) rootView.findViewById(R.id.etPassword);
        containerUsername = (TextInputLayout) rootView.findViewById(R.id.etUsernameContainer);
        containerPassword = (TextInputLayout) rootView.findViewById(R.id.etPasswordContainer);
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
    }

    @Override
    protected void initEditTexts() {
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username = etUsername.getText().toString();
                usernameCorrect = !username.isEmpty();
                showButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = etPassword.getText().toString();
                passwordCorrect = !password.isEmpty();
                showButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initButtons() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    username = etUsername.getText().toString().trim();
                    password = etPassword.getText().toString().trim();

//            if (!defaultChecksPassed()) {
//                Toast.makeText(getActivity(), R.string.exception_login_credentials, Toast.LENGTH_SHORT).show();
//                return;
//            }//todo: this one's a good check tbh, might return it later

                    checkCredentials(username, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showButton() {
        if (usernameCorrect && passwordCorrect) {
            if (btnLogin.getVisibility() == View.INVISIBLE) {
                btnLogin.setEnabled(true);
                btnLogin.setVisibility(View.VISIBLE);
                Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
                btnLogin.startAnimation(a);
            }
        } else {
            if (btnLogin.getVisibility() == View.VISIBLE) {
                btnLogin.setEnabled(false);
                Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
                btnLogin.startAnimation(a);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        btnLogin.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
    }

    private boolean defaultChecksPassed() {
        ValidationObject validationUsername = getDefaultUsernameValidation(username);
        boolean usernameIsCorrect = validationUsername.isCorrect();
        if (!usernameIsCorrect) {
            return false;
        }

        ValidationObject validationPassword = getDefaultPasswordValidation(password);
        boolean passwordIsCorrect = validationPassword.isCorrect();
        if (!passwordIsCorrect) {
            return false;
        }
        //

        return true;
    }
}