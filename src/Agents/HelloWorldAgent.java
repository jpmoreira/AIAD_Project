package Agents;

import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent that says hello to the world.")
public class HelloWorldAgent {
 
	@AgentBody
	public void sayHello(){
		System.out.println("Hello word!");
	}
 
}