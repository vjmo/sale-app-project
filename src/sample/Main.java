package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Инф. подсистема ООО Аквалюкс");
        primaryStage.setWidth(1020);
        primaryStage.setHeight(1020);
        primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
      /*  WordGenerator wg = new WordGenerator();
        //Gather VK Lines from text file
        List<String> vkLines = wg.getLines(
                new File((Objects.requireNonNull(Main.class.getClassLoader().getResource("vk_no.txt")).toURI())));
        //Create word document according to VK lines
        wg.createWord(vkLines);*/

        System.out.println("HELLO category!");
        SqlConnection.getDataCategory();
        System.out.println("HELLO products!");
        
        SqlConnection.getDataProduct();
        System.out.println("HELLO goods!");
        SqlConnection.getDataGoodsInOrder();
        //Instantiate WordGenerator Class

        launch(args);


    }
}
