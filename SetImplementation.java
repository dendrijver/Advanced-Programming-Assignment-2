package Assignment2;


public class SetImplementation implements Set {
    private Identifier[] identifierArray; 
	private static final int MAX_ELEMENTS = 20; 
    private int numberOfElements; 
	
    SetImplementation(){
    	identifierArray = new Identifier[MAX_ELEMENTS]; 
    	numberOfElements = 0; 
    }
    
    SetImplementation(Set src) throws Exceptions{
    	identifierArray = new Identifier[MAX_ELEMENTS]; 
    	Identifier[] toCopyBack = new Identifier[MAX_ELEMENTS]; 
    	int toCopyBackElements = 0; 
    	numberOfElements = 0; 
    	int size = src.size(); 
    	for(int i = 0; i < size; i++){
    		Identifier toCopy = src.get(); 
    		src.remove(toCopy);
    		add(toCopy);
    		toCopyBack[toCopyBackElements] = toCopy; 
    		toCopyBackElements += 1; 
    	}
    	
    	for(int i = 0; i < toCopyBackElements; i++){
    		src.add(toCopyBack[i]);
    	}
    }
	
    public void init(){
    	identifierArray = new Identifier[MAX_ELEMENTS]; 
    	numberOfElements = 0; 
    }
    
    public void add(Identifier identifier) throws Exceptions {
    	if(numberOfElements >= 20){
    		Exceptions e = new Exceptions("A set may not contains more than 20 elements.");
    		throw e; 
    	} else {
    		if(!has(identifier)){
    			identifierArray[numberOfElements] = identifier; 
    			numberOfElements += 1;
    		}
    	}
    }

    public void remove(Identifier identifier){
    	for(int i = 0; i < numberOfElements; i++){
    		if(identifierArray[i].isEqualTo(identifier)){
    			for(int j = i; j < numberOfElements; j++){
    				identifierArray[i] = identifierArray[j]; 
    			}
    			numberOfElements -= 1; 
    		}
    	}
    }

    public boolean has(Identifier identifier){
    	for(int i = 0; i < numberOfElements; i++){
    		if(identifierArray[i].isEqualTo(identifier)){
    			return true; 
    		}
    	}
    	return false; 
    }
    
    public Identifier get(){
    	return identifierArray[numberOfElements - 1]; 
    }
    
    public int size(){
    	return numberOfElements; 
    }
    
    public SetImplementation union(Set set) throws Exceptions{
    	SetImplementation thisCopy = new SetImplementation(this),
    			          setCopy = new SetImplementation(set); 
    	SetImplementation newSet = new SetImplementation(); 
    	
    	while(thisCopy.size() > 0){
    		Identifier toCopy = thisCopy.get(); 
    		
    		newSet.add(toCopy);
    		thisCopy.remove(toCopy); 
    	}
    	
    	while(setCopy.size() > 0){
    		Identifier toCopy = setCopy.get(); 
    		newSet.add(toCopy);
    		setCopy.remove(toCopy); 
    	}
    	return newSet; 
    	
    }
    
    public SetImplementation difference(Set set) throws Exceptions{
    	SetImplementation thisCopy = new SetImplementation(this),
    		              setCopy = new SetImplementation(set);

    	for(int i = 0; i < set.size(); i++){ 
    		Identifier toRemove = setCopy.get(); 
    		thisCopy.remove(toRemove); 
    		setCopy.remove(toRemove);
    	}
    	
    	return thisCopy;
    }

    public SetImplementation intersection(Set set) throws Exceptions{
    	SetImplementation newSet = new SetImplementation(), 
    					  setCopy = new SetImplementation(set);
    	int size = setCopy.size(); 
    	for(int i = 0; i < size; i++){
    		Identifier toCheck = setCopy.get(); 
    		setCopy.remove(toCheck);
    		if(this.has(toCheck)){
    			newSet.add(toCheck); 
    		}
    	}
    	
    	return newSet; 
    }

    public SetImplementation symDifference(Set set) throws Exceptions{
    	SetImplementation setCopy = new SetImplementation(set),
    			          thisCopy = new SetImplementation(this),
    			          union = setCopy.union(thisCopy),
    			          intersection = setCopy.intersection(thisCopy);
    	
    	return union.difference(intersection);
    }
}