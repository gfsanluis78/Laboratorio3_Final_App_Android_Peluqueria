package com.farias.laboratorio3_final_app_android_peluqueria.ui.c_cliente;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentClienteCrearBinding;
import com.farias.laboratorio3_final_app_android_peluqueria.modelo.Cliente;

import java.io.ByteArrayOutputStream;

public class ClienteCrearFragment extends Fragment {

    private ClienteCrearViewModel mViewModel;
    private FragmentClienteCrearBinding binding;
    private EditText ET_nombre, ET_apellido, ET_email, ET_telefono, ET_dni;
    private ImageView imagen1;
    private String imgDeCode="";
    private Button btCargarFoto, btGuardarCliente;
    private Cliente cliente;

    public static ClienteCrearFragment newInstance() {
        return new ClienteCrearFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentClienteCrearBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        iniciarVista();

//        mViewModel.getClienteMutableLiveData().observe(getViewLifecycleOwner(), i -> {
//                cliente = i;
//        });

        btCargarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        btGuardarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    private void guardar() {
        cliente = new Cliente();
        cliente.setNombre(ET_nombre.getText().toString());
        cliente.setApellido(ET_apellido.getText().toString());
        cliente.setEmail(ET_email.getText().toString());
        cliente.setTelefono(ET_telefono.getText().toString());
        cliente.setDni(ET_dni.getText().toString());
        convertirImagen();
        cliente.setAvatar(imgDeCode);

        mViewModel.guardarCliente(cliente);
    }

    public void iniciarVista(){

        mViewModel= new ViewModelProvider(this).get(ClienteCrearViewModel.class);

        ET_nombre = binding.ETNombre;
        ET_apellido = binding.ETApellido;
        ET_email = binding.ETEmail;
        ET_telefono = binding.ETTelefono;
        ET_dni = binding.ETDni;
        imagen1 = binding.imageView;
        btCargarFoto = binding.BTSubirFoto;
        btGuardarCliente = binding.BTGuardarCliente;
    }

    private void cargarImagen(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion..."),10);
    }

    private void convertirImagen(){
        BitmapDrawable drawable = (BitmapDrawable) imagen1.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        if(bitmap!=null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            imgDeCode= Base64.encodeToString(b,Base64.DEFAULT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10){
            Uri path=data.getData();
            imagen1.setImageURI(path);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ClienteCrearViewModel.class);
        // TODO: Use the ViewModel
    }

}