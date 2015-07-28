package control_calificaciones_6a_verano.cc6averano;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AgregarAlumno extends Activity {

    EditText etAlumno;
    CrearTablas ct =new CrearTablas(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alumno);
    }


    public void eventoAgegaCali (View v)
    {
        Intent intento=new Intent();
        intento.setClass(this,AgregarCalificacion.class);
        startActivity(intento);
    }

    public void eventoAgregarAlumno(View v)
    {
         etAlumno= (EditText) findViewById(R.id.etNombreA);
        long estudianteAgregado = ct.agregarAlumnos(String.valueOf(etAlumno.getText().toString()));

        if(estudianteAgregado !=-1)
        {
            Toast.makeText(this,"Estudiante agregado", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Estudiante no agregado", Toast.LENGTH_LONG).show();
        }
    }



}
