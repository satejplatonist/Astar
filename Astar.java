package Main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Vector;

public class Astar 
{
	private Vector<Vector<Valve>> Graph = new Vector<Vector<Valve>>();
	   
	   NodeComparator comparator = new NodeComparator();
	   
	   private PriorityQueue<Valve> openList = new PriorityQueue<Valve>(4,comparator);
	   private PriorityQueue<Valve> closedList = new PriorityQueue<Valve>(4,comparator);
	   private ArrayList<ArrayList<Valve>> alternate = new ArrayList<ArrayList<Valve>>();
	   
	   Valve start;
	   Valve target;
	   
	   private int startx;
	   private int starty;
	   private int targetx;
	   private int targety;
	   
	   public Astar() 
	   {
		   for(int i=0;i<3;i++)
		   {	
			   Vector<Valve> temp = new Vector<Valve>();
			   for(int j=0;j<3;j++)
			   {
				   temp.add(new Valve(0,i,j));
			   }
			   Graph.add(temp);
		   }
	   }
	    
	   public void Input()
	   {
		   
	   }
	   public void startNode()
	   {
		   Graph.get(2).get(2).setStartNode(true);
		   start = Graph.get(2).get(2);
		   startx = 2;
		   starty = 2;
	   }
	   
	   public void targetNode()
	   {
		   Graph.get(0).get(0).setStartNode(true);
		   target = Graph.get(0).get(0);
		   targetx = 0;
		   targety = 0;
	   }
	   
	   private double getEucladianDistance(int x1,int y1) 
	   {
		   return Math.sqrt(Math.pow(targetx - x1, 2) + Math.pow(targety - y1, 2));
	   }
	   private double getGCost(int x1,int y1) { return (Math.abs(x1 - startx)) + (Math.abs(y1 - starty)); }
	   private double getFCost(Valve node) { return node.getG() + node.getH(); }
	   
	   public void GCostSetter()
	   {
		   for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					Graph.get(i).get(j).setG(getGCost(i, j));;
				}
			}
	   }
	   
	   public void HCostSetter()
	   {
		   for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					Graph.get(i).get(j).setH(getEucladianDistance(i, j));
				}
			}
	   }
	   
	   public void FCostSetter()
	   {
		   for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					Graph.get(i).get(j).setF(getFCost(Graph.get(i).get(j)));
				}
			}
	   }
	   
	   public void isAllowedSetter(int t1,int t2)
	   {
		   Graph.get(t1).get(t2).setAllowed(false);
	   }

	   public void findPath()
	   {
		   int temp1 = 2;
		   int temp2 = 2;
		   Valve tempNode;
		   ArrayList<Valve> alt = new ArrayList<Valve>();
		   
	       tempNode = getStartNode();
	       
		   do
		   {
		       temp1 = tempNode.getXC();
			   temp2 = tempNode.getYC();
			   tempNode.setChecked(true);
			   
			   if(isValid(temp1+1, temp2)) { openList.add(Graph.get(temp1+1).get(temp2)); }
			   if(isValid(temp1-1, temp2)) { openList.add(Graph.get(temp1-1).get(temp2)); }
			   if(isValid(temp1, temp2+1)) { openList.add(Graph.get(temp1).get(temp2+1)); }
			   if(isValid(temp1, temp2-1)) { openList.add(Graph.get(temp1).get(temp2-1)); }
	
			   System.out.println(tempNode.getXC()+","+tempNode.getYC());
			   if(openList.isEmpty())
			   {
				   System.out.println("i");
				   if(tempNode==getStartNode() && alternate.size()==0)
				   {
					   System.out.println("Path not found wait");
					   return;   
				   }
				   else 
				   {
					   while(alternate.size()-1>=0)
					   {
						   if(!alternate.get(alternate.size()-1).equals(null))
						   {
							   break;
						   }
						   else
						   {
							   closedList.remove(lastElmClosedList());
							   alternate.remove(alternate.size()-1);
						   }
					   }
					   if(alternate.size()-1==0)
					   {
						   System.out.println("Path not found wait");
						   return; 
					   }
					   closedList.add(alternate.get(alternate.size()-1).get(0));
					   tempNode = alternate.get(alternate.size()-1).remove(0);
					   System.out.println("DONE");
				   }
			   } 
			   else if(!openList.isEmpty())
			   {
				   tempNode = openList.poll();
				   temp1 = tempNode.getXC();
				   temp2 = tempNode.getYC();
				   if(!openList.isEmpty())
				   {
					   for(Valve n:openList)
					   {
						   alt.add(openList.poll());
					   }
				   }
				   else
				   {
					   alt.add(null);
				   }
				   alternate.add(alt);
				   closedList.offer(tempNode);   
				    
			   }  
		   }
		   while(temp1!=targetx && temp2!=targety);
	   }
	   
	   private Valve getStartNode()
	   {
		   return start;
	   }
	   
	   
	   private boolean isValid(int t1,int t2)
	   {
		  if( (t1>=0 && t1<Graph.size()) && (t2>=0 && t2<Graph.get(t1).size()) && Graph.get(t1).get(t2).isChecked()==false )
		  {
			  if(!openList.contains(Graph.get(t1).get(t2)) && !closedList.contains(Graph.get(t1).get(t2)))
			  {
				  if(!isAlternate(t1, t2) && Graph.get(t1).get(t2).isAllowed()==true)
				  {
					  return true;
				  }
				  return false;
			  }
			  return false;
		  }
		  return false;
	   }
	   
	   private boolean isAlternate(int t1,int t2)
	   {
		   for(int i=0;i<alternate.size();i++)
		   {
			   for(int j=0;j<alternate.get(i).size();j++)
			   {
				   if(alternate.get(i).contains(Graph.get(t1).get(t2)))
				   {
					   return true;
				   }
			   }
		   }
		   return false;
	   }
	   
	   public void print()
	   {
		   for(int i=0;i<3;i++)
		   {		   
			   for(int j=0;j<3;j++)
			   {
				   System.out.print(Graph.get(i).get(j).getData() + " ");
			   } 
			   System.out.println();
		   }
	   }
	   
	   public void printG()
	   {
		   for(int i=0;i<3;i++)
		   {		   
			   for(int j=0;j<3;j++)
			   {
				   System.out.print(Graph.get(i).get(j).getG()+" ");
			   } 
			   System.out.println();
		   }
	   }
	   
	   public void printH()
	   {
		   for(int i=0;i<3;i++)
		   {		   
			   for(int j=0;j<3;j++)
			   {
				   System.out.print(Graph.get(i).get(j).getH()+" ");
			   } 
			   System.out.println();
		   }
	   }
	   
	   public void printF()
	   {
		   for(int i=0;i<3;i++)
		   {		   
			   for(int j=0;j<3;j++)
			   {
				   System.out.print(Graph.get(i).get(j).getF()+" ");
			   } 
			   System.out.println();
		   }
	   }
	   
	   private Valve lastElmClosedList()
	   {
		   Valve temp = null;
		   for(Valve n:closedList)
		   {
			   temp=n;
		   }
		   return temp;
	   }
	   
	   public void printPath()
	   {
		  Set<String> path = new LinkedHashSet<String>();
		  path.add("("+startx+","+starty+")");
		  for(Valve n:closedList)
		  {
			  path.add("("+n.getXC()+","+n.getYC()+")");
		  }
		  path.add("("+targetx+","+targety+")");
		  for(String s:path)
		  {
			  System.out.println(s);
		  }
	   }
	}


	class NodeComparator implements Comparator<Valve>
	{
	    @Override
		public int compare(Valve o1, Valve o2) 
	    {
			if(Double.compare(o1.getF(),o2.getF())==0)
			{
				if(Double.compare(o1.getH(),o2.getH())==0)
				{
					return Double.compare(o2.getG(),o1.getG());
				}
				return Double.compare(o1.getH(),o2.getH());
			}
			return Double.compare(o1.getF(),o2.getF());
		}
}
