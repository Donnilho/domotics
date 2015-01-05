package com.nielsroos42ES06A.domotics;
 
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
 
public class FragmentOne extends Fragment {
      ImageView ivIcon;
      TextView tvItemName; 
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      
      public ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
      //public ArrayList<ArrayList> sensact = new ArrayList<ArrayList>();
      public CharSequence selected;
      public int select;
      public int testmodule;
      public int testdevice;
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
          ArrayList<Button> tester = new ArrayList<Button>();

         // sensact.addAll(MainActivity.sensorsInRoom);
          //sensact.addAll(MainActivity.actuatorsInRoom);
          int buttonID = 0;
    	  
    	 // System.out.println("modules.size: "+ MainActivity.modules.size());
    	  for(int i = 0; i < MainActivity.modules.size(); i++){
    		  if(MainActivity.modules.get(i).get(1).equals("true")){
	          	TextView viewText = new TextView(getActivity());
	          	viewText.setId(i);
	              viewText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
	                      LayoutParams.WRAP_CONTENT));
	              viewText.setText(	"ID: "+ MainActivity.modules.get(i).get(0) + 
	            		  			"   -   Module: " + MainActivity.modules.get(i).get(2) + 
	            		  			"   -   Enabled: " + MainActivity.modules.get(i).get(1));   
	          	test.add(viewText);
	          	for(int j = 0 ; j < MainActivity.sensorsInRoom.size(); j++){
	          		if((MainActivity.sensorsInRoom.get(j).get(1) == MainActivity.modules.get(i).get(0)) && MainActivity.sensorsInRoom.get(j).get(5).equals("true")){
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
	          		if((MainActivity.actuatorsInRoom.get(j).get(1) == MainActivity.modules.get(i).get(0)) && MainActivity.actuatorsInRoom.get(j).get(5).equals("true")){
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
    	  }
    	  
    	  for(int j = 0; j < 3; j++){
          	Button button = new Button(getActivity());
          	button.setMaxWidth(5);
          	button.setId(buttonID);
          	buttonID++;
              final int id_button = button.getId();
              
              if(j == 1){
            	  button.setText("Enable Disabled Device");
                	button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                        	
                        	ArrayList<String> bravo = new ArrayList<String>();
                        	final ArrayList<ArrayList> disdevice = new ArrayList<ArrayList>();
                        	for(int x = 0; x < MainActivity.sensact.size(); x++){
                        		if(MainActivity.sensact.get(x).get(5).equals("false")){
                        			String xray = "Module: " + MainActivity.sensact.get(x).get(1).toString() +
                        					" - Device: " +
                        					 MainActivity.sensact.get(x).get(0).toString() +
                        					" - Naam: "+ MainActivity.sensact.get(x).get(7).toString()
                        					;
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
    								
    								// Toast.makeText(getActivity().getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
    								 //dialog.dismiss();
    								selected = items[which];
    								select = which;
    								 
    							}
                        	})
                                   .setCancelable(false)
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
              else if(j==2){
	                button.setText("Rename Device");
	            	button.setOnClickListener(new View.OnClickListener() {
	                    public void onClick(View view) {
	                    	
	                    	
	                    	//final CharSequence[] items = {"Red", "Green", "Blue"};
	                    	//final CharSequence[] items = MainActivity.rooms.toArray(new CharSequence[MainActivity.rooms.size()]);
	                    	ArrayList<String> bravo = new ArrayList<String>();
	                    	final ArrayList<ArrayList> endevice = new ArrayList<ArrayList>();
                        	for(int x = 0; x < MainActivity.sensact.size(); x++){
                        		if(MainActivity.sensact.get(x).get(5).equals("true")){
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
	                               .setCancelable(false);
	                       final EditText input = new EditText(getActivity());
							LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							        LinearLayout.LayoutParams.FILL_PARENT,
							        LinearLayout.LayoutParams.FILL_PARENT);
							input.setLayoutParams(lp);
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
                      		if(MainActivity.sensact.get(x).get(5).equals("true")){
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
                                 .setCancelable(false)
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
          	button.setLayoutParams(new LayoutParams(100,LayoutParams.WRAP_CONTENT));
          	tester.add(button);
        }
    	  
    	  
    	  
    	  

          for(int i = 0; i< test.size() ; i++){
	          	try{
	          		System.out.println("testerdetest: " + test.get(i).toString());
	                  linearLayout.addView(test.get(i));
	           }catch(Exception e){
	                  e.printStackTrace();
	           }
          }
          
          for(int i = 0; i < 3; i++){
        	  try{
                  linearLayout.addView(tester.get(i));
           }catch(Exception e){
                  e.printStackTrace();
           }
          }

            return container;
      }

}