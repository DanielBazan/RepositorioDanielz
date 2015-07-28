package control_calificaciones_6a_verano.cc6averano;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AgregarCalificacion extends Activity {

    Spinner SpinnerAlumno;
    CrearTablas ct;


    boolean califAgragadas;

    EditText cal1,cal2,cal3,cal4;
    String acumulaAlumno[][]=new String[15][2];

    String alumno[];
    int renglon=0;
    int idAlumno=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_calificacion);

       LlenarSpinnerAlumnos();

    }


    public void LlenarSpinnerAlumnos()
    {
        SpinnerAlumno= (Spinner) findViewById(R.id.spinnerSA);
        ct =new CrearTablas(this);

        Cursor c=ct.ObtenerAlumnos();
        int i=0;
        int contador=0;

        while (c.moveToNext())
        {
            int columnaId=c.getColumnIndex(TablaAlumno.ID_ALumno);
            String id=c.getString(columnaId);
            acumulaAlumno[renglon][0]=String.valueOf(id);

            int columnaAlumno=c.getColumnIndex(TablaAlumno.ALUMNO);
            String alumno=c.getString(columnaAlumno);
            acumulaAlumno[renglon][1]=String.valueOf(alumno);

        i=i+1;
            renglon=renglon+1;
        }

        alumno=new String[renglon];
        for (int j=0;j<renglon;j++)
        {
            alumno[j]=String.valueOf(acumulaAlumno[j][1]);
        }
        ArrayAdapter adaptador=
                new ArrayAdapter(this,android.R.layout.simple_spinner_item,alumno);
        SpinnerAlumno.setAdapter(adaptador);



    }

    public void eventoAgregarCalif(View v)
    {
        ct =new CrearTablas(this);
        cal1= (EditText) findViewById(R.id.etc1);
        cal2= (EditText) findViewById(R.id.etc2);
        cal3= (EditText) findViewById(R.id.etc3);
        cal4= (EditText) findViewById(R.id.etc4);

        String nombreAlumno =SpinnerAlumno.getSelectedItem().toString();

        for(int k=0;k<renglon;k++)
        {
            if(acumulaAlumno[k][1].equals(nombreAlumno))
            {
                idAlumno=Integer.parseInt(String.valueOf(acumulaAlumno[k][0]));

            }
        }
        int identificadorAlumno=idAlumno;
        int c1=Integer.parseInt(cal1.getText().toString());
        int c2=Integer.parseInt(cal2.getText().toString());
        int c3=Integer.parseInt(cal3.getText().toString());
        int c4=Integer.parseInt(cal4.getText().toString());

        califAgragadas=ct.agregarCalificacion(identificadorAlumno,c1,c2,c3,c4);

        if(califAgragadas)
            Toast.makeText(this,"Calificaciones agregadas correctamente",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Calificaciones no agregadas",Toast.LENGTH_LONG).show();


    }




}
