package Agents;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.commons.future.DefaultResultListener;
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

import Others.Product;
import Services.NegociationService;


//public <T> IFuture<T> searchService(Class<T> type, String scope);


@RequiredServices(@RequiredService(name="negociationService", type=NegociationService.class,
binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM)))
@Agent
public class ConsumerAgent extends MarketAgent{

	
	@Belief
	ArrayList<Product> products;
	
	@Belief
	ArrayList<Integer> necessity;
	
	@Agent
	MicroAgent agent;
	
	
	@AgentBody
	public void executeAgent(){
		IFuture<Collection<NegociationService>> fut=agent.getServiceContainer().getRequiredServices("negociationService");
		
	
		fut.addResultListener(new IResultListener<Collection<NegociationService>>() {

			@Override
			public void exceptionOccurred(Exception arg0) {
				
				
			}

			@Override
			public void resultAvailable(Collection<NegociationService> arg0) {
			
				Iterator<NegociationService> it=arg0.iterator();
				
				it.next().startNegociation(null);
				
			}
			
		});
		
	}
	
}
