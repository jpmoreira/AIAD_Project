package Others;

import java.util.ArrayList;

public class CheapestBidEvaluator implements Evaluator{

	
	QuotationQuestion q=null;
	@Override
	public void setDemand(QuotationQuestion question) {
		q=question;
		
	}

	@Override
	public QuotationQuestion getDemand() {
		return q;
	}

	@Override
	public int evaluate(ArrayList<Bid> bids) {
		Bid bestBidSoFar=null;
		int bestBidSoFarPos=-1;
		
		for(int i=0;i<bids.size();i++){
			Bid bid=bids.get(i);
			
			//if is a different product or a if is an insufficient quantity or a too high price go on
			if(bid.getProduct()!=q.getProduct())continue;
			if(bid.getQuantity()<q.getQuantity())continue;
			if(bid.getUnitaryPrice()>q.unitaryPrice)continue;
			
			if(bestBidSoFar==null ){//if we havnt got a good one pick the first suitable one
				
				bestBidSoFar=bid;
				bestBidSoFarPos=i;
			}
			
			else if(bestBidSoFar.getUnitaryPrice()>bid.getUnitaryPrice()){
			
				bestBidSoFar=bid;
				bestBidSoFarPos=i;
				
			}
			
		}
		
		return bestBidSoFarPos;
		
		
	}
}
