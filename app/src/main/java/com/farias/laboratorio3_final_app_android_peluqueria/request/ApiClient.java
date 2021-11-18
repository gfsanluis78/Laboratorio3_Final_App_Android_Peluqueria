package com.farias.laboratorio3_final_app_android_peluqueria.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Administrador;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Bloque;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.ConsultaByTipoTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.ConsultaHorarios;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.DatosGrafico;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Empleado;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.LoginRetrofit;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Preparacion;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.TipoDeTrabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Trabajo;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Turno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

/**
 * Created by Genaro Farias el 10/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class ApiClient {
    // Declaraciones
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private static final String URLBASE = "https://192.168.1.111:45455/api/";    //le pongo el nombre de Url base que es mas informativa, termina en /
    private static PostInterface postInterface;
    private static SharedPreferences sharedPreferences;

    // metodos
    private static SharedPreferences conectar(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("Usuario", 0);
        }
        return sharedPreferences;
    }

    public static void guardar(Context context, String token) {

        SharedPreferences sharedPreferences = conectar(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", "Bearer " + token);
        Log.d("mensaje/ApiC/guardar", "El token guardado: " + token);
        editor.commit();
    }

    public static String leer(Context context, String ubicacion) {
        SharedPreferences sharedPreferences = conectar(context);
        String token = sharedPreferences.getString("token", "No token");
        Log.d("mensaje/ApiC/leer/", "El token leido: ok");
        return token;
    }

    // Construccion del objeto retrofit
    public static PostInterface getMyApiClient(String ubicacion) {

        Log.d("mensaje Api", ubicacion);
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                // agrego para confiar
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        postInterface = retrofit.create(PostInterface.class);

        return postInterface;
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // Armar la interface
    public interface PostInterface {

        @FormUrlEncoded
        @POST("Administradores/login")
        Call<LoginRetrofit> login(@Field("Usuario") String usuario, @Field("Clave") String clave);

        @GET("Administradores")
        Call<Administrador> obtenerUsuarioActual(@Header("Authorization") String token);


        @GET("Clientes/GetAll")
        Call<List<Cliente>> obtenerClientes(@Header("Authorization") String token);

        @GET("TipoDeTrabajos/GetAll")
        Call<List<TipoDeTrabajo>> obtenerTipoDeTrabajos(@Header("Authorization") String token);

        @POST("Empleados/GetAllByTipo")    // todo: falta ver disponibilidad horaria
        Call<List<Empleado>> obtenerEmpleadoDisponibleParaTrabajo (@Header("Authorization") String token,@Body TipoDeTrabajo tipoDeTrabajo);

        @POST("Bloques/GetAllByHorarioByEmpleado")      // todo: Busca los bloques del dia y del empleado
        Call<List<Bloque>> obtenerBloquesLibresDelDiaDelProfesional(@Header("Authorization") String token,@Body ConsultaHorarios consultaHorarios);

        @POST("Trabajos/GetAllByTipoTrabajoByEmpleado")      // todo: Busca los bloques del dia y del empleado
        Call<List<Trabajo>> obtenerTrabajosByTipoTrabajo(@Header("Authorization") String token,@Body ConsultaByTipoTrabajo entidad);

        @POST("Turnos")      // todo: Busca los bloques del dia y del empleado
        Call<Turno> crearTurno(@Header("Authorization") String token, @Body Turno entidad);

        @GET("Turnos/GetAllFull")
        Call<List<Turno>> obtenerTurnosFull(@Header("Authorization") String token);

        @POST("Turnos/GetAllFullByFecha")
        Call<List<Turno>> obtenerTurnosFullByFecha(@Header("Authorization") String token, @Body ConsultaHorarios consultaHorarios );

        // actualizarPerfil(Administrador administrador)
        @PATCH("Administradores/Editar")
        Call<Administrador> actualizarPerfil(@Header("Authorization") String token, @Body Administrador administrador);

        // buscarUltimoTurno
        @POST("Turnos/GetUltimoByCliente")
        Call<Turno> obtenerUltimoTurnoByCliente(@Header("Authorization") String token, @Body Cliente cliente );

        //obtenerTurnosByCliente
        @POST("Turnos/GetTurnosByCliente")
        Call<List<Turno>> obtenerTurnosByCliente(@Header("Authorization") String token, @Body Cliente cliente );

        // crearCliente
        @POST("CLientes/InsertConFoto")
        Call<Cliente> crearCliente(@Header("Authorization") String token, @Body Cliente cliente );

        // obtenerEmpleados
        @GET("Empleados/GetAll")
        Call<List<Empleado>> obtenerEmpleados(@Header("Authorization") String token);

        // obtenerTipoTrabajos
//        @GET("TipoDeTrabajos/GetAll")
//        Call<List<TipoDeTrabajo>> obtenerTipoTrabajos(@Header("Authorization") String token);

        // obtenerEmpleadosByTipoTrabajo
        @POST("Empleados/GetAllByTipoTrabajo")
        Call<List<Empleado>> obtenerEmpleadosByTipoTrabajo(@Header("Authorization") String token, @Body TipoDeTrabajo tipoDeTrabajo );

        // obtenerTipoTrabajoByEmpleado
        @POST("TipoDeTrabajos/GetAllByEmpleado")
        Call<List<TipoDeTrabajo>> obtenerTipoTrabajoByEmpleado(@Header("Authorization") String token, @Body Empleado empleado);

        // obtenerCantidadTurnosBYEmpleado
        @POST("Turnos/GetCantidadByEmpleado")
        Call<Integer> obtenerCantidadTurnosBYEmpleado(@Header("Authorization") String token, @Body Empleado empleado);

        // obtenerDatosGrafico1
        @GET("Turnos/GetTurnosAndEmpleado")
        Call<List<DatosGrafico>> obtenerDatosGrafico1(@Header("Authorization") String token);

    }

}
