package primMST;

public class Link implements Comparable<Link>{
	
	private int src;
	private int dst;
	
	public Link(){}
	public Link(int i,int j){
		this.src=i;
		this.dst=j;
	}
	public int getSrc(){
		return this.src;
	}
	public int getDst(){
		return this.dst;
	}
	
	@Override
	public String toString(){
		return "link: [ src: "+this.src+" , dst: "+this.dst+" ]";
	}
	@Override
	public int compareTo(Link l){
		if(this.src != l.getSrc()){
			return this.src-l.getDst();
		}else{
			return this.dst-l.getDst();
		}
		
	}

}
