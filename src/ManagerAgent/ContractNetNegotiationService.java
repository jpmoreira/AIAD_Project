package ManagerAgent;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.annotation.Service;
import jadex.bridge.service.annotation.ServiceComponent;
import jadex.bridge.service.annotation.ServiceStart;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import Others.Evaluator;
import Services.NegociationService;

import java.lang.Void;

@Service
public class ContractNetNegotiationService implements NegociationService{

	@ServiceComponent
	IInternalAccess agent;
	
	@Override
	public void startNegociation(Evaluator ev) {
		
		
		System.out.println(agent.getComponentIdentifier().getLocalName()+" was asked to start negotiating");
		// TODO Auto-generated method stub
		
	}
	
	@ServiceStart
	public IFuture<Void> initService(){
		
		System.out.println("ContractNet Negociation Service started");
		
		return IFuture.DONE;
	}

}
