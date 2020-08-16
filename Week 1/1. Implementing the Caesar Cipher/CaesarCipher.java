import edu.duke.*;

public class CaesarCipher
{
    public String encrypt(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        for(int i=0; i<encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);

            if(idx != -1) //...currChar is an ALPHABET...//
            {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }

            else //...for lowercase...//
            {
                currChar = Character.toUpperCase(currChar);
                idx = alphabet.indexOf(currChar);
                if(idx != -1)
                {
                    char newChar = shiftedAlphabet.charAt(idx);
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys (String input, int key1, int key2)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabetKey1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabetKey2 = alphabet.substring(key2) + alphabet.substring(0, key2);

        for(int i=0; i<encrypted.length(); i++)
        {
            if(i%2==0)
            {
                char currChar = encrypted.charAt(i);
                int idx = alphabet.indexOf(currChar);

                if(idx != -1) //...currChar is an ALPHABET...//
                {
                    char newChar = shiftedAlphabetKey1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }

                else //...for lowercase...//
                {
                    currChar = Character.toUpperCase(currChar);
                    idx = alphabet.indexOf(currChar);
                    if(idx != -1)
                    {
                        char newChar = shiftedAlphabetKey1.charAt(idx);
                        newChar = Character.toLowerCase(newChar);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
            else
            {
                char currChar = encrypted.charAt(i);
                int idx = alphabet.indexOf(currChar);

                if(idx != -1) //...currChar is an ALPHABET...//
                {
                    char newChar = shiftedAlphabetKey2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }

                else //...for lowercase...//
                {
                    currChar = Character.toUpperCase(currChar);
                    idx = alphabet.indexOf(currChar);
                    if(idx != -1)
                    {
                        char newChar = shiftedAlphabetKey2.charAt(idx);
                        newChar = Character.toLowerCase(newChar);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }

        }
        return encrypted.toString();
    }

//////////////////////////////////////////////////////////////////////////////////////
    public void testCaesar()
    {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String encrypted = encrypt(message, 23);
        //System.out.println("key is " + 23 + "\n" + encrypted);
        //System.out.println("ENCRYPTED: " + encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15));
        System.out.println("ENCRYPTED: " + encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        CaesarCipher CIPHER = new CaesarCipher();
        CIPHER.testCaesar();
    }
}
