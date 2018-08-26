package sample;

import javafx.stage.FileChooser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class FilesHandler {

    public void AddMessageIntoFile(String filePath, String Message) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filePath, true));
        out.append(Message);

        out.close();
    }
    public void OverWriteMessageIntoFile(String filePath, String Message) throws IOException {
        PrintWriter out = new PrintWriter(new File(filePath));
//        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(new FileOutputStream(filePath));
//        outputStreamWriter.write(Message);
//        outputStreamWriter.flush();
//        outputStreamWriter.flush();
        out.println(Message);


        out.close();
    }
    public String ReadMessageFromFile(String filePath) throws FileNotFoundException {
        StringBuilder ReddenMessageFromTheFile = new StringBuilder();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNext()) {

            ReddenMessageFromTheFile.append(scanner.next());
        }
        return ReddenMessageFromTheFile.toString();
    }
    public Map<String,String> ReadMessageFromFile() throws FileNotFoundException {
        Map<String,String> fileDeatil=new HashMap<>();
        FileChooser jFileChooser = new FileChooser();
        String ReddenMessageFromTheFile = "";
        File result = jFileChooser.showOpenDialog(null);
            Scanner scanner = new Scanner(result);
            while (scanner.hasNextLine()) {

                ReddenMessageFromTheFile+=scanner.nextLine();
            }
        fileDeatil.put("file_name",result.getPath());
        fileDeatil.put("file_data", ReddenMessageFromTheFile);
        return fileDeatil;
    }


}
