package business;

public enum GameDatails 
{
	   GAME(40,16,16);
	    
	   private GameDatails(int pMines,int pRows,int pCols)
	   {
	       this.mMines=pMines;
	       this.mRows=pRows;
	       this.mCols=pCols;
	   }

	    public int getMines() 
	    {
	        return mMines;
	    }

	    public int getRows() 
	    {
	        return mRows;
	    }

	    public int getCols() 
	    {
	        return mCols;
	    }
	   
	   
	   private final int mMines;
	   private final int mRows;
	   private final int mCols;
}
