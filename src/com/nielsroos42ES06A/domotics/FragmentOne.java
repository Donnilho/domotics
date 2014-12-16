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
import android.widget.ImageView;
import android.widget.TextView;
 
public class FragmentOne extends Fragment {
 //oleasdf
      ImageView ivIcon;
      TextView tvItemName; 
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      
      public ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
      //public ArrayList<Object> moduleinfo = new ArrayList<Object>();
 
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
    	  System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");
    	  MainActivity activity = (MainActivity) getActivity();
    	  
    	  for(int i = 0; i < MainActivity.modules.size(); i++){
				//modules.add( ((ArrayList<ArrayList>) msg.obj).get(i));
				System.out.println("Module ID MAIN : " + i);
				for(int j = 0; j < 4 ; j++){
					String x = MainActivity.modules.get(i).get(j).toString();
					System.out.println("Fragment one : " + x);
				}
			}
    	  
    	  
    	  String x = MainActivity.modules.get(0).get(1).toString();
    	  System.out.println("Fragment one data from main : " + x);
            View view = inflater.inflate(R.layout.fragment_layout_one, container,
                        false);
 
            ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
            tvItemName = (TextView) view.findViewById(R.id.frag1_text);
 
            tvItemName.setText(getArguments().getString(ITEM_NAME));
            ivIcon.setImageDrawable(view.getResources().getDrawable(
                        getArguments().getInt(IMAGE_RESOURCE_ID)));
            return view;
      }

}