import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.ArrayList;

public class TextFieldTab {
  //gui
  VBox tab;
  GridPane messageContainer; 
  int rowCount = 0;
  Text t;
  TextField tf;
  Button submitButton;
  ArrayList<Message> messages = new ArrayList<Message>();

  private class Message {
    String text;
    int index;
    Label textElement;
    Button deleteElement;

    public Message(String messageText, int messageIndex){
      text = messageText;
      index = messageIndex;

      textElement = new Label(messageText);
      textElement.setWrapText(true);
      deleteElement = new Button("Delete");
      deleteElement.setMinWidth(100);
      deleteElement.setMaxWidth(100);
      deleteElement.setMinHeight(30);
      deleteElement.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
          messageContainer.getChildren().remove(deleteElement);
          messageContainer.getChildren().remove(textElement);
          messages.remove(index);
          for(int i = 0; i < messages.size(); i++){
            messages.get(i).index = messages.indexOf(messages.get(i));
          }
          rowCount--;
          System.out.println("Row Count: "+rowCount+" Message Count: "+messages.size());
        }
      });
      messageContainer.add(textElement, 0, rowCount);
      messageContainer.add(deleteElement, 1, rowCount);
      rowCount++;
      System.out.println("Row Count: "+rowCount+" Message Count: "+messages.size());
    }
    public String getText(){
      return text;
    }
    public int getIndex(){
      return index;
    }
  }
  
  public TextFieldTab(){
    t = new Text("Text Field Thing");
    tf = new TextField("Text Field!");
    tf.setMaxWidth(200);
    
    messageContainer = new GridPane();
    ColumnConstraints textColSizing = new ColumnConstraints(300);
    textColSizing.setHgrow(Priority.ALWAYS);
    messageContainer.getColumnConstraints().add(textColSizing);

    messageContainer.setMinWidth(400);
    messageContainer.setMaxWidth(405);

    ScrollPane sp = new ScrollPane();
    sp.setPrefSize(422, 200);
    sp.setMaxWidth(422);
    sp.setContent(messageContainer);
    sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    //messageContainer.setAlignment(Pos.CENTER);
    messageContainer.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;");
    submitButton = new Button("Add"); 
    submitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        addMessage(tf.getText());
        updateMessages();
      }
    });
    tab = new VBox(t, sp, tf, submitButton);
    tab.setSpacing(20);
    tab.setAlignment(Pos.CENTER);
    tab.setVisible(false);
  }
  private void addMessage(String messageText){
    int index = messages.size();
      
    System.out.println("New Message Index: "+index);
    Message newMessage = new Message(messageText, index);
    messages.add(newMessage);
  }
  private void updateMessages(){
    return;
  }
  public VBox getTab(){
    return tab;
  }
}