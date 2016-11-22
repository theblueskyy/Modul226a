package presentation;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application 
{

	@Override
	public void start(Stage pPrimaryStage) 
	{
		pPrimaryStage.setResizable(false);
		pPrimaryStage.setMaximized(false);
		Interface lInterface = new Interface();

		lInterface.widthProperty().addListener((e) -> 
		{
			pPrimaryStage.setWidth(lInterface.widthProperty().getValue());
			pPrimaryStage.centerOnScreen();
		});
		lInterface.heightProperty().addListener((e) -> 
		{
			pPrimaryStage.setHeight(lInterface.heightProperty().getValue() + 20);
			pPrimaryStage.centerOnScreen();
		});

		pPrimaryStage.setScene(lInterface.getScene());
		pPrimaryStage.setTitle("MineSweeper");
		pPrimaryStage.show();
	}

	public static void main(String[] args) 
	{
		launch(args);
	}

}
