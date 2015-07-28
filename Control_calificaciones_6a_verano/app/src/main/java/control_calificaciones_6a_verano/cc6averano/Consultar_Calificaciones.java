package control_calificaciones_6a_verano.cc6averano;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.ref.SoftReference;


public class Consultar_Calificaciones extends Activity {

    Spinner spinner_culsultar_calif;

    CrearTablas ct;

    String AcomulaAlumno[][]=new String[10][7];
    String alumnos[];
    int renglon=0;
    int idAlumno=0;
    int aprobados=0, reprobados=0;


    String idCalif,claveAlumno,c1,c2,c3,c4,texto;
    TextView tvEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar__calificaciones);
llenarSpinerAlumnos1();
    }




    public void llenarSpinerAlumnos1()
    {
        spinner_culsultar_calif= (Spinner) findViewById(R.id.spinner_consultar_calif);
        ct=new CrearTablas(this);
        Cursor c= ct.ObtenerAlumnos();

        int i=0;
        int contador=0;
        while (c.moveToNext()) {
            int columnaId = c.getColumnIndex(TablaAlumno.ID_ALumno);
            String id = c.getString(columnaId);
            AcomulaAlumno[renglon][0] = String.valueOf(id);

            int columnaAlumno = c.getColumnIndex(TablaAlumno.ALUMNO);
            String alumno = c.getString(columnaAlumno);

            AcomulaAlumno[renglon][1] = String.valueOf(alumno);
            i++;
           renglon=renglon+1;

        }
    alumnos=new String[renglon];
        for(int j=0;j<renglon;j++)
        {
            alumnos[j]=String.valueOf(AcomulaAlumno[j][1]);
        }

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,alumnos);
        spinner_culsultar_calif.setAdapter(adaptador);

    }


    public void eventomostrarcali (View v)
    {
        ct= new CrearTablas(this);

        String nombreAlumno=spinner_culsultar_calif.getSelectedItem().toString();

        tvEliminar = (TextView) findViewById(R.id.tvEliminar);

        for(int k=0;k<renglon;k++)
        {
            if(AcomulaAlumno[k][1].equals(nombreAlumno))
            {
                idAlumno=Integer.parseInt(AcomulaAlumno[k][0]);
            }

        }
        int identificadorAlumno=idAlumno;
        Cursor c=ct.obtenercalificacionesEstudiant(identificadorAlumno);
        while (c.moveToNext())
        {
            int colid=c.getColumnIndex(TablaCalificaciones.ID_CALIF);
            idCalif=c.getString(colid);

            int ca=c.getColumnIndex(TablaCalificaciones.CLAVE_ALUMNO);
            claveAlumno=c.getString(ca);
            int cal1=c.getColumnIndex(TablaCalificaciones.CALIFICACION1);
            c1=c.getString(cal1);

            if(Integer.parseInt(c1)>=70)
                aprobados++;
            else
                reprobados++;

            int cal2=c.getColumnIndex(TablaCalificaciones.CALIFICACION2);
            c2=c.getString(cal2);
            if(Integer.parseInt(c2)>=70)
                aprobados++;
            else
                reprobados++;

            int cal3=c.getColumnIndex(TablaCalificaciones.CALIFICACION3);
            c3=c.getString(cal3);
            if(Integer.parseInt(c3)>=70)
                aprobados++;
            else
                reprobados++;

            int cal4=c.getColumnIndex(TablaCalificaciones.CALIFICACION4);
            c4=c.getString(cal4);
            if(Integer.parseInt(c4)>=70)
                aprobados++;
            else
                reprobados++;

    texto=texto+"\n"+idCalif +"       "+claveAlumno+"     "+c1+"      "+c2+"      "+c3+"      "+c4;


        }
        tvEliminar.setText(texto);
    }

    public void eventoMostrar (View v)
    {

    int x1=Integer.parseInt(c1);
        int x2=Integer.parseInt(c2);
        int x3=Integer.parseInt(c3);
        int x4=Integer.parseInt(c4);

BarGraph bar =new BarGraph(x1,x2,x3,x4);
        Intent intento=bar.getIntent(this);
        startActivity(intento);

    }

    public  void  eventomostrarporcentaje(View v)
    {

        PieGraph pie =new PieGraph(aprobados,reprobados);
        Intent intento =pie.getIntent(this);
        startActivity(intento);


    }


}
