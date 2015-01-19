package com.nielsroos42ES06A.domotics;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragmentchart2 extends Fragment {
	 ImageView ivIcon;
     TextView tvItemName;


     public static final String IMAGE_RESOURCE_ID = "iconResourceID";
     public static final String ITEM_NAME = "itemName";
     	public long currenttime;
     	public String cmd;
     	public String currentTimeStamp;
        private GraphicalView graphView;
        private static final int SERIES_NR = 1;
        private String sensorNaam;
        private String Eenheid;
        private double ymindouble;
        private double ymaxdouble; 
        private int aantallogs; 
        private int xmin;
        private int xmax;
        private double xmindouble;
        private double xmaxdouble;
        private String type;
        
        
        
        public Fragmentchart2() {
            }
 
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                View view = (LinearLayout) inflater.inflate(R.layout.fragment_layout_chart,
                        container, false);
        LinearLayout chartContainerlayout = (LinearLayout) view.findViewById(R.id.fragmentchart);
        System.out.println("inside chart 2");
        sensorNaam = MainActivity.singledevice.get(7).toString();
        Eenheid = MainActivity.singledevice.get(6).toString();
        ymindouble = Double.parseDouble(MainActivity.singledevice.get(8).toString());
        ymaxdouble = Double.parseDouble(MainActivity.singledevice.get(9).toString());
        type = MainActivity.singledevice.get(2).toString();
        aantallogs = MainActivity.logs.size();
        xmin = 0;
        xmax = aantallogs;
        xmindouble = (double) xmin;
        xmaxdouble = (double) xmax;
        
        
        /*currenttime = System.currentTimeMillis() / 1000L;
        System.out.println("UnixTime: " + currenttime);
        Date date = new Date(currenttime*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT-1")); // give a timezone reference for formating (see comment at the bottom
        currentTimeStamp = sdf.format(date);
        System.out.println(currentTimeStamp);*/
        
    	/*List<Object> param = new ArrayList<Object>();
        cmd = MainActivity.c.ParsRequest("getLogs",param);
        System.out.println("cmd of getLogs  =  " + cmd);
        MainActivity.c.giveCommand(cmd);*/
        
        //getLogs(int moduleID, int deviceID, long fromUnixTimestamp,long untilUnixTimestamp)
        
                if (container == null) {
                    return null;
                }
                XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
                //final int nr = 7;
                //Random r = new Random();
                for (int i = 0; i < SERIES_NR; i++) {
                    XYSeries series = new XYSeries("Demo series " + (i + 1));
                    for (int k = 0; k < aantallogs; k++) {
                       // series.add(k, 20 + r.nextInt() % 100);
                    	double xray = (double) k;
                    	double value = Double.parseDouble(MainActivity.logs.get(k).get(3).toString());
                    	series.add(xray, value);
                    }
                    dataset.addSeries(series);
                }
                
                
                XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
                renderer.setAxisTitleTextSize(12);
                renderer.setChartTitleTextSize(12);
                renderer.setLabelsTextSize(15);
                renderer.setLegendTextSize(15);
                renderer.setPointSize(5f);
                //renderer.setZoomEnabled(false);
                renderer.setBackgroundColor(Color.WHITE);
                renderer.setMargins(new int[] { 20, 30, 100, 0 });
                renderer.setPanEnabled(true, false);
                XYSeriesRenderer rx = new XYSeriesRenderer();
                rx.setColor(Color.BLACK);
                
                rx.setPointStyle(PointStyle.DIAMOND);
                rx.setFillBelowLine(false);
                rx.setFillPoints(true);
                renderer.addSeriesRenderer(rx);
                setChartSettings(renderer);
                             
                              graphView = ChartFactory.getLineChartView(getActivity(),
                                     dataset, renderer);
 
                              // Adding the pie chart to the custom layout
                              chartContainerlayout.addView(graphView);
                              return view;
    }
            
            private void setChartSettings(XYMultipleSeriesRenderer renderer) {
                renderer.setChartTitle("Chart of: "+ sensorNaam);
                renderer.setXTitle("Time");
                renderer.setYTitle(type +" ("+ Eenheid + ")");
                renderer.setFitLegend(true);
                renderer.setShowCustomTextGrid(true);
                for(int i = 0 ; i < aantallogs; i++){
                	String x = "                           " + MainActivity.logs.get(i).get(2).toString();
                	renderer.addXTextLabel((i), x);	
                	
                }
                renderer.setXLabelsAngle(90);
                //renderer.setXLabelsPadding(80);
                //renderer.setInScroll(false);
                renderer.setXRoundedLabels(true);
                renderer.setApplyBackgroundColor(true);
                renderer.setRange(new double[] {xmindouble,xmaxdouble,ymindouble,ymaxdouble});
                renderer.setFitLegend(true);
                renderer.setAxesColor(Color.BLACK);
                renderer.setShowGrid(true);
                renderer.setPanEnabled(false, false);
                renderer.setZoomEnabled(false, false);
                renderer.setXAxisMin(xmindouble);
                renderer.setXAxisMax(xmaxdouble);
                renderer.setYAxisMin(ymindouble);
                //renderer.setZoomEnabled(false);
                renderer.setYAxisMax(ymaxdouble);
                renderer.setBackgroundColor(Color.WHITE);
               // renderer.setXLabels(3);
               
                //renderer.setGridColor(Color.WHITE);
              }
}


 