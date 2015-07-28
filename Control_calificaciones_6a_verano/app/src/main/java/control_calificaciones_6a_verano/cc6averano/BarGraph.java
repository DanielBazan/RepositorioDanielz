package control_calificaciones_6a_verano.cc6averano;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class BarGraph{

    int clf[];

    BarGraph(int cal1,int cal2,int cal3,int cal4)
    {
         //calificaciones= new int[calif.length];
        clf= new int[4];

        clf[0]=cal1;
        clf[1]=cal2;
        clf[2]=cal3;
        clf[3]=cal4;

        /*
        for(int i=0;i<calif.length;i++)
        {
          calificaciones= calif[i];
        }
        */
    }

    public Intent getIntent(Context context)
    {
        // Bar 1
        //int[] y = { 90, 100, 70, 56 };
        CategorySeries series = new CategorySeries(" Calificaciones obtenidas por Unidad ");


        for (int i = 0; i < clf.length; i++) {
            series.add("Bar " + (i+1), clf[i]);
        }


        // Bar 2
        int[] y2 = { 0, 0, 0, 0 };
        CategorySeries series2 = new CategorySeries(" ");
        for (int i = 0; i < clf.length; i++) {
            series2.add("Bar " + (i+1), y2[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series.toXYSeries());
        dataset.addSeries(series2.toXYSeries());

        // This is how the "Graph" itself will look like
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.setChartTitle("Grafica de Calificaciones Por Unidad");
        mRenderer.setXTitle("X VALUES");
        mRenderer.setYTitle("Y VALUES");
        mRenderer.setAxesColor(Color.GREEN);
        mRenderer.setLabelsColor(Color.RED);

        // Customize bar 1
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing((float) 0.5);
        renderer.setChartValuesSpacing((float) 0.0);
        mRenderer.addSeriesRenderer(renderer);


        // Customize bar 2
        XYSeriesRenderer renderer2 = new XYSeriesRenderer();
        renderer.setColor(Color.CYAN);
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing((float) 0.5);
        mRenderer.addSeriesRenderer(renderer2);


        Intent intent = ChartFactory.getBarChartIntent(context, dataset,mRenderer, Type.DEFAULT);
        return intent;
    }

}