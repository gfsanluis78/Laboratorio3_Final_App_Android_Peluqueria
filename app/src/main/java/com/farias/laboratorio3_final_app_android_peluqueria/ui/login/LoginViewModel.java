package com.farias.laboratorio3_final_app_android_peluqueria.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Administrador;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.LoginRetrofit;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<Administrador> administrador;
    private MutableLiveData<LoginRetrofit> loginRetrofitMutableLiveData;
    private Context context;
    private MutableLiveData<String> suceso;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        loginFormState = new MutableLiveData<>();
        administrador = new MutableLiveData<>();
        loginRetrofitMutableLiveData = new MutableLiveData<>();
        suceso = new MutableLiveData<>();
    }

    public MutableLiveData<LoginFormState> getLoginFormState(){
        if(loginFormState == null){
            loginFormState = new MutableLiveData<>();
        }
        return loginFormState;
    }

    public MutableLiveData<Administrador> getAdministrador() {
        if( administrador == null) {
            administrador = new MutableLiveData<>();
        }
        return administrador;
    }

    public MutableLiveData<LoginRetrofit> getLoginRetrofitMutableLiveData() {
        if( loginRetrofitMutableLiveData == null){
            loginRetrofitMutableLiveData = new MutableLiveData<>();
        }
        return loginRetrofitMutableLiveData;
    }

    public MutableLiveData<String> getSuceso(){
        if( suceso == null){
            suceso = new MutableLiveData<>();
        }
        return suceso;
    }

    public void login(String username, String password) {

        Call<LoginRetrofit> request = ApiClient.getMyApiClient("Login VM").login(username, password);
        Log.d("mensaje", "Hizo el CALL desde LoginVM.login con " + username + " y " + password);

        request.enqueue(new Callback<LoginRetrofit>() {
            @Override
            public void onResponse(Call<LoginRetrofit> call, Response<LoginRetrofit> response) {
                if (response.isSuccessful()) {
                    LoginRetrofit laRta = response.body();

                    String elToken = laRta.getToken();

                    ApiClient.guardar(context, elToken);
                    setAdministrador();

                } else
                {
                    ApiClient.guardar(context, "");
                    Toast.makeText(context, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    suceso.postValue("Fallido");
                }
            }
            @Override
            public void onFailure(Call<LoginRetrofit> call, Throwable t) {
                Toast.makeText(context, "Fallo en la comunicacion con la APi web", Toast.LENGTH_SHORT).show();
                Log.d("mensaje: ", "Fallo en LoginVM/login "+t.getMessage());
                suceso.postValue("Fallido");
            }
        });

    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    public void setAdministrador(){

        String token = ApiClient.leer(context, getClass().toString());

        if(token.length() > 10) {

            Call<Administrador> usuario = ApiClient.getMyApiClient("Login VM").obtenerUsuarioActual(token);
            usuario.enqueue(new Callback<Administrador>() {
                @Override
                public void onResponse(Call<Administrador> call, Response<Administrador> response) {
                    //Log.d("mensaje", response.body().getId() + " " + response.body().getApellido());
                    if (response.isSuccessful()) {
                        administrador.setValue(response.body());
                        if(administrador!= null){
                            Log.d("mensaje/LoginVM/succes", administrador.getValue().getEmail());
                        }
                    } else
                    {
                        Log.d("mensaje/LoginVM/notSucc",  response.message());
                    }


                }

                @Override
                public void onFailure(Call<Administrador> call, Throwable t) {
                    Log.d("mensaje/LogVM/onfailure", t.getMessage().toString());
                    Toast.makeText(context, "Fallo en buscar usuario actual" +t.getMessage() , Toast.LENGTH_SHORT).show();
                }
            });

        }
        else {
            Log.d("mensaje", "Quiso setear administrador con token "+ token);
        }
    } // setAdministrador
}