package presentation;
/**
 * @author Nana Schütz
 * @version 2.2
 */
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

import business.LetterManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AppController implements Initializable
{
	@FXML private TextField mTextField;
	@FXML private Label mLabel;
	@FXML private Line line1;
	@FXML private Line line2;
	@FXML private Line line3;
	@FXML private Line line4;
	@FXML private Circle line5;
	@FXML private Line line6;
	@FXML private Line line7;
	@FXML private Line line8;
	@FXML private Line line9;
	@FXML private Line line10;
	@FXML private HBox mHBox;

	private boolean mLetter = false;
	
	private int mRoundPlayed = 0;
	private int mCorrectLetter = 0;
	private int mWrongLetter = 0;
	
	private String[] mSavedLetters;
	private String[] mWords = {
        "hello", "middle", "whatever"
    };
	private String mWordToGuess;
	private String mGetLetter;
	private String mLetterGuessed = "";
    
	private Random random = new Random();
    
    private HashMap<Character, Text> alphabet = new HashMap<Character, Text>();
    private static final Font DEFAULT_FONT = new Font("Courier", 36);
    LetterManager mLetterManager = new LetterManager();
	
	//Action which detects release of keys
	@FXML
    private void keyReleased(KeyEvent event)
	{ 
        mGetLetter = mTextField.getText().toLowerCase();
        mTextField.setText("");
        mLetterGuessed = mGetLetter;
        fillLetter();
        drawLine();
        mLetterManager.checkWin(mCorrectLetter, mWordToGuess, mWrongLetter);
        mLetter = false;
        LetterManager.useLetter(event, alphabet, mRoundPlayed);
	}
	
	//fills correct letters into to right position of word
	public void fillLetter()
	{
		for (int p = 0; p < mWordToGuess.length(); p++) 
		{
			if (mLetterGuessed.charAt(0) == mWordToGuess.charAt(p)) 
			{
				mLetter = true;
				mCorrectLetter++;
				mSavedLetters[p] = mLetterGuessed;
				StringBuilder builder = new StringBuilder();
				   for (String value : mSavedLetters) {
				       builder.append(value);
				   }
				   String text = builder.toString();
				mLabel.setText(text);
			}	
		}
	}
	
	//draw line when guessing wrong letters
	public void drawLine()
	{
		if(!mLetter)
		{
			mRoundPlayed++;
			mWrongLetter++;
			switch (mRoundPlayed) {
			case 1:
				((Node) line1).setVisible(true);
				break;
			case 2:
				((Node) line2).setVisible(true);
				break;
			case 3:
				((Node) line3).setVisible(true);
				break;
			case 4:
				((Node) line4).setVisible(true);
				break;
			case 5:
				line5.setVisible(true);
				break;
			case 6:
				((Node) line6).setVisible(true);
				break;
			case 7:
				((Node) line7).setVisible(true);
				break;
			case 8:
				((Node) line8).setVisible(true);
				break;
			case 9:
				((Node) line9).setVisible(true);
				break;
			case 10:
				((Node) line10).setVisible(true);
				break;
			}
		}
	}

	//called when application is starting 
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		mWordToGuess = mWords[random.nextInt(mWords.length)];
		mSavedLetters = new String[mWordToGuess.length()];
		int length = mWordToGuess.length();
		String[] lLine = new String[length];
		for (int i = 0; i < length; i++) 
		{
			mSavedLetters[i] = "_ ";
			lLine[i] = "_ ";
		} 
		StringBuilder builder = new StringBuilder();
		for (String value : mSavedLetters) 
		{
		   builder.append(value);
		}
		String text = builder.toString();
		mLabel.setText(text);
		   
		LetterManager.createLetter(mHBox, alphabet, DEFAULT_FONT);
	}
}
