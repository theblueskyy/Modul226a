package business;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TilesManager 
{
	  BooleanProperty mClickedOn   = new SimpleBooleanProperty(true);
	  BooleanProperty mMarked     = new SimpleBooleanProperty(false);
	  IntegerProperty mNext = new SimpleIntegerProperty(0);
	  BooleanProperty mSelected  = new SimpleBooleanProperty(false);
	  boolean mVisited = false;
	  
	  
	  int mCol;
	  int mRow;
	  
	    public TilesManager(int pCol,int pRow) 
	    {
	        this.mCol=pCol;
	        this.mRow=pRow;        
	    }

	    public BooleanProperty getClickedOn() 
	    {
	        return mClickedOn;
	    }

	    
	    public void setClickedOn(BooleanProperty pClickedOn) 
	    {
	        this.mClickedOn = pClickedOn;
	    }

	    public BooleanProperty getSelected() 
	    {
	        return mSelected;
	    }

	    public void setSelected(BooleanProperty pSelected) 
	    {
	        this.mSelected = pSelected;
	    }

	    public boolean isSelected()
	    {
	        return mSelected.getValue();        
	    }
	    
	    public void setSelected(boolean pSelected)
	    {
	        this.mSelected.setValue(pSelected);
	    }
	   
	    public BooleanProperty getMarked() 
	    {
	        return mMarked;
	    }

	    public void setMarked(BooleanProperty pMarked) 
	    {
	        this.mMarked = pMarked;
	    }

	   public boolean isMarked()
	   {
	       return mMarked.getValue()==true;
	   }
	  
	   public boolean isClickedOn()
	   {
	       return mClickedOn.getValue()==true;
	   }
	    
	   public void setMarked(boolean pMarked)
	   {
	       this.mMarked.set(pMarked);
	   } 
	   
	   public void setClickedOn(boolean pClickedOn)
	   {
	       this.mClickedOn.set(pClickedOn);
	   } 

	    public int getCol() 
	    {
	        return mCol;
	    }

	    public void setCol(int pCol) 
	    {
	        this.mCol = pCol;
	    }

	    public int getRow() 
	    {
	        return mRow;
	    }

	    public void setRow(int pRow) 
	    {
	        this.mRow = pRow;
	    }

	    public IntegerProperty getNeighbour() 
	    {
	        return mNext;
	    }

	 
	    public void setNeighbours(int pNext) 
	    {
	        this.mNext.setValue(pNext);
	    }

	    public boolean isVisited() 
	    {
	        return mVisited;
	    }

	    public void setVisited(boolean pVisited) 
	    {
	        this.mVisited = pVisited;
	    }
	    
	    
	}
