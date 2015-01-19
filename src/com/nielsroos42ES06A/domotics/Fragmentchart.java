package com.nielsroos42ES06A.domotics;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragmentchart extends Fragment {
	 ImageView ivIcon;
     TextView tvItemName;


     public static final String IMAGE_RESOURCE_ID = "iconResourceID";
     public static final String ITEM_NAME = "itemName";
	
        private GraphicalView graphView;
        private static final int totalcorrect = 5;
        
        public Fragmentchart() {
            }
 
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
                View view = (LinearLayout) inflater.inflate(R.layout.fragment_layout_chart,
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
                              return view;
    }
}
 