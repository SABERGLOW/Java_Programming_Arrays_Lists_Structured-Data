import java.util.*;

public class Tester
{
    public void testLogEntry()
    {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("short-test_log");
        test.printAll();
    }

    public void testUniqueIP()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        System.out.println("Unique Is: " + test.countUniqueIPs());
        //test.uniqueIPVisitsOnDay("Sep 14");

    }
    public void testprintAllHigherThanNum()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog1_log");
        test.printAllHigherThanNum(300);

    }
    public void testuniqueIPVisitsOnDay()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        test.uniqueIPVisitsOnDay("Sep 24");
        //test.uniqueIPVisitsOnDay("Sep 30");
    }
    public void testcountUniqueIPsInRange()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        test.countUniqueIPsInRange(200, 299);
        //test.countUniqueIPsInRange(300, 399);
    }

    public void testcountVisitsPerIP()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        HashMap<String, Integer> counts = test.countVisitsPerIP();
        System.out.println(counts);
    }

    public void testmostNumberVisitsByIP()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        HashMap<String, Integer> counts = test.countVisitsPerIP();
        int max = test.mostNumberVisitsByIP(counts);
        System.out.println("Most Number Visits By IP: " + max);
    }

    public void testiPsMostVisits()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        HashMap<String, Integer> counts = test.countVisitsPerIP();
        ArrayList<String> max = test.iPsMostVisits(counts);
        System.out.println("IPs most visits: " + max);
    }

    public void testiPsForDays()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ips = test.iPsForDays();
        for (String s: ips.keySet())
        {
            System.out.println(s+" maps to"+"\t"+ips.get(s));
        }
    }

    public void testdayWithMostIPVisits()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> ips = test.iPsForDays();
        String day = test.dayWithMostIPVisits(ips);
        System.out.println("Day with most IP visits: " + day);
    }

    public void testiPsWithMostVisitsOnDay()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ips = test.iPsForDays();
        ArrayList<String> allDemIPs = test.iPsWithMostVisitsOnDay(ips, "Sep 30");
        System.out.println("IPs most visited on this day: " + allDemIPs);
    }





    public static void main (String []args)
    {
        Tester testMe = new Tester();
        //testMe.testLogAnalyzer();
        //testMe.testUniqueIP();
        //testMe.testuniqueIPVisitsOnDay();
        //testMe.testcountUniqueIPsInRange();
        //testMe.testprintAllHigherThanNum();
        //testMe.testmostNumberVisitsByIP();
        //testMe.testiPsMostVisits();
        //testMe.testiPsForDays();
        //testMe.testdayWithMostIPVisits();
        //testMe.testiPsWithMostVisitsOnDay();

    }
}
