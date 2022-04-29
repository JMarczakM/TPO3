package helpers;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetAddress {
    private int port;
    private String address;

    public NetAddress(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public NetAddress(int port) throws UnknownHostException {
        this.port = port;
        this.address = InetAddress.getLocalHost().getHostAddress();
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "NetAddress{" +
                "port=" + port +
                ", address='" + address + '\'' +
                '}';
    }
}
