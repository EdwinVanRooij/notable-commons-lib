package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.launcher;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.launcher.validation.LoginFragment;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.launcher.validation.RegisterFragment;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private ImageView ivLogo;
    private TextView tvAppName;
    private Button btnRegister;
    private Button btnLogin;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_launcher_main, container, false);
        initViews(rootView);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ADAM.otf");
        tvAppName.setTypeface(tf);

        Animation aLogo = AnimationUtils.loadAnimation(getActivity(), R.anim.move_mid_to_top);
        aLogo.setStartOffset(getResources().getInteger(R.integer.default_animation_duration) * 4 / 2);
        ivLogo.startAnimation(aLogo);

        Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_from_bottom);
        a.setStartOffset(getResources().getInteger(R.integer.default_animation_duration) * 4);
        tvAppName.startAnimation(a);
        btnRegister.startAnimation(a);
        btnLogin.startAnimation(a);

        initButtons();

        return rootView;
    }

    private void initViews(ViewGroup rootView) {
        ivLogo = (ImageView) rootView.findViewById(R.id.ivLogo);
        tvAppName = (TextView) rootView.findViewById(R.id.tvAppName);
        btnRegister = (Button) rootView.findViewById(R.id.btnRegister);
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
    }

    private void initButtons() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment loginFragment = new LoginFragment();
                ft.add(R.id.fragment_container, loginFragment);
                ft.setCustomAnimations(R.anim.slide_in_left, 0, 0, R.anim.slide_out_right);
                ft.addToBackStack(null);
                ft.show(loginFragment);
                ft.commit();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment registerFragment = new RegisterFragment();
                ft.add(R.id.fragment_container, registerFragment);
                ft.setCustomAnimations(R.anim.slide_in_left, 0, 0, R.anim.slide_out_right);
                ft.addToBackStack(null);
                ft.show(registerFragment);
                ft.commit();
            }
        });
    }
}