package VendorAgents;

import Products.Banana;
import jadex.micro.annotation.Agent;


@Agent
public class DummyBananaVendorAgentBDI extends VendorAgentBDI {

	
	public DummyBananaVendorAgentBDI(){
		
		
		product=Banana.product();
		productionCapacity=1;
		basePrice=50;
		
	}
}
