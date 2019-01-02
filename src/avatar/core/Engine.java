package avatar.core;

import avatar.io.ConsoleReader;
import avatar.io.ConsoleWriter;
import avatar.utilities.Constants;

import java.io.IOException;

public class Engine {
    private ConsoleReader reader;
    private ConsoleWriter writer;
    private AvatarsManager avatarsManager;

    public Engine() {
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
        this.avatarsManager = new AvatarsManager();
    }

    public void run() throws IOException {

        while (true) {

            boolean loopBreak = false;
            String[] input = reader.readLine().split("\\s+");
            String command = input[0];

            switch (command){
                case Constants.BENDER_COMMAND:
                    String type = input[1];
                    String name = input[2];
                    int power = Integer.parseInt(input[3]);
                    double secondaryProp = Double.parseDouble(input[4]);
                    avatarsManager.createBender(type,name,power,secondaryProp);
                    break;
                case Constants.MONUMENT_COMMAND:
                    avatarsManager.createMonument(input[1],input[2],Integer.parseInt(input[3]));
                    break;
                case Constants.STATUS_COMMAND:
                    writer.write(avatarsManager.statusNation(input[1]));
                    break;
                case Constants.WAR_COMMAND:
                    avatarsManager.war(input[1]);
                    break;
                case Constants.QUIT_COMMAND:
                    loopBreak = true;
                    writer.writeLine(avatarsManager.quit());
                    break;
            }

            if(loopBreak){
                break;
            }
        }
    }
}
