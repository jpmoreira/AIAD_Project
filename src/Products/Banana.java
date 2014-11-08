package Products;

public class Banana extends Product {

	
	static Banana singleton=null;
	
	private Banana(){
		
		description="A super tasty banana";
		name="banana";
		id=0;
	}
	
	static public Product product(){
		
		if(singleton==null)singleton=new Banana();
		return singleton;
		
	}
	
	
}
