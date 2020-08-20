import edu.duke.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class WordsInFiles
{
    private HashMap<String,ArrayList<String>> word_filename;

    public WordsInFiles()
    {
        word_filename = new HashMap<String, ArrayList<String>>();
    }


    private void addWordsFromFile(File f)
    {
        FileResource fileResource = new FileResource(f);
        String name = f.getName();


        for(String word:fileResource.words())
        {
            word = word.toLowerCase();
            if(!word_filename.containsKey(word))
            {
                ArrayList<String> newArrayList = new ArrayList<String>();
                newArrayList.add(name);
                word_filename.put(word, newArrayList);
            }

            else if (word_filename.containsKey(word) && !word_filename.get(word).contains(name))
            {
                ArrayList<String> currentFileNameList = word_filename.get(word);
                currentFileNameList.add(name);
                word_filename.put(word, currentFileNameList);
            }
        }
    }


    public void buildWordFileMap()
    {
        word_filename.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    public int maxNumber()
    {
        int max = 0;
        for(String word:word_filename.keySet())
        {
            ArrayList<String> currentFileList = word_filename.get(word);
            int currentNum = currentFileList.size();
            if (currentNum > max)
            {
                max = currentNum;
            }
        }
        return max;
    }

    private ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> ReturnList = new ArrayList<String>();
        for(String word:word_filename.keySet())
        {
            ArrayList<String> currentFileList = word_filename.get(word);
            int currentNum = currentFileList.size();
            if (currentNum == number) {
                ReturnList.add(word);
            }
        }
        return ReturnList;
    }

    private void printFilesIn(String word)
    {
        ArrayList<String> fileNames = word_filename.get(word);
        for (int index = 0; index < fileNames.size(); index++)
        {
            System.out.println(fileNames.get(index));
        }
    }

    public void test()
    {
        buildWordFileMap();
        int maximum = maxNumber();
        ArrayList<String> TestList = wordsInNumFiles(4);
        System.out.println("The maximum number of files word is in: "+maximum +" and there are "+TestList.size());
        for (int k =0;k< TestList.size(); k++)
        {
            System.out.println("All the words in the files "+TestList.get(k)+"");
        }
        System.out.println("\t");

        for (int k =0;k <TestList.size();k++)
        {
            System.out.println("Filenames where the words are ");
            printFilesIn(TestList.get(k));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        WordsInFiles TESTCASE = new WordsInFiles();
        TESTCASE.test();
    }
}
