package presentation;

import business.BoardManager;
import business.PropertiesManager;
import business.TilesManager;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameTiles extends Label 
{

    TilesManager mTilesManager;
    BoardManager mBoardManager;

    static final Lighting lighting = new Lighting();

    static 
    {
        Light.Distant lLight = new Light.Distant();
        lLight.setAzimuth(-135);
        lLight.setElevation(30);
        lighting.setLight(lLight);
    }

    public GameTiles(TilesManager pTilesManager, BoardManager pBoardManager) 
    {
        this.mTilesManager = pTilesManager;
        this.mBoardManager = pBoardManager;
        init();
    }

    private void init()
    {
        setMinSize(PropertiesManager.getTileWidth(), PropertiesManager.getTileHeight());
        setAlignment(Pos.BASELINE_CENTER);
        styleProperty().bind(Bindings.when(mTilesManager.getSelected()).then(PropertiesManager.getMineColor()).otherwise(PropertiesManager.getBackgroundColor()));
        effectProperty().bind(Bindings.when(mTilesManager.getClickedOn()).then(lighting).otherwise((Lighting) null));


        textProperty().bind(Bindings.when(mTilesManager.getClickedOn().not().and(mTilesManager.getNeighbour().isEqualTo(0)).or(mTilesManager.getClickedOn())).then("")
                                    .otherwise(mTilesManager.getNeighbour().asString()));
        
        setOnMouseClicked((MouseEvent e) -> 
        {

            if (e.getButton() == MouseButton.PRIMARY && ! mTilesManager.isSelected()  && mTilesManager.isClickedOn()) 
            {
                
                if (mTilesManager.isMarked()) 
                {
                    mBoardManager.setLose(true);
                    mBoardManager.uncoverNextSquares(mTilesManager,true);
                }
                else
                {                
                    mBoardManager.uncoverNextSquares(mTilesManager,false);
                }
                
            } 
            else if (e.getButton() == MouseButton.SECONDARY && mTilesManager.isClickedOn()) 
            {
                
                mTilesManager.setSelected(!mTilesManager.getSelected().getValue());
            }
            
        });
    }
}
