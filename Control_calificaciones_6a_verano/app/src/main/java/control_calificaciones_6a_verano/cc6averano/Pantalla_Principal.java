package control_calificaciones_6a_verano.cc6averano;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Pantalla_Principal extends Activity

    {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__principal);
    }

 public void eventoAltasPP (View v)
 {
     Intent intento=new Intent();
     intento.setClass(this, AgregarAlumno.class);
     startActivity(intento);
 }


        public void eventolista(View v)
        {
            Intent intento=new Intent();
            intento.setClass(this,ListaCalificaciones.class);
            startActivity(intento);

        }



        public void Actualizar(View v)
        {
            Intent intento=new Intent();
            intento.setClass(this,ActualizarCalificaciones.class);
            startActivity(intento);

        }



        public void Eliminar(View v)
        {
            Intent intento=new Intent();
            intento.setClass(this,Eliminar_Alumnos.class);
            startActivity(intento);

        }

    public void agregarcali (View v)
    {
        Intent intento=new Intent();
        intento.setClass(this,Consultar_Calificaciones.class);
                startActivity(intento);

    }

    }
