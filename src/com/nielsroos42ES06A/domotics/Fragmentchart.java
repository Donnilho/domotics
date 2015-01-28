package com.nielsroos42ES06A.domotics;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragmentchart extends Fragment {
	 ImageView ivIcon;
     TextView tvItemName;


     public static final String IMAGE_RESOURCE_ID = "iconResourceID";
     public static final String ITEM_NAME = "itemName";
	

        private static final int totalcorrect = 5;
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
        
        public Fragmentchart() {
            }
 
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            	MainActivity.load = false;
          	  	MainActivity.closeDialog();
		      //  if(MainActivity.singledevice.get(4).toString().equalsIgnoreCase("String")){
		        	container = (ViewGroup) inflater.inflate(R.layout.fragment_layout_chart, null);
		        	ArrayList<TextView> Stringlogs = new ArrayList<TextView>();
		        	TextView logHeader = new TextView(getActivity());
		        	String text = "<b>" + MainActivity.singledevice.get(7).toString() + "</b>";
		        	logHeader.setText(text);
		        	logHeader.setText(Html.fromHtml(text));
		            logHeader.setTextSize(35);
		        	Stringlogs.add(logHeader);
		        	for(int x = 0 ;x < MainActivity.logs.size(); x++){
		        		TextView logText = new TextView(getActivity());
		        		logText.setId(x);
		        		logText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
			                      LayoutParams.WRAP_CONTENT));
		        		Date date = new Date(Long.valueOf(MainActivity.logs.get(x).get(2).toString())); // *1000 is to convert seconds to milliseconds
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
	                    currentTimeStamp = sdf.format(date);
		        		CharSequence Tekst = "		" +
	                    currentTimeStamp + "   -    " +
		        		MainActivity.logs.get(x).get(3).toString();	
		        				;
		        		logText.setText(Tekst);
		        		Stringlogs.add(logText);
		        	}
		        	LinearLayout linearLayout = (LinearLayout) container.findViewById(R.id.fragmentchart);
		        	
		        	for(int x = 0; x < Stringlogs.size() ; x ++){
		        		linearLayout.addView(Stringlogs.get(x));
		        	}
		        	
		        	return container;
            	//PIE CHART
                /*View view = (LinearLayout) inflater.inflate(R.layout.fragment_layout_chart,
                        container, false);
        LinearLayout chartContainerlayout = (LinearLayout) view.findViewById(R.id.fragmentchart);
                if (container == null) {
                    return null;
                }
                 int []Performance = { totalcorrect,(9-totalcorrect)};  // [0] for correct ans, [1] for wrong ans
                 CategorySeries series = new CategorySeries("pie"); // adding series of charts from array .
                     series.add("Correct Answer",Performance[0]);          
                     series.add("Wrong Answer",Performance[1]);
                     int []colors = new int[]{Color.GREEN, Color.RED};            // set style for chart series
                              DefaultRenderer renderer = new DefaultRenderer();
                              for(int color : colors){
                                  SimpleSeriesRenderer r = new SimpleSeriesRenderer();
                                  r.setColor(color);
//                                r.setDisplayBoundingPoints(true);
//                                r.setDisplayChartValuesDistance(5);
                                  r.setDisplayChartValues(true);
                                  r.setChartValuesTextSize(15);
                                  renderer.addSeriesRenderer(r);
                              }
                              renderer.isInScroll();
                              //renderer.setZoomButtonsVisible(true);   //setting zoom button of Graph
                              renderer.setApplyBackgroundColor(true);
                              renderer.setBackgroundColor(Color.WHITE); //setting background color of chart
                              renderer.setChartTitle("Result Chart");   //setting title of chart
                              renderer.setZoomEnabled(false);
                              renderer.setChartTitleTextSize((float) 30);
                              renderer.setShowLabels(true);
                              renderer.setLabelsTextSize(20);
                              renderer.setLegendTextSize(25);
                             
                              graphView = ChartFactory.getPieChartView(getActivity(),
                                     series, renderer);
 
                              // Adding the pie chart to the custom layout
                              chartContainerlayout.addView(graphView);
                              return view;*/
    }
}
 