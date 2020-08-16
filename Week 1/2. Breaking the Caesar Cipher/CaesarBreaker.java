//import edu.duke.FileResource;

public class CaesarBreaker
{
    public String decrypt(String encrypted)
    {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex-4;
        if(maxDex < 4)
        {
            dkey = 26-(4-maxDex);
        }
        System.out.println("Key is " + dkey);
        return cc.encrypt(encrypted, 26-dkey);
    }

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
//////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public String decryptTwoKeys (String encrypted)
    {
        String encrypted1 = halfOfString(encrypted, 0);
        String encrypted2 = halfOfString(encrypted, 1);
        int key1 = getKey(encrypted1);
        int key2 = getKey(encrypted2);

        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);

    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void testCaesarBreaker()
    {
        System.out.println("DECRYPTED: " + decrypt("Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth"));
    }
    void testhalfOfString()
    {
        System.out.println("HALF: " + halfOfString("Qbkm Zgis", 1));
    }
    void testdecryptTwoKeys()
    {
        System.out.println("HALF: " + decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
    }

//////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        CaesarBreaker TESTCASE = new CaesarBreaker();
        //TESTCASE.testCaesarBreaker();
        //TESTCASE.testhalfOfString();
        TESTCASE.testdecryptTwoKeys();
    }
}
