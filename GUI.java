import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class GUI extends Application {
    public Stage stage;
    final int BORDER_WIDTH = 10;

    public void start(Stage stage) { 
        GridPane main = new GridPane();
        main.setHgap(10);
        main.setVgap(10);

        main.setLayoutX(BORDER_WIDTH);
        main.setLayoutY(BORDER_WIDTH);
        main.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));



        for (int i = 0; i < 9; i++) {
            GridPane pane = new GridPane();
            pane.setLayoutX(BORDER_WIDTH);
            pane.setLayoutY(BORDER_WIDTH);
            pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            pane.setHgap(7);
            pane.setVgap(7);

            for (int j = 0; j < 9; j++) {
                int index = 0;
                switch (i) { //different offset depending on which row and which column
                    case 0:
                    case 3:
                    case 6: //first column
                        if (j <= 2) 
                            index = (9*i+j); 
                        else if (j <= 5) 
                            index = (9*i+j+6);
                        else 
                            index = (9*i+j+12);
                        break;
                    case 1:
                    case 4:
                    case 7: //second column
                        if (j <= 2) 
                            index = (9*i+j-6);
                        else if (j <= 5) 
                            index = (9*i+j);
                        else 
                            index = (9*i+j+6);
                        break;
                    case 2:
                    case 5:
                    case 8: //third column
                        if (j <= 2) 
                            index = (9*i+j-12);
                        else if (j <= 5) 
                            index = (9*i+j-6);
                        else 
                            index = (9*i+j);
                        break;
                }


                if (Sudoku.grid[index] != 0) {
                    Label label = new Label(String.valueOf(Sudoku.grid[index]));
                    label.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
                    label.setStyle("-fx-text-fill: black");
                    label.setMinSize(75, 75);
                    label.setMaxSize(75, 75);
                    label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));                    
                    label.setAlignment(Pos.CENTER);
                    pane.add(label, j%3, j/3);
                } else {
                    RestrictiveTextField textField = new RestrictiveTextField(); 

                    textField.setBoxNum(i);
                    textField.setIndex(index);

                    textField.setFont(Font.font("Verdana", 40));
                    textField.setStyle("-fx-text-fill: gray");
                    textField.setMaxLength(1);
                    textField.setRestrict("[0-9]");
                    
                    textField.setMinSize(75, 75);
                    textField.setMaxSize(75, 75);
                    
                    textField.setAlignment(Pos.CENTER);
                    pane.add(textField, j%3, j/3);

                    textField.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            if (Sudoku.valid(Sudoku.grid, textField.getIndex(), textField.getBoxNum(), Integer.parseInt(textField.getText()))) {
                                textField.setStyle("-fx-text-fill: gray");
                            } else
                                textField.setStyle("-fx-text-fill: red");

                            //Sudoku.display(Sudoku.grid);

                            if (Sudoku.getCompleted()) {
                                System.out.println("Congratulations! You won!");
                                stage.close();
                            }
                        }
                    });
                }
            }
            main.add(pane, i%3, i/3);
        }

        Scene sc = new Scene(main, 757, 757, Color.GRAY);//9 box 900, 8 divider 80, 2 padding 20
 
        stage.setScene(sc);
        stage.setTitle("Sudoku");
        stage.show();
    }
 
    public static void main(String args[]) {
        launch(args);       
    }
}