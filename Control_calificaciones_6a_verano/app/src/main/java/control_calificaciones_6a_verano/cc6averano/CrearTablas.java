package control_calificaciones_6a_verano.cc6averano;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CrearTablas extends SQLiteOpenHelper
{

    private static final String NOMBRE_BD="bdcalificaciones";
    private static final int VERSION_BASE_DATOS=1;

    public CrearTablas(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }




    CrearTablas (Context context)
    {
        super(context, NOMBRE_BD, null, VERSION_BASE_DATOS);

    }

    

    @Override
    public void onCreate(SQLiteDatabase db)
    {
    db.execSQL("CREATE TABLE " + TablaAlumno.NNOMBRE_TABLA
            + " ( " + TablaAlumno.ID_ALumno + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TablaAlumno.ALUMNO + " TEXT NOT NULL "
            + " ); ");

            db.execSQL("CREATE TABLE " + TablaCalificaciones.NOMBRE_TABLA
                            + " ( " + TablaCalificaciones.ID_CALIF + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + TablaCalificaciones.CLAVE_ALUMNO + " INTEGER NOT NULL, "
                            + TablaCalificaciones.CALIFICACION1 + " INTEGER NOT NULL, "
                            + TablaCalificaciones.CALIFICACION2 + " INTEGER NOT NULL, "
                            + TablaCalificaciones.CALIFICACION3 + " INTEGER NOT NULL, "
                            + TablaCalificaciones.CALIFICACION4 + " INTEGER NOT NULL, "
                            + " FOREIGN KEY ( " + TablaCalificaciones.CLAVE_ALUMNO + " ) "
                            + " REFERENCES " + TablaAlumno.NNOMBRE_TABLA
                            + " ( " + TablaAlumno.ID_ALumno + " ) ); "

            );

        db.execSQL("CREATE TRIGGER cascada_eliminar BEFORE DELETE ON Alumnos "
        + " FOR EACH ROW "
        +"              BEGIN DELETE FROM Calificaciones WHERE clave_alumno = OLD.id ; "
        +" END "
        );

        db.execSQL(" pragma foreign_keys=ON ");

    }

    public long agregarAlumnos (String nombre)
    {
        //crear contenedor del valor
        ContentValues cv = new ContentValues();

        cv.put(TablaAlumno.ALUMNO, nombre);
        SQLiteDatabase sd=getWritableDatabase();
        long result = sd.insert(TablaAlumno.NNOMBRE_TABLA, null, cv);

        return result;

    }

    public Cursor ObtenerAlumnos()
    {
        SQLiteDatabase sd =getWritableDatabase();
        String [] cols =new String[]{TablaAlumno.ID_ALumno,TablaAlumno.ALUMNO};

        Cursor c=sd.query(TablaAlumno.NNOMBRE_TABLA, cols, null, null, null, null, null);
        return c;



    }
public boolean agregarCalificacion(int idAlumno, int c1,int c2,int c3,int c4)
{
    ContentValues cv=new ContentValues();
    cv.put(TablaCalificaciones.CLAVE_ALUMNO,idAlumno);
    cv.put(TablaCalificaciones.CALIFICACION1,c1);
    cv.put(TablaCalificaciones.CALIFICACION2,c2);
    cv.put(TablaCalificaciones.CALIFICACION3,c3);
    cv.put(TablaCalificaciones.CALIFICACION4,c4);

    SQLiteDatabase sd=getWritableDatabase();
    long result=sd.insert(TablaCalificaciones.NOMBRE_TABLA, null, cv);

    return (result>=0);

}public boolean eliminarEstudiante(int idAlumno)
{
    SQLiteDatabase ct=getWritableDatabase();
    String[] whereArgs =new String[] {String.valueOf(idAlumno)};

    int result =ct.delete(TablaAlumno.NNOMBRE_TABLA, TablaAlumno.ID_ALumno + " =? ", whereArgs);
    return (result >0);
}



    public long actualizarCalificaciones(int idAlumno,int c1,int c2,int c3,int c4)
    {
        ContentValues cv=new ContentValues();
        cv.put(TablaCalificaciones.CALIFICACION1,c1);
        cv.put(TablaCalificaciones.CALIFICACION2,c2);
        cv.put(TablaCalificaciones.CALIFICACION3,c3);
        cv.put(TablaCalificaciones.CALIFICACION4,c4);


        SQLiteDatabase sd= getWritableDatabase();
        String[] args = new String[] {"idAlumno"};

        long result =sd.update("Calificaciones",cv,TablaCalificaciones.CLAVE_ALUMNO + " = "+ idAlumno,null);
        return result;


    }

    public Cursor obtenercalificacionesEstudiant(int idAlumno)
    {
        SQLiteDatabase sd = getWritableDatabase();
        String[] cols =new String[]
                {
                        TablaCalificaciones.ID_CALIF,
                        TablaCalificaciones.CLAVE_ALUMNO,
                        TablaCalificaciones.CALIFICACION1,
                        TablaCalificaciones.CALIFICACION2,
                        TablaCalificaciones.CALIFICACION3,
                        TablaCalificaciones.CALIFICACION4
                };
    String[] selectionArgs = new String[]
       {
          String.valueOf(idAlumno)
       };

        Cursor c=sd.query(TablaCalificaciones.NOMBRE_TABLA, cols, TablaCalificaciones.CLAVE_ALUMNO + " =? ",
                selectionArgs, null, null, null);

    return c;
    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);

    }


}
