package com.nielsroos42ES06A.domotics;


import java.util.ArrayList;
import java.util.List;

import net.minidev.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntroActivity extends Activity {
	String gebruiker;
	String wachtwoord;
	String cmd;
	static Boolean loginreturn = true;
	static Boolean next = false;
    private Connector c = null;
    private boolean loginn = false;

  public static ArrayList<Object> localrooms = new ArrayList<Object>();
    public static ArrayList<ArrayList> modules = new ArrayList<ArrayList>();
    //public ArrayList<Object> moduleinfo = new ArrayList<Object>();
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		localrooms.clear();
	    c = new Connector(handler);
	    c.start();
        c.changeHandler(handler);
        loginn = true;
        setContentView(R.layout.activity_intro);
		
		Button login = (Button)findViewById(R.id.button1);
		TextView error = (TextView) findViewById(R.id.error);
		error.setText("");

		
    	List<Object> param = new ArrayList<Object>();
         cmd = c.ParsRequest("getAllRooms", param);
         System.out.println("getAllRooms command given");
         c.giveCommand(cmd);
		
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) { //whats going to be executed once the button is clicked
				EditText username=(EditText) findViewById(R.id.usernametext);
				EditText password=(EditText) findViewById(R.id.passwordtext);
				TextView error = (TextView) findViewById(R.id.error);

				
				gebruiker = username.getText().toString();
				wachtwoord = password.getText().toString();
				
				
				System.out.println(gebruiker);
				System.out.println(wachtwoord);
				
				//c.giveCommand("Domotics App online");
				
	            List<Object> params = new ArrayList<Object>();
	            params.add(gebruiker);
	            params.add(wachtwoord);
	           // cmd = c.ParsRequest("Login", params);
	           // c.giveCommand(cmd); //method doesnt excist yet.
	            if(loginreturn == true){	 	            	
	            	 sendMessage1(v);
		 	            /*if(next == true){
		 	            	next = false;
		 	   	            System.out.println("Switching to main");
		 	   	            sendMessage1(v);
		 	            }*/
	            }
	            else{
	            	error.setText("Gebruikersnaam of wachtwoord fout!");
	            }
			}
		});
	}
	
	public void sendMessage1(View v) {
		System.out.println("inside sendmessage");
	    MainActivity.c = c;
	    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
	    intent.putExtra("size", localrooms.size());
	  
	    for(int i = 0; i < localrooms.size() ; i++){
		   String x = String.valueOf(i);
		    intent.putExtra(x, localrooms.get(i).toString());	
		    //System.out.println("Addednn to intent: "+ localrooms.get(i).toString());
	    }
	    startActivity(intent);
	    finish();
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
					System.out.println("Intro Activity msg.what : "+msg.what);
					switch(msg.what){
					case 3:
						if(loginn == true){
							localrooms.clear();
							System.out.println("inside handlemessage intro for getallrooms");
							//MainActivity m = new MainActivity();
							System.out.println("input" + msg.arg1);
							//m.setRoomsize(msg.arg1);
							
							for(int i = 0; i < ((ArrayList<Object>) msg.obj).size(); i++){
								localrooms.add(((ArrayList<Object>) msg.obj).get(i).toString());
								//System.out.println("checkcheck "+((ArrayList<Object>) msg.obj).get(i).toString());
							}
							
							next = true;
							loginn = false;
						}
						break;
					case 36:
						AlertDialog alertDialog = new AlertDialog.Builder(IntroActivity.this).create();
						alertDialog.setTitle("Alert");
						String x = (String) msg.obj;
						alertDialog.setMessage("Not Connected to Server");
						alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
						    new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int which) {
						            dialog.dismiss();
						        }
						    });
						alertDialog.show();
						
						break;
						
					case 37:
						//return value login methode
						loginreturn = true;
						break;
						
					default : 
						loginreturn = true; //als methode er is wordt dit false
					}
					super.handleMessage(msg);
				}
				
			};	
}
