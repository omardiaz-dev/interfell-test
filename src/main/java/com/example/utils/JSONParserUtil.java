package com.example.utils;

import java.io.IOException;
import java.util.ArrayList;


import com.example.pojos.BackbasePOJO;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONParserUtil {
	
	public static final String TRANSACTION_TYPE_EMPTY ="EMPTY";
	public static final String TRANSACTION_TYPE_ALL ="ALL";
	public static final String TRANSACTION_TYPE_PAYMENT = "SANDBOX-PAYMENT";
	public static final String TRANSACTION_TYPE_TAN = "SANDBOX_TAN";
	
	public static ArrayList<BackbasePOJO> fromJsonToBackbaseArray(String json) throws JsonParseException , JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<BackbasePOJO> bbObjArray = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, BackbasePOJO.class));	
		return bbObjArray;
	}
	
	/**
	 * Method to parse the json and prepare the final html table
	 * @param json Read from the service
	 * @param transactionType TAN,PAYMENT OR NULL
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String getTransactions(String json, String transactionType)throws JsonParseException , JsonMappingException, IOException{
		Double totalAmount = 0.0;
		Double transactionAmount =0.0;
		String amount ="";
		String formatedTable = "<table style='width:70%'><tr><th>Id</th><th>Type</th><th>Amount</th></tr>";
		 
		
		String localTransactiontype = "";
		transactionType = transactionType.toUpperCase();
		
		ArrayList<BackbasePOJO> bbObjArray = fromJsonToBackbaseArray(json);
		for(int x=0;x<bbObjArray.size();x++) {
			  if(bbObjArray.get(x).getDetails().getType()!=null){
				  localTransactiontype = bbObjArray.get(x).getDetails().getType().toUpperCase();
		      }else{
		    	  localTransactiontype = TRANSACTION_TYPE_EMPTY;
		      }
			  
			  if(bbObjArray.get(x).getDetails().getValue()!=null){
				  amount = bbObjArray.get(x).getDetails().getValue().getAmount().toString();
				  transactionAmount = Double.parseDouble(amount);
		      }else{
		    	  transactionAmount = 0.0;
		      }
			  
			  if(transactionType.equals(TRANSACTION_TYPE_ALL)){
				  if(localTransactiontype!=null){
					  formatedTable +=getHTMLRow(getHTMLTDElement(bbObjArray.get(x).getId()) + getHTMLTDElement(localTransactiontype) + getHTMLTDElement(amount));
					  totalAmount = Double.sum(totalAmount,transactionAmount);
				  }else{
					  if(transactionType.equals(TRANSACTION_TYPE_EMPTY)){
						  formatedTable +=getHTMLRow(getHTMLTDElement(bbObjArray.get(x).getId()) + getHTMLTDElement(TRANSACTION_TYPE_EMPTY) + getHTMLTDElement(amount));
						  totalAmount = Double.sum(totalAmount,transactionAmount);
					  }
				  }
			  }else if(transactionType.equals(TRANSACTION_TYPE_EMPTY)){
				  if(localTransactiontype.equals(TRANSACTION_TYPE_EMPTY)){
					  formatedTable +=getHTMLRow(getHTMLTDElement(bbObjArray.get(x).getId()) + getHTMLTDElement(TRANSACTION_TYPE_EMPTY) + getHTMLTDElement(amount));
					  totalAmount = Double.sum(totalAmount,transactionAmount);
				  }
			  }else{
				  if(localTransactiontype.equals(transactionType)){
					  formatedTable +=getHTMLRow(getHTMLTDElement(bbObjArray.get(x).getId()) + getHTMLTDElement(localTransactiontype) + getHTMLTDElement(amount));
					  totalAmount = Double.sum(totalAmount,transactionAmount);
				  }
			  }
		}
		formatedTable +="<tr><td></td><td></td><td>" + totalAmount + "</td></tr>";

		formatedTable += "</table>";
		System.out.println(formatedTable);
		return formatedTable;
	}
	
	
	/**
	 * Method to parse the json and prepare the final html table based on the transaction type
	 * @param json
	 * @param transactionType
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String getTotalForTransactions(String json, String transactionType)throws JsonParseException , JsonMappingException, IOException{
		Double totalAmount = 0.0;
		Double transactionAmount =0.0;
		String amount ="";
		String formatedTable = "<table style='width:70%'><tr><th>Type</th><th>Amount</th></tr>";
		 
		String localTransactiontype = "";
		transactionType = transactionType.toUpperCase();
		
		ArrayList<BackbasePOJO> bbObjArray = fromJsonToBackbaseArray(json);
		for(int x=0;x<bbObjArray.size();x++) {
			  if(bbObjArray.get(x).getDetails().getType()!=null){
				  localTransactiontype = bbObjArray.get(x).getDetails().getType().toUpperCase();
		      }else{
		    	  localTransactiontype = TRANSACTION_TYPE_EMPTY;
		      }
			  
			  if(bbObjArray.get(x).getDetails().getValue()!=null){
				  amount = bbObjArray.get(x).getDetails().getValue().getAmount().toString();
				  transactionAmount = Double.parseDouble(amount);
		      }else{
		    	  transactionAmount = 0.0;
		      }
			  
			  if(transactionType.equals(TRANSACTION_TYPE_ALL)){
				  if(localTransactiontype!=null){
					  totalAmount = Double.sum(totalAmount,transactionAmount);
				  }else{
					  if(transactionType.equals(TRANSACTION_TYPE_EMPTY)){
						  totalAmount = Double.sum(totalAmount,transactionAmount);
					  }
				  }
			  }else if(transactionType.equals(TRANSACTION_TYPE_EMPTY)){
				  if(localTransactiontype.equals(TRANSACTION_TYPE_EMPTY)){
					  totalAmount = Double.sum(totalAmount,transactionAmount);
				  }
			  }else{
				  if(localTransactiontype.equals(transactionType)){
					  totalAmount = Double.sum(totalAmount,transactionAmount);
				  }
			  }
		}
		
		formatedTable += "<tr><td>" +transactionType+ "</td><td>" +totalAmount+ "</td></tr>" ;
		formatedTable += "</table>";
		System.out.println(formatedTable);
		return formatedTable;
	}
	
	/**
	 * Method to parse the json and prepare the final html table
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String getList(String json)throws JsonParseException , JsonMappingException, IOException{
		Double totalAmount = 0.0;
		Double transactionAmount =0.0;
		String amount ="";
		String formatedTable = "<table style='width:70%'><tr><th>Id</th><th>Type</th><th>Amount</th></tr>";
		String localTransactiontype = "";
		
		ArrayList<BackbasePOJO> bbObjArray = fromJsonToBackbaseArray(json);
		for(int x=0;x<bbObjArray.size();x++) {
			  if(bbObjArray.get(x).getDetails().getType()!=null){
				  localTransactiontype = bbObjArray.get(x).getDetails().getType().toUpperCase();
		      }else{
		    	  localTransactiontype = TRANSACTION_TYPE_EMPTY;
		      }
			  
			  if(bbObjArray.get(x).getDetails().getValue()!=null){
				  amount = bbObjArray.get(x).getDetails().getValue().getAmount().toString();
				  transactionAmount = Double.parseDouble(amount);
		      }else{
		    	  transactionAmount = 0.0;
		      }
			  
			  if(localTransactiontype!=null){
				  formatedTable +=getHTMLRow(getHTMLTDElement(bbObjArray.get(x).getId()) + getHTMLTDElement(localTransactiontype) + getHTMLTDElement(amount));
				  totalAmount = Double.sum(totalAmount,transactionAmount);
			  }else{
				  formatedTable +=getHTMLRow(getHTMLTDElement(bbObjArray.get(x).getId()) + getHTMLTDElement(TRANSACTION_TYPE_EMPTY) + getHTMLTDElement(amount));
				  totalAmount = Double.sum(totalAmount,transactionAmount);
			  }
		}
		formatedTable +="<tr><td></td><td></td><td>" + totalAmount + "</td></tr>";
		formatedTable += "</table>";
		return formatedTable;
	}
	
	
	private String getHTMLRow(String value){
		String htmlRow="";
		htmlRow = "<tr>" + value + "</tr>";
		return htmlRow;
	}
	
	private String getHTMLTDElement(String value){
		String tdElement = "";
		tdElement = "<td>" + value + "</td>";
		return tdElement;
	}


}
