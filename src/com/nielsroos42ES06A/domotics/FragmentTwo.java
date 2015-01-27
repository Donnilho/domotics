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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
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
 
          
 
            container = (ViewGroup) inflater.inflate(R.layout.fragment_layout_two, null);
            LinearLayout linearLayout = (LinearLayout) container.findViewById(R.id.fragmenttwo);
            //TextView viewText = new TextView(getActivity());
            final ArrayList<TextView> test = new ArrayList<TextView>();
            ArrayList<Button> tester = new ArrayList<Button>();
            ArrayList<ToggleButton> toggle = new ArrayList<ToggleButton>();
            ArrayList<SeekBar> seek = new ArrayList<SeekBar>();
            ArrayList<ProgressBar> prog = new ArrayList<ProgressBar>();
            
            for(int i = 0; i < 100; i++){
            	TextView viewText = new TextView(getActivity());
            	Button button = new Button(getActivity());
            	ToggleButton tb = new ToggleButton(getActivity());
            	SeekBar seeker = new SeekBar(getActivity());
            	ProgressBar bar = new ProgressBar(getActivity(),null, 
                        android.R.attr.progressBarStyleHorizontal);
            	viewText.setId(i);
                final int id_text = viewText.getId();
                viewText.setLayoutParams(new LayoutParams(500,LayoutParams.WRAP_CONTENT));
                viewText.setText("Test : " + id_text);
                
                bar.setProgress(10);
                bar.setPadding(20, 0, 0, 0);
                bar.setMax(50);
                bar.setId(i);
                bar.setLayoutParams(new LayoutParams(400, LayoutParams.WRAP_CONTENT));
                prog.add(bar);


               // button.setPadding(20, 0, 0, 0);
                button.setMaxWidth(5);
            	button.setId(i);
                final int id_button = button.getId();
                button.setText("button " + id_button);
            	button.setLayoutParams(new LayoutParams(100,LayoutParams.WRAP_CONTENT));
            	
            	 //tb.setPadding(20, 0, 0, 0);
                 tb.setTextOn("ON");
                 tb.setTextOff("OFF");
                 tb.setChecked(true);
                 tb.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                 
                 seeker.setMax(50);
                 seeker.setId(i);
                 seeker.setProgress(15);
                 LayoutParams lp = new LayoutParams(400, LayoutParams.WRAP_CONTENT); 
                 seeker.setLayoutParams(lp);
                 final int id_seeker = seeker.getId();
                 seeker.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
         			int progressChanged = 0;

        			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        				progressChanged = progress;
        			}

        			public void onStartTrackingTouch(SeekBar seekBar) {
        				// TODO Auto-generated method stub
        			}

        			public void onStopTrackingTouch(SeekBar seekBar) {
        				Toast.makeText(getActivity().getApplicationContext(),id_seeker +" seek bar progress:"+progressChanged, 
        						Toast.LENGTH_SHORT).show();
        			}
        		});
                seek.add(seeker);
                
           

            	
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

                    	
                    }
                });
            }


    
            for(int i = 0; i< 100 ; i++){
	            	try{
	            		linearLayout.addView(toggle.get(i));
	            		linearLayout.addView(prog.get(i));
	                    linearLayout.addView(test.get(i));
	                    linearLayout.addView(tester.get(i));
	                    linearLayout.addView(seek.get(i));
	                    
	             }catch(Exception e){
	                    e.printStackTrace();
	             }
            }
      
            return container;
      }

 
}