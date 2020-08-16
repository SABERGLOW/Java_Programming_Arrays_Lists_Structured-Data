import edu.duke.FileResource;

public class TestCaesarCipherTwo
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

    public String halfOfString (String message, int start)
    {
        String res = "";
        for(int i=start; i<message.length(); i+=2)
        {
            res += message.charAt(i);
        }
        return  res;
    }

    public int getKey(String s)
    {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex-4;
        if(maxDex < 4)
        {
            dkey = 26-(4-maxDex);
        }
        return dkey;
    }

    public String breakCaesarCipher(String input)
    {
        String encrypted1 = halfOfString(input, 0);
        String encrypted2 = halfOfString(input, 1);
        int key1 = getKey(encrypted1);
        int key2 = getKey(encrypted2);
        System.out.println("key1: " + key1 + " | key2: " + key2);
        CaesarCipherTwo  CIPHER = new CaesarCipherTwo (key1, key2);
        return CIPHER.decrypt(input);
    }

    public void simpleTests()
    {

        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo CIPHER = new CaesarCipherTwo(17, 3);

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
        TestCaesarCipherTwo CIPHER = new TestCaesarCipherTwo();
        CIPHER.simpleTests();
    }
}
