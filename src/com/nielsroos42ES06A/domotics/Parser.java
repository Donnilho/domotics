package com.nielsroos42ES06A.domotics;
import com.thetransactioncompany.jsonrpc2.*;
import java.util.*;
public class Parser {
	int ID;

	public String ParsRequest(String cmd, List<Object>params){
	JSONRPC2Request ReqOut = new JSONRPC2Request(cmd, params, ID);
	String jsons = ReqOut.toJSONString();
	System.out.println("J's Son : "+jsons);
	ID++;
	return jsons;
	}
	
	public String ParsAnswer(){
		String jsons = "test";
		return jsons;
	}
	
	public void ParseResponse(String jsonString){
	// Parse response string
		JSONRPC2Response respIn = null;
	
		try {
			respIn = JSONRPC2Response.parse(jsonString);
		} catch (JSONRPC2ParseException e) {
			System.out.println(e.getMessage());
			// Handle exception...
		}
	
	
		// Check for success or error
		if (respIn.indicatesSuccess()) {
	
			System.out.println("The request succeeded :");
	
			System.out.println("\tresult : " + respIn.getResult());
			System.out.println("\tid     : " + respIn.getID());
		}
		else {
			System.out.println("The request failed :");
	
			JSONRPC2Error err = respIn.getError();
	
			System.out.println("\terror.code    : " + err.getCode());
			System.out.println("\terror.message : " + err.getMessage());
			System.out.println("\terror.data    : " + err.getData());
		}
	}
	
}
