import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Session currSes = new Session(Util.status.MENU);
        currSes.dispatchNextAction();
    }
}
