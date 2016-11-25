package presentation;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author Nana Schütz
 * @version 2.2
 */
public class App extends Application
{
	private static Parent root;
	
	@Override
    public void start(Stage primaryStage) throws Exception 
 	{
        root = FXMLLoader.load(getClass().getResource("hangman.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Hangman");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
	//launches application
    public static void main(String[] args) 
    {
    	launch(args);
    }

}
