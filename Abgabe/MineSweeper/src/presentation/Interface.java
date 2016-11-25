package presentation;

import business.BoardManager;
import business.PropertiesManager;
import business.GameDatails;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Interface 
{

    private BorderPane mGame = new BorderPane();
    private MenuPane mMenu = new MenuPane();
    private Pane mPane = new StackPane();
    private GameBoard mGameBoard;
    private DoubleProperty mWidth = new SimpleDoubleProperty(this, "width", 0);
    private DoubleProperty mHeight = new SimpleDoubleProperty(this, "height", 0);

    private BoardManager mBoardManager;
    private GameDatails mGameDatails = GameDatails.GAME;
    private Scene mScene;

    public Interface() 
    {
        mPane.getChildren().addAll(mMenu);
        mScene = new Scene(mPane, mMenu.getWidth(), mMenu.getHeight());
    }

    public final void constructGameArea() 
    {
        
        mBoardManager = new BoardManager(mGameDatails);
        mGameBoard = new GameBoard(mBoardManager);
        mGameBoard.disableProperty().bind(Bindings.or(mBoardManager.getLose(),mBoardManager.getWin()));
        resizeUserInterface();
        mGame.setCenter(mGameBoard);
        
        mBoardManager.getLose().addListener((Observable value) -> 
        {
            if (mBoardManager.getLose().get()) {
            	String titleTxt = "You LOST!";
            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle(titleTxt);
            	alert.setHeaderText("You Loser, you lost!!");
            	String s ="Press OK to take your loser ass back to the MENU";
            	alert.setContentText(s);
            	alert.show();
            	mMenu.switchToMenuArea();
            }
        });
        
        mBoardManager.getWin().addListener((Observable value) -> 
        {
            if (mBoardManager.getWin().get()) {
            	String titleTxt = "You WON!";
            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle(titleTxt);
            	alert.setHeaderText("You won, you lil bastard!!");
            	String s ="Do you feel good about yourself now? Press OK to return to the MENU";
            	alert.setContentText(s);
            	alert.show();
            	mMenu.switchToMenuArea();
            }
        });
        
        
    }

    private void resizeUserInterface() 
    {
        mWidth.setValue(mGameDatails.getCols() * PropertiesManager.getTileWidth());
        mHeight.setValue(mGameDatails.getRows() * PropertiesManager.getTileHeight());
    }

    public DoubleProperty widthProperty() 
    {
        return mWidth;
    }

    public DoubleProperty heightProperty() 
    {
        return mHeight;
    }

    public Scene getScene() 
    {
        return mScene;
    }

    final class MenuPane extends VBox 
    {
        Button playBtn = new Button("Play");
        Button closeBtn = new Button("Close");

        public MenuPane() 
        {
            setAlignment(Pos.CENTER);
            setSpacing(20);
            setStyle("-fx-background-color: white;");
           
            
            playBtn.setPrefWidth(100);
            closeBtn.setPrefWidth(100);
            getChildren().addAll(playBtn, closeBtn);

            playBtn.setOnAction((e) -> 
            {
            	mGameDatails = GameDatails.GAME;
                switchToGameArea();
            });
            
            closeBtn.setOnAction((e) -> 
            {
                System.exit(0);
            });
        }

        public void switchToGameArea() 
        {
            constructGameArea();
            mPane.getChildren().remove(mMenu);
            mPane.getChildren().addAll(mGame);
        }
        
        public void switchToMenuArea() 
        {         
            mPane.getChildren().remove(mGame);
            mPane.getChildren().addAll(mMenu);
            mWidth.setValue(mMenu.getWidth());
            mHeight.setValue(mMenu.getHeight());
        }
        
    }


}

