package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import business.LetterManager;

public class LetterManagerTest {

	@Before
	public void setUp() throws Exception 
	{
		System.out.println("Setting up Test");
	}

	@Test
	public void testWordLengthCounter() 
	{
		String lWord = "Hallo";
		assertEquals(5, lWord.length(), 0);
	}
	
	@Test
	public void testCheckUseLetter() 
	{
		boolean lCheck = false;
		char lLetter = 'd';
		if(lLetter == 'd')
		{
			lCheck = true;
		}
		assertEquals(true, lCheck);
	}
	
	@Test
	public void testCheckWin() 
	{
		boolean lCheck = false;
		int lGuessedWord = 4;
		String lWord = "Test";
		if(lGuessedWord == lWord.length())
		{
			lCheck = true;
		}
		assertEquals(true, lCheck);
	}
	

}
