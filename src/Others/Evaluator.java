package Others;

import java.util.ArrayList;

public interface Evaluator {
	
	
	void setDemand(QuotationQuestion question);
	QuotationQuestion getDemand();
	int evaluate(ArrayList<Bid> bids);

}
