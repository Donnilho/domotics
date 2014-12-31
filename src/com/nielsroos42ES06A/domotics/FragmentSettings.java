package com.nielsroos42ES06A.domotics;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FragmentSettings extends Fragment{
    ImageView ivIcon;
   // TextView tvItemName;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public FragmentSettings()
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

          container = (ViewGroup) inflater.inflate(R.layout.fragment_layout_settings, null);
          LinearLayout linearLayout = (LinearLayout) container.findViewById(R.id.fragmenttwo);
          //TextView viewText = new TextView(getActivity());
          ArrayList<TextView> test = new ArrayList<TextView>();
          ArrayList<Button> tester = new ArrayList<Button>();
          
          for(int i = 0; i < 100; i++){
          	TextView viewText = new TextView(getActivity());
          	Button button = new Button(getActivity());
          	viewText.setId(i);
              viewText.setLayoutParams(new LayoutParams(500,LayoutParams.WRAP_CONTENT));
              viewText.setText("Test : " + i);


          	button.setMaxWidth(5);
          	//button.setLayoutParams(new LayoutParams(100,LayoutParams.WRAP_CONTENT));
              RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)button.getLayoutParams();
              params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
              button.setLayoutParams(params);
          	button.setText("Test");
          	test.add(viewText);
          	tester.add(button);
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