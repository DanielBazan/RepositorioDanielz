package control_calificaciones_6a_verano.cc6averano;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class PieGraph
{
    int a=0;
    int re=0;

    PieGraph(int noCeros, int ceros)
    {
        a=noCeros;
        re=ceros;
    }
    public Intent getIntent(Context context)
    {

        //int[] values = { 1, 2, 3, 4, 5 };
        //int[] values = { 50, 50 };
        int[] values = new int[2];
        values[0]=a;
        values[1]=re;

        CategorySeries series = new CategorySeries("Pie Graph");

        int k = 0;
        /*
        for (int value : values)
        {
            series.add("Aprobados  " + ++k, value);
            series.add("Reprobados  " + ++k, value);
        }
        */
        series.add("Aprbados  " , values[0]);
        series.add("Reprobados  " , values[1]);


        //int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };
        int[] colors = new int[] { Color.BLUE, Color.GREEN };

        DefaultRenderer renderer = new DefaultRenderer();

        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        renderer.setChartTitle("Porcentaje de Aprobados y Reprobados");
        renderer.setChartTitleTextSize(15);
        renderer.setZoomButtonsVisible(true);

        Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Porcentaje Aprobados");
        return intent;
    }
}