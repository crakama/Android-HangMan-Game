import java.io.IOException;
import java.net.Socket;

/**
 * Created by kate on 02/01/2018.
 */

public interface ServeInterface {


    void initializeGame(Socket clientSocket) throws IOException;

    void playGame(ConnectionHandler connHandler) throws IOException, ClassNotFoundException;
}
