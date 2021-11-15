package com.farias.laboratorio3_final_app_android_peluqueria.ui.f_perfil;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.farias.laboratorio3_final_app_android_peluqueria.MainActivityViewModel;
import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.data.model.LoggedInUser;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentPerfilBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Administrador;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;

    private LoggedInUser user;
    Administrador p;

    private FragmentPerfilBinding binding;
    private EditText id,dni,nombre,apellido,email,clave,telefono;
    private TextView titulo;
    private ImageView foto;
    private Button editar, guardar;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =                                                                   // Instanciamos el viewModel
                new ViewModelProvider(this).get(PerfilViewModel.class);

        MainActivityViewModel modelActivity = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);    // Instancio el binding del fragment
        View root = binding.getRoot();  // Del binding saco el root

        this.inicializarControles(root);         // Inicializo la vista

        perfilViewModel.getUsuario().observe(getViewLifecycleOwner(), administrador ->  {  // De mi nuevo perfilView uso el metodo getUsuario
            {

                if(administrador != null){
                    Log.i("mensaje", administrador.getNombre() );
                    modelActivity.actualizarPerfil(administrador);
                    p= administrador;

                    binding.TVTitulo.setText(administrador.getNombre() + " " + administrador.getApellido());
                    binding.ETId.setText(administrador.getIdAdministrador()+"");
                    binding.ETDni.setText(administrador.getDni().toString());
                    binding.ETNombre.setText(administrador.getNombre());
                    binding.ETApellido.setText(administrador.getApellido());
                    binding.ETEmail.setText(administrador.getEmail());
                    binding.ETPassword.setText(administrador.getContraseña());
                    binding.ETTelefono.setText(administrador.getTelefono());
                    //binding.IVFoto.setImageResource(R.drawable.juan); // Todo: usar glide administrador.getAvatar()
                    Glide.with(getActivity().getApplicationContext())
                            .load(administrador.getUrlFoto())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)       // Llama la imagen remota y la carga en el cache,
                            .into(binding.IVFoto);                          // despues la busca de ahi y es mas rapido
                }

            };
        });

        perfilViewModel.getEstadoEditable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {  // observo el boton
            @Override
            public void onChanged(Boolean aBoolean) {
//                id.setEnabled((aBoolean));
                dni.setEnabled((aBoolean));
                nombre.setEnabled((aBoolean));
                apellido.setEnabled((aBoolean));
                email.setEnabled((aBoolean));
                clave.setEnabled((false));
                telefono.setEnabled((aBoolean));
            }
        });

        perfilViewModel.getEstadoNoEditable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                dni.setEnabled((aBoolean));
                nombre.setEnabled((aBoolean));
                apellido.setEnabled((aBoolean));
                email.setEnabled((aBoolean));
                clave.setEnabled((false));
                telefono.setEnabled((aBoolean));

                editar.setText("Editar");
                editar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        perfilViewModel.cambiarEstadoEditable();
                    }
                });


                perfilViewModel.obtenerUsuario();

            }
        });

        perfilViewModel.getVisibleEditar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                editar.setVisibility(integer);
                editar.setText("Cancelar");
                editar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Cancelar edicion");
                        builder.setMessage("Seguro desea cancelar y salir de editar perfil?");
                        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Navigation.findNavController(view).navigate(R.id.nav_home);
                            }
                        });
                        // y si no quiero
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        //Inicializar el alert dialog
                        AlertDialog alertDialog = builder.create();
                        // Mostrar el alert
                        alertDialog.show();
                    }
                });
            }
        });

        perfilViewModel.getFuncionBoton().observe(getViewLifecycleOwner(), funcion -> {
            if( funcion == "Editar" ){

                editar.setText("Editar");
                editar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        perfilViewModel.cambiarEstadoEditable();
                    }
                });

            }


        });

        perfilViewModel.getVisibleGuardar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                guardar.setVisibility(integer);

            }
        });


        perfilViewModel.obtenerUsuario();

        return root;
    }

    private void inicializarControles(View view){
        titulo = view.findViewById(R.id.TV_titulo);
        id = view.findViewById(R.id.ET_Id);
        dni = view.findViewById(R.id.ET_dni);
        nombre = view.findViewById(R.id.ET_nombre);
        apellido = view.findViewById(R.id.ET_apellido);
        email = view.findViewById(R.id.ET_email);
        clave = view.findViewById(R.id.ET_password);
        telefono = view.findViewById(R.id.ET_telefono);
        editar = view.findViewById(R.id.BT_Editar);
        foto = view.findViewById(R.id.IV_foto);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfilViewModel.cambiarEstadoEditable();
            }
        });

        guardar= view.findViewById(R.id.BT_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                p.setIdAdministrador(Integer.parseInt(id.getText().toString()));
                p.setDni(dni.getText().toString());
                p.setNombre(nombre.getText().toString());
                p.setApellido(apellido.getText().toString());
                p.setEmail(email.getText().toString());
                p.setContraseña(clave.getText().toString());
                p.setTelefono(telefono.getText().toString());

                perfilViewModel.modificarDatos(p);
                //perfilViewModel.obtenerUsuario();



                // Posibilidad de salir al home una vez aplicados los cambios
                // Navigation.findNavController(view).navigate(R.id.nav_home);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}