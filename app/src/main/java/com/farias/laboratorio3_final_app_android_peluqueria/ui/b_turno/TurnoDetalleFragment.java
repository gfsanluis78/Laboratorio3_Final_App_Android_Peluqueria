package com.farias.laboratorio3_final_app_android_peluqueria.ui.b_turno;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farias.laboratorio3_final_app_android_peluqueria.R;
import com.farias.laboratorio3_final_app_android_peluqueria.databinding.FragmentTurnoDetalleBinding;

public class TurnoDetalleFragment extends Fragment {

    private TurnoDetalleViewModel mViewModel;
    private FragmentTurnoDetalleBinding binding;
    private View view;
    private TextView TV_titulo, TV_fecha,  TV_horario, TV_costo,
            TV_cliente_nombre,TV_cliente_dni, TV_cliente_telefono, TV_cliente_email,
            TV_profesional_nombre,TV_profesional_dni, TV_profesional_telefono, TV_profesinal_email,
            TV_trabajo_tipo, TV_trabajo_descripcion;
    private CardView CV_cliente, CV_profesional, CV_trabajo;

    public static TurnoDetalleFragment newInstance() {
        return new TurnoDetalleFragment();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(TurnoDetalleViewModel.class);
        binding = FragmentTurnoDetalleBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        TV_titulo = binding.TVTitulo;
        TV_fecha = binding.TVFecha;
        TV_horario = binding.TVHorario;
        TV_costo = binding.TVCosto;
        TV_cliente_nombre = binding.TVClienteNombre;
        TV_cliente_dni = binding.TVClienteDni;
        TV_cliente_telefono = binding.TVClienteTelefono;
        TV_cliente_email = binding.TVClienteEmail;
        TV_profesional_nombre = binding.TVProfesionalNombre;
        TV_profesional_dni = binding.TVProfesionalDni;
        TV_profesional_telefono = binding.TVProfesionalTelefono;
        TV_profesinal_email = binding.TVProfesionalEmail;
        TV_trabajo_tipo = binding.TVTrabajoTipo;
        TV_trabajo_descripcion = binding.TVTrabajoDescripcion;
        CV_cliente = binding.CVCliente;
        CV_profesional = binding.CVProfesional;
        CV_trabajo = binding.CVTrabajo;

        mViewModel.getTurnoMutableLiveData().observe(getViewLifecycleOwner(), turno -> {

            if(turno != null){

                TV_titulo.setText("Detalles del turno " + turno.getIdTurno());
                TV_fecha.setText("Fecha: " + turno.getFecha());
                TV_horario.setText("Horario: " + turno.getBloque().getDesdeHasta());
                TV_costo.setText("Costo: " + turno.getCosto());

                TV_cliente_nombre.setText("Nombre: " + turno.getCliente().getNombreCompleto());
                TV_cliente_dni.setText("Dni: " + turno.getCliente().getDni());
                TV_cliente_telefono.setText("Telefono: " + turno.getCliente().getTelefono());
                TV_cliente_email.setText("Email: " + turno.getCliente().getEmail());
                CV_cliente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cliente", turno.getCliente());
                        Log.d("mensaje", "Turno detalle fragment: " + turno.getCliente().toString());
                        Navigation.findNavController(view).navigate(R.id.clienteDetalleFragment,bundle);       // Meto el bundle clave budle en el navigation
                    }
                });

                TV_profesional_nombre.setText("Nombre: " + turno.getTrabajo().getEmpleado().getNombreCompleto());
                TV_profesional_dni.setText("Dni: " + turno.getTrabajo().getEmpleado().getDni());
                TV_profesional_telefono.setText("Telefono: " + turno.getTrabajo().getEmpleado().getTelefono());
                TV_profesinal_email.setText("Email: " + turno.getTrabajo().getEmpleado().getEmail());
                CV_profesional.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("empleado", turno.getTrabajo().getEmpleado());
                        Log.d("mensaje", "Turno detalle fragment: " + turno.getTrabajo().getEmpleado().toString());
                        Navigation.findNavController(view).navigate(R.id.empleadoDetalleFragment,bundle);
                    }
                });


                TV_trabajo_tipo.setText("Tipo de trabajo: " + turno.getTrabajo().getTipoDeTrabajo().getNombre());
                TV_trabajo_descripcion.setText("Descripcion: " + turno.getTrabajo().getTipoDeTrabajo().getDescripcion());
                CV_trabajo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("trabajo", turno.getTrabajo().getTipoDeTrabajo());
                        Log.d("mensaje", "Turno detalle fragment: " + turno.getTrabajo().getTipoDeTrabajo().toString());
                        Navigation.findNavController(view).navigate(R.id.trabajoDetalleFragment,bundle);
                    }
                });
             }

        });

        mViewModel.setTurnoMutableLiveData(getArguments());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}