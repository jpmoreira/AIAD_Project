package Others;

import VendorAgents.VendorAgentBDI;

public class SealedProposal {
	
	
	public VendorAgentBDI vendor;
	public Bid bid;
	
	
	public SealedProposal(VendorAgentBDI ag,Bid b) {
		vendor=ag;
		bid=b;
	}

}
