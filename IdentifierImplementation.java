package Assignment2;

public class IdentifierImplementation implements Identifier {
	private String string; 

	public IdentifierImplementation(String s){
		string = s; 
	}
	
	public void init(String s){
		 string = s; 
	}
 
	public boolean isEqualTo(Identifier identifier){
		if(identifier.toString().equals(this.toString())){
			return true;
		} else {
			return false; 
		}
	}
     
	public String toString(){
		return string; 
	}
}
