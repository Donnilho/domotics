package com.nielsroos42ES06A.domotics;
 
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
 
public class FragmentOne extends Fragment {
      ImageView ivIcon;
      TextView tvItemName; 
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      
      public ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
      public ArrayList<ArrayList> selectedRooms = new ArrayList<ArrayList>();
      public ArrayList<Object> weergave = new ArrayList<Object>();
      public ArrayList<Integer> saveID = new ArrayList<Integer>();
      //public ArrayList<ArrayList> sensact = new ArrayList<ArrayList>();
      public CharSequence selected;
      public int moduleID;
      public int deviceID;
      public int select;
      public int testmodule;
      public int testdevice;
      public CharSequence selectedmodule;
      public CharSequence selectedroom;
      public int selectmodule;
      public int selectroom;
      public int jee;
      public int toggleID;
      public String cmd;
     
      public FragmentOne() {
 
      }
      
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
    	  //System.out.println("%%%%%%%%%%%FRAGMENTONE%%%%%%%%%%%%%%%");
    	 // MainActivity activity = (MainActivity) getActivity();
          ArrayList<TextView> test = new ArrayList<TextView>();
      	  container = (ViewGroup) inflater.inflate(R.layout.fragment_layout_two, null);
          LinearLayout linearLayout = (LinearLayout) container.findViewById(R.id.fragmenttwo);//was fragmenttwo
          ArrayList<Button> tester = new ArrayList<Button>();
          ArrayList<ToggleButton> toggle = new ArrayList<ToggleButton>();
          ArrayList<SeekBar> seek = new ArrayList<SeekBar>();
          ArrayList<ProgressBar> prog = new ArrayList<ProgressBar>();
          ArrayList<Button> press = new ArrayList<Button>();

          weergave.clear();
          int buttonID = 0;
          toggleID = 0;
          saveID.clear();
    	  
    	  System.out.println("modules.size: "+ MainActivity.modules.size());
    	  for(int i = 0; i < MainActivity.modules.size(); i++){
    		  //System.out.println(i);
    		  if((MainActivity.modules.get(i).get(1).toString()).equalsIgnoreCase("true")){
	          	TextView viewText = new TextView(getActivity());
	          	viewText.setId(i);
	              viewText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
	                      LayoutParams.WRAP_CONTENT));
	              
	              String tekst = "<b>"// + "ID: "+ MainActivity.modules.get(i).get(0) 
      		  			+"       Module: " + MainActivity.modules.get(i).get(2) + "</b>" ;
      		  			//+ "   -   Enabled: " + MainActivity.modules.get(i).get(1) ;
	              //System.out.println("Module: " + tekst);
	              viewText.setText(Html.fromHtml(tekst));
	              viewText.setTextSize(35);
	          	test.add(viewText);
	          	weergave.add("Header");
	          	for(int j = 0 ; j < MainActivity.sensorsInRoom.size(); j++){
	          		jee = j;
	          		if((MainActivity.sensorsInRoom.get(j).get(1).toString().equalsIgnoreCase(MainActivity.modules.get(i).get(0).toString())) && (MainActivity.sensorsInRoom.get(j).get(5).toString()).equalsIgnoreCase("true")){
	          			TextView sensorText = new TextView(getActivity());
			          	sensorText.setId(i);
			          	sensorText.setTextSize(20);
			              sensorText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
			                      LayoutParams.WRAP_CONTENT));
			              
			              
			              String tekst1 = //"   ModuleID: "+ MainActivity.sensorsInRoom.get(j).get(1) + 
	            		  			//"   -   SensorID: " + MainActivity.sensorsInRoom.get(j).get(0) +
	            		  			"       Sensor: " + MainActivity.sensorsInRoom.get(j).get(7) + //naam
	            		  			//"   -   Type: " + MainActivity.sensorsInRoom.get(j).get(2) + 
	            		  			//"   -   Weergave: "+ MainActivity.sensorsInRoom.get(j).get(3) +
	            		  			//"   -   Enabled: " + MainActivity.sensorsInRoom.get(j).get(5) +
	            		  			"        Waarde: " + MainActivity.sensorsInRoom.get(j).get(10)
	            		  			//+ " " + MainActivity.sensorsInRoom.get(j).get(6)) //Eenheid
	            		  			;
			             // System.out.println(tekst1);
			              sensorText.setText(tekst1);
			              
			              if((MainActivity.sensorsInRoom.get(j).get(3).toString()).equalsIgnoreCase("Bar")){
			            	  ProgressBar bar = new ProgressBar(getActivity(),null, 
			                          android.R.attr.progressBarStyleHorizontal);
			            	  int waarde;
			            	  if(!(MainActivity.sensorsInRoom.get(j).get(10).toString().equalsIgnoreCase("-"))){
			            		  waarde = Integer.parseInt(MainActivity.sensorsInRoom.get(j).get(10).toString());
			            	  }
			            	  else{
			            		  waarde = 0;
			            	  }
			            	  
			            	  int min = Integer.parseInt(MainActivity.sensorsInRoom.get(j).get(8).toString());
			            	  int max = Integer.parseInt(MainActivity.sensorsInRoom.get(j).get(9).toString());
			            	  int bereik = max - min; 
			            	  bar.setProgress(waarde);
			                  bar.setPadding(40, 0, 0, 0);
			                  bar.setMax(bereik);
			                  bar.setId(j);
			                  bar.setLayoutParams(new LayoutParams(370, LayoutParams.WRAP_CONTENT));
			                  
			                  prog.add(bar);
			            	  weergave.add("Bar");
			              }
			              else{
			            	  weergave.add("Sensor"); 
			              }
			          	test.add(sensorText);
			          	
	          		}
	          		/*TextView space = new TextView(getActivity());
		          	space.setId(i);
		              space.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		                      LayoutParams.WRAP_CONTENT));
		              
		              space.setText("  ");   
		          	test.add(space); 	    
		          	weergave.add("Space");*/
	          	}
	          	for(int j = 0 ; j < MainActivity.actuatorsInRoom.size(); j++){
	          		jee = j;
	          		if((MainActivity.actuatorsInRoom.get(j).get(1).toString().equalsIgnoreCase(MainActivity.modules.get(i).get(0).toString())) && (MainActivity.actuatorsInRoom.get(j).get(5).toString()).equalsIgnoreCase("true")){
	          			TextView actuatorText = new TextView(getActivity());
			          	actuatorText.setId(i);
			          	actuatorText.setTextSize(20);
			              actuatorText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
			                      LayoutParams.WRAP_CONTENT));
			              
			              String tekst2 = //	"   ModuleID: "+ MainActivity.actuatorsInRoom.get(j).get(1) + 
	            		  			//"   -   ActuatorID: " + MainActivity.actuatorsInRoom.get(j).get(0) +
	            		  			"       Actuator: " + MainActivity.actuatorsInRoom.get(j).get(7) + //naam
	            		  			//"   -   Type: " + MainActivity.actuatorsInRoom.get(j).get(2) + 
	            		  			//"   -   Weergave: "+ MainActivity.actuatorsInRoom.get(j).get(3) +
	            		  			//"   -   Enabled: " + MainActivity.actuatorsInRoom.get(j).get(5) +
	            		  			//"   -   Min: " + MainActivity.actuatorsInRoom.get(j).get(8) +
	            		  			//"   -   Max: " + MainActivity.actuatorsInRoom.get(j).get(9) +
	            		  			"        Waarde: " + MainActivity.actuatorsInRoom.get(j).get(10) +
	            		  			" " + MainActivity.actuatorsInRoom.get(j).get(6)
			            		  
			            		  		;
			             // System.out.println(tekst2);
			              actuatorText.setText(tekst2);
			              if((MainActivity.actuatorsInRoom.get(j).get(3).toString()).equalsIgnoreCase("Switch")){
			            	  final ToggleButton tb = new ToggleButton(getActivity());
			            	  tb.setTextOn("ON");
			                  tb.setTextOff("OFF");
			                  tb.setId(jee);
			                  //tb.setPadding(20, 0, 0, 0);
			                  //System.out.println("%%%%%Value: " + MainActivity.actuatorsInRoom.get(j).get(10).toString());
			                  if(!(MainActivity.actuatorsInRoom.get(j).get(10).toString().equalsIgnoreCase("-")) && (MainActivity.actuatorsInRoom.get(j).get(10).toString().equalsIgnoreCase("true"))){
			            		  tb.setChecked(true);
			            	  }
			            	  else{
			            		  tb.setChecked(false);
			            	  }
			                  
			                  tb.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			                  //tb.setLeft(20);
			                  tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			              	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

			              	        if(isChecked)
			              	        {
			              	        	CharSequence tekst = MainActivity.actuatorsInRoom.get(tb.getId()).get(1) + " " +
			              	        			MainActivity.actuatorsInRoom.get(tb.getId()).get(0) + " " +
			              	        			MainActivity.actuatorsInRoom.get(tb.getId()).get(7) + " On";
			  							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
			  							List<Object> params = new ArrayList<Object>();
			  							moduleID = Integer.parseInt(MainActivity.actuatorsInRoom.get(tb.getId()).get(1).toString());
			  							deviceID = Integer.parseInt(MainActivity.actuatorsInRoom.get(tb.getId()).get(0).toString());
			  			            	params.add(moduleID);
			  			            	params.add(deviceID);
			  			            	params.add("true");
			  			                cmd = MainActivity.c.ParsRequest("setActuatorValue",params);
			  			                System.out.println("cmd of setActuatorValue  =  " + cmd);
			  			                MainActivity.c.giveCommand(cmd);
			  							//setActuatorValue(int moduleID, int deviceID, String value)
			  			             
			              	        }
			              	        else
			              	        {
			              	        	CharSequence tekst = MainActivity.actuatorsInRoom.get(tb.getId()).get(1) + " " +
			              	        			MainActivity.actuatorsInRoom.get(tb.getId()).get(0) + " " +
			              	        			MainActivity.actuatorsInRoom.get(tb.getId()).get(7) + "Off";
			  							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
			  							List<Object> params = new ArrayList<Object>();
			  							moduleID = Integer.parseInt(MainActivity.actuatorsInRoom.get(tb.getId()).get(1).toString());
			  							deviceID = Integer.parseInt(MainActivity.actuatorsInRoom.get(tb.getId()).get(0).toString());
			  			            	params.add(moduleID);
			  			            	params.add(deviceID);
			  			            	params.add("false");
			  			                cmd = MainActivity.c.ParsRequest("setActuatorValue",params);
			  			                System.out.println("cmd of setActuatorValue  =  " + cmd);
			  			                MainActivity.c.giveCommand(cmd);
			  			               
			  							//setActuatorValue(int moduleID, int deviceID, String value)
			              	        }
			              	    }
			              	});
			                toggle.add(tb);
			                weergave.add("Switch");
			              }
			              else if((MainActivity.actuatorsInRoom.get(j).get(3).toString()).equalsIgnoreCase("Toets")){
			            	  final Button druk = new Button(getActivity());
			            	  druk.setText("Press");
			            	  druk.setId(jee);
			            	  //druk.setPadding(20, 0, 0, 0);
			            	  druk.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			            	  druk.setOnClickListener(new View.OnClickListener() {
			                        public void onClick(View view) {
			                        	CharSequence tekst = MainActivity.actuatorsInRoom.get(druk.getId()).get(1) + " " +
			              	        			MainActivity.actuatorsInRoom.get(druk.getId()).get(0) + " " +
			              	        			MainActivity.actuatorsInRoom.get(druk.getId()).get(7) + "Pressed";
			  							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
			  							List<Object> params = new ArrayList<Object>();
			  							moduleID = Integer.parseInt(MainActivity.actuatorsInRoom.get(druk.getId()).get(1).toString());
			  							deviceID = Integer.parseInt(MainActivity.actuatorsInRoom.get(druk.getId()).get(0).toString());
			  			            	params.add(moduleID);
			  			            	params.add(deviceID);
			  			            	params.add("true");
			  			                cmd = MainActivity.c.ParsRequest("setActuatorValue",params);
			  			                System.out.println("cmd of setActuatorValue  =  " + cmd);
			  			                MainActivity.c.giveCommand(cmd);	
			                        }
			            	  });
			            	  press.add(druk);
			            	  weergave.add("Toets");
			              }
			              
			              else if((MainActivity.actuatorsInRoom.get(j).get(3).toString()).equalsIgnoreCase("Slider")){
			            	  final SeekBar seeker = new SeekBar(getActivity());
			            	  int min = Integer.parseInt(MainActivity.actuatorsInRoom.get(j).get(8).toString());
			            	  int max = Integer.parseInt(MainActivity.actuatorsInRoom.get(j).get(9).toString());
			            	  int bereik = max - min; 
			            	  
			            	  
			            	  seeker.setPadding(20,0, 0, 0);
			            	  seeker.setMax(bereik);
			                  seeker.setId(jee);
			                  if(!(MainActivity.actuatorsInRoom.get(jee).get(10).toString()).equalsIgnoreCase("-")){
			                	seeker.setProgress(Integer.parseInt(MainActivity.actuatorsInRoom.get(jee).get(10).toString())); 
			                  }
			                  
			                  
			               
			                  LayoutParams lp = new LayoutParams(400, LayoutParams.WRAP_CONTENT);
			                  seeker.setLayoutParams(lp);
			                  
			                  //final int id_seeker = seeker.getId();
			                  seeker.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			          			int progressChanged = 0;

			         			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
			         				int min = Integer.parseInt(MainActivity.actuatorsInRoom.get(seeker.getId()).get(8).toString());
			         				int max = Integer.parseInt(MainActivity.actuatorsInRoom.get(seeker.getId()).get(9).toString());
			         				progressChanged = progress - min;
			         			}

			         			public void onStartTrackingTouch(SeekBar seekBar) {
			         				// TODO Auto-generated method stub
			         			}

			         			public void onStopTrackingTouch(SeekBar seekBar) {
			         				CharSequence tekst = MainActivity.actuatorsInRoom.get(seeker.getId()).get(1) + " " +
		              	        			MainActivity.actuatorsInRoom.get(seeker.getId()).get(0) + " ";
			         				Toast.makeText(getActivity().getApplicationContext(),tekst +"seek bar progress:"+progressChanged, 
			         						Toast.LENGTH_SHORT).show();
			         				
			         				List<Object> params = new ArrayList<Object>();
		  							moduleID = Integer.parseInt(MainActivity.actuatorsInRoom.get(seeker.getId()).get(1).toString());
		  							deviceID = Integer.parseInt(MainActivity.actuatorsInRoom.get(seeker.getId()).get(0).toString());
		  			            	String value = Integer.toString(progressChanged);
		  							params.add(moduleID);
		  			            	params.add(deviceID);
		  			            	params.add(value);
		  			                cmd = MainActivity.c.ParsRequest("setActuatorValue",params);
		  			                System.out.println("cmd of setActuatorValue  =  " + cmd);
		  			                MainActivity.c.giveCommand(cmd);

			         			}
			         		});
			                seek.add(seeker);
			                weergave.add("Slider");
			              }
			              else{
			            	  weergave.add("Actuator");
			              }
			          	test.add(actuatorText);
	          		}
	          		/*TextView space = new TextView(getActivity());
		          	space.setId(i);
		              space.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
		                      LayoutParams.WRAP_CONTENT));
		              space.setText("  ");   
		          	test.add(space); 	    
		          	weergave.add("Space");*/
	          	}
	          	/*TextView space = new TextView(getActivity());
	          	space.setId(i);
	              space.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
	                      LayoutParams.WRAP_CONTENT));
	              space.setText("  ");   
	          	test.add(space); 
	          	test.add(space);
	          	weergave.add("Space");
	          	weergave.add("Space");*/
			}
    	  }
    	  
    	  for(int j = 0; j < 4; j++){
          	Button button = new Button(getActivity());
          	button.setMaxWidth(5);
              
              if(j == 1){
            	  button.setText("Enable Disabled Device");
                	button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                        	
                        	ArrayList<String> bravo = new ArrayList<String>();
                        	final ArrayList<ArrayList> disdevice = new ArrayList<ArrayList>();
                        	for(int x = 0; x < MainActivity.sensact.size(); x++){
                        		if((MainActivity.sensact.get(x).get(5).toString()).equalsIgnoreCase("false")){
                        			String xray = "Module: " + MainActivity.sensact.get(x).get(1).toString() +
                        					" - Device: " +
                        					 MainActivity.sensact.get(x).get(0).toString() +
                        					" - Naam: "+ MainActivity.sensact.get(x).get(7).toString()
                        					;
                        			//System.out.println(xray);
                        			bravo.add(xray);
                        			disdevice.add(MainActivity.sensact.get(x));
                        			
                        		}
                        	}
                        	final CharSequence[] items = bravo.toArray(new CharSequence[bravo.size()]);
                        	
                        	

                        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        	builder.setTitle("Select a Device to Enable");
                        	
                        	builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {
    							@Override
    							public void onClick(DialogInterface dialog,
    									int which) {
    								
    								selected = items[which];
    								select = which;
    								 
    							}
                        	})
                                   //.setCancelable(False)
    				       .setPositiveButton("Enable", new android.content.DialogInterface.OnClickListener() {
    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							//CharSequence tekst = "Enabling Device: " + selected + " ...";
    							int module;
    							int device;
    							
    							
    							module = Integer.parseInt(disdevice.get(select).get(1).toString()); //moduleID
    							System.out.println("Module: " + MainActivity.sensact.get(select).get(1).toString());
    							device = Integer.parseInt(disdevice.get(select).get(0).toString()); //deviceID
    							System.out.println("Device: " + MainActivity.sensact.get(select).get(0).toString());
    							CharSequence tekst = "Enabling Device: " + selected + " - Module: " + module +" Device: "+ device + " ...";
    							List<Object> param = new ArrayList<Object>();
  								param.add(module);
  								param.add(device);
  		         	            String cmd = MainActivity.c.ParsRequest("enableDevice",param);
  		         	            System.out.println("cmd of enableDevice  =  " + cmd);
  		         	            MainActivity.c.giveCommand(cmd);
    							
    							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
    							
    							// enableDevice(int moduleID, int deviceID)
    							
    							dialog.dismiss();
    						}
    				       })
    				       .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							dialog.cancel();
    						}
    				       });
                        	AlertDialog alert = builder.create();
                        	//And if the line above didn't bring ur dialog up use this bellow also:
                        	alert.show();
                        }
                    });
              }
              else if(j==2 ){
	                button.setText("Rename Device");
	            	button.setOnClickListener(new View.OnClickListener() {
	                    public void onClick(View view) {
	                    	
	                    	
	                    	//final CharSequence[] items = {"Red", "Green", "Blue"};
	                    	//final CharSequence[] items = MainActivity.rooms.toArray(new CharSequence[MainActivity.rooms.size()]);
	                    	ArrayList<String> bravo = new ArrayList<String>();
	                    	final ArrayList<ArrayList> endevice = new ArrayList<ArrayList>();
                        	for(int x = 0; x < MainActivity.sensact.size(); x++){
                        		if((MainActivity.sensact.get(x).get(5).toString()).equalsIgnoreCase("true")){
                        			String xray = "Module: " + MainActivity.sensact.get(x).get(1).toString() +
                        					" - Device: " +
                        					 MainActivity.sensact.get(x).get(0).toString() +
                        					" - Naam: "+ MainActivity.sensact.get(x).get(7).toString()
                        					;
                        			bravo.add(xray);
                        			endevice.add(MainActivity.sensact.get(x));
                        		}
                        	}
                        	final CharSequence[] items = bravo.toArray(new CharSequence[bravo.size()]);
	                    	
	                    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	                    	builder.setTitle("Select the Device you want to rename.");
	                    	//builder.setCancelable(False);
	                    	builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									
									// Toast.makeText(getActivity().getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
									 //dialog.dismiss();
									selected = items[which];
									select = which;
									 
								}
	                    	});
	                               //.setCancelable(False);
	                       final EditText input = new EditText(getActivity());
							LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							        LinearLayout.LayoutParams.FILL_PARENT,
							        LinearLayout.LayoutParams.FILL_PARENT);
							input.setLayoutParams(lp);
							input.setHint("Type new Device name");
							builder.setView(input)
					       .setPositiveButton("Rename", new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//CharSequence tekst = "Renaming " + selected + " to " + input.getText().toString() + " ...";
								
								int module;
								int device;
			
								module = Integer.parseInt(endevice.get(select).get(1).toString()); //moduleID
								device = Integer.parseInt(endevice.get(select).get(0).toString()); //deviceID
								CharSequence tekst = "Renaming Device: " + selected + " - ModuleID: " + module +" DeviceID: "+ device+ " to " + input.getText().toString() + "...";
								List<Object> param = new ArrayList<Object>();
									param.add(module);
									param.add(device);
									param.add(input.getText().toString());
			         	            String cmd = MainActivity.c.ParsRequest("renameDevice",param);
			         	            System.out.println("cmd of renameDevice  =  " + cmd);
			         	            MainActivity.c.giveCommand(cmd);
								
	  							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
								dialog.dismiss();
								
								//renameDevice(int moduleID, int deviceID,String newDeviceName)
								
							}
					       })
					       .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
					       });
	                    	AlertDialog alert = builder.create();
	                    	//And if the line above didn't bring ur dialog up use this bellow also:
	                    	alert.show();
	                    }
	                });
	                
              }
              else if(j==0){
              	button.setText("Disable Device");
              	button.setOnClickListener(new View.OnClickListener() {
                      public void onClick(View view) {
                      	
           
                    	  ArrayList<String> bravo = new ArrayList<String>();
                    	  final ArrayList<ArrayList> endevice = new ArrayList<ArrayList>();
                       	for(int x = 0; x < MainActivity.sensact.size(); x++){
                      		if((MainActivity.sensact.get(x).get(5).toString()).equalsIgnoreCase("true")){
                    			String xray = "Module: " + MainActivity.sensact.get(x).get(1).toString() +
                    					" -  Device: " +
                    					 MainActivity.sensact.get(x).get(0).toString() +
                    					" - Naam: "+ MainActivity.sensact.get(x).get(7).toString()
                    					;
                    			bravo.add(xray);
                    			endevice.add(MainActivity.sensact.get(x));
                      		}
                      	}
                      	final CharSequence[] items = bravo.toArray(new CharSequence[bravo.size()]);

                      	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                      	builder.setTitle("Select a Device to Disable");
                      	//builder.setCancelable(False);
                      	builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {
  							@Override
  							public void onClick(DialogInterface dialog,
  									int which) {
  								
  								// Toast.makeText(getActivity().getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
  								 //dialog.dismiss();
  								selected = items[which];
  								select = which;
  								 
  							}
                      	})
                                // .setCancelable(False)
  				       .setPositiveButton("Disable", new android.content.DialogInterface.OnClickListener() {
  						@Override
  						public void onClick(DialogInterface dialog, int which) {
  							
  							int module;
							int device;
		
							module = Integer.parseInt(endevice.get(select).get(1).toString()); //moduleID
							device = Integer.parseInt(endevice.get(select).get(0).toString()); //deviceID
							CharSequence tekst = "Disabling Device: " + selected + " - Module: " + module +" Device: "+ device + " ...";
							List<Object> param = new ArrayList<Object>();
								param.add(module);
								param.add(device);
		         	            String cmd = MainActivity.c.ParsRequest("disableDevice",param);
		         	            System.out.println("cmd of disableDevice  =  " + cmd);
		         	            MainActivity.c.giveCommand(cmd);
							
  							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
  							
  							//disableDevice(int moduleID, int deviceID)
  							
  							dialog.dismiss();
  						}
  				       })
  				       .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
  						@Override
  						public void onClick(DialogInterface dialog, int which) {
  							dialog.cancel();
  						}
  				       });
                      	AlertDialog alert = builder.create();
                      	//And if the line above didn't bring ur dialog up use this bellow also:
                      	alert.show();
                      }
                  });
              }
              else if(j == 3 && (MainActivity.modules.size() > 0)){
            	  button.setText("Edit Module");
                	button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                        	
             
                      	  ArrayList<String> bravo = new ArrayList<String>();
                         	for(int x = 0; x < MainActivity.modules.size(); x++){
                        		
                      			String xray = "Module: " + MainActivity.modules.get(x).get(0).toString() + 
                      					" - "+ MainActivity.modules.get(x).get(2).toString();
                      			bravo.add(xray);	
                        	}
                        	final CharSequence[] items = bravo.toArray(new CharSequence[bravo.size()]);
                        	
                          	final CharSequence[] roomitems = MainActivity.rooms.toArray(new CharSequence[MainActivity.rooms.size()]);
                          	
                            // arraylist to keep the selected items
                            final ArrayList<Integer> selectedItems=new ArrayList<Integer>();
                           
                        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        

                            builder.setTitle("Choose Module to edit");
                            //builder.setCancelable(False);
                            builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {
    							@Override
    							public void onClick(DialogInterface dialog,
    									int which) {
    								
    								// Toast.makeText(getActivity().getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
    								 //dialog.dismiss();
    								selected = items[which];
    								selectmodule = which;
    								System.out.println("$$$$ " + selected + " " + selectmodule + "  "+ which);
    								 
    							}
                        	})
                        	  //.setCancelable(False)

    				       .setPositiveButton("Select", new android.content.DialogInterface.OnClickListener() {
    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							/*CharSequence tekst = "";// = "1: " +selectedItems.get(0)+ " - 2: " + selectedItems.get(1) + " - 3: " + selectedItems.get(2);
    							for(int i = 0; i < selectedItems.size(); i++){
    								tekst = tekst + ""+ i + ": " + selectedItems.get(i) + " - ";
    								selectedRooms.add(MainActivity.modules.get(selectedItems.get(i)));
    							}*/
    							System.out.println("Module ID: "+ MainActivity.modules.get(selectmodule).get(0).toString());
    							CharSequence tekst = "Module ID: " + MainActivity.modules.get(selectmodule).get(0).toString();
    							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
    							
    						
    							
    							
			    							AlertDialog.Builder buildertwo = new AlertDialog.Builder(getActivity());
			                                buildertwo.setTitle("Select new Room for Module: " + MainActivity.modules.get(selectmodule).get(2).toString());
			                              //  buildertwo.setCancelable(False);
			                                buildertwo.setSingleChoiceItems(roomitems, -1, new android.content.DialogInterface.OnClickListener() {
			        							@Override
			        							public void onClick(DialogInterface dialog,
			        									int which) {
			        								
			        								// Toast.makeText(getActivity().getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
			        								 //dialog.dismiss();
			        								selectedroom = roomitems[which];
			        								 
			        							}
			                            	})
			                            	//  .setCancelable(False)
			    				       .setPositiveButton("Change", new android.content.DialogInterface.OnClickListener() {
			    						@Override
			    						public void onClick(DialogInterface dialog, int which) {
			    							//Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
			    							int moduleID = Integer.parseInt(MainActivity.modules.get(selectmodule).get(0).toString());
			    							
			  							CharSequence tekst = "Changing Module: " +moduleID +" " + MainActivity.modules.get(selectmodule).get(2).toString() + " to Room:  "+ selectedroom + " ...";
			  							List<Object> param = new ArrayList<Object>();
			  								param.add(moduleID);
			  								param.add(selectedroom);
			  		         	            String cmd = MainActivity.c.ParsRequest("changeModuleRoom",param);
			  		         	            System.out.println("cmd of changeModuleRoom  =  " + cmd);
			  		         	            MainActivity.c.giveCommand(cmd);
			  							
			    							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
			    							
			    							//changeModuleRoom(int moduleID, String newRoomName)
			    							
			    							dialog.dismiss();
			    						}
			    				       })
			    				       .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
			    						@Override
			    						public void onClick(DialogInterface dialog, int which) {
			    							dialog.cancel();
			    						}
			    				       });
			                        	AlertDialog alert = buildertwo.create();
			                        	//And if the line above didn't bring ur dialog up use this bellow also:
			                        	alert.show();

    							dialog.dismiss();
    							
    						}
    				       })
    				       .setNegativeButton("Remove", new android.content.DialogInterface.OnClickListener() {
    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							int moduleID = Integer.parseInt(MainActivity.modules.get(selectmodule).get(0).toString());
    							
	  							CharSequence tekst = "Removing Module: " +moduleID +" " + MainActivity.modules.get(selectmodule).get(2).toString() + " from Room ...";
	  							List<Object> param = new ArrayList<Object>();
	  								param.add(moduleID);
	  		         	            String cmd = MainActivity.c.ParsRequest("removeModuleFromRoom",param);
	  		         	            System.out.println("cmd of removeModuleFromRoom  =  " + cmd);
	  		         	            MainActivity.c.giveCommand(cmd);
	  							
	    							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
    							//removeModuleFromRoom(int moduleID)
    							dialog.dismiss();
    						}
    				       })
    				       .setNeutralButton("Rename", new android.content.DialogInterface.OnClickListener() {
    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							AlertDialog.Builder buildertwo = new AlertDialog.Builder(getActivity());
    	                    	buildertwo.setTitle("New Module name");
    	                    	//buildertwo.setCancelable(False);
    	  
    	                    	  final EditText input = new EditText(getActivity());
    								LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
    								        LinearLayout.LayoutParams.FILL_PARENT,
    								        LinearLayout.LayoutParams.FILL_PARENT);
    								input.setLayoutParams(lp);
    								input.setHint("Type new Module name");
    								buildertwo.setView(input)
    						       .setPositiveButton("Rename", new android.content.DialogInterface.OnClickListener() {
    								@Override
    								public void onClick(DialogInterface dialog, int which) {
    									int moduleID = Integer.parseInt(MainActivity.modules.get(selectmodule).get(0).toString());
    									CharSequence tekst = "Renaming " + moduleID + " " + selected + " to " + input.getText().toString() + " ...";
    									
    									List<Object> param = new ArrayList<Object>();
    									param.add(moduleID);
    									param.add(input.getText().toString());
    			         	            String cmd = MainActivity.c.ParsRequest("renameModule",param);
    			         	            System.out.println("cmd of renameModule  =  " + cmd);
    			         	            MainActivity.c.giveCommand(cmd);
    									
    									Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
    									dialog.dismiss();
    									
    									//renameModule(int moduleID, String newModuleName)
    									
    								}
    						       })
    						       .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
					       });
	                    	AlertDialog alert = buildertwo.create();
	                    	//And if the line above didn't bring ur dialog up use this bellow also:
	                    	alert.show();
    	                    	
    							//removeModuleFromRoom(int moduleID)
    							dialog.dismiss();
    						}
    				       });
    				      
                        	AlertDialog alert = builder.create();
                        	//And if the line above didn't bring ur dialog up use this bellow also:
                        	alert.show();
                        }
                    });
              }
              
          	button.setLayoutParams(new LayoutParams(100,LayoutParams.WRAP_CONTENT));
          	tester.add(button);
        }
    	  
    	  

    	  
    	  
    	  int tog = 0;
    	  int sb = 0;
    	  int pb = 0;
    	  int dk = 0;
          for(int i = 0; i< test.size() ; i++){
	          	try{
	          		//System.out.println("testerdetest: " + test.get(i).toString());
	                  linearLayout.addView(test.get(i));
	                  if((weergave.get(i).toString()).equalsIgnoreCase("Switch")){
	                	  linearLayout.addView(toggle.get(tog));
	                	  tog++;
	                  }
	                  else if((weergave.get(i).toString()).equalsIgnoreCase("Slider")){
	                	  linearLayout.addView(seek.get(sb));
	                	  sb++;
	                  }
	                  else if((weergave.get(i).toString()).equalsIgnoreCase("Toets")){
	                	  linearLayout.addView(press.get(dk));
	                	  dk++;
	                  }
	                  else if((weergave.get(i).toString()).equalsIgnoreCase("Bar")){
	                	  linearLayout.addView(prog.get(pb));
	                	  pb++;
	                  }
	                  TextView space = new TextView(getActivity());
	                  space.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
	                          LayoutParams.WRAP_CONTENT));
	                  space.setText("  ");   
	                  linearLayout.addView(space);
	                  
	                	  
	           }catch(Exception e){
	                  e.printStackTrace();
	           }
          }
          
          for(int i = 0; i < 4; i++){
        	  try{
                  linearLayout.addView(tester.get(i));
           }catch(Exception e){
                  e.printStackTrace();
           }
          }

            return container;
      }
  
}

/*
 * 
 * Message equals [
 * [2,1,"Lamp","Switch","bool",true,"Geen","Verlichting",0,1,"0",10,0],
 * [3,1,"Lamp","SwitchDisabled","bool",true,"Geen","Verlichting Disabled",0,1,"1",10,0]]

 * Message equals [
 * [4,154,"mdi-av-play-arrow","Switch","bool",true,"none","Play",0,0,"false",0,1420614502000],
 * [5,154,"mdi-av-skip-next","Toets","bool",true,"none","Next",0,0,"true",0,1420621995000],
 * [6,154,"mdi-av-skip-previous","Toets","bool",true,"none","Previous",0,0,"true",0,1420613138000],
 * [7,154,"mdi-av-stop","Toets","bool",true,"none","Stop",0,0,"true",0,1420611579000],
 * [8,154,"mdi-av-volume-up","Slider","int",true,"none","Volume",0,100,"20",0,0],
 * [9,154,"mdi-av-equalizer","Switch","bool",true,"none","Bass Boost",0,0,null,0,0],
 * [10,154,"mdi-av-shuffle","Switch","bool",true,"none","Shuffle",0,0,null,0,0],
 * [11,154,"mdi-av-repeat","Switch","bool",true,"none","Repeat",0,0,null,0,0]]

 * 
 * 
 * 
 * 
 * 
 * */
