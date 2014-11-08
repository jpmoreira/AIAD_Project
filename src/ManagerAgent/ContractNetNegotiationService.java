package ManagerAgent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import jadex.bridge.IInternalAccess;
import jadex.bridge.SFuture;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
import jadex.bridge.service.annotation.ServiceStart;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import jadex.commons.future.IResultListener;
import Others.Bid;
import Others.Evaluator;
import Others.SealedProposal;
import Services.NegociationService;
import Services.SellingService;
import VendorAgents.VendorAgentBDI;

import java.lang.Void;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.sun.xml.internal.ws.util.CompletedFuture;

@Service
public class ContractNetNegotiationService implements NegociationService{

	@ServiceComponent
	IInternalAccess agent;
	
	@Override
	public IFuture<SealedProposal> startNegociation(Evaluator ev) {
	
		
		return bestSuitedAgent(ev);
		
		
		
	}

	
	
	private IFuture<SealedProposal> bestSuitedAgent(final Evaluator ev){
		
		
		final Future<SealedProposal> futureVendorAgent=new Future<SealedProposal>();
		
		if(ev==null){
			
			System.out.println("No evaluator set so nothing will be returned");
			futureVendorAgent.setResult(null);
			return futureVendorAgent;
			
		}

		
		IFuture<Collection<SellingService>> fut=agent.getServiceContainer().getRequiredServices("SellingService");
		
		
		fut.addResultListener(new IResultListener<Collection<SellingService>>() {

			@Override
			public void exceptionOccurred(Exception arg0) {
				
				System.out.println("found an error: "+arg0);
			}

			@Override
			public void resultAvailable(Collection<SellingService> arg0) {
				
				System.out.println("result available");
				ArrayList<Bid> bids=new ArrayList<Bid>();
				Iterator<SellingService> it=arg0.iterator();
				
				
				System.out.println("found "+arg0.size()+" suited candidates");
				while(it.hasNext()){
					bids.add(it.next().bidForQuestion(ev.getDemand()));
				}
				
				
				int pos=ev.evaluate(bids);
				SellingService[] sellingAgentsArray=(SellingService[]) arg0.toArray();
				
				
				SealedProposal p=new SealedProposal((VendorAgentBDI) sellingAgentsArray[pos],bids.get(pos));
				System.out.println("sealed proposal");
				futureVendorAgent.setResult(p);
				

			}
			
		});
		return futureVendorAgent;
	}
	
	
	@ServiceStart
	public IFuture<Void> initService(){
		
		System.out.println("ContractNet Negociation Service started");
		
		return IFuture.DONE;
	}

}
