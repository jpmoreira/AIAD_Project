package Agents;
import jadex.bdiv3.annotation.Belief;
import jadex.micro.annotation.Agent;

import java.util.ArrayList;

import Others.Product;


@Agent
public class ConsumerAgent extends MarketAgent{

	@Belief
	ArrayList<Product> products;
	
	@Belief
	ArrayList<Integer> necessity;
	
}
