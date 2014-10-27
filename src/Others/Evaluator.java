package Others;

import java.util.ArrayList;

public interface Evaluator {
	
	void setDemand(Bid demand);
	Bid evaluate(ArrayList<Bid> bids);

}
