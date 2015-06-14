package primMST;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;




public class PrimAlogrithm {
	/**
	 *  Prim最小生成树生成核心算法，
	 * @param switchLinks 整个拓扑的链路信息
	 * @param linkCost 所有的链路的权重信息
	 * @return  最小生成树算法生成的拓扑的链路信息
	 */
	public Map<Integer,Set<Link>> primMST(Map<Integer,Set<Link>> switchLinks,Map<Link,Integer> linkCost){
		
		
		//对最小生成树生成的拓扑进行初始化，最初始时整个只存在节点，而不再存在链路；
		Map<Integer,Set<Link>> switchLinksMSTMap=new HashMap<Integer,Set<Link>>();
		Set<Integer> keySet=switchLinks.keySet();
		Iterator<Integer> iterator=keySet.iterator();
		while(iterator.hasNext()){
			Set<Link> links=new HashSet<Link>();
			switchLinksMSTMap.put(iterator.next(), links);
		}
		
		//整个拓扑中交换机的个数
		int length=keySet.size();
		//prim算法所需要的一些变量
		int[] value=new int[length];
		boolean[] visited=new boolean[length];
		int[] parent=new int[length];
		
		//将网络中的最小生成树的链路加入下面的Map中；
		//Map<Integer,Set<Link>> switchLinksMST=new HashMap<Integer,Set<Link>>();
		for(int i=0;i<9;i++){
			value[i]=Integer.MAX_VALUE;
			visited[i]=false;
		}
		//选定dpid为0的交换机为根节点
		//根据网络的流量信息，可以选择根节点为流量最大的节点，需要了解MST中根节点的作用；选择依据；
		value[0]=0;           
		parent[0]=-1;
		
		for(int i=0;i<length-1;i++){
			
			int switchId=selectSwitch(value,visited);
			visited[switchId]=true;
			
			Set<Link> links=switchLinks.get(switchId);
			Iterator<Link> iterator2=links.iterator();
			
			while(iterator2.hasNext()){
				Link link=iterator2.next();
				if(!visited[link.getDst()] && linkCost.get(link)<value[link.getDst()]){
					value[link.getDst()]=linkCost.get(link);
					parent[link.getDst()]=switchId;
				}
			}
		}
		
		for(int i=1;i < parent.length;i++){
			Link link=selectLink(switchLinks,parent[i],i);
			switchLinksMSTMap.get(link.getSrc()).add(link);
			Link link2=selectLink(switchLinks,i,parent[i]);
			switchLinksMSTMap.get(link2.getSrc()).add(link2);
		}
		//打印输出，非算法必须
		Set<Integer> keySet2=switchLinksMSTMap.keySet();
		Iterator<Integer> iterator3 = keySet2.iterator();
		while(iterator3.hasNext()){
			Integer id=iterator3.next();
			Set<Link> links=switchLinksMSTMap.get(id);
			Iterator<Link> iterator4=links.iterator();
			while(iterator4.hasNext()){
				System.out.println(id+" "+iterator4.next());
			}
		}
		
		return switchLinksMSTMap;
	}
	/**
	 * 选择链路权重最小的节点
	 * @param value
	 * @param visited
	 * @return
	 */
	public int selectSwitch(int[] value,boolean[] visited){
		int length=value.length;
		int min_index=0,minValue=Integer.MAX_VALUE;
		for(int i=0;i<length;i++){
			if(value[i]< minValue && !visited[i]){
				min_index=i;
				minValue=value[i];
			}
		}
		return min_index;
	}
	/**
	 * return the link base on the src and dst in switchLinks
	 * @param switchLinks
	 * @param src
	 * @param dst
	 * @return 
	 */
	public Link selectLink(Map<Integer,Set<Link>> switchLinks,int src,int dst){
		
		Set<Link> links=switchLinks.get(src);
		Iterator<Link> iterator=links.iterator();
		while(iterator.hasNext()){
			Link link=iterator.next();
			if(link.getSrc()==src && link.getDst()==dst){
				return link;
			}
		}
		return null;
	}
	public static void main(String args[]){
		//28条单向链路
		Link link1=new Link(0,1);
		Link link2=new Link(0,7);
		Link link3=new Link(1,0);
		Link link4=new Link(1,2);
		Link link5=new Link(1,7);
		Link link6=new Link(2,1);
		Link link7=new Link(2,3);
		Link link8=new Link(2,8);
		Link link24=new Link(2,5);
		Link link9=new Link(3,2);
		Link link10=new Link(3,4);
		Link link11=new Link(3,5);
		Link link12=new Link(4,3);
		Link link13=new Link(4,5);
		Link link14=new Link(5,2);
		Link link15=new Link(5,3);
		Link link28=new Link(5,4);
		Link link16=new Link(5,6);
		Link link17=new Link(6,5);
		Link link18=new Link(6,7);
		Link link19=new Link(6,8);
		Link link20=new Link(7,0);
		Link link21=new Link(7,6);
		Link link22=new Link(7,8);
		Link link23=new Link(7,1);
		Link link25=new Link(8,2);
		Link link26=new Link(8,6);
		Link link27=new Link(8,7);
		
		
		Map<Integer,Set<Link>> switchLinks=new HashMap<Integer,Set<Link>>();
		Set<Link> links0=new HashSet<Link>();
		links0.add(link1);
		links0.add(link2);
		
		Set<Link> links1=new HashSet<Link>();
		links1.add(link3);
		links1.add(link4);
		links1.add(link5);
		
		Set<Link> links2=new HashSet<Link>();
		links2.add(link6);
		links2.add(link7);
		links2.add(link8);
		links2.add(link24);
		
		Set<Link> links3=new HashSet<Link>();
		links3.add(link9);
		links3.add(link10);
		links3.add(link11);
		
		Set<Link> links4=new HashSet<Link>();
		links4.add(link12);
		links4.add(link13);
		
		Set<Link> links5=new HashSet<Link>();
		links5.add(link14);
		links5.add(link15);
		links5.add(link28);
		links5.add(link16);
		
		Set<Link> links6=new HashSet<Link>();
		links6.add(link17);
		links6.add(link18);
		links6.add(link19);
		
		Set<Link> links7=new HashSet<Link>();
		links7.add(link20);
		links7.add(link21);
		links7.add(link22);
		links7.add(link23);
		
		Set<Link> links8=new HashSet<Link>();
		links8.add(link25);
		links8.add(link26);
		links8.add(link27);
		
		switchLinks.put(0,links0);
		switchLinks.put(1,links1);
		switchLinks.put(2,links2);
		switchLinks.put(3,links3);
		switchLinks.put(4,links4);
		switchLinks.put(5,links5);
		switchLinks.put(6,links6);
		switchLinks.put(7,links7);
		switchLinks.put(8,links8);
		//linkCost
		Map<Link,Integer> linkCost=new HashMap<Link,Integer>();
		linkCost.put(link1, 4);
		linkCost.put(link2, 8);
		linkCost.put(link3, 4);
		linkCost.put(link4, 8);
		linkCost.put(link5, 11);
		linkCost.put(link6, 8);
		linkCost.put(link7, 7);
		linkCost.put(link8, 2);
		linkCost.put(link9, 2);
		linkCost.put(link10, 9);
		linkCost.put(link11, 14);
		linkCost.put(link12, 9);
		linkCost.put(link13, 10);
		linkCost.put(link14, 4);
		linkCost.put(link15, 14);
		linkCost.put(link16, 2);
		linkCost.put(link17, 2);
		linkCost.put(link18, 1);
		linkCost.put(link19, 6);
		linkCost.put(link20, 8);
		linkCost.put(link21, 1);
		linkCost.put(link22, 7);
		linkCost.put(link23, 11);
		linkCost.put(link24, 4);
		linkCost.put(link25, 2);
		linkCost.put(link26, 6);
		linkCost.put(link27, 7);
		linkCost.put(link28, 10);
		
		new PrimAlogrithm().primMST(switchLinks, linkCost);
	}
}
