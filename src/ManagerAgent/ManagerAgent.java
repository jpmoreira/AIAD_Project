package ManagerAgent;

import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

import java.util.Map;

import Agents.MarketAgent;
import Services.NegociationService;


@ProvidedServices(@ProvidedService(type=NegociationService.class, implementation=@Implementation(ContractNetNegotiationService.class)))
@Agent
public class ManagerAgent extends MarketAgent{
	
	Map<MarketAgent,Integer> totalNrContracts;
	Map<MarketAgent,Integer> nrContractsDone;
	

}
