package com.nielsroos42ES06A.domotics;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentSettings extends Fragment{
    ImageView ivIcon;
   // TextView tvItemName;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    public CharSequence selected;

    public FragmentSettings()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

          container = (ViewGroup) inflater.inflate(R.layout.fragment_layout_settings, null);
          LinearLayout linearLayout = (LinearLayout) container.findViewById(R.id.fragmentsettings);
          //TextView viewText = new TextView(getActivity());
          final ArrayList<TextView> test = new ArrayList<TextView>();
          ArrayList<Button> tester = new ArrayList<Button>();
          int buttonID = 0;
          
          for(int i = 0; i < MainActivity.rooms.size(); i++){
	          	TextView viewText = new TextView(getActivity());
	          	viewText.setId(i);
	              viewText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
	                      LayoutParams.WRAP_CONTENT));
	              viewText.setText(	"Room: "+ MainActivity.rooms.get(i).toString());   
	          	test.add(viewText);         	
          }
          for(int j = 0; j < 3; j++){
            	Button button = new Button(getActivity());
            	button.setMaxWidth(5);
            	button.setId(buttonID);
            	buttonID++;
                final int id_button = button.getId();
                
                if(j == 0){
                	button.setText("Add Room");
                	button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            //String tekst = test.get(id_button).getText().toString();
                        	//String xray = ("Button clicked index = " + id_button + " containing text: " + tekst);
    						AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
    						alertDialog.setTitle("Add new Room");
    						//String x = (String) msg.obj;
    						alertDialog.setMessage("Set name for new room");
    						
    						final EditText input = new EditText(getActivity());
    						LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
    						        LinearLayout.LayoutParams.FILL_PARENT,
    						        LinearLayout.LayoutParams.FILL_PARENT);
    						input.setLayoutParams(lp);
    						alertDialog.setView(input);
    						alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Add",
    						    new DialogInterface.OnClickListener() {
    						        public void onClick(DialogInterface dialog, int which) {
    	    							CharSequence tekst = "Room " + input.getText().toString() + " has been added";
    	    							 System.out.println("New Room: " + input.getText().toString() + " added");
    	    							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
    						            dialog.dismiss();
    						        }
    						    });
    						alertDialog.show();
                        }
                    });
                }
                else if(j==1){
	                button.setText("Change room name");
	            	button.setOnClickListener(new View.OnClickListener() {
	                    public void onClick(View view) {
	                    	
	                    	
	                    	//final CharSequence[] items = {"Red", "Green", "Blue"};
	                    	final CharSequence[] items = MainActivity.rooms.toArray(new CharSequence[MainActivity.rooms.size()]);

	                    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	                    	builder.setTitle("Select the Room you want to change.");
	                    	builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									
									// Toast.makeText(getActivity().getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
									 //dialog.dismiss();
									selected = items[which];
									 
								}
	                    	})
	                               .setCancelable(false);
	                       final EditText input = new EditText(getActivity());
							LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							        LinearLayout.LayoutParams.FILL_PARENT,
							        LinearLayout.LayoutParams.FILL_PARENT);
							input.setLayoutParams(lp);
							builder.setView(input)
					       .setPositiveButton("Change", new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								CharSequence tekst = selected + " has been changed to " + input.getText().toString();
								Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
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
                	button.setText("Delete Room");
                	button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                        	
             
                        	final CharSequence[] items = MainActivity.rooms.toArray(new CharSequence[MainActivity.rooms.size()]);


                        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        	builder.setTitle("Select a Room to delete");
                        	builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {
    							@Override
    							public void onClick(DialogInterface dialog,
    									int which) {
    								
    								// Toast.makeText(getActivity().getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
    								 //dialog.dismiss();
    								selected = items[which];
    								 
    							}
                        	})
                                   .setCancelable(false)
    				       .setPositiveButton("Delete", new android.content.DialogInterface.OnClickListener() {
    						@Override
    						public void onClick(DialogInterface dialog, int which) {
    							CharSequence tekst = "Room " + selected + " has been deleted";
    							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
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
        


          int size = MainActivity.rooms.size(); //evt uitbreiden
        /*  for(int i = 0; i< size ; i++){
	            	try{
	                    linearLayout.addView(test.get(i));
	             }catch(Exception e){
	                    e.printStackTrace();
	             }
          }*/
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
