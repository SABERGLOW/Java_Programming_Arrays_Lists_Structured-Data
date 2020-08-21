import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer()
    {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename)
    {
        FileResource fr = new FileResource(filename);
        for(String line: fr.lines())
        {
            records.add(WebLogParser.parseEntry(line));
        }

    }

    public int countUniqueIPs()
    {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le: records)
        {
            String ipAddress = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddress))
            {
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num)
    {
        for(LogEntry le: records)
        {
            int statusCode = le.getStatusCode();
            if(statusCode > num)
            {
                System.out.println("Status Code greater than " +num+ ": " + statusCode);
            }
        }
    }

    public ArrayList uniqueIPVisitsOnDay (String someday)
    {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le: records)
        {
            Date d = le.getAccessTime();
            String date = d.toString();
            String ipAddress = le.getIpAddress();
            if(date.contains(someday) && !uniqueIPs.contains(ipAddress))
            {
                uniqueIPs.add(ipAddress);
                //System.out.println("Unique IPs on " +someday+ ": " + ipAddress);
            }
        }
        System.out.println("UNIQUE IPs on " + someday+ ": " + uniqueIPs.size());
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high)
    {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le: records)
        {
            int statusCode = le.getStatusCode();
            String ipAddress = le.getIpAddress();
            if((statusCode >= low) && (statusCode<= high) && !uniqueIPs.contains(ipAddress))
            {
                uniqueIPs.add(ipAddress);
            }
        }
        System.out.println("UNIQUE IPs in range: " + uniqueIPs.size());
        return uniqueIPs.size();
    }

    public void printAll()
    {
        for(LogEntry le: records)
        {
            System.out.println(le);
        }
    }



}
