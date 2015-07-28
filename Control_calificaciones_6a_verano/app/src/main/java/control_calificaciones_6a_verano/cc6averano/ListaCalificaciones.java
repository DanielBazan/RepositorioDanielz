package control_calificaciones_6a_verano.cc6averano;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ListaCalificaciones extends Activity {



    TableLayout Cabecera;
    TableLayout tabla;
    TableRow fila;
    TableRow filacabecera;



    ///Objetos para crear la cabecera
    TableRow.LayoutParams layoutCabeceraId;
    TableRow.LayoutParams layoutCabeceraNombre;
    TableRow.LayoutParams layoutCabeceraC1;
    TableRow.LayoutParams layoutCabeceraC2;
    TableRow.LayoutParams layoutCabeceraC3;
    TableRow.LayoutParams layoutCabeceraC4;
    TableRow.LayoutParams layoutCabeceraP;

    //Objetos para crear la tabla

    TableRow.LayoutParams layoutFila;
    TableRow.LayoutParams layoutId;
    TableRow.LayoutParams layoutNombre;
    TableRow.LayoutParams layoutC1;
    TableRow.LayoutParams layoutC2;
    TableRow.LayoutParams layoutC3;
    TableRow.LayoutParams layoutC4;
    TableRow.LayoutParams layoutP;

    Resources rs;

    CrearTablas ct=new CrearTablas(this);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_calificaciones);

ListaCalificaciones();
        agregarcabecera();

    }

    public void ListaCalificaciones()
    {
        rs=this.getResources();
        tabla= (TableLayout) findViewById(R.id.tabla);
        Cabecera= (TableLayout) findViewById(R.id.cabecera);

        layoutCabeceraNombre=new TableRow.LayoutParams(80,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraC1=new TableRow.LayoutParams(60,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraC2=new TableRow.LayoutParams(60,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraC3=new TableRow.LayoutParams(60,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraC4=new TableRow.LayoutParams(60,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraP=new TableRow.LayoutParams(60,TableRow.LayoutParams.WRAP_CONTENT);


        layoutFila=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
        layoutNombre=new TableRow.LayoutParams(300,TableRow.LayoutParams.WRAP_CONTENT);

        layoutC1=new TableRow.LayoutParams(80,TableRow.LayoutParams.WRAP_CONTENT);
        layoutC2=new TableRow.LayoutParams(80,TableRow.LayoutParams.WRAP_CONTENT);
        layoutC3=new TableRow.LayoutParams(80,TableRow.LayoutParams.WRAP_CONTENT);
        layoutC4=new TableRow.LayoutParams(80,TableRow.LayoutParams.WRAP_CONTENT);
        layoutP=new TableRow.LayoutParams(80,TableRow.LayoutParams.WRAP_CONTENT);

        TextView tvNombre;
        TextView tvC1;
        TextView tvC2;
        TextView tvC3;
        TextView tvC4;
        TextView tvP;

        CrearTablas sqh =new CrearTablas(this);
        SQLiteDatabase sqdb =sqh.getWritableDatabase();

        Cursor c= sqdb.rawQuery(" select alumnno, calificacion1, calificacion2, calificacion3, calificacion4 from"+
                                " Alumnos, Calificaciones where Alumnos.id== Calificaciones.id ",null);

        while (c.moveToNext())
        {
            int colid = c.getColumnIndex(TablaAlumno.ALUMNO);
            int colid2=c.getColumnIndex(TablaCalificaciones.CALIFICACION1);
            int colid3=c.getColumnIndex(TablaCalificaciones.CALIFICACION2);
            int colid4=c.getColumnIndex(TablaCalificaciones.CALIFICACION3);
            int colid5=c.getColumnIndex(TablaCalificaciones.CALIFICACION4);
            int total= ( colid2+colid3+colid4+colid5)/4;

        String alumno = c.getString(colid);
            String c2 = c.getString(colid2);
            String c3 = c.getString(colid3);
            String c4 = c.getString(colid4);
            String c5 = c.getString(colid5);
            String cP = c.getString(total);



            fila=new TableRow(this);
            fila.setLayoutParams(layoutFila);

            tvNombre=new TextView(this);
            tvC1 = new TextView(this);
            tvC2 = new TextView(this);
            tvC3 = new TextView(this);
            tvC4 = new TextView(this);
            tvP = new TextView(this);




            tvNombre.setText(String.valueOf(alumno));
            tvNombre.setGravity(Gravity.CENTER_HORIZONTAL);
            tvNombre.setTextAppearance(this, R.style.etiqueta);
            tvNombre.setBackgroundResource(R.drawable.tabla_celda);
            tvNombre.setLayoutParams(layoutNombre);

            tvC1.setText(c2);
            tvC1.setGravity(Gravity.CENTER_HORIZONTAL);
            //tvC1.setGravity(this, R.style.etiqueta);
            tvC1.setTextAppearance(this, R.style.etiqueta);
            tvC1.setBackgroundResource(R.drawable.tabla_celda);
            tvC1.setLayoutParams(layoutC1);

            tvC2.setText(c3);
            tvC2.setGravity(Gravity.CENTER_HORIZONTAL);
            //tvC1.setGravity(this, R.style.etiqueta);
            tvC2.setTextAppearance(this, R.style.etiqueta);
            tvC2.setBackgroundResource(R.drawable.tabla_celda);
            tvC2.setLayoutParams(layoutC1);

            tvC3.setText(c4);
            tvC3.setGravity(Gravity.CENTER_HORIZONTAL);
            //tvC1.setGravity(this, R.style.etiqueta);
            tvC3.setTextAppearance(this, R.style.etiqueta);
            tvC3.setBackgroundResource(R.drawable.tabla_celda);
            tvC3.setLayoutParams(layoutC1);

            tvC4.setText(c5);
            tvC4.setGravity(Gravity.CENTER_HORIZONTAL);
            //tvC1.setGravity(this, R.style.etiqueta);
            tvC4.setTextAppearance(this, R.style.etiqueta);
            tvC4.setBackgroundResource(R.drawable.tabla_celda);
            tvC4.setLayoutParams(layoutC1);


            tvP.setText(cP);
            tvC4.setGravity(Gravity.CENTER_HORIZONTAL);
            //tvC1.setGravity(this, R.style.etiqueta);
            tvC4.setTextAppearance(this, R.style.etiqueta);
            tvC4.setBackgroundResource(R.drawable.tabla_celda);
            tvC4.setLayoutParams(layoutP);


            fila.addView(tvNombre);
            fila.addView(tvC1);
            fila.addView(tvC2);
            fila.addView(tvC3);
            fila.addView(tvC4);
            fila.addView(tvP);


            tabla.removeView(fila);
            tabla.addView(fila);


        }
        }



        public void agregarcabecera()
    {
        Cabecera = (TableLayout) findViewById(R.id.cabecera);

        layoutCabeceraNombre =new TableRow.LayoutParams(300,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraC1=new TableRow.LayoutParams(10,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraC2=new TableRow.LayoutParams(10,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraC3=new TableRow.LayoutParams(10,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraC4=new TableRow.LayoutParams(10,TableRow.LayoutParams.WRAP_CONTENT);
        layoutCabeceraP=new TableRow.LayoutParams(10,TableRow.LayoutParams.WRAP_CONTENT);

        layoutFila=new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        fila =new TableRow(this);
        fila.setLayoutParams(layoutFila);


        TextView txtNombre= new TextView(this);
        TextView txtC1=new TextView(this);
        TextView txtC2=new TextView(this);
        TextView txtC3=new TextView(this);
        TextView txtC4=new TextView(this);
        TextView txtP=new TextView(this);

        txtNombre.setText("Nombre");
        txtNombre.setGravity(Gravity.CENTER_HORIZONTAL);
        txtNombre.setTextAppearance(this, R.style.etiqueta);
        txtNombre.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtNombre.setLayoutParams(layoutCabeceraNombre);

        txtC1.setText("C1");
        txtC1.setGravity(Gravity.CENTER_VERTICAL);
        txtC1.setTextAppearance(this, R.style.etiqueta);
        txtC1.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtC1.setLayoutParams(layoutC1);

        txtC2.setText("C2");
        txtC2.setGravity(Gravity.CENTER);
        txtC2.setTextAppearance(this, R.style.etiqueta);
        txtC2.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtC2.setLayoutParams(layoutC2);

        txtC3.setText("C3");
        txtC3.setGravity(Gravity.CENTER);
        txtC3.setTextAppearance(this, R.style.etiqueta);
        txtC3.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtC3.setLayoutParams(layoutC3);

        txtC4.setText("C4");
        txtC4.setGravity(Gravity.CENTER);
        txtC4.setTextAppearance(this, R.style.etiqueta);
        txtC4.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtC4.setLayoutParams(layoutC4);

        txtP.setText("Prome");
        txtC4.setGravity(Gravity.CENTER);
        txtC4.setTextAppearance(this, R.style.etiqueta);
        txtC4.setBackgroundResource(R.drawable.tabla_celda_cabecera);
        txtC4.setLayoutParams(layoutP);

        fila.addView(txtNombre);
        fila.addView(txtC1);
        fila.addView(txtC2);
        fila.addView(txtC3);
        fila.addView(txtC4);
        fila.addView(txtP);

        Cabecera.addView(fila);


    }



}
