import edu.duke.FileResource;
import edu.duke.URLResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap
{
    HashMap <String, ArrayList<String>> map;
    private ArrayList<String> wordUsed;//...arrayList for words that're already used...//
    private int wordUsedCount = 0;
    private ArrayList<String> usedCategories;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap()
    {
        map = new HashMap <String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordUsed = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }

    public GladLibMap(String source)
    {
        initializeFromSource(source);
        myRandom = new Random();
        wordUsed = new ArrayList<String>();
        usedCategories = new ArrayList<String>();

    }

    private void initializeFromSource(String source)
    {
        String []labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String label: labels)
        {
            ArrayList<String> list = readIt(source+ "/" + label + ".txt");
            map.put(label, list);
        }
    }

    private String randomFrom(ArrayList<String> source)
    {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label)
    {
        if (label.equals("number"))
        {
            return ""+myRandom.nextInt(50)+5;
        }
        addUsedCategory(label);
        return randomFrom(map.get(label));
    }

    private void addUsedCategory(String label)
    {
        if (usedCategories.indexOf(label) == -1)
        {
            usedCategories.add(label);
        }
    }

    private String processWord(String w)
    {
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1)
        {
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = "";

        //...now we check if the word was already used or not...//
        while(true)
        {
            sub = getSubstitute(w.substring(first+1,last));
            if(!wordUsed.contains(sub))
            {
                wordUsed.add(sub);
                wordUsedCount += 1;
                break;
            }
        }

        return prefix+sub+suffix;
    }

    public int totalWordsInMap()
    {
        int freq = 0;
        for(String word: map.keySet())
        {
            ArrayList<String> words = map.get(word);
            System.out.println("Category:" +word+ "\tTotal words in category:" + words.size());
            freq += words.size();
        }
        System.out.println("Lists size: "+freq);
        return freq;
    }

    public int totalWordsConsidered()
    {
        ArrayList<String> content = new ArrayList<String>();
        int sum = 0;
        System.out.println("\nCategories used in this story:");
        for (int index = 0; index < usedCategories.size(); index++)
        {
            String category = usedCategories.get(index);
            content = map.get(category);
            System.out.println("Category: " + category + "\tWords in category: "
                    + content.size());
            sum += content.size();
        }
        System.out.println("sum of possible words: " + sum);
        return sum;
    }

    private void printOut(String s, int lineWidth)
    {
        int charsWritten = 0;
        for(String w : s.split("\\s+"))
        {
            if (charsWritten + w.length() > lineWidth)
            {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source)
    {
        String story = "";
        if (source.startsWith("http"))
        {
            URLResource resource = new URLResource(source);
            for(String word : resource.words())
            {
                story = story + processWord(word) + " ";
            }
        }
        else
        {
            FileResource resource = new FileResource(source);
            for(String word : resource.words())
            {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory()
    {
        wordUsed.clear();
        wordUsedCount = 0;
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        wordUsedCount = 0;
        System.out.println("\n");
        System.out.println(totalWordsInMap());
        System.out.println("\n"+totalWordsConsidered());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        GladLibMap TESTCASE = new GladLibMap();
        TESTCASE.makeStory();
    }
}
