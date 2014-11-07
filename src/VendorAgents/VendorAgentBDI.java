package VendorAgents;

import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

import java.util.ArrayList;
import java.util.TimerTask;

import Agents.MarketAgent;
import Others.Bid;
import Others.QuotationQuestion;
import Others.SealedProposal;
import Others.Utilities;
import Products.Product;
import Services.*;

@ProvidedServices(@ProvidedService(type=SellingService.class, implementation=@Implementation(SimpleSellingService.class)))
@Agent
public class VendorAgentBDI extends MarketAgent{
	
	
	@Agent
	protected BDIAgent agent;
	
	@Belief
	int stockCapacity;

	@Belief
	int currentStock=0;
	
	@Belief
	int productionCapacity=0;
	
	@Belief
	Product product;
	
	int price=0;
	int basePrice=0;
	
	
	@Belief
	int productQuantity=0;
	
	
	@AgentBody
	public void executeAgent(){
		
		//FIXME set it as a configurable parameter
		stockCapacity=Utilities.randInt(0, 100);
	
		
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				updatePeriodicParameters();
				
			}
		}, 0, 1000);
		
		
	}
	
	
	private void updatePeriodicParameters() {
		
		
		if(currentStock==stockCapacity){
		
			System.out.println("out");
			return;
			
			
		}
		int production=productionCapacity;
		
		if(stockCapacity-currentStock<production){
			production=stockCapacity-currentStock;
		}
		productQuantity+=production;
		currentStock+=production;
		
		
		
		
	}
	
	
	@Plan(trigger=@Trigger(factchangeds="productQuantity"))
	public void checkPricesPlan(ChangeEvent ev){
		
		float delta=0.5f-currentStock/(float)stockCapacity;
		price=(int) (basePrice+Math.pow(delta, 3)*4*basePrice);
		
		
	}

	
	
	Bid bidForQuestion(QuotationQuestion q){
		
		System.out.print("was asked to place a bid");
		if(q==null || q.getProduct()!=product || q.getQuantity()>currentStock)return null;
		
		
		

		return new Bid(product,q.getQuantity(), 0,price);
	}
	
	
	
	public static void executeProposal(SealedProposal p){
		
		//TODO implement it
		
	}

}
