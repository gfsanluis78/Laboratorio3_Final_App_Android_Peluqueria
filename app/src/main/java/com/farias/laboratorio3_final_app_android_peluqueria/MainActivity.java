package com.farias.laboratorio3_final_app_android_peluqueria;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Administrador;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private MainActivityViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        model = new ViewModelProvider(this).get(MainActivityViewModel.class);

        inicializarHeader(navigationView);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_turno,
                R.id.nav_cliente,
                R.id.nav_informes,
                R.id.nav_configuraciones,
                R.id.nav_perfil,
                R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void inicializarHeader(NavigationView navigationView) {

        View header = navigationView.getHeaderView(0);

        Administrador a = (Administrador) getIntent().getBundleExtra("administrador").getSerializable("administrador");

        model.actualizarPerfil(a);
        model.getAdministradorMutableLiveData().observe(this, administrador ->  {
            {
                if(a != null) {
                    ImageView h_avatar = header.findViewById(R.id.header_avatar);
                    TextView h_nombre = header.findViewById(R.id.header_title);
                    TextView h_correo = header.findViewById(R.id.header_correo);

                    Log.d("mensaje MainAct", administrador.getUrlFoto());

                    h_nombre.setText(administrador.getNombre() + " " + administrador.getApellido());
                    h_correo.setText(administrador.getEmail());
                    Glide.with(this.getApplicationContext())
                            .load(administrador.getUrlFoto())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)       // Llama la imagen remota y la carga en el cache,
                            .into(h_avatar);                          // despues la busca de ahi y es mas rapido
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}