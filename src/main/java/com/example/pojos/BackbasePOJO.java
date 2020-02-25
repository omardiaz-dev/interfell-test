package com.example.pojos;


import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.example.utils.JSONParserUtil;

public class BackbasePOJO {
	 private String id;
	 This_account This_accountObject;
	 Other_account Other_accountObject;
	 Details DetailsObject;
	 Metadata MetadataObject;
	 
	 
	 // Getter Methods 
	 
	 public String getId() {
	 return id;
	 }

	 public This_account getThis_account() {
	 return This_accountObject;
	 }

	 public Other_account getOther_account() {
	 return Other_accountObject;
	 }

	 public Details getDetails() {
	 return DetailsObject;
	 }

	 public Metadata getMetadata() {
	 return MetadataObject;
	 }
	 
	 public String getHTMLRow(){
		 String htmlRow="";
		 String amount;
		 String type ="";
		 if (this.getDetails().getValue().getAmount()==null){
			 amount ="0.0";
		 }else{
			 amount = this.getDetails().getValue().getAmount();
		 }
		 
		 if( this.getDetails().getType()==null ){
			type = JSONParserUtil.TRANSACTION_TYPE_EMPTY;
		 }else{
			 type =  this.getDetails().getType().toUpperCase();
		 }
		 
		 htmlRow = "<tr><td>" + this.getId() + "</td><td>" + type + "</td><td>" +  amount  +"</tr>";
		 return htmlRow;
	 }
	
	 
	// Setter Methods 

	 public void setId( String id ) {
	 this.id = id;
	 }

	 public void setThis_account( This_account this_accountObject ) {
	 this.This_accountObject = this_accountObject;
	 }

	 public void setOther_account( Other_account other_accountObject ) {
	 this.Other_accountObject = other_accountObject;
	 }

	 public void setDetails( Details detailsObject ) {
	 this.DetailsObject = detailsObject;
	 }

	 public void setMetadata( Metadata metadataObject ) {
	 this.MetadataObject = metadataObject;
	 }
		 
		
	/**
	 * Main Node of the JSON Backbase
	 * @author odiaz
	 *
	 */
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 private static class Metadata {
		  private String narrative = null;
		  ArrayList<Object> comments = new ArrayList<Object>();
		  ArrayList<Object> tags = new ArrayList<Object>();
		  ArrayList<Object> images = new ArrayList<Object>();
		  private String where = null;


		 // Getter Methods 

		  public String getNarrative() {
		    return narrative;
		  }

		  public String getWhere() {
		    return where;
		  }

		 // Setter Methods 

		  public void setNarrative( String narrative ) {
		    this.narrative = narrative;
		  }

		  public void setWhere( String where ) {
		    this.where = where;
		  }
		}
	 
	 /**
	 	 * Inner Class
	 	 * @author odiaz
	 	 *
	 	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	 public static class Details {
		  private String type;
		  private String description;
		  private String posted;
		  private String completed;
		  New_balance New_balanceObject;
		  Value ValueObject;


		 // Getter Methods 

		  public String getType() {
		    return type;
		  }

		  public String getDescription() {
		    return description;
		  }

		  public String getPosted() {
		    return posted;
		  }

		  public String getCompleted() {
		    return completed;
		  }

		  public New_balance getNew_balance() {
		    return New_balanceObject;
		  }

		  public Value getValue() {
		    return ValueObject;
		  }

		 // Setter Methods 

		  public void setType( String type ) {
			if(type==null){
				type=JSONParserUtil.TRANSACTION_TYPE_EMPTY;
			}
		    this.type = type;
		  }

		  public void setDescription( String description ) {
		    this.description = description;
		  }

		  public void setPosted( String posted ) {
		    this.posted = posted;
		  }

		  public void setCompleted( String completed ) {
		    this.completed = completed;
		  }

		  public void setNew_balance( New_balance new_balanceObject ) {
		    this.New_balanceObject = new_balanceObject;
		  }

		  public void setValue( Value valueObject ) {
		    this.ValueObject = valueObject;
		  }
		}
	 
	/**
	 * Inner Class
	 * @author odiaz
	 *
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	 public static class Value {
		  private String currency;
		  private String amount;


		 // Getter Methods 

		  public String getCurrency() {
		    return currency;
		  }

		  public String getAmount() {
		    return amount;
		  }

		 // Setter Methods 

		  public void setCurrency( String currency ) {
		    this.currency = currency;
		  }

		  public void setAmount( String amount ) {
			if (amount == null) {
				amount = "0.0";
			}
			
		    this.amount = amount;
		  }
		}
	 
	/**
	 * Inner Class
	 * @author odiaz
	 *
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	 private static class New_balance {
		  private String currency;
		  private String amount = null;


		 // Getter Methods 

		  public String getCurrency() {
		    return currency;
		  }

		  public String getAmount() {
		    return amount;
		  }

		 // Setter Methods 

		  public void setCurrency( String currency ) {
		    this.currency = currency;
		  }

		  public void setAmount( String amount ) {
		    this.amount = amount;
		  }
		}
	 
	/**
	 * Inner Class
	 * @author odiaz
	 *
	 */
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public class Other_account {
		  private String id;
		  Holder HolderObject;
		  private String number;
		  private String kind = null;
		  private String IBAN = null;
		  private String swift_bic = null;
		  Bank BankObject;
		  Metadata MetadataObject;


		 // Getter Methods 

		  public String getId() {
		    return id;
		  }

		  public Holder getHolder() {
		    return HolderObject;
		  }

		  public String getNumber() {
		    return number;
		  }

		  public String getKind() {
		    return kind;
		  }

		  public String getIBAN() {
		    return IBAN;
		  }

		  public String getSwift_bic() {
		    return swift_bic;
		  }

		  public Bank getBank() {
		    return BankObject;
		  }

		  public Metadata getMetadata() {
		    return MetadataObject;
		  }

		 // Setter Methods 

		  public void setId( String id ) {
		    this.id = id;
		  }

		  public void setHolder( Holder holderObject ) {
		    this.HolderObject = holderObject;
		  }

		  public void setNumber( String number ) {
		    this.number = number;
		  }

		  public void setKind( String kind ) {
		    this.kind = kind;
		  }

		  public void setIBAN( String IBAN ) {
		    this.IBAN = IBAN;
		  }

		  public void setSwift_bic( String swift_bic ) {
		    this.swift_bic = swift_bic;
		  }

		  public void setBank( Bank bankObject ) {
		    this.BankObject = bankObject;
		  }

		  public void setMetadata( Metadata metadataObject ) {
		    this.MetadataObject = metadataObject;
		  }
		}

	 /**
	 	 * Inner Class
	 	 * @author odiaz
	 	 *
	 	 */
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 private static class Bank {
		 private String national_identifier;
		 private String name;
		
		 // Getter Methods 
		 
		public String getNational_identifier() {
			return national_identifier;
		}
	
		public String getName() {
			return name;
		}
	
		// Setter Methods 
	
		public void setNational_identifier( String national_identifier ) {
			this.national_identifier = national_identifier;
		}
	
		public void setName( String name ) {
			this.name = name;
		}
	}
	 
	 
	 	/**
	 	 * Inner Class
	 	 * @author odiaz
	 	 *
	 	 */
	 	@JsonIgnoreProperties(ignoreUnknown = true)
		private static class Holder {
		  private String name;
		  private boolean is_alias;


		 // Getter Methods 

		  public String getName() {
		    return name;
		  }

		  public boolean getIs_alias() {
		    return is_alias;
		  }

		 // Setter Methods 

		  public void setName( String name ) {
		    this.name = name;
		  }

		  public void setIs_alias( boolean is_alias ) {
		    this.is_alias = is_alias;
		  }
		}
		
	 	/**
	 	 * Inner Class
	 	 * @author odiaz
	 	 *
	 	 */
		@JsonIgnoreProperties(ignoreUnknown = true)
		public class This_account {
		  private String id;
		  ArrayList<Object> holders = new ArrayList<Object>();
		  private String number;
		  private String kind;
		  private String IBAN = null;
		  private String swift_bic = null;
		  Bank BankObject;


		 // Getter Methods 

		  public String getId() {
		    return id;
		  }

		  public String getNumber() {
		    return number;
		  }

		  public String getKind() {
		    return kind;
		  }

		  public String getIBAN() {
		    return IBAN;
		  }

		  public String getSwift_bic() {
		    return swift_bic;
		  }

		  public Bank getBank() {
		    return BankObject;
		  }

		 // Setter Methods 

		  public void setId( String id ) {
		    this.id = id;
		  }

		  public void setNumber( String number ) {
		    this.number = number;
		  }

		  public void setKind( String kind ) {
		    this.kind = kind;
		  }

		  public void setIBAN( String IBAN ) {
		    this.IBAN = IBAN;
		  }

		  public void setSwift_bic( String swift_bic ) {
		    this.swift_bic = swift_bic;
		  }

		  public void setBank( Bank bankObject ) {
		    this.BankObject = bankObject;
		  }
		}

		
		

	 


}
