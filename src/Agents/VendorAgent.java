package Agents;

import jadex.bdiv3.annotation.Belief;
import jadex.micro.annotation.Agent;

import java.util.ArrayList;

import Others.Product;


@Agent
public class VendorAgent extends MarketAgent{
	
	
	int stockCapacity;

	@Belief
	int currentStock;
	
	@Belief
	ArrayList<Integer>productionCapacity;
	
	@Belief
	ArrayList<Product> products;
	
	
	@Belief
	ArrayList<Integer> productQuantity;
	
	
	void getPrice(int quantity){
		
		
		//TODO implement it
	}

}
