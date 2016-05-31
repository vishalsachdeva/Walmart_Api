// vishal sachdeva
// Test cases

package walmart;
import static org.junit.Assert.*;
import org.junit.Test.*;
import java.util.*;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Map.Entry;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
public class test {
	
	
	//testing if the rating for the particular recomendations are coming correct or not 
	
	@Test
   public void Review_Test(){
	   
	   main obj= new main();
	   
	   ArrayList<String> Sample_Recommended_products= Add_product_id();
	   String Expected_Review= "25857866=5.046649625=4.8130146246=4.7831232984=4.5642608125=4.5642608106=4.4239875894=4.1445804400=3.9744460683=3.39";
	   
	   assertEquals(obj.review_search_api(Sample_Recommended_products),Expected_Review);
	   
   }
	
	//testing if we send the empty array list to review_search method, it should send empty string 
	
	@Test
	public void Review_Test_null()
	{
		main obj = new main();
		ArrayList<String> Sample_Recommended_products_Null= new ArrayList<String>();
		String expected_output_null= "";
		assertEquals(obj.review_search_api(Sample_Recommended_products_Null),expected_output_null);
		
	}
	
	// if the product name is passed, it will give correct product id
	
	@Test
	public void Product_id_search() throws ParserConfigurationException{
		Document doc_response = Document_Sample_id();
		main obj = new main();
		main obj_test = mock(main.class);
		
		when(obj_test.Document_Return(anyString())).thenReturn(doc_response);
		
		String Product_id = obj.product_seach_api("test");
		assertEquals(Product_id, "27830677");
	}
	
	//if random product name is entered but no id is found for it
	
	@Test
	public void Product_id_Null() throws ParserConfigurationException{
		Document doc_response = Document_Sample_id_null();
		main obj = new main();
		main obj_test = mock(main.class);
		when(obj_test.Document_Return(anyString())).thenReturn(doc_response);
		
		String Product_id = obj.product_seach_api("response_test");
		assertEquals(Product_id, "");
	}
	
	
	
	
	@Test (expected = NullPointerException.class)
	public void Product_search_null() throws ParserConfigurationException{
		main obj = new main();
		main obj_test = mock(main.class);
		when(obj_test.Document_Return(anyString())).thenReturn(null);
		
		obj.product_seach_api("test input");
	}
	
	
	
	@Test
	public void Recommendations_search_null(){
		main obj = new main();
		main obj_test = mock(main.class);
		when(obj_test.Document_Return(anyString())).thenReturn(null);
		assertEquals(obj.recommendations_search_api("12345"),null);
	}
	
	
	@Test (expected = NullPointerException.class)
	public void Review_Search_null() throws ParserConfigurationException{
		main obj = new main();
		main obj_test = mock(main.class);
		when(obj_test.Document_Return(anyString())).thenReturn(null);
		
		obj.review_search_api(null);
	}
	
	
	
   
   
   // 
   
   public  ArrayList<String> Add_product_id(){
	   ArrayList<String> Sample_Recommended_products= new ArrayList<String>();
	   Sample_Recommended_products.add("46649625");
	   Sample_Recommended_products.add("30146246");
	   Sample_Recommended_products.add("31232984");
	   Sample_Recommended_products.add("42608125");
	   Sample_Recommended_products.add("42608106");
	   Sample_Recommended_products.add("39875894");
	   Sample_Recommended_products.add("25857866");
	   Sample_Recommended_products.add("45804400");
	   Sample_Recommended_products.add("44460683");
	return Sample_Recommended_products;
	   
   }
   
   private Document Document_Sample_id() throws ParserConfigurationException {
		DocumentBuilderFactory Document_Factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder Document_Builder = Document_Factory.newDocumentBuilder();
		Document doc_response = Document_Builder.newDocument();
		Element rootElement = doc_response.createElement("searchresponse");
		doc_response.appendChild(rootElement);
		Element totalResultsElement = doc_response.createElement("totalResults");
		rootElement.appendChild(totalResultsElement);
		totalResultsElement.insertBefore(doc_response.createTextNode("1"), totalResultsElement.getLastChild());
		Element itemsElement = doc_response.createElement("items");
		rootElement.appendChild(itemsElement);
		Element itemElement = doc_response.createElement("item");
		itemsElement.appendChild(itemElement);
		Element itemIdElement = doc_response.createElement("itemId");
		itemElement.appendChild(itemIdElement);
		itemIdElement.insertBefore(doc_response.createTextNode("12345"), itemIdElement.getLastChild());
		return doc_response;
	}
   
   private Document Document_Sample_id_null() throws ParserConfigurationException {
		DocumentBuilderFactory Document_Factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder Document_Builder = Document_Factory.newDocumentBuilder();
		Document doc_response = Document_Builder.newDocument();
		Element rootElement = doc_response.createElement("searchresponse");
		doc_response.appendChild(rootElement);
		Element totalResultsElement = doc_response.createElement("totalResults");
		rootElement.appendChild(totalResultsElement);
		totalResultsElement.insertBefore(doc_response.createTextNode("0"), totalResultsElement.getLastChild());
		return doc_response;
	}
   
 
   
   
   
   
}