package bgu.spl.net.impl.command.ClientFrames;

import bgu.spl.net.impl.command.ServerFrames.ReceiptFrame;
import bgu.spl.net.srv.BlockingConnectionHandler;
import bgu.spl.net.srv.BookClub;
import bgu.spl.net.srv.ConnectionsImp;
import bgu.spl.net.srv.User;

public class DisconnectFrame implements ClientFrame {

    private String receiptId;
    private BookClub bookClub = BookClub.getInstance();
    private ConnectionsImp connections;

    public DisconnectFrame(String receiptId){
        this.receiptId = receiptId;
    }



    @Override
    public void execute(int connectionId) {
        User tmpUser = bookClub.getUser(connectionId);
        tmpUser.unSubscribeAll();
        tmpUser.logout();
        bookClub.exitAllGenres(tmpUser);
        connections.send(connectionId, new ReceiptFrame(receiptId));
        connections.disconnect(connectionId);
    }

    @Override
    public void setConnections(ConnectionsImp<String> connections) {
        this.connections = connections;
    }
}
