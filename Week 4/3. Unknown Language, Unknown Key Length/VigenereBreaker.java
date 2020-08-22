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
            key[i] = resKey;
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet <String> Dict = new HashSet<>();
        for(String line: fr.lines())
        {
            line = line.toLowerCase();
            Dict.add(line);
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

    public char mostCommonCharIn(HashSet<String>words)
    {
        HashMap<Character, Integer> map = new HashMap<>();
        char mostCommon = 'a';
        for(String word: words)
        {
            for(int i=0; i<word.length(); i++)
            {
                char currentChar = word.charAt(i);
                if(!map.containsKey(currentChar))
                {
                    map.put(currentChar, 1);
                }
                else if(map.containsKey(currentChar))
                {
                    map.put(currentChar, map.get(currentChar)+1);
                }
            }
        }
        int mostCommonCount = 0;
        for(Character character: map.keySet())
        {
            if(map.get(character) > mostCommonCount)
            {
                mostCommonCount = map.get(character);
                mostCommon = character;
            }
        }
        System.out.println("Most common char: " + mostCommon);
        return mostCommon;
    }

    public String breakForAllLanguage(String encrypted, HashMap<String, HashSet<String>> language)
    {
        int max = 0;
        String finalDecrypt = new String();
        String finalLanguage = new String();
        HashSet<String>set = new HashSet<>();
        for(String lang: language.keySet())
        {
            HashSet<String>accumulateLanguages = language.get(lang);
            String decrypted = breakForLanguage(encrypted, accumulateLanguages);
            int countWords = countWords(decrypted, accumulateLanguages);
            if(countWords>max)
            {
                max = countWords;
                finalDecrypt = decrypted;
                finalLanguage = lang;
            }
        }
        System.out.println("Language that makes sense: " + finalLanguage);
        System.out.println("DECRPTED that makes sense: " + finalDecrypt);

        return finalDecrypt;
    }

    public String breakForLanguage(String encrypted, HashSet<String>dictionary)
    {
        int max = 0;
        int keyLen = 0;
        String decrypt = new String();
        String finalDecrypt = "";
        char mostCommon = mostCommonCharIn(dictionary);
        for(int i=1; i<100; i++)
        {
            int []key;
            key = tryKeyLength(encrypted, i, mostCommon);
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
        //System.out.println("KeyLength that worked:" + keyLen);
        //System.out.println("Max Word Counts:" + max);
        return finalDecrypt;
    }

    public void breakVigenere ()
    {
        HashMap<String, HashSet<String>>map = new HashMap<>();
        //////////////////////////////////////////////////////////
        FileResource fr = new FileResource();
        String FILE = fr.asString();
        //////////////////////////////////////////////////////////
        DirectoryResource DictResource = new DirectoryResource();
        for(File f: DictResource.selectedFiles())//...creating a hasmap for all Languages' Dictionaries...//
        {
            FileResource DictFileResource = new FileResource(f);
            HashSet<String>Dictionary = readDictionary(DictFileResource);
            map.put(f.getName(), Dictionary);
        }
        breakForAllLanguage(FILE, map);

    }

    ///////////////////////////////////////////////////////////
    public static void main (String []args)
    {
        VigenereBreaker VC = new VigenereBreaker();
        VC.breakVigenere();
    }
    
}
