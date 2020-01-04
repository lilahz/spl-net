package bgu.spl.net.impl.command.ServerFrames;

import bgu.spl.net.srv.ConnectionsImp;

public class ConnectedFrame implements ServerFrame {

    private ConnectionsImp<String> connections;
    private String version;

    public ConnectedFrame(String version) {
        this.version = version;
    }

    public String toFrame() {
        String output = "CONNECTED\nversion:" + version + "\n" + '\u0000';
        return output;
    }
}
