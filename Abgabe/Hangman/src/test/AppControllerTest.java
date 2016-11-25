package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.Node;

public class AppControllerTest {

	@Before
	public void setUp() throws Exception 
	{
		
	}

	@Test
	public void testFillLetter() 
	{
		String lWordToGuess = "Test";
		String lLetterGuessed = "T";
		String lString = new String();
		for (int p = 0; p < lWordToGuess.length(); p++) 
		{
			if (lLetterGuessed.charAt(0) == lWordToGuess.charAt(p)) 
			{
				lString = lLetterGuessed;
			}
		}
		assertEquals("T", lString);
	}
	
	@Test
	public void testDrawLine() 
	{
		boolean lLetter = false;
		int lRoundPlayed = 2;
		String lString = new String();
		if(!lLetter)
		{
			switch (lRoundPlayed) 
			{
				case 1:
					lString = "falsch";
					break;
				case 2:
					lString = "richtig";
					break;
			}
		}
		assertEquals("richtig", lString);
	}
}
