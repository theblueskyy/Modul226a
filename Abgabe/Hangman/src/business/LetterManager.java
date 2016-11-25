package business;
/**
 * @author Nana Schütz
 * @version 2.2
 */
import java.util.HashMap;
import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LetterManager 
{
	//creates letters and shows them in GUI
	public static void createLetter(HBox mHBox, HashMap<Character, Text> alphabet, Font DEFAULT_FONT) 
	{
		mHBox.setAlignment(Pos.CENTER);
        for (char c = 'A'; c <= 'Z'; c++) {
            Text t = new Text(String.valueOf(c));
            t.setFont(DEFAULT_FONT);
            alphabet.put(c, t);
            mHBox.getChildren().add(t);
        }
	}
	
	//colors used letters light grey
	public static void useLetter(KeyEvent event, HashMap<Character, Text> alphabet, int mRoundPlayed) 
	{
		char pressed = event.getText().toUpperCase().charAt(0);
        if ((pressed < 'A' || pressed > 'Z') && pressed != '-')
            return;
        Text t = alphabet.get(pressed);
        if (t.getFill().equals(Color.LIGHTGRAY))
        {
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Letter already used");
        	alert.setHeaderText(null);
        	alert.setContentText("You've used this letter already! Pay attention!");
        	alert.showAndWait();
        }
        t.setFill(Color.LIGHTGRAY);
	}
	
	//checks if win or lose
	public void checkWin(int mCorrectLetter, String mWordToGuess, int mWrongLetter) 
	{
		if(mCorrectLetter == mWordToGuess.length())
		{
			Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("You WON!!");
        	alert.setHeaderText(null);
        	alert.setContentText("Congrats!! You WON!!");
        	ButtonType buttonTypeOne = new ButtonType("OK");
        	alert.getButtonTypes().setAll(buttonTypeOne);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne)
			{
				System.exit(0);
			}
		}
		
		if(mWrongLetter == 10)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("You LOST!!");
        	alert.setHeaderText(null);
        	alert.setContentText("Boo!! You LOST!!");
        	ButtonType buttonTypeOne = new ButtonType("OK");
        	alert.getButtonTypes().setAll(buttonTypeOne);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne)
			{
				System.exit(0);
			}
		}
	}
}
