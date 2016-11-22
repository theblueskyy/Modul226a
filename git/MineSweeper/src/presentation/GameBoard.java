package presentation;

import javafx.scene.layout.GridPane;
import business.BoardManager;

public class GameBoard extends GridPane
{
	   BoardManager mBoardManager;

	    public GameBoard(BoardManager pBoardManager) 
	    {
	       this.mBoardManager=pBoardManager;
	       createTiles();                 
	    }
	   
	    private void createTiles() 
	    {
	        for (int i = 0; i < mBoardManager.getCols(); i++) 
	        {
	            for (int j = 0; j < mBoardManager.getRows(); j++) 
	            {
	            	add(new GameTiles(mBoardManager.getSquare(i, j),mBoardManager ),i,j);
	            }
	        }
	      
	    }
	       
	}
