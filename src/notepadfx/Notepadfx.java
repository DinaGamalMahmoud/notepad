
package notepadfx;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Notepadfx extends Application {
    
        
        MenuBar bar=new MenuBar();
        Menu file=new Menu("File");
        MenuItem item1=new MenuItem("New");
        MenuItem item2=new MenuItem("Open");
        MenuItem item3=new MenuItem("Save");
        SeparatorMenuItem s=new SeparatorMenuItem() ;
        MenuItem item4=new MenuItem("Exit");
        Menu edit=new Menu("Edit");
        MenuItem eitem1=new MenuItem("Undo");
        MenuItem eitem2=new MenuItem("Cut");
        MenuItem eitem3=new MenuItem("Copy");
        MenuItem eitem4=new MenuItem("Paste");
        MenuItem eitem5=new MenuItem("Delete");
        MenuItem eitem6=new MenuItem("Select All");
        Menu help=new Menu("Help");
        MenuItem hitem1=new MenuItem("About Notepad");
        TextArea text=new TextArea();

        BorderPane pane=new BorderPane();
        

 

        

           

        
                 
        
        
        
       

         
   
    


    @Override
    public void start(Stage primaryStage) {
        
        file.getItems().addAll(item1,item2,item3,s,item4);
        edit.getItems().addAll(eitem1,s,eitem2,eitem3,eitem4,eitem5,eitem6);
        help.getItems().add(hitem1);
        
        bar.getMenus().addAll(file,edit,help);
        pane.setTop(bar);
        pane.setCenter(text);
        Scene scene = new Scene(pane, 800, 600);
        
        item1.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              text.clear(); 
            }
        });
        item1.setAccelerator(KeyCombination.keyCombination("Alt+n"));
        item2.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text.clear();
 FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Open File");
File file=fileChooser.showOpenDialog(primaryStage);
System.out.println(file);
                FileInputStream myinput=null;
            try {
                myinput = new FileInputStream(file);
                DataInputStream data=new DataInputStream(myinput);
    try {
        text.setText(data.readLine());
    } catch (IOException ex) {
    }
            } catch (FileNotFoundException ex) {
            } finally {
                try {
                    myinput.close();
                } catch (IOException ex) {
                }
            }

            }
        });
        item3.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                         try {
FileChooser fileChooser = new FileChooser();
 fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("txt", "*.txt")
            );
     fileChooser.setTitle("Save File");
     File file=fileChooser.showSaveDialog(primaryStage);
     System.out.println(file);
            FileWriter fileWriter;
             
            fileWriter = new FileWriter(file);
            fileWriter.write(text.getText());
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            }
        });
        item4.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              primaryStage.close();
            }
        });
        
        eitem1.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            text.undo();
            }
        });
        eitem1.setAccelerator(KeyCombination.keyCombination("Alt+z"));

        eitem2.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              text.cut();
            }
        });
        eitem2.setAccelerator(KeyCombination.keyCombination("Alt+x"));

        eitem3.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              text.copy();
            }
        });
        eitem3.setAccelerator(KeyCombination.keyCombination("Alt+c"));

         eitem4.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              text.paste();
            }
        });
         eitem4.setAccelerator(KeyCombination.keyCombination("Alt+v"));

         eitem5.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              text.deleteText(text.getSelection());
            }
        });
         eitem6.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              text.selectAll();
            }
        });
        eitem6.setAccelerator(KeyCombination.keyCombination("Alt+a"));
       hitem1.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("about");
alert.setHeaderText(null);
alert.setContentText("this notepad made by Dina Gamal");

alert.showAndWait();
            }
        });
        
        primaryStage.setTitle("Notepad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
