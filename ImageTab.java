import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ImageTab {
  private class ImageInfo {
    String path;
    String name;
    Image image;
    
    public ImageInfo(String filepath, String filename){
      path = filepath;
      name = filename;
      image = new Image(filepath, true);
    }
    public Image getImage(){
      return image;
    }
    public String getName(){
      return name;
    }
    public String getPath(){
      return path;
    }
  }
  //gui
  VBox tab;
  Label imageNameLabel; 
  Label imagePathLabel;
  Label imageCountLabel;
  Text t;
  HBox buttonContainer;
  Button leftButton;
  Button rightButton;
  ImageView iv;

  //image data
  ImageInfo images[] = {
    new ImageInfo("/images/coin.png", "Purple Coin"),
    new ImageInfo("/images/potato.jpg", "Potato"),
    new ImageInfo("/images/logo.png", "Java Logo"),
    new ImageInfo("/images/snake.png", "Snake"),
  };
  int imageIndex = 0;
  
  public ImageTab(){
    t = new Text("Image Gallery");
    imageNameLabel = new Label();
    imagePathLabel = new Label();
    imageCountLabel = new Label();
    
    iv = new ImageView();
    iv.setImage(images[imageIndex].getImage());
    iv.setPreserveRatio(true);
    iv.setFitWidth(300);
    iv.setFitHeight(150);

    displayImage();
    //button container
    buttonContainer = new HBox();
    leftButton = new Button("Left"); 
    rightButton = new Button("Right");
    
    leftButton.setMinWidth(150);
    rightButton.setMinWidth(150);
    leftButton.setMaxWidth(150);
    rightButton.setMaxWidth(150);
    
    buttonContainer.getChildren().addAll(leftButton, rightButton);
    buttonContainer.setAlignment(Pos.CENTER);
    
    leftButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        imageIndex--;
        if(imageIndex < 0)
          imageIndex = images.length-1;
        displayImage();
      }
    });
    rightButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        imageIndex++;
        if(imageIndex >= images.length)
          imageIndex = 0;
        displayImage();
      }
    });
    
    tab = new VBox(t, imageCountLabel, iv, imageNameLabel, imagePathLabel, buttonContainer);
    tab.setSpacing(20);
    tab.setAlignment(Pos.CENTER);
    tab.setVisible(false);
  }
  private void displayImage(){
    iv.setImage(images[imageIndex].getImage());
    imageCountLabel.setText((imageIndex+1)+"/"+images.length);
    imageNameLabel.setText(images[imageIndex].getName());
    imagePathLabel.setText("Located at "+images[imageIndex].getPath());
  }
  
  public VBox getTab(){
    return tab;
  }
}