package com.nielsroos42ES06A.domotics;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentEvent extends Fragment {
	 ImageView ivIcon;
     TextView tvItemName;


     public static final String IMAGE_RESOURCE_ID = "iconResourceID";
     public static final String ITEM_NAME = "itemName";
     
     public ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
     public ArrayList<ArrayList> selectedRooms = new ArrayList<ArrayList>();
     //public ArrayList<ArrayList> sensact = new ArrayList<ArrayList>();
     public String cmd;
     public CharSequence selected;
     public int select;
     public int testmodule;
     public int testdevice;
     public CharSequence selectedmodule;
     public CharSequence selectedroom;
     public int selectmodule;
     public int selectroom;
     //SCRIPT Variabelen
     public String scriptName;
     public int scriptID;
     public String scriptEnable;
     public int conditionModuleID;
     public int conditionSensorID;
     public String conditionComparator;
     public String conditionValue;
     public int actionModuleID;
     public int actionActuatorID;
     public String actionValue;
     /*
      * addScript(String name, int conditionModuleID,
			int conditionSensorID, String conditionComparator,
			String conditionValue, int actionModuleID, int actionActuatorID,
			String actionValue)
	 * @param name the name of the script that will be added.
	 * @param conditionModuleID the moduleID of the sensor for the condition of the script.
	 * @param conditionSensorID the sensorID of the sensor for the condition of the script.
	 * @param conditionComparator the comparator that is responsible for comparing the sensor's value and the value in the database.
	 * @param conditionValue the value that will be compared.
	 * @param actionModuleID the moduleID of the actuator for the action of the script.
	 * @param actionActuatorID the deviceID of the actuator for the action of the script.
	 * @param actionValue the new value for the actuator
	 * [ =scripts
	 * 		[ID,Name,EnabledStatus,[ === ID = scripts.get(i).get(0) -> Name = scripts.get(i).get(1) -> EnabledStatus = scripts.get(i).get(2)
	 * 			[ConditionID, SensorID, SensorModuleID,ConditionComperator, ConditionValue], === ConditionID = scripts.get(i).get(3).get(0)
	 * 			[ActionID, ActuatorID, ModuleActuatorID, ActuatorValue] === ActionID = scripts.get(i).get(4).get(0)
	 * 		],
	 * 		[array 2], 
	 * 		[array 3]]
	 * 
	 * 
	 * 
	 * */


     public FragmentEvent() {

     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
    	   MainActivity.positie = MainActivity.roomSize + 3;
           container = (ViewGroup) inflater.inflate(R.layout.fragment_layout_two, null);
           LinearLayout linearLayout = (LinearLayout) container.findViewById(R.id.fragmenttwo);
           //TextView viewText = new TextView(getActivity());
           final ArrayList<TextView> test = new ArrayList<TextView>();
           final ArrayList<Button> tester = new ArrayList<Button>();

          /* ArrayList<Object> param = new ArrayList<Object>();
           cmd = MainActivity.c.ParsRequest("getAllDevices", param);
           System.out.println("cmd of getAllDevices  =  " + cmd);
           MainActivity.c.giveCommand(cmd); //getalldevices voor aanmaken van scripts*/
           
           ArrayList<Object> param = new ArrayList<Object>();
           cmd = MainActivity.c.ParsRequest("getAllSensors", param);
           System.out.println("cmd of getAllSensors  =  " + cmd);
           MainActivity.c.giveCommand(cmd);
           
           cmd = MainActivity.c.ParsRequest("getAllActuators", param);
           System.out.println("cmd of getAllActuators  =  " + cmd);
           MainActivity.c.giveCommand(cmd);
           
           
          // ArrayList<String> test123 = new ArrayList<String>();
           TextView intro = new TextView(getActivity());
           intro.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
           intro.setText("Events");
           test.add(intro);

           
           for(int j = 0 ; j < MainActivity.scripts.size(); j++){
	          		TextView scriptText = new TextView(getActivity());
		              scriptText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
		                      LayoutParams.WRAP_CONTENT));
		              scriptText.setText(	"   Event: "+ MainActivity.scripts.get(j).get(0) + 
		            		  			"   -   Name: " + MainActivity.scripts.get(j).get(1) +
		            		  			"   -   Enable: " + MainActivity.scripts.get(j).get(2) +
		            		  			"   -   ConditionModuleID: " + MainActivity.scripts.get(j).get(8) +
		            		  			"   -   ConditionSensorID: " + MainActivity.scripts.get(j).get(9) + 
		            		  			"   -   ConditionValue: " + MainActivity.scripts.get(j).get(10) +
		            		  			"   -   Comperator: " + MainActivity.scripts.get(j).get(11) +
		            		  			"   -   ActionModuleID: " + MainActivity.scripts.get(j).get(5) +
		            		  			"   -   ActionActuator: " + MainActivity.scripts.get(j).get(4) +
		            		  			"   -   ActionValue: " + MainActivity.scripts.get(j).get(6) +
		            		  			" " );  
		          	test.add(scriptText);
         		
         	}
           
           
           /*for(int i = 0; i < MainActivity.rooms.size(); i++){
 	          	TextView viewText = new TextView(getActivity());
 	          	viewText.setId(i);
 	              viewText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
 	                      LayoutParams.WRAP_CONTENT));
 	              viewText.setText(	"Room: "+ MainActivity.rooms.get(i).toString());   
 	          	test.add(viewText);         	
           }*/
           for(int j = 0; j < 1; j++){
             	Button button = new Button(getActivity());
             	button.setMaxWidth(5);

                 
         
                  if(j==0){ 
                       	button.setText("Add Script");
                       	button.setOnClickListener(new View.OnClickListener() {
                               public void onClick(View view) {
                               	
                    
                             	  ArrayList<String> bravo = new ArrayList<String>();
                             	  
                                	for(int x = 0; x < MainActivity.sensors.size(); x++){
                             			String xray = "Module: " + MainActivity.sensors.get(x).get(1).toString() +
                             					" -  Device: " +
                             					 MainActivity.sensors.get(x).get(0).toString() +
                             					" - Naam: "+ MainActivity.sensors.get(x).get(7).toString()
                             					;
                             			bravo.add(xray);
                             			
                               	}
                               	final CharSequence[] items = bravo.toArray(new CharSequence[bravo.size()]);

                               	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                               	builder.setTitle("Select Sensors which will be used for condition");
                               	
                                final EditText input = new EditText(getActivity());
    							LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
    							        LinearLayout.LayoutParams.FILL_PARENT,
    							        LinearLayout.LayoutParams.FILL_PARENT);
    							input.setLayoutParams(lp);
    							input.setHint("Type new Script name");
    							builder.setView(input);
                               	
                               	builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {
           							@Override
           							public void onClick(DialogInterface dialog,
           									int which) {
          
           								selected = items[which];
           								select = which;
           								 
           							}
                               	})
                                         // .setCancelable(false)
           				       .setPositiveButton("Select", new android.content.DialogInterface.OnClickListener() {
           						@Override
           						public void onClick(DialogInterface dialog, int which) {
           							scriptName = input.getText().toString();
         							conditionModuleID = Integer.parseInt(MainActivity.sensors.get(select).get(1).toString()); //moduleID
         							System.out.println("ModuleID: " + conditionModuleID);
         							
         							conditionSensorID = Integer.parseInt(MainActivity.sensors.get(select).get(0).toString()); //deviceID
         							
         							System.out.println("DeviceID: " + conditionSensorID);
         							CharSequence tekst = "Module: " + conditionModuleID + " - Device: " + conditionSensorID +" Name: "+ selected + "  = selected as condition Device";
         							
           							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
           							dialog.dismiss();
           							//disableDevice(int moduleID, int deviceID)
           							//BENEATH SECOND DIALOG
	           							final CharSequence[] items2 = {"Equals","Does not Equal","Less or Equal", "Larger or Equal","Less","Larger"}; // =,!=,<=,>=,<,> 
	           							final String[] comperators = {"=","!=","<=",">=","<",">"};
	           							
	           							AlertDialog.Builder buildertwo = new AlertDialog.Builder(getActivity());
	                                   	buildertwo.setTitle("Select Comperator and compared value used in condition");
	                                   	//buildertwo.setMessage("Message about minimum and maximum value");
	                                   	
	                                    final EditText input2 = new EditText(getActivity());
	        							LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
	        							        LinearLayout.LayoutParams.FILL_PARENT,
	        							        LinearLayout.LayoutParams.FILL_PARENT);
	        							input2.setLayoutParams(lp);
	        							input2.setHint("Type new compared value");
	        							buildertwo.setView(input2);
	        							
	        							buildertwo.setSingleChoiceItems(items2, -1, new android.content.DialogInterface.OnClickListener() {
	               							@Override
	               							public void onClick(DialogInterface dialog,
	               									int which2) {
	              
	               								selected = comperators[which2];
	               								select = which2;
	               								 
	               							}
	                                   	})
	                                   	.setPositiveButton("Select", new android.content.DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog2, int which2) {
											CharSequence tekst = "Sensor has to be " + selected + " compared to " + input2.getText().toString();
											
										     conditionComparator = comperators[select];
										     conditionValue = input2.getText().toString();
											
											Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
											
											//dialog2.dismiss();
											//THIRD DIALOG BENEATH!!!!!!!!!
											
													
													ArrayList<String> bravo2 = new ArrayList<String>();
					                             	  
				                                	for(int x = 0; x < MainActivity.actuators.size(); x++){
				                             			String xray = "Module: " + MainActivity.actuators.get(x).get(1).toString() +
				                             					" -  Device: " +
				                             					 MainActivity.actuators.get(x).get(0).toString() +
				                             					" - Naam: "+ MainActivity.actuators.get(x).get(7).toString()
				                             					;
				                             			bravo2.add(xray);
				                             			
				                                	}
				                                	final CharSequence[] items3 = bravo2.toArray(new CharSequence[bravo2.size()]);
				           							
				           							AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
				                                   	builder3.setTitle("Select actuator to react when condition matched");
				                                   	//buildertwo.setMessage("Message about minimum and maximum value");
				                                   	
				                                    final EditText input3 = new EditText(getActivity());
				        							LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
				        							        LinearLayout.LayoutParams.FILL_PARENT,
				        							        LinearLayout.LayoutParams.FILL_PARENT);
				        							input3.setLayoutParams(lp3);
				        							input3.setHint("Type new value for actuator if condition matched");
				        							builder3.setView(input3);
				        							
				        							builder3.setSingleChoiceItems(items3, -1, new android.content.DialogInterface.OnClickListener() {
				               							@Override
				               							public void onClick(DialogInterface dialog2,
				               									int which3) {
				              
				               								selected = items3[which3];
				               								select = which3;
				               								 
				               							}
				                                   	})
				                                   	.setPositiveButton("Select", new android.content.DialogInterface.OnClickListener() {
													@Override
													public void onClick(DialogInterface dialog3, int which3) {
														actionValue = input3.getText().toString();
					         							actionModuleID = Integer.parseInt(MainActivity.actuators.get(select).get(1).toString()); //moduleID
					         							System.out.println("ModuleID: " + actionModuleID);
					         							actionActuatorID = Integer.parseInt(MainActivity.actuators.get(select).get(0).toString()); //deviceID
					         							
					         							System.out.println("ActuatorID: " + actionActuatorID);
					         							CharSequence tekst = "Module: " + actionModuleID + " - Device: " + actionActuatorID +" Name: "+ selected + "  = selected as action actuator";
					         							
					           							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
					           						    
					           							ArrayList<Object> param = new ArrayList<Object>();
					           							param.add(scriptName); 
					           							param.add(conditionModuleID);
					           							param.add(conditionSensorID);
					           							param.add(conditionComparator);
					           							param.add(conditionValue);
					           							param.add(actionModuleID);
					           							param.add(actionActuatorID);
					           							param.add(actionValue);
					           							cmd = MainActivity.c.ParsRequest("addScript", param);
					           							System.out.println("cmd of addScript  =  " + cmd);
					           							MainActivity.c.giveCommand(cmd);
					           							
					           							//addScript(String name, int conditionModuleID,
					           								//	int conditionSensorID, String conditionComparator,
					           									//String conditionValue, int actionModuleID, int actionActuatorID,
					           									//String actionValue)
					           							CharSequence tekst2 = "Adding script: "+ scriptName + " ...";
					         							
					           							Toast.makeText(getActivity().getApplicationContext(), tekst2, Toast.LENGTH_SHORT).show();
														
					           							
					           							dialog3.dismiss();
														
														
														 
														
													}
											       })
											       .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
														@Override
														public void onClick(DialogInterface dialog3, int which) {
															dialog3.cancel();
														}
												       });
								                    	AlertDialog alert3 = builder3.create();
								                    	//And if the line above didn't bring ur dialog up use this bellow also:
								                    	alert3.show();
											
											
											dialog2.dismiss();
											
											
											 
											
										}
								       })
								       .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog2, int which) {
												dialog2.cancel();
											}
									       });
					                    	AlertDialog alert2 = buildertwo.create();
					                    	//And if the line above didn't bring ur dialog up use this bellow also:
					                    	alert2.show();
					                    
								       
           							
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
           for(int i = 0; i < test.size(); i ++){
          	  try{
                  linearLayout.addView(test.get(i));
           }catch(Exception e){
                  e.printStackTrace();
           }
          
           }
           
         
           for(int i = 0; i < tester.size(); i++){
         	  try{
                   linearLayout.addView(tester.get(i));
            }catch(Exception e){
                   e.printStackTrace();
            }
           }

           return container;
     }
     public void test(){
    	 
     }
 }