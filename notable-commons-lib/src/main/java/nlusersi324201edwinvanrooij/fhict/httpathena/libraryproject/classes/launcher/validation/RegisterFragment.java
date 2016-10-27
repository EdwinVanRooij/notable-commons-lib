package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.launcher.validation;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.asynctasks.AsyncDataRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.asynctasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers.LocalHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends ValidationFragment {

    protected EditText etUsername;
    protected EditText etEmail;
    protected EditText etPassword;
    protected TextInputLayout containerUsername;
    protected TextInputLayout containerEmail;
    protected TextInputLayout containerPassword;
    protected Button btnRegister;

    private boolean usernameCorrect;
    private boolean emailCorrect;
    private boolean passwordCorrect;

    private String username;
    private String email;
    private String password;

    private static final String TAG = "RegisterFragment";

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initialize() {
        localHandler = new LocalHandler(getActivity());
        accountHandler = new AccountHandler(getActivity());

        initEditTexts();
        initButtons();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_launcher_register, container, false);
        initViews(rootView);

        Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        a.setStartOffset(getResources().getInteger(R.integer.slide_duration_horizontal));
        containerUsername.startAnimation(a);
        containerEmail.startAnimation(a);
        containerPassword.startAnimation(a);
        Animation a2 = AnimationUtils.loadAnimation(getActivity(), R.anim.open_to_sides);
        a2.setStartOffset(getResources().getInteger(R.integer.default_animation_duration) + getResources().getInteger(R.integer.slide_duration_horizontal));
        etUsername.startAnimation(a2);
        etEmail.startAnimation(a2);
        etPassword.startAnimation(a2);

        initialize();

        return rootView;
    }

    @Override
    protected void initViews(ViewGroup rootView) {
        etUsername = (EditText) rootView.findViewById(R.id.etUsername);
        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        etPassword = (EditText) rootView.findViewById(R.id.etPassword);
        containerUsername = (TextInputLayout) rootView.findViewById(R.id.etUsernameContainer);
        containerEmail = (TextInputLayout) rootView.findViewById(R.id.etEmailContainer);
        containerPassword = (TextInputLayout) rootView.findViewById(R.id.etPasswordContainer);
        btnRegister = (Button) rootView.findViewById(R.id.btnRegister);
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
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email = etEmail.getText().toString();
                emailCorrect = !email.isEmpty();
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
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    username = etUsername.getText().toString().trim();
                    email = etEmail.getText().toString().trim();
                    password = etPassword.getText().toString().trim();

                    if (!defaultChecksPassed()) {
                        return;
                    }

                    checkIfUsernameExists();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showButton() {
        if (usernameCorrect && passwordCorrect && emailCorrect) {
            if (btnRegister.getVisibility() == View.INVISIBLE) {
                btnRegister.setEnabled(true);
                btnRegister.setVisibility(View.VISIBLE);
                Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
                btnRegister.startAnimation(a);
            }
        } else {
            if (btnRegister.getVisibility() == View.VISIBLE) {
                btnRegister.setEnabled(false);
                Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
                btnRegister.startAnimation(a);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        btnRegister.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
    }

    private void checkIfUsernameExists() throws UnsupportedEncodingException, MalformedURLException {
        AsyncResponse a = new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    if (accountHandler.booleanFromJSON(output)) {
                        Log.d(TAG, "processFinish: Username " + username + " already exists.");
                        onUserExists();
                        return;
                    }

                    // Username does not exists, proceed to create account
                    Log.d(TAG, "processFinish: Username does not exist yet, checking email now");

                    checkIfEmailExists();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        accountHandler.getUsername(a, username);
    }

    private void checkIfEmailExists() throws UnsupportedEncodingException, MalformedURLException {
        AsyncResponse a = new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    if (accountHandler.booleanFromJSON(output)) {
                        Log.d(TAG, "processFinish: Email " + email + " already exists.");
                        onEmailExists();
                        return;
                    }
                    // Email does not exists, proceed to create account
                    Log.d(TAG, "processFinish: Email does not exist yet, proceeding to create account");

                    createAccount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        String query = "select count(*) boolean from account where email = '" + email + "';";
        new AsyncDataRequest(a, getActivity(), AsyncURLRequest.queryType.Select, query, true).execute();
    }

    private void onUserExists() {
        etUsername.setError("Gebruikersnaam is al in gebruik.");
        etUsername.requestFocus();
    }
    private void onEmailExists() {
        etEmail.setError("Email is al in gebruik.");
        etEmail.requestFocus();
    }

    private void createAccount() throws UnsupportedEncodingException, MalformedURLException {
        AsyncResponse a = new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    Log.d(TAG, "processFinish: Checking credentials after create account");
                    checkCredentials(username, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        accountHandler.insertAccount(a, username, email, password);
    }

    private boolean defaultChecksPassed() {
        ValidationObject validationUsername = getDefaultUsernameValidation(username);
        boolean usernameIsCorrect = validationUsername.isCorrect();
        if (!usernameIsCorrect) {
            etUsername.setError(validationUsername.getInformativeString());
            etUsername.requestFocus();
        }

        ValidationObject validationEmail = getDefaultEmailValidation(email);
        boolean emailIsCorrect = validationEmail.isCorrect();
        if (!emailIsCorrect) {
            etEmail.setError(validationEmail.getInformativeString());
            etEmail.requestFocus();
        }

        ValidationObject validationPassword = getDefaultPasswordValidation(password);
        boolean passwordIsCorrect = validationPassword.isCorrect();
        if (!passwordIsCorrect) {
            etPassword.setError(validationPassword.getInformativeString());
            etPassword.requestFocus();
        }

        if (!usernameIsCorrect || !passwordIsCorrect || !emailIsCorrect) {
            return false;
        }

        if (etUsername.getError() != null) {
            etUsername.setError(null);
        }
        if (etEmail.getError() != null) {
            etEmail.setError(null);
        }
        if (etPassword.getError() != null) {
            etPassword.setError(null);
        }
        return true;
    }
}