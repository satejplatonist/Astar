package Main;

public class Main 
{
  public static void main(String[] args) 
  {
	   Astar star = new Astar();
	   star.print();
	   //star.Input();
	   star.startNode();
	   star.targetNode();
	   star.GCostSetter();
	   star.HCostSetter();
	   star.FCostSetter();
	   star.isAllowedSetter(2, 1);
	   star.isAllowedSetter(0, 1);
	   star.printG();
	   star.printH();
	   star.printF();
	   star.findPath();
	   star.printPath();
  }
}
