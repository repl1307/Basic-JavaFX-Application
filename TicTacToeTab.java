import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.ArrayList;

public class TicTacToeTab {
  private class Cell {
    public String mark;
    int xPos;
    int yPos;
    Button button;
    
    public Cell(String mark, int xPos, int yPos){
      this.mark = mark;
      this.xPos = xPos;
      this.yPos = yPos;
      button = new Button();
      button.setStyle("-fx-font-size: 24; -fx-border-color: black;");
      
      
      int size = 50;
      button.setMinWidth(size);
      button.setMinHeight(size);
      button.setMaxWidth(size);
      button.setMaxHeight(size);
      
      button.setAlignment(Pos.CENTER);
      button.setText(" ");
      addButtonClickEvent();
    }
    private void addButtonClickEvent(){
      button.setOnAction(new EventHandler<ActionEvent>(){
        @Override public void handle(ActionEvent e){
          if(mark.equals(" ") && !gameOver){
            mark = player;
            button.setText(mark);
            updateGame();
            System.out.println("Mark: "+mark);
            System.out.println("Player: "+player);
          }
        }
      });
    }
    public Button getElement(){
      return button;
    }
    public int getXPos(){
      return xPos;
    }
    public int getYPos(){
      return yPos;
    }
    public String getText(){
      return button.getText();
    }
    @Override
    public String toString(){
      return "Mark: "+mark+" XPos: "+xPos+"YPos: "+yPos;
    }
  }
  //gui
  VBox tab;
  Text header;
  GridPane grid;
  HBox buttonsContainer;
  Button startGame;
  Label winnerLabel;
  Cell[][] cells;
  String player = "x";
  boolean gameOver = false;
  
  public TicTacToeTab(){
    grid = new GridPane();
    createGrid(3);

    winnerLabel = new Label("Player "+player.toUpperCase()+"'s turn");
    
    startGame = new Button("New Game");
    startGame.setMinWidth(100);
    startGame.setMinHeight(30);
    startGame.setOnAction(new EventHandler<ActionEvent>(){
      @Override public void handle(ActionEvent e){
        player = "x";
        gameOver = false;
        winnerLabel.setText("Player "+player.toUpperCase()+"'s turn");
        for(int i = 0; i < cells.length; i++){
          for(int j = 0; j < cells[0].length; j++){
            cells[i][j].getElement().setText(" ");
            cells[i][j].mark = " ";
          }
        }
      }
    });

    
    buttonsContainer = new HBox();
    buttonsContainer.setAlignment(Pos.CENTER);
    buttonsContainer.getChildren().addAll(startGame);

    header = new Text("Tic Tac Toe");
    tab = new VBox(header, winnerLabel, grid, buttonsContainer);
    tab.setSpacing(20);
    tab.setAlignment(Pos.CENTER);
    tab.setVisible(false);
  }
  
  private void createGrid(int size){
    grid.setAlignment(Pos.CENTER);

    cells = new Cell[size][size];
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        Cell c = new Cell(" ", i, j);
        cells[i][j] = c;
        grid.add(c.getElement(), c.getXPos(), c.getYPos());
      }
    }
  }
  private void updateGame(){
    checkWin(player);
    if(!gameOver){
      player = player == "x"? "o" : "x";
      winnerLabel.setText("Player "+player.toUpperCase()+"'s turn");
    }
  }
  private void checkWin(String player){
    //3 in a column
    for(int i = 0; i < cells.length; i++){
      if(cells[i][0].getText().equals(player) && 
         cells[i][1].getText().equals(player) &&
         cells[i][2].getText().equals(player)){
          gameOver = true;
          winnerLabel.setText("Game Over! "+player.toUpperCase()+" has won!");
          return;
      }
    }
    //3 in a row
    for(int i = 0;  i < cells[0].length; i++){
      if(cells[0][i].getText().equals(player) && 
         cells[1][i].getText().equals(player) &&
         cells[2][i].getText().equals(player)){
          gameOver = true;
          winnerLabel.setText("Game Over! "+player.toUpperCase()+" has won!");
          return;
      }
    }
    //3 diagonally
    if(cells[0][0].getText().equals(player) && cells[1][1].getText().equals(player) && cells[2][2].getText().equals(player)){
        gameOver = true;
        winnerLabel.setText("Game Over! "+player.toUpperCase()+" has won!");
        return;
    }
    if(cells[0][2].getText().equals(player) && cells[1][1].getText().equals(player) && cells[2][0].getText().equals(player)){
        gameOver = true;
        winnerLabel.setText("Game Over! "+player.toUpperCase()+" has won!");
        return;
    }
  }
  public VBox getTab(){
    return tab;
  }
}