package ConsumerAgents;

import jadex.bdiv3.annotation.*;
import jadex.micro.MicroAgent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import Others.CheapestBidEvaluator;
import Others.Evaluator;
import Others.QuotationQuestion;
import Products.Banana;

@Agent
public class MonkeyAgentBDI extends ConsumerAgentBDI {
	
	
	public MonkeyAgentBDI(){
	
		product=Banana.product();
		
	}


}
