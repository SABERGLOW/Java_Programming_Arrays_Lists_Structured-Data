import java.util.*;
import edu.duke.*;

public class VigenereBreaker
{
    public String sliceString(String message, int whichSlice, int totalSlices)
    {
        StringBuilder result = new StringBuilder();
        for(int i=whichSlice; i<message.length(); i+=totalSlices)
        {
            result.append(message.charAt(i));
        }
        return result.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon)
    {
        CaesarCracker CC = new CaesarCracker(mostCommon);
        int[] key = new int[klength];
        for(int i=0; i<klength; i++)
        {
            String slice = sliceString(encrypted, i, klength);
            int resKey = CC.getKey(slice);
            System.out.println("KEY: " + resKey);
            key[i] = resKey;
        }

        return key;
    }

    public void breakVigenere ()
    {
        FileResource fr = new FileResource();
        String FILE = fr.asString();
        int[]key = tryKeyLength(FILE, 4, 'e');
        VigenereCipher VC = new VigenereCipher(key);
        String decrypt = VC.decrypt(FILE);
        System.out.println("DECRYPTED MESSAGE: " + decrypt);
    }

    ///////////////////////////////////////////////////////////
    public static void main (String []args)
    {
        VigenereBreaker VC = new VigenereBreaker();
        VC.breakVigenere();
    }
    
}
