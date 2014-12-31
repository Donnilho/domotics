package com.nielsroos42ES06A.domotics;
 
import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class FragmentOne extends Fragment {
      ImageView ivIcon;
      TextView tvItemName; 
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      
      public ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
 
      public FragmentOne() {
 
      }
      final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
					switch (msg.what) {
					case 3:

						break;
					case 100:
						
						break;
					case 99:
						
						break;
				
					}
				System.out.println("default: " + msg.obj);
				

				super.handleMessage(msg);
			}
			
		};
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
    	  System.out.println("%%%%%%%%%%%FRAGMENTONE%%%%%%%%%%%%%%%");
    	 // MainActivity activity = (MainActivity) getActivity();
          ArrayList<TextView> test = new ArrayList<TextView>();
      	  container = (ViewGroup) inflater.inflate(R.layout.fragment_layout_two, null);
          LinearLayout linearLayout = (LinearLayout) container.findViewById(R.id.fragmenttwo);
    	  
    	  
    	 // System.out.println("modules.size: "+ MainActivity.modules.size());
    	  for(int i = 0; i < MainActivity.modules.size(); i++){
	          	TextView viewText = new TextView(getActivity());
	          	viewText.setId(i);
	              viewText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
	                      LayoutParams.WRAP_CONTENT));
	              viewText.setText(	"ID: "+ MainActivity.modules.get(i).get(0) + 
	            		  			"   -   Module: " + MainActivity.modules.get(i).get(2) + 
	            		  			"   -   Enabled: " + MainActivity.modules.get(i).get(1));   
	          	test.add(viewText);
	          	for(int j = 0 ; j < MainActivity.sensorsInRoom.size(); j++){
	          		if(MainActivity.sensorsInRoom.get(j).get(1) == MainActivity.modules.get(i).get(0)){
		          		TextView sensorText = new TextView(getActivity());
			          	sensorText.setId(i);
			              sensorText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
			                      LayoutParams.WRAP_CONTENT));
			              sensorText.setText(	"   ModuleID: "+ MainActivity.sensorsInRoom.get(j).get(1) + 
			            		  			"   -   SensorID: " + MainActivity.sensorsInRoom.get(j).get(0) +
			            		  			"   -   Naam: " + MainActivity.sensorsInRoom.get(j).get(7) +
			            		  			"   -   Type: " + MainActivity.sensorsInRoom.get(j).get(2) + 
			            		  			"   -   Enabled: " + MainActivity.sensorsInRoom.get(j).get(5) +
			            		  			"   -   Waarde: " + MainActivity.sensorsInRoom.get(j).get(10) +
			            		  			" " + MainActivity.sensorsInRoom.get(j).get(6));   
			          	test.add(sensorText);
	          		}
	          	}
	          	for(int j = 0 ; j < MainActivity.actuatorsInRoom.size(); j++){
	          		if(MainActivity.actuatorsInRoom.get(j).get(1) == MainActivity.modules.get(i).get(0)){
		          		TextView actuatorText = new TextView(getActivity());
			          	actuatorText.setId(i);
			              actuatorText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
			                      LayoutParams.WRAP_CONTENT));
			              actuatorText.setText(	"   ModuleID: "+ MainActivity.actuatorsInRoom.get(j).get(1) + 
			            		  			"   -   ActuatorID: " + MainActivity.actuatorsInRoom.get(j).get(0) +
			            		  			"   -   Naam: " + MainActivity.actuatorsInRoom.get(j).get(7) +
			            		  			"   -   Type: " + MainActivity.actuatorsInRoom.get(j).get(2) + 
			            		  			"   -   Enabled: " + MainActivity.actuatorsInRoom.get(j).get(5) +
			            		  			"   -   Waarde: " + MainActivity.actuatorsInRoom.get(j).get(10) +
			            		  			" " + MainActivity.actuatorsInRoom.get(j).get(6));   
			          	test.add(actuatorText);
	          		}
	          	}
	          	TextView space = new TextView(getActivity());
	          	space.setId(i);
	              space.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
	                      LayoutParams.WRAP_CONTENT));
	              space.setText("  ");   
	          	test.add(space);  		
			}

          for(int i = 0; i< test.size() ; i++){
	          	try{
	          		System.out.println("testerdetest: " + test.get(i).toString());
	                  linearLayout.addView(test.get(i));
	           }catch(Exception e){
	                  e.printStackTrace();
	           }
          }

            return container;
      }

}