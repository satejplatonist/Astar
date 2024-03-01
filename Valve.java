package Main;

public class Valve 
{
	 private int data;
	    
	    private Double h;
	    private Double g;
	    private Double f;
	    
	    private boolean isAllowed = true;
	    private boolean isChecked = false;
	    
	    private boolean targetNode;
	    private boolean startNode;
	    
	    private int XC;
	    private int YC;
	    
		public Valve(int x,int y) {targetNode = false; startNode = false;XC = x;YC = y;}
		public Valve(int data,int x,int y) {this.data = data; targetNode = false; startNode = false;XC = x;YC = y;}
		
		public boolean isChecked() { return isChecked; }
		public void setChecked(boolean isChecked) { this.isChecked = isChecked; }
		
		public boolean isAllowed() { return isAllowed; }
		public void setAllowed(boolean isAllowed) { this.isAllowed = isAllowed; }
		
		public int getData() { return data; }
		public void setData(int data) { this.data = data; }
		
		public Double getH() { return h; }
		public void setH(Double h) { this.h = h; }
		
		public Double getG() { return g; }
		public void setG(Double g) { this.g = g; }
		
		public Double getF() { return f; }
		public void setF(Double f) { this.f = f; }
		
		public boolean isTargetNode() { return targetNode; }
		public void setTargetNode(boolean targetNode) { this.targetNode = targetNode; }
		
		public boolean isStartNode() { return startNode; }
		public void setStartNode(boolean startNode) { this.startNode = startNode; }
		
		public int getXC() { return XC; }
		public void setXC(int xC) { XC = xC; }
		
		public int getYC() { return YC; }
		public void setYC(int yC) { YC = yC; }
		
		@Override
		public boolean equals(Object obj) 
		{
			return super.equals(obj);
		}
		
}
