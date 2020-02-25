package com.example.training.controllers;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.clients.RestFulClient;
import com.example.pojos.BackbasePOJO;
import com.example.utils.JSONParserUtil;
import com.example.utils.TimeConverter;

@Controller
public class MainController {

	//private static final String WEB_SERVICE_URI="https://dev.tuten.cl:443/TutenREST/rest/user/testapis%40tuten.cl";
	//I used this URL because I wasn't able to get a valid response from the above one. This will mimic the user's API
	private static final String WEB_SERVICE_URI="https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions";
	
		@RequestMapping("/")
		public String index() {
			return "signin.html";
		}
		
		
	
		@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	    public String search(Model model,@RequestParam(defaultValue = "") String username,@RequestParam(defaultValue = "") String password) throws IOException {
		
		String returnPage = "";
		String localTransactiontype = "";
		String output;
		RestFulClient restClient = new RestFulClient();
		
		try {
			
			if(username.equals("omardiazdiaz@gmail.com") && password.equals("1234")) {
				returnPage = "hello.html";
				URL url = new URL(WEB_SERVICE_URI);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}
							
				output = restClient.getServiceContent();
				
				ArrayList<BackbasePOJO> bbObjArray = JSONParserUtil.fromJsonToBackbaseArray(output);
				for(int x=0;x<bbObjArray.size();x++) {
					  localTransactiontype = bbObjArray.get(x).getDetails().getType().toUpperCase();
				}
			}else {
				returnPage = "notallowed.html";
			}
			
			
			
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			System.out.println("testing authenticate");
			return returnPage;
	   }
		
		
		
		
		@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	    public String getTransactions(Model model,@RequestParam(defaultValue = "") String username,@RequestParam(defaultValue = "") String password) throws IOException {
		
		String returnPage = "";
		String localTransactiontype = "";
		String output;
		RestFulClient restClient = new RestFulClient();
		
		
		try {
									
			output = restClient.getServiceContent();
			
			ArrayList<BackbasePOJO> bbObjArray = JSONParserUtil.fromJsonToBackbaseArray(output);
			for(int x=0;x<bbObjArray.size();x++) {
				  localTransactiontype = bbObjArray.get(x).getDetails().getType().toUpperCase();
			}
			
			model.addAttribute("transactions", bbObjArray);
			
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			System.out.println("testing authenticate");
			return "transactionslist.html";
	   }
		
		
		@RequestMapping(value = "/utc", method = RequestMethod.GET, produces = "application/json")
		@ResponseBody
	    public String getUTCTime(Model model,@RequestParam(defaultValue = "") String time,@RequestParam(defaultValue = "") String zone) throws IOException {
		
		String returnPage =TimeConverter.getTimeInJSON(zone,time) ;
		
		
		System.out.println("testing authenticate");
		return returnPage;
	   }
	
	
	
}
