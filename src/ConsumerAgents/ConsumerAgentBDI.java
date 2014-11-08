package ConsumerAgents;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.commons.future.IFuture;
import jadex.commons.future.IResultListener;
import jadex.micro.MicroAgent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TimerTask;

import Agents.MarketAgent;
import Others.CheapestBidEvaluator;
import Others.Evaluator;
import Others.QuotationQuestion;
import Others.SealedProposal;
import Others.Utilities;
import Products.Product;
import Services.NegociationService;
import VendorAgents.VendorAgentBDI;



@RequiredServices(@RequiredService(name="negociationService", type=NegociationService.class,
binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM)))
@Agent
public class ConsumerAgentBDI extends MarketAgent{

	
	final static float probabilityOfNeedingSomething=(float) 0.5;
	
	@Belief
	Product product;
	
	@Belief
	int deadline;
	
	int price=-1;

	@Belief
	int quantity;
	
	@Belief(dynamic=true)
	boolean searchingSomething=quantity>0;
	
	@Agent
	protected BDIAgent agent;
	
	static final int updateTime=1000;
	
	
	IResultListener<SealedProposal> makeTransactionResultListener=new IResultListener<SealedProposal>() {

		@Override
		public void exceptionOccurred(Exception arg0) {
			
		}

		@Override
		public void resultAvailable(SealedProposal proposal) {
			
			System.out.println("Executing proposal");
			VendorAgentBDI.executeProposal(proposal);
			
			
		}
	};
	
	
	@AgentBody
	public void executeAgent(){
		
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				updatePeriodicStuff();
				
			}
		}, 0, 1000);
	}

	protected void updatePeriodicStuff() {
		
		if(!searchingSomething){
			
			boolean b=Utilities.tossCoin(probabilityOfNeedingSomething);
			if(b){
				quantity=Utilities.randInt(1, 100);
				price=5;
				deadline=Utilities.randInt(0, 100);
				
			}
			
		}
		
		else if(deadline>0)deadline--;
		
	}
	
	@Plan(trigger=@Trigger(factchangeds="deadline"))
	protected void updateBidPrice(){
		price+=(int) (price*(100.-deadline)/100.);
	}

	@Plan(trigger=@Trigger(factchangeds="deadline"))
	public void simpleNegociationPlan(ChangeEvent changeEV){
		
		Evaluator ev=new CheapestBidEvaluator();
		ev.setDemand(new QuotationQuestion(product,quantity,deadline,price));
		startNegociation(ev);
	}
	
	
	protected void startNegociation(final Evaluator ev) {
		
		IFuture<Collection<NegociationService>> fut=agent.getServiceContainer().getRequiredServices("negociationService");
		
	
		fut.addResultListener(new IResultListener<Collection<NegociationService>>() {

			@Override
			public void exceptionOccurred(Exception arg0) {
				
				
			}

			@Override
			public void resultAvailable(Collection<NegociationService> arg0) {
			
				Iterator<NegociationService> it=arg0.iterator();
				
				IFuture<SealedProposal> fut=it.next().startNegociation(ev);
				
				fut.addResultListener(makeTransactionResultListener);
				
			}
			
		});
	}
	
	

	
}
