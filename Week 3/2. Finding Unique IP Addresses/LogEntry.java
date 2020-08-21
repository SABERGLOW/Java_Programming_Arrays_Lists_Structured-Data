import java.util.Date;

public class LogEntry {
    private final String ipAddress;
    private final Date accessTime;
    private final String request;
    private final int statusCode;
    private final int bytesReturned;

    public LogEntry(String ip, Date time, String req, int status, int bytes) {
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;

    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytesReturned() {
        return bytesReturned;
    }

    public String toString() {
        return ipAddress + " " + accessTime + " " + request
                + " " + statusCode + " " + bytesReturned;
    }
}
