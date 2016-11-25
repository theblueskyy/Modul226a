package business;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BoardManager 
{

	private TilesManager[][] mTilesManager;
	private GameDatails mDatails;
	private BooleanProperty mLose = new SimpleBooleanProperty();
	private IntegerProperty mEmpty = new SimpleIntegerProperty();
	private BooleanProperty mWin = new SimpleBooleanProperty(false);

    public BoardManager(GameDatails pDatails) 
    {
        this.mDatails=pDatails;
        initiate();
    }

    public final void initiate()
    {
      mTilesManager = new TilesManager[mDatails.getCols()][mDatails.getRows()];
      createSquares();
      createMines();
      mEmpty.setValue(0); 
      mLose.setValue(false);     
      mWin.bind(Bindings.when(Bindings.not(mLose).and(mEmpty.isEqualTo((mDatails.getCols()*mDatails.getRows())-mDatails.getMines()))).then(true).otherwise(false));      
    }
    
    public void createMines() 
    {
        int nbMines = mDatails.getMines();
        int n = nbMines;
        while (n != 0) 
        {
            int col = (int) (Math.random() * mDatails.getCols());
            int line = (int) (Math.random() * mDatails.getRows());
            if (!mTilesManager[col][line].isMarked()) {
                mTilesManager[col][line].setMarked(true);
                n--;
            }
        }
    }

    public int getRows() 
    {
        return mDatails.getRows();
    }

    public int getCols() 
    {
        return mDatails.getCols();
    }

    public GameDatails getGameMode() 
    {
        return mDatails;
    }

    public TilesManager getSquare(int col, int row) 
    {
        return mTilesManager[col][row];
    }

    private void createSquares() 
    {
        for (int i = 0; i < mDatails.getCols(); i++) 
        {
            for (int j = 0; j < mDatails.getRows(); j++) 
            {
                mTilesManager[i][j] = new TilesManager(i, j);
            }
        }
    }

    public List<TilesManager> getNextSquare(TilesManager pTilesManager) 
    {
        int col = pTilesManager.getCol();
        int row = pTilesManager.getRow();
        assert col < mDatails.getCols() && row < mDatails.getRows() : "bounds pb";
        List<TilesManager> result = new ArrayList();
        for (int i = col - 1; i <= col + 1; i++) 
        {
            for (int j = row - 1; j <= row + 1; j++) 
            {
                if ((i >= 0 && j >= 0)
                        && !(i == col && j == row)
                        && (i < mDatails.getCols() && j < mDatails.getRows())
                        && !mTilesManager[i][j].isMarked()
                        && !mTilesManager[i][j].isVisited()) {
                    result.add(mTilesManager[i][j]);
                    mTilesManager[i][j].setVisited(true);
                }
            }
        }
        return result;
    }

    public int getMinesAround(TilesManager pTilesManager) 
    {
        int col = pTilesManager.getCol();
        int row = pTilesManager.getRow();
        assert col < mDatails.getCols() && row < mDatails.getRows() : "IndexOutOfBounds";
        int result = 0;
        for (int i = col - 1; i <= col + 1; i++) 
        {
            for (int j = row - 1; j <= row + 1; j++) 
            {
                if ((i >= 0 && j >= 0)
                        && !(i == col && j == row)
                        && (i < mDatails.getCols() && j < mDatails.getRows())
                        && (mTilesManager[i][j].isMarked())) 
                {
                    result++;
                }
            }
        }
        return result;
    }

    public void uncoverNextSquares(TilesManager pTilesManager,boolean all) 
    {
        uncoverSquare(pTilesManager);
        if ( getMinesAround(pTilesManager) == 0  || all) 
        {
            getNextSquare(pTilesManager).stream().forEach((s) -> 
            {
               uncoverNextSquares(s,all);
            });
        }
    }

    private void uncoverSquare(TilesManager pTilesManager) 
    {
        if (pTilesManager.isClickedOn() && !pTilesManager.isMarked())
        {
            mEmpty.setValue(mEmpty.getValue()+1);
            pTilesManager.setClickedOn(false);
            pTilesManager.setNeighbours(getMinesAround(pTilesManager));
        }
    }

    public BooleanProperty getLose() 
    {
        return mLose;
    }

    public void setLose(BooleanProperty pLose) 
    {
        this.mLose = pLose;
    }

    public void setLose(boolean pLose) 
    {
        this.mLose.setValue(pLose);
    }
    
    public BooleanProperty getWin() 
    {
        return mWin;
    }

    public void setWin(BooleanProperty pWin) 
    {
        this.mWin = pWin;
    }
        
}
