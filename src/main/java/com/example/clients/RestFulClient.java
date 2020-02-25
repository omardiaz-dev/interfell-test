package com.example.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import com.example.utils.JSONParserUtil;
import com.example.utils.LoggerUtil;



/**
 * Class to read the content from the main web service: (OpenBank Project) 
 * 
 * @author odiaz
 *
 */
public class RestFulClient {
	
	public static final String GET_TOTAL_AMOUNT_YES="yes";
	
	private static final String WEB_SERVICE_URI="https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions";

	/**
	 * This method was created just for testing and learning process.
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			URL url = new URL(WEB_SERVICE_URI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Method to retrieve all the transaction's list from the web service.
	 * @return
	 */
	public String getTransactionsList ()throws JsonParseException, JsonMappingException, IOException{
		JSONParserUtil converter = new JSONParserUtil();
		String backBaseContent=getServiceContent();
		backBaseContent = converter.getList(backBaseContent);
		return backBaseContent;
	}
	
	/**
	 * Method to retrieve the transactions from the web service based on the transaction type
	 * @param transactionType
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public String getTransactionsType (String transactionType, String retTotalAmount) throws JsonParseException, JsonMappingException, IOException{
		JSONParserUtil converter = new JSONParserUtil();
		String wsContent="";
		wsContent = getServiceContent();
		if(retTotalAmount.equals(GET_TOTAL_AMOUNT_YES)){
			wsContent = converter.getTotalForTransactions(wsContent,transactionType);
		}else{
			wsContent = converter.getTransactions(wsContent,transactionType);
		}
		return wsContent;
	}
	
	/**
	 * Retrieve the Stream from the web service
	 * @return
	 */
	public String getServiceContent(){
		String wsContent=null;
		try {
			URL url = new URL(WEB_SERVICE_URI);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			wsContent= readString(br);
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			LoggerUtil.logError(this.getClass().getName() + ".getServiceContent" + e.getStackTrace());
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.logError(this.getClass().getName() + ".getServiceContent" + e.getStackTrace());
		}
		return wsContent;
		
	}
	
	/**
	 * Reading the buffer string and preparing the String content
	 * @param buffIn
	 * @return
	 * @throws IOException
	 */
	private String readString(BufferedReader buffIn) throws IOException {
	    StringBuilder everything = new StringBuilder();
	    StringBuilder preparedString = null;
	    String line;
	    while( (line = buffIn.readLine()) != null) {
	       everything.append(line);
	    }
	    preparedString = everything.replace(0, 16, "");
	    preparedString = preparedString.replace(preparedString.length()-1, preparedString.length(), "");
	    return preparedString.toString();
	}
}
