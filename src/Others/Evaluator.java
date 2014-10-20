package Others;

import java.util.ArrayList;

interface Evaluator {
	
	void setDemand(Bid demand);
	Bid evaluate(ArrayList<Bid> bids);

}
