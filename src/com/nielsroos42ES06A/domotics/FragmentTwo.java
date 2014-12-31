package com.nielsroos42ES06A.domotics;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
 
public class FragmentTwo   extends Fragment {
 
      ImageView ivIcon;
     // TextView tvItemName;
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
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
            
            for(int i = 0; i < 100; i++){
            	TextView viewText = new TextView(getActivity());
            	Button button = new Button(getActivity());
            	viewText.setId(i);
                final int id_text = viewText.getId();
                viewText.setLayoutParams(new LayoutParams(500,LayoutParams.WRAP_CONTENT));
                viewText.setText("Test : " + id_text);


            	button.setMaxWidth(5);
            	button.setId(i);
                final int id_button = button.getId();
                button.setText("button " + id_button);
            	button.setLayoutParams(new LayoutParams(100,LayoutParams.WRAP_CONTENT));

            	
            	test.add(viewText);
            	tester.add(button);
            	
            	button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                       /* Toast.makeText(DynamicLayout.this,
                                "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                                .show();
                                findViewById(v.getId()
                                */
                    	String tekst = test.get(id_button).getText().toString();
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
						alertDialog.show();
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