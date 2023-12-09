import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Main extends Application 
{ 
  Pane currentTab;
  @Override
  public void start(Stage primaryStage) {

    BorderPane root;
    StackPane tabContainer;
    Pane[] tabs = {
        new TextFieldTab().getTab(),
        new ImageTab().getTab(),
        new TicTacToeTab().getTab(),
    };
    currentTab = tabs[0];
    currentTab.setVisible(true);
    VBox tab1;
    VBox tab2;
    GridPane navBar;
    Scene scene;

    //tab container
    tabContainer = new StackPane();
    for(Pane p : tabs){
      tabContainer.getChildren().add(p);
    }
    tabContainer.setAlignment(Pos.CENTER);
    
    //navigation bar
    Button[] navButtons = {
      new Button("Text Field"),
      new Button("Image Gallery"),
      new Button("Tic Tac Toe"),
    };
    
    for(int i = 0; i < navButtons.length; i++){
      Button b = navButtons[i];
      b.setMinWidth(250);
      b.setMaxWidth(500);
      b.setOnAction(new EventHandler<ActionEvent>(){
        @Override public void handle(ActionEvent e){
          int index = 0;
          for(int i = 0; i < navButtons.length; i++){
            if(navButtons[i] == b){
              index = i;
              break;
            }
          }
          System.out.println("Pressed Tab "+index);
          currentTab.setVisible(false);
          currentTab = tabs[index];
          currentTab.setVisible(true);
        }
      });
    }
  
    navBar = new GridPane();
    ColumnConstraints colSizing = new ColumnConstraints();
    colSizing.setHgrow(Priority.ALWAYS);
    for(int i = 0; i < navButtons.length; i++){
      navBar.getColumnConstraints().add(colSizing);
      navBar.add(navButtons[i], i, 0);
    }

    //final setup
    root = new BorderPane();
    root.setCenter(tabContainer);
    root.setTop(navBar);
    scene = new Scene(root, 750, 450);
    
    primaryStage.setTitle("Basic Javafx Application");
    primaryStage.setScene(scene);
    primaryStage.show();
  } 
    
  public static void main(String[] args) {
    launch(args);
  }
} 
