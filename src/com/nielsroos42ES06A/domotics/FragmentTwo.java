package com.nielsroos42ES06A.domotics;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
 
public class FragmentTwo   extends Fragment {
 
      ImageView ivIcon;
     // TextView tvItemName;
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      public CharSequence selected;
 
      public FragmentTwo()
      {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
            /*View view=inflater.inflate(R.layout.fragment_layout_two,container, false);
 
            ivIcon=(ImageView)view.findViewById(R.id.frag2_icon);
            for(int i = 0; i < 10; i++){
                TextView tvItemName=(TextView)view.findViewById(R.id.frag2_text);
                tvItemName.setText("test");
            }*/
 
            container = (ViewGroup) inflater.inflate(R.layout.fragment_layout_two, null);
            LinearLayout linearLayout = (LinearLayout) container.findViewById(R.id.fragmenttwo);
            //TextView viewText = new TextView(getActivity());
            final ArrayList<TextView> test = new ArrayList<TextView>();
            ArrayList<Button> tester = new ArrayList<Button>();
            ArrayList<ToggleButton> toggle = new ArrayList<ToggleButton>();
            
            for(int i = 0; i < 100; i++){
            	TextView viewText = new TextView(getActivity());
            	Button button = new Button(getActivity());
            	ToggleButton tb = new ToggleButton(getActivity());
            	viewText.setId(i);
                final int id_text = viewText.getId();
                viewText.setLayoutParams(new LayoutParams(500,LayoutParams.WRAP_CONTENT));
                viewText.setText("Test : " + id_text);


            	button.setMaxWidth(5);
            	button.setId(i);
                final int id_button = button.getId();
                button.setText("button " + id_button);
            	button.setLayoutParams(new LayoutParams(100,LayoutParams.WRAP_CONTENT));
            	
            	 
                 tb.setTextOn("ON");
                 tb.setTextOff("OFF");
                 tb.setChecked(true);
                 tb.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
           

            	
            	test.add(viewText);
            	tester.add(button);
            	toggle.add(tb);
            	tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            	        if(isChecked)
            	        {
            	        	CharSequence tekst = "Button On";
							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
            	        }
            	        else
            	        {
            	        	CharSequence tekst = "Button off";
							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
            	        }
            	    }
            	});
            	
            	button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                    	
                    	
                    	final CharSequence[] items = {"Red", "Green", "Blue"};

                    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    	builder.setTitle("Pick a color");
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
				       .setPositiveButton("Yes", new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							CharSequence tekst = selected + " has been selected";
							Toast.makeText(getActivity().getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						}
				       })
				       .setNegativeButton("No", new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
				       });
                    	AlertDialog alert = builder.create();
                    	//And if the line above didn't bring ur dialog up use this bellow also:
                    	alert.show();

                    	/*String tekst = test.get(id_button).getText().toString();
                    	String xray = ("Button clicked index = " + id_button + " containing text: " + tekst);
						AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
						alertDialog.setTitle("Alert");
						//String x = (String) msg.obj;
						alertDialog.setMessage("Set new name for room: " + tekst);
						
						final EditText input = new EditText(getActivity());
						LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						        LinearLayout.LayoutParams.FILL_PARENT,
						        LinearLayout.LayoutParams.FILL_PARENT);
						input.setLayoutParams(lp);
						alertDialog.setView(input);
						alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
						    new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int which) {
						            dialog.dismiss();
						            System.out.println("New Name: " + input.getText().toString());
						        }
						    });
						alertDialog.show();*/
                    }
                });
            }


            //final int i = 5;
          /*  viewText.setId(i); //Set id to remove in the future.
            viewText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                       LayoutParams.WRAP_CONTENT));
            viewText.setText("Hello");*/
       //     Log.d("View","Start");
            for(int i = 0; i< 100 ; i++){
	            	try{
	            		linearLayout.addView(toggle.get(i));
	                    linearLayout.addView(test.get(i));
	                    linearLayout.addView(tester.get(i));
	             }catch(Exception e){
	                    e.printStackTrace();
	             }
            }
            /*try{
                   linearLayout.addView(viewText);
            }catch(Exception e){
                   e.printStackTrace();
            }*/
            /*tvItemName.setText(getArguments().getString(ITEM_NAME));
            ivIcon.setImageDrawable(view.getResources().getDrawable(
                        getArguments().getInt(IMAGE_RESOURCE_ID)));*/
            return container;
      }

 
}