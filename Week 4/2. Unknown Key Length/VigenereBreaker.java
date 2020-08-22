import java.io.File;
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
            //System.out.println("KEY: " + resKey);
            key[i] = resKey;
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet <String> Dict = new HashSet<String>();
        for(String line: fr.lines())
        {
            line = line.toLowerCase();
            if(!Dict.contains(line))
            {
                Dict.add(line);
            }
        }
        return Dict;
    }

    public int countWords(String message, HashSet<String>dictionary)
    {
        int countWords = 0;
        String[] words = message.split("\\W+");
        for(String word: words)
        {
            if(dictionary.contains(word.toLowerCase()))//...check if Dict contains the word (from message)...//
            {
                countWords++;
            }
        }
        return countWords;
    }

    public String breakForLanguage(String encrypted, HashSet<String>dictionary)
    {
        int max = 0;
        int keyLen = 0;
        String decrypt = "";
        String finalDecrypt = "";
        for(int i=1; i<100; i++)
        {
            int []key;
            key = tryKeyLength(encrypted, i, 'e');
            VigenereCipher VC = new VigenereCipher(key);
            decrypt = VC.decrypt(encrypted);
            int countWords = countWords(decrypt,dictionary);
            if(countWords>max)
            {
                max = countWords;
                finalDecrypt = decrypt;
                keyLen = i;
            }
        }
        System.out.println("KeyLength that worked:" + keyLen);
        System.out.println("Max Word Counts:" + max);
        return finalDecrypt;
    }

    public void breakVigenere ()
    {
        ////////////////////////////////////////////////////////////////////////////////////////
        HashSet<String> Dictionary = new HashSet<String>(); //...Dictionary HashSet...//
        FileResource DictResource = new FileResource("dictionaries/English");
        Dictionary = readDictionary(DictResource);
        //////////////////////////////////////////////////////////////////////////////////////

        FileResource fr = new FileResource();
        String FILE = fr.asString();
        String decrypt = breakForLanguage(FILE, Dictionary);
        System.out.println("Valid words: " + countWords(decrypt,Dictionary));
        System.out.println("DECRYPTED MESSAGE: " + decrypt);
    }

    ///////////////////////////////////////////////////////////
    public static void main (String []args)
    {
        VigenereBreaker VC = new VigenereBreaker();
        VC.breakVigenere();
    }
    
}
