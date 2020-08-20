import edu.duke.*;
import java.util.*;

public class CodonCount
{
    private HashMap <String, Integer> DNA_MAP;

    public CodonCount()
    {
        DNA_MAP = new HashMap <String, Integer>();
    }

    public void buildCodonMap(int start, String dna)
    {
        DNA_MAP.clear();
        dna = dna.toUpperCase();
        int codonCount = (dna.length() - start) / 3;
        int stopIndex = 0;
        String currentCodon = "";
        while (stopIndex <= (dna.length() - 3)) {
            stopIndex = start + 3;
            currentCodon = dna.substring(start, stopIndex);
            if (DNA_MAP.keySet().contains(currentCodon)) {
                DNA_MAP.put(currentCodon, DNA_MAP.get(currentCodon) + 1);
            } else {
                DNA_MAP.put(currentCodon, 1);
            }
            start += 3;
        }
    }

    public String getMostCommonCodon()
    {
        String mostCommonCodon = "";
        int maxFreq = (Collections.max(DNA_MAP.values()));
        for(Map.Entry<String, Integer> entry : DNA_MAP.entrySet())
        {
            if(entry.getValue() == maxFreq)
            {
                mostCommonCodon = entry.getKey();
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end)
    {
        int codonCount = 0;
        for(String codon : DNA_MAP.keySet())
        {
            if((DNA_MAP.get(codon) >= start) && (DNA_MAP.get(codon) <= end))
            {
                System.out.println("Codon between START and END: " + codon);
                codonCount++;
            }
        }
        System.out.println("number of unique codons  is: "+ codonCount);
    }

    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        int start =1;
        int end = 100;
        buildCodonMap(0, dna);
        String largest = getMostCommonCodon();
        System.out.println("Most common codon is "+largest+" with count "+DNA_MAP.get(largest)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);


        buildCodonMap(1, dna);
        largest = getMostCommonCodon();
        System.out.println("\nMost common codon is "+largest+" with count "+DNA_MAP.get(largest)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);

        buildCodonMap(2, dna);
        largest = getMostCommonCodon();
        System.out.println("\nMost common codon is "+largest+" with count "+DNA_MAP.get(largest)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        CodonCount TESTCASE = new CodonCount();
        TESTCASE.tester();
    }
}
