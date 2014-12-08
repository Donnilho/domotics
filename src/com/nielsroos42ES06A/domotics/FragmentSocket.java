package com.nielsroos42ES06A.domotics;
import java.util.*;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;

	 
	public class FragmentSocket extends Fragment {
		
			    private Connector c = null;
			    Parser p = new Parser();
			    public String IP;

			  
			    String cmd;
			    String host = "192.168.0.103";
/*			    @Override
			    public void onCreate(Bundle savedInstanceState) {
			        super.onCreate(savedInstanceState);
			        setContentView(R.layout.activity_socket);  
			        new Thread(new ClientThread()).start();
			    }*/
			    
			    ImageView ivIcon;
			      TextView tvItemName;
			 
			      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
			      public static final String ITEM_NAME = "itemName";

			
			    @Override
			    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {             
		            View view = inflater.inflate(R.layout.fragment_layout_socket, container,
	                        false);
		            
		    		final EditText setIP = (EditText) findViewById(R.id.ip);
		    		final SharedPreferences preferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
		    		//setIP.setText(preferences.getString("ip", "0.0.0.0"));
		            
		            c = new Connector(handler);
		    		c.start();
		    		c.giveCommand("Domotics App online");

		            LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_layout_socket,
		                            container, false);

		            

		            // note that we're looking for a button with id="@+id/myButton" in your inflated layout
		            // Naturally, this can be any View; it doesn't have to be a button
		            Button mButton = (Button) mLinearLayout.findViewById(R.id.myButton);
		            mButton.setOnClickListener(new OnClickListener() {
		                @Override
		                public void onClick(View v) {
		                	EditText et = (EditText) getView().findViewById(R.id.EditText01);
		                	            String str = et.getText().toString();
		                	            System.out.println(str);
		                	            List<Object> test = new ArrayList<Object>();
		                	            String test1 = "briansfuckingwankroom";
		                	            //String test2 = "";
		                	            test.add(test1);
		                	           // test.add(test2);
		                	            cmd = p.ParsRequest(str, test);
		                	            //cmd = str; //test
		                	            c.giveCommand(cmd);
		                }
	 
		            });
		            
		            Button submitIP = (Button) mLinearLayout.findViewById(R.id.saveip);
		    		
		    		submitIP.setOnClickListener(new View.OnClickListener() {
		    			@Override
		    			public void onClick(View v) { //whats going to be executed once the button is clicked
		    				EditText setIP = (EditText)getView().findViewById(R.id.ip);
		    				String ip = setIP.getText().toString();
		    				c.setHost(ip);
		    				System.out.println(ip);
		    				System.out.println("IP Setted");
		    				
		    				//SharedPreferences preferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
		    				SharedPreferences.Editor edit = preferences.edit();
		    				
		    				edit.putString("ip", ip);
		    				edit.commit();
		    				//finish();
		    			}
		    		});
		            // after you've done all your manipulation, return your layout to be shown
		            return mLinearLayout;

			    }


				private TextView findViewById(int textview1) {
					// TODO Auto-generated method stub
					return null;
				}
			    
				final Handler handler = new Handler() {
				/*	private static final int HANDLER_PLAY = 1;
					private static final int HANDLER_PAUSE = 2;
					private static final int HANDLER_ARTIST = 3;
					private static final int HANDLER_TITLE = 4;
					private static final int HANDLER_CUR_TIME = 5;
					private static final int HANDLER_TOT_TIME = 6;
					private static final int HANDLER_NOT_CONNECTED = 7;*/

					@Override
					public void handleMessage(Message msg) {
						TextView serverreturn = (TextView)getView().findViewById(R.id.returntext);
						serverreturn.setHint("Message");
							switch (msg.what) {
							case 1:
								serverreturn.setText("Return1 : " + (CharSequence) msg.obj);
								p.ParseResponse((String) msg.obj);
								break;
							case 2:
								serverreturn.setText("Return2 : " + (CharSequence) msg.obj);
								p.ParseResponse((String) msg.obj);
								break;
						
							}
						System.out.println("default: " + msg.obj);
						

						super.handleMessage(msg);
					}
					
				};	 
	}
