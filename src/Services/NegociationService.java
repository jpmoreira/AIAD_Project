package Services;

import jadex.commons.future.IFuture;

import Others.Evaluator;
import Others.SealedProposal;


public interface NegociationService {
	
	IFuture<SealedProposal> startNegociation(Evaluator ev);

}
