package Services;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
import Others.Bid;
import Others.QuotationQuestion;
import VendorAgents.VendorAgentBDI;

@Service
public class SimpleSellingService implements SellingService {

	@ServiceComponent
	IInternalAccess agent;


	@Override
	public Bid bidForQuestion(QuotationQuestion question) {
		// TODO Auto-generated method stub
		return null;
	}

}
