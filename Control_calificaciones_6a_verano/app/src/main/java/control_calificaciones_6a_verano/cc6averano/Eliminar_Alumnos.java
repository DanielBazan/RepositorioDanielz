package control_calificaciones_6a_verano.cc6averano;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class Eliminar_Alumnos extends Activity {

    Spinner spEliminar;
    CrearTablas ct;

    String acumulaAlumno[][]= new String[15][2];

    String alumnos[];
    int renglon=0;
    int idAlumno=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar__alumnos);

        spEliminar= (Spinner) findViewById(R.id.spinnerEliminar);
        LlenarSpinnerAlumnos();
    }


    public void LlenarSpinnerAlumnos()
    {
        //spEliminar = (Spinner) findViewById(R.id.spinnerSA);
        ct= new CrearTablas(this);

        Cursor c = ct.ObtenerAlumnos();

        int i=0;
        int contador=0;

        while(c.moveToNext())
        {
            int columnaId=c.getColumnIndex(TablaAlumno.ID_ALumno);
            String id=c.getString(columnaId);
            acumulaAlumno[renglon][0]=String.valueOf(id);

            int columnaAlumno=c.getColumnIndex(TablaAlumno.ALUMNO);
            String alumno= c.getString(columnaAlumno);

            acumulaAlumno[renglon][1]=String.valueOf(alumno);

            i=i+1;
            renglon=renglon+1;
        }//fin de while
        alumnos= new String[renglon];
        for(int j=0;j<renglon;j++)
        {
            alumnos[j]=String.valueOf(acumulaAlumno[j][1]);
        }

        ArrayAdapter adaptador =
                new ArrayAdapter(this,android.R.layout.simple_spinner_item,alumnos);
        spEliminar.setAdapter(adaptador);

    }

    public void eventoEliminarAlumno(View v)
    {
        //obtener elemento seleccionado
        String nombreAlumno = spEliminar.getSelectedItem().toString();
        for(int k=0;k<renglon;k++)
        {
            if(acumulaAlumno[k][1].equals(nombreAlumno))
            {
                idAlumno=Integer.parseInt(String.valueOf(acumulaAlumno[k][0]));
            }
        }
        boolean res=ct.eliminarEstudiante(idAlumno);
        String texto="";
        String mensaje="";
        int duracion=1;
        if(res)
        {
            mensaje="DAtos eliminados correctamente ";
            duracion= Toast.LENGTH_SHORT;
        }
        else
        {
            mensaje="Datos no eliminados";
            duracion=Toast.LENGTH_LONG;
        }
        Toast.makeText(this, mensaje, duracion).show();

    }

}
