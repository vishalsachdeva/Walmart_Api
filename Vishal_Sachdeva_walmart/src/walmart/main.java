// Vishal Sachdeva
// Walmart Coding Assignment


package walmart;
import java.util.*;
import java.util.Map.Entry;

import java.io.IOException;

import java.net.HttpURLConnection;

import java.net.URL;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder	;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.SAXException;







class main{
	
	
	
	
	
	
	
	// It will connect to API get xml respponse and convert it to Document type
	// Every method will call this method to get document response
	 Document Document_Return(String api_address) {
		Document doc_response = null;
		try {
			URL address_url = new URL(api_address);
			HttpURLConnection address_conn = (HttpURLConnection) address_url.openConnection();
			address_conn.setRequestMethod("GET");
			address_conn.setRequestProperty("Accept", "");
			if (address_conn.getResponseCode() != 200) {
				
				return doc_response;          
			}
			DocumentBuilderFactory Document_Factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder Document_Builder = Document_Factory.newDocumentBuilder();
			try{
				Document_Builder.setErrorHandler(null);
				doc_response = Document_Builder.parse(address_conn.getInputStream());
			} catch(SAXException e){
				return doc_response;
			}
			address_conn.disconnect();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return doc_response;
	}

	
	
	
	
	// First api, it will be passed with product name and this gives product id in return

	 public String  product_seach_api(String Product_name){
		String api_address = "http://api.walmartlabs.com/v1/search?query="+Product_name+"&3F&format=xml&apiKey=nbyuqp9he6sxnvy4efabxhth";
		
		Document doc_response= Document_Return(api_address);
		String product_Id = "";
		
		int results = Integer.parseInt(doc_response.getElementsByTagName("totalResults").item(0).getTextContent());
		if(results > 0){
			product_Id = doc_response.getElementsByTagName("itemId").item(0).getTextContent();
			System.out.println("Product id of product searched for: "+product_Id);
			
		}
		else
		{
			return product_Id;
		}
		if(product_Id.equals(""))
		{
			System.out.println("No Product Id found, search again");
		}else{
			
			recommendations_search_api(product_Id);	
		}
			
		return product_Id;
	}
	
	
	// Now we have product Id, we will pass that too recommendations methods and will get recommended products for particular prodcut id
	
	public ArrayList recommendations_search_api(String product_Id){
		String api_address = " http://api.walmartlabs.com/v1/nbp?apiKey=nbyuqp9he6sxnvy4efabxhth&itemId="+product_Id+"&3F&format=xml";
		ArrayList<String> Recommended_products= new ArrayList<String>();
		try {
		Document doc_response= Document_Return(api_address);
		if(doc_response == null) return null;
		
		
		for(int i=0;i<=9;i++)
		{
			
			Recommended_products.add(doc_response.getElementsByTagName("itemId").item(i).getTextContent());
		}
		
		if(Recommended_products.size()==0)
		{
			System.out.println("Couldn't find any recommendations, try for another product ");
		}
		else{
			review_search_api(Recommended_products );
		}
		}
		catch(Exception e){
			System.out.println("No Recomendations");
			
			return null;
		}
		
		return Recommended_products;
		
	}
	
	
	
	// Now we have recommendations, we will get reviews for each recommendation
	
	public String review_search_api(ArrayList Recommended_products )
	{
		
		HashMap<String, String> hmp= new HashMap <String, String>();
		
		
		
		for(int i=0;i<Recommended_products.size();i++)
		{
			String review_prod= (String) Recommended_products.get(i);
			
			String api_address ="http://api.walmartlabs.com/v1/reviews/"+review_prod+"?apiKey=nbyuqp9he6sxnvy4efabxhth&format=xml";
			
			
			
			Document doc_response= Document_Return(api_address);
			
			if(doc_response==null)
			{
				return null;
			}
			
			String prod="";
		try {	
		 prod= doc_response.getElementsByTagName("averageOverallRating").item(0).getTextContent();
		
		 hmp.put(review_prod,prod);
		 
		}
		catch(Exception e){
			
			//System.out.println("no record found");
			
			continue;
		}
			
		}
		System.out.println();
		System.out.println("Product Id with the ratings in sorted order: ");
		System.out.println(entriesSortedByValues(hmp));
	//for junit I want the return statement to be string format 
		
		
		ArrayList<Entry<String, String>> hmp1=entriesSortedByValues(hmp);
        String s1="";


		for(int j=0;j<hmp1.size();j++)
		{
			s1+=  hmp1.get(j);
		}
		
		return s1;
		
		
}		  
		
	
	 static <K,V extends Comparable<? super V>> 
	    ArrayList<Entry<K, V>> entriesSortedByValues(Map<K,V> hmp) {

	ArrayList<Entry<K, V>> sortedEntries = new ArrayList<Entry<K,V>>(hmp.entrySet());
   
	Collections.sort(sortedEntries, 
	    new Comparator<Entry<K,V>>() {
	        @Override
	        public int compare(Entry<K,V> e1, Entry<K,V> e2) {
	            return e2.getValue().compareTo(e1.getValue());
	        }
	    }
	);

	return sortedEntries;
	 }
	
	
	
// Main Method 
	 // First call to api will be made through this
	
	public static void main(String args[]) throws IOException
	{
	System.out.println("Enter the product name");
	Scanner input= new Scanner(System.in);
	String Product_name= input.next();
	
	if(input.equals("")){
		System.out.println("Please enter valid product name");
	}
	else
	{
	main obj1= new main();
    obj1.product_seach_api(Product_name);
	}	
	

	
		}
	
}