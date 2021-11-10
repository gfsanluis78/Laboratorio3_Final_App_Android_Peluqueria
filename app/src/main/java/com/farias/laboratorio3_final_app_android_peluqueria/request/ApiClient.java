package com.farias.laboratorio3_final_app_android_peluqueria.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;
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
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Genaro Farias el 10/11/2021.
 * Estudiante de la ULP
 * gfsanluis78@gmail.com
 */

public class ApiClient {
    // Declaraciones
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private static final String URLBASE="https://192.168.1.111:45455/api/";    //le pongo el nombre de Url base que es mas informativa, termina en /
    private static  PostInterface postInterface;
    private static SharedPreferences sharedPreferences;

    // metodos
    private static SharedPreferences conectar(Context context){
        if (sharedPreferences==null){
            sharedPreferences = context.getSharedPreferences("Usuario",0);
        }
        return  sharedPreferences;
    }

    public static void guardar(Context context, String token){

        SharedPreferences sharedPreferences = conectar(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", "Bearer " + token);
        Log.d("mensaje/ApiC/guardar", "El token guardado: " + token);
        editor.commit();
    }

    public static String leer(Context context, String ubicacion){
        SharedPreferences sharedPreferences = conectar(context);
        String token = sharedPreferences.getString("token", "No token");
        Log.d("mensaje/ApiC/leer/", "El token leido: ok");


        return token;
    }

    // Construccion del objeto retrofit
    public static PostInterface getMyApiClient(String ubicacion){

        Log.d("mensaje Api", ubicacion);
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URLBASE)
                // agrego para confiar
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        postInterface=retrofit.create(PostInterface.class);

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

        // obtenerInquilinos()
        @GET("Inquilino/Todos")
        Call<List<Cliente>> obtenerClientes (@Header("Authorization") String token);



    }




}
