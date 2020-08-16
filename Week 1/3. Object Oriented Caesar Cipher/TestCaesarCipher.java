import edu.duke.FileResource;

public class TestCaesarCipher
{


    public int[] countLetters(String message)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[]counts = new int[26];
        for(int i=0; i<message.length(); i++)
        {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alphabet.indexOf(ch);
            if(dex != -1)
            {
                counts[dex]+=1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] counts)
    {
        int max = 0;
        for(int i=1; i<counts.length; i++)
        {
            if(counts[i] > counts[max])
            {
                max = i;
            }
        }
        return max;
    }

    public String breakCaesarCipher (String input)
    {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex-4;
        if(maxDex < 4)
        {
            dkey = 26-(4-maxDex);
        }
        System.out.println("Key is " + dkey);
        CaesarCipher CIPHER = new CaesarCipher(dkey);
        return CIPHER.decrypt(input);
    }

    public void simpleTests()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher CIPHER = new CaesarCipher(18);
        String encrypted = CIPHER.encrypt(message);
        String decrypted = CIPHER.decrypt(encrypted); //...decrypting with 26-key...//

        System.out.println("Real Message: " + message);
        System.out.println("Encrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);

        decrypted = breakCaesarCipher(encrypted); //...decrypting with 26-key...//
        System.out.println("Decrypted Message with BREAK: " + decrypted);

    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        TestCaesarCipher CIPHER = new TestCaesarCipher();
        CIPHER.simpleTests();
    }
}
