package sample;

import com.sun.javaws.exceptions.ErrorCodeResponseException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.SUCCESSFUL;
import tray.notification.TrayNotification;

import javax.management.Notification;

import java.lang.reflect.Type;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("layouts/root.fxml"));
        primaryStage.setTitle("CipherSecure");
        primaryStage.getIcons().add(new Image("sample/picture/appicom.png"));
        primaryStage.setScene(new Scene(root, 559, 506));
//        primaryStage.setFullScreen(false);
//        primaryStage.setMaxHeight(600  );
//        primaryStage.setMaxWidth(400);
//        primaryStage.setMinHeight(600);
//        primaryStage.setMinWidth(400);
        String title = "Congratulations sir";
        String message = "You've successfully created your first Tray Notification";


        TrayNotification tray = new TrayNotification();

        tray.setTitle(title);
        tray.setMessage(message);
        tray.showAndWait();
        primaryStage.show();

//        CaserAlgorithm caserEncryption=new CaserAlgorithm();
//
//

//    String x= caserEncryption.MessageEncryption("mohamed",caserEncryption.getAllEnglishAlphabet(),caserEncryption.getAllEnglishAlphabetReversed(),12);
//    caserEncryption.SaveMessageInTheFile("C:\\Users\\Mohammed Elamin\\IdeaProjects\\CaserAlgorithm\\src\\sample\\sample.RootController","hellomynameismohammed");
//    System.out.print(caserEncryption.ReadMessageFromFile("C:\\Users\\Mohammed Elamin\\IdeaProjects\\CaserAlgorithm\\src\\sample\\sample.RootController"));
//


    }


    public static void main(String[] args) {
        launch(args);
    }
}
