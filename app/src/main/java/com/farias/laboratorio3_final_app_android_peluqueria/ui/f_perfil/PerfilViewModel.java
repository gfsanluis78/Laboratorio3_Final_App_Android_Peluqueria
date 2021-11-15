package com.farias.laboratorio3_final_app_android_peluqueria.ui.f_perfil;

import static android.widget.Toast.LENGTH_SHORT;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Administrador;
import com.farias.laboratorio3_final_app_android_peluqueria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<String> mText;

    private MutableLiveData<Administrador> usuario;   // Se declaran los mutables de cada elemento del fragment para poder...

    private MutableLiveData<Integer> visibleEditar; // accederlos desde la vista
    private MutableLiveData<Integer> visibleGuardar;
    private MutableLiveData<Boolean> estadoEditable;
    private MutableLiveData<Boolean> estadoNoEditable;
    private MutableLiveData<String> funcionBoton;

    private Context context;

    public PerfilViewModel (@NonNull Application application ){
        super(application);
        context = application.getApplicationContext();
        usuario =  new MutableLiveData<>();
        visibleGuardar =  new MutableLiveData<>();
        estadoEditable =  new MutableLiveData<>();
        estadoNoEditable =  new MutableLiveData<>();
        funcionBoton = new MutableLiveData<>();
    }

    public LiveData<Administrador> getUsuario() {
        if (usuario == null){
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }

    public MutableLiveData<Integer> getVisibleEditar() {

        if(visibleEditar == null){
            visibleEditar = new MutableLiveData<>();
        }

        return visibleEditar;
    }

    public MutableLiveData<Integer> getVisibleGuardar() {

        if (visibleGuardar == null){
            visibleGuardar = new MutableLiveData<>();
        }

        return visibleGuardar;
    }

    public MutableLiveData<Boolean> getEstadoEditable() {

        if (estadoEditable == null) {
            estadoEditable = new MutableLiveData<>();
        }

        return estadoEditable;
    }

    public MutableLiveData<Boolean> getEstadoNoEditable() {
        if(estadoNoEditable == null) {
            estadoNoEditable = new MutableLiveData<>();
        }
        return estadoNoEditable;
    }

    public MutableLiveData<String> getFuncionBoton() {
        if(funcionBoton == null) {
            funcionBoton = new MutableLiveData<>();
        }
        return   funcionBoton;
    }

    public void obtenerUsuario() {                  // metodo para recrear al inicio de la vista al usuario actual. Usa el mutable  usuario

        String token = elToken();

        Call<Administrador> callActual = ApiClient.getMyApiClient("Perfil VM").obtenerUsuarioActual(token);
        callActual.enqueue(new Callback<Administrador>() {
            @Override
            public void onResponse(Call<Administrador> call, Response<Administrador> response) {
                usuario.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Administrador> call, Throwable t) {
                Log.d("Mensaje:", "PerfilVM/obtenerUsuario: Fallo en buscar(" + t.getMessage() + ")");
            }
        });

    }

    public void modificarDatos(Administrador p){      // del lado de la activity saco los datos del propietario y los mando a este metodo

        // Propietario miPropietario = new Propietario(){}; miPropietario = p;
        p.setContraseña("");

        Log.d("mensaje: ", "PerfilVM/modificarDato El propietario que llega es " + p.toString());

        String token = elToken();

        Call<Administrador> callMod = ApiClient.getMyApiClient("Perfil VM").actualizarPerfil(token, p);
        callMod.enqueue(new Callback<Administrador>() {
            @Override
            public void onResponse(Call<Administrador> call, Response<Administrador> response) {
                if(response.isSuccessful()){

                    visibleGuardar.setValue((View.INVISIBLE));

                    funcionBoton.setValue("Editar");

                    estadoEditable.setValue(false);

                    Administrador prop = response.body();
                    usuario.setValue(prop);

                    Toast.makeText(context,"Datos guardados correctamente", LENGTH_SHORT).show();

                }
                else {
                    Log.d("mensaje: ", "@PerfilVM/modificarDatos Respuesta no Exitosa " + response.body());
                }

            }

            @Override
            public void onFailure(Call<Administrador> call, Throwable t) {
                Log.d("mensaje: ", "@PerfilVM/modificarDatos Fallo en traer nuevo propietario (" + t.getMessage() + ")" );

            }
        });
    }

    public void cambiarEstadoEditable(){                    // Uso el mutable estadoEditable para cambiar los estados de los mutables vedi y v guardar
        estadoEditable.setValue(true);
        visibleEditar.setValue((View.VISIBLE));
        visibleGuardar.setValue(View.VISIBLE);
    }

    public void cambiarEstadoNoEditable(){                    // Uso el mutable estadoEditable para cambiar los estados de los mutables vedi y v guardar
        estadoEditable.setValue(false);
        visibleEditar.setValue((View.VISIBLE));
        visibleGuardar.setValue(View.INVISIBLE);
    }

    private String elToken(){
        String token = ApiClient.leer(context, getClass().toString());
        return token;
    }

}