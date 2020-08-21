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
        HashMap<String, Integer> uniqueIPs = countVisitsPerIP();
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

    public HashMap<String, Integer> countVisitsPerIP()
    {
        HashMap <String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le: records)
        {
            String ipAddress = le.getIpAddress();
            if(!counts.containsKey(ipAddress))
            {
                counts.put(ipAddress, 1);
            }
            else
            {
                counts.put(ipAddress, counts.get(ipAddress)+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> ipMap)
    {
        int max = 0;
        for(String ip: ipMap.keySet())
        {
            if(ipMap.get(ip) > max)
            {
                max = ipMap.get(ip);
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> ipMap)
    {
        int max = mostNumberVisitsByIP(ipMap);
        ArrayList<String>mostIPS = new ArrayList<String>();
        for(String ip: ipMap.keySet())
        {
            if(ipMap.get(ip) == max)
            {
                mostIPS.add(ip);
            }
        }
        return mostIPS;
    }

    public HashMap<String, ArrayList<String>> iPsForDays()
    {
        HashMap<String, ArrayList<String>> ipsForDays = new HashMap<String,ArrayList<String>>();
        ArrayList<String> ipAddresses = new ArrayList<String>();
        for(LogEntry le: records)
        {
            Date d = le.getAccessTime();
            String ip = (le.getIpAddress());
            String date = d.toString();
            date = date.substring(4, 10);
            if(!ipsForDays.containsKey(date))
            {
                ipAddresses.add(ip);
                ipsForDays.put(date, ipAddresses);
            }
            else
            {
                ArrayList<String> tempIP = ipsForDays.get(date);
                tempIP.add(ip);
                ipsForDays.put(date, tempIP);
            }
        }
        return ipsForDays;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> dayIPs)
    {
        String date = "";
        int max = 0;
        for(String s: dayIPs.keySet())
        {
            if(dayIPs.get(s).size() > max)
            {
                max = dayIPs.get(s).size();
                date = s;
            }
        }
        return date;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> dayIPs, String someday)
    {
        ArrayList<String> ips = new ArrayList<String>();
        int max = 0;
        for(String day: dayIPs.keySet())
        {
            if(day.contains(someday))
            {
                ips = (dayIPs.get(day));
            }
        }
        HashMap <String, Integer> maxIPS = new HashMap<String, Integer>();
        for(String ip : ips)
        {
            if(!maxIPS.containsKey(ip))
            {
                maxIPS.put(ip, 1);
            }
            else
            {
                maxIPS.put(ip, maxIPS.get(ip)+1);
            }
        }
        ips.clear();
        ips = iPsMostVisits(maxIPS);
        return ips;
    }



    public void printAll()
    {
        for(LogEntry le: records)
        {
            System.out.println(le);
        }
    }



}
