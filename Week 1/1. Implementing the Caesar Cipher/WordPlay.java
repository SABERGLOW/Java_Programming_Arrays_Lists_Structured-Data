import edu.duke.*;

public class WordPlay
{
    public boolean isVowel(char ch)
    {
        String vowels = "aeiouAEIOU";
        if(vowels.indexOf(ch) != -1)
        {
            return true;
        }
        return false;
    }

    public String replaceVowels (String phrase, char ch)
    {
        StringBuilder result = new StringBuilder(phrase);
        for(int i=0; i<result.length(); i++)
        {
            if(isVowel(result.charAt(i)))
            {
                result.setCharAt(i,ch);
            }
        }
        return result.toString();
    }

    public String emphasize (String phrase, char ch)
    {
        StringBuilder result = new StringBuilder(phrase);
        for(int i=0; i<result.length(); i++)
        {
            if(result.charAt(i) == ch || result.charAt(i) == Character.toUpperCase(ch))
            {
                if(i%2 == 0) //...even index, but odd number location
                {
                    result.setCharAt(i,'*');
                }
                else
                {
                    result.setCharAt(i,'+');
                }
            }
        }
        return result.toString();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public void testisVowel()
    {
        System.out.println("F is vowel..?" + isVowel('a'));
    }
    public void testreplaceVowels()
    {
        System.out.println("Hello World replaced vowels: " + replaceVowels("Hello World",'*'));
    }
    public void testemphasize()
    {
        System.out.println("Mary Bella Abracadabra: " + emphasize("Mary Bella Abracadabra",'a'));
    }

//////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        WordPlay CIPHER = new WordPlay();
        CIPHER.testisVowel();
        CIPHER.testreplaceVowels();
        CIPHER.testemphasize();
    }
}
