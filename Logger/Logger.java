package Logger;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    
    public void writeToLog(String str){
        
        FileWriter writer = null;
    
        try{
            writer = new FileWriter("./Logs/log.txt", true);
            writer.write(str);
            writer.append('\n');
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } 
    }

}
