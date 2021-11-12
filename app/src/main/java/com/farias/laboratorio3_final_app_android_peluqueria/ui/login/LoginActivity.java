package com.farias.laboratorio3_final_app_android_peluqueria.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.farias.laboratorio3_final_app_android_peluqueria.MainActivity;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.ActivityLoginBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Administrador;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private Administrador a;
    public static Context contextOfApplication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginViewModel.getSuceso().observe(this, elSuceso -> {
            if (elSuceso == "Exito") {
                Log.d("mensaje: ", "Login Exitoso");
            } else {
                Log.d("mensaje: ", "Login Fallido");
                loadingProgressBar.setVisibility(View.INVISIBLE);
                passwordEditText.setText("");
                usernameEditText.setText("");

            }

        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());

                // para cerrar el teclado virtual
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);

                //loginViewModel.setPropietario();
                loginViewModel.getAdministrador().observe(LoginActivity.this, administrador -> {
                    a = administrador;

                    if (a != null) {

                        Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("administrador", a);
                        MainIntent.putExtra("administrador", bundle);


                        startActivity(MainIntent);

                        String nombre = a.getNombre();

                        // TODO: se personaliza el saludo de acuerdo a la termincion del nombre para Todas y todos :)
                        char ultimo;
                        ultimo = nombre.charAt(nombre.length() - 1);

                        Log.d("mensaje", "updateUiWithUser: " + ultimo);

                        String welcome;
                        welcome = Objects.equals(ultimo, 'a') ? "Bienvenida " + a.getNombre() : "Bienvenido " + a.getNombre();
                        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


        private void showLoginFailed (@StringRes Integer errorString){
            Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
        }
    }


