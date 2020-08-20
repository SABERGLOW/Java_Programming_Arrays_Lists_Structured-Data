import java.util.ArrayList;
import  edu.duke.*;

public class CharactersInPlay
{
    private ArrayList<String> myNames;
    private ArrayList <Integer> myFreqs;

    public CharactersInPlay()  //...constructor...//
    {
        myNames = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void update(String person)
    {
        String name = "";
        name = person.substring(0, person.indexOf("."));
        name = name.trim();
        int index = myNames.indexOf(name);
        if(index == -1) //... Name doesn't exist in the Arraylist...//
        {
            myNames.add(name);
            myFreqs.add(1);
        }
        else
        {
            int value = myFreqs.get(index);
            myFreqs.set(index, value+1);
        }
    }



    public void findAllCharacters ()
    {
        FileResource lineResource = new FileResource();
        for(String line : lineResource.lines())
        {
            if(line.contains(".")) //...the line has a period '.' at it's end...//
            {
                update(line);
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2)
    {
        for (int i = 0; i < myFreqs.size(); i++)
        {
            if (myFreqs.get(i) >= num1 && myFreqs.get(i) <= num2)
            {
                System.out.println("Count greater than  :" +num1+" and less than"+ num2 +" "+ myNames.get(i) + "\t" + myFreqs.get(i));
            }
        }
    }

    public int findindexOfMax()
    {
        int max = myFreqs.get(0);
        int index = 0;
        for(int i=1; i<myFreqs.size(); i++)
        {
            if(myFreqs.get(i) > max)
            {
                max = myFreqs.get(i);
                index = i;
            }
        }
        return index;
    }

    public void tester()
    {
        findAllCharacters();
        charactersWithNumParts(10, 15);
        int index = findindexOfMax();
        System.out.println("Character with the most speaking parts " + myNames.get(index) + " " + myFreqs.get(index));
        for (int i = 0; i < myNames.size(); i++)
        {
            if(myFreqs.get(i) > 1)
            {
                System.out.println(myNames.get(i) + "\t" + myFreqs.get(i));
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        CharactersInPlay TESTCASE = new CharactersInPlay();
        TESTCASE.tester();
    }
}
