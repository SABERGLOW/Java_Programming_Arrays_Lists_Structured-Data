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
        test.readFile("short-test_log");
        test.countUniqueIPs();
        test.uniqueIPVisitsOnDay("Sep 14");

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
        test.readFile("weblog1_log");
        test.uniqueIPVisitsOnDay("Mar 24");
        //test.uniqueIPVisitsOnDay("Sep 30");
    }
    public void testcountUniqueIPsInRange()
    {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog1_log");
        //test.countUniqueIPsInRange(200, 299);
        test.countUniqueIPsInRange(300, 399);
    }

    public static void main (String []args)
    {
        Tester testMe = new Tester();
        testMe.testLogAnalyzer();
        testMe.testUniqueIP();
        testMe.testuniqueIPVisitsOnDay();
        testMe.testcountUniqueIPsInRange();
        testMe.testprintAllHigherThanNum();
    }
}
