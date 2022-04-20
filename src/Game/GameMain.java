package Game;
/*
KingOfMongeese
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Random;

public class GameMain extends Application
{

    //get player names
    private static final String Player1 = JOptionPane.showInputDialog("Please enter Player 1's name: ");
    private static final String Player2 = JOptionPane.showInputDialog("Please enter Player 2's name: ");

    //sets intial turn
    //1 = player 1, 2 = player 2
    private static int turn = 1;

    //bottom label
    private static Label footerLabel = new Label("It is " + Player1 + "'s turn.");

    //The middle panes where player status is kept
    private static VBox player1Status = new VBox();
    private static VBox player2Status = new VBox();

    //player scores
    private static int player1score = 0;
    private static int player2score = 0;

    private static Label scoreLabel1 = new Label("Score: " + player1score);
    private static Label scoreLabel2 = new Label("Score: " + player2score);

    private static final String[] dieFaces = {"Game/DieFace1.gif", "Game/DieFace2.gif", "Game/DieFace3.gif", "Game/DieFace4.gif", "Game/DieFace5.gif", "Game/DieFace6.gif"};

    //inside the player status, this is the dice
    private static HBox player1Dice = new HBox();
    private static HBox player2Dice = new HBox();

    private static int masterDie1 = 0;
    private static int masterDie2 = 0;

    private static boolean isGamerOver = false;

    private static HBox footer = new HBox();


    public static void player1Keep1()
    {

        //for the keep 1 button on player 1
        //turn prevents this players button from being clicked when it is not there turn
        if (turn == 1)
        {
        if (!isGamerOver) {
            //increments based on the chosen die
            player1score += masterDie1;
            if (player1score == 30) {
                player1Win();
            } else if (player1score > 30) {
                player1Bust();
            }

            //game could end in the above tree
            if (!isGamerOver) {
                scoreLabel1.setText("Score: " + player1score);
                footerLabel.setText("It is " + Player2 + "'s turn.");
                turn = 2;
                roll();
            }
        }  }
    }

    public static void player1Keep2()
    {
        //see above
        if (turn == 1) {
            if (!isGamerOver) {
                player1score += masterDie2;
                if (player1score == 30) {
                    player1Win();
                } else if (player1score > 30) {
                    player1Bust();
                }

                if (!isGamerOver) {
                    scoreLabel1.setText("Score: " + player1score);
                    footerLabel.setText("It is " + Player2 + "'s turn.");
                    turn = 2;
                    roll();
                }
            }
        }
    }

    public static void player1KeepBoth()
    {
        //see above
        if (turn == 1) {
            if (!isGamerOver) {
                player1score += masterDie2 + masterDie1;
                if (player1score == 30) {
                    player1Win();
                } else if (player1score > 30) {
                    player1Bust();
                }

                if (!isGamerOver) {
                    scoreLabel1.setText("Score: " + player1score);
                    footerLabel.setText("It is " + Player2 + "'s turn.");
                    turn = 2;
                    roll();
                }
            }
        }
    }

    public static void player2Keep1()
    {
        //see above
        if (turn == 2) {
            if (!isGamerOver) {
                player2score += masterDie1;
                if (player2score == 30) {
                    player2Win();
                } else if (player2score > 30) {
                    player2Bust();
                }

                if (!isGamerOver) {
                    scoreLabel2.setText("Score: " + player2score);
                    footerLabel.setText(("It is " + Player1 + "'s turn."));
                    turn = 1;
                    roll();
                }
            }
        }
    }

    public static void player2Keep2()
    {
        //see above
        if (turn == 2) {
            if (!isGamerOver) {
                player2score += masterDie2;
                if (player2score == 30) {
                    player2Win();
                } else if (player2score > 30) {
                    player2Bust();
                }

                if (!isGamerOver) {
                    scoreLabel2.setText("Score: " + player2score);
                    footerLabel.setText(("It is " + Player1 + "'s turn."));
                    turn = 1;
                    roll();
                }
            }
        }
    }

    public static void player2KeepBoth()
    {
        //see above
        if (turn == 2) {
            if (!isGamerOver) {
                player2score += masterDie1;
                player2score += masterDie2;
                if (player2score == 30) {
                    player2Win();
                } else if (player2score > 30) {
                    player2Bust();
                }

                if (!isGamerOver) {
                    scoreLabel2.setText("Score: " + player2score);
                    footerLabel.setText(("It is " + Player1 + "'s turn."));
                    turn = 1;
                    roll();
                }
            }
        }
    }

    private static void endOfGame()
    {
        //called when the game ends
        Button resetButton = new Button("Play again?");
        Button exitButton = new Button("Exit");
        resetButton.setOnAction(e -> reset());
        exitButton.setOnAction(e -> System.exit(0));
        footer.setSpacing(10);
        footer.getChildren().addAll(resetButton,exitButton);
    }


    public static void reset()
    {
        //resests the game

        player1score = 0;
        player2score = 0;

        footer.getChildren().remove(1,3);
        isGamerOver = false;
        scoreLabel1.setText("Score: " + player1score);
        scoreLabel2.setText("Score " + player2score);
        if (turn == 1)
        {
            footerLabel.setText("It is " + Player1 + "'s turn.");
        }
        else
        {
            footerLabel.setText("It is " + Player2 + "'s turn.");
        }

    }

    public static void player1Win()
    {

        isGamerOver = true;
        footerLabel.setText(Player1 + " wins! Congrats.");
        scoreLabel1.setText("Score: " + player1score);

        endOfGame();

    }

    public static void player2Win()
    {
        isGamerOver = true;
        footerLabel.setText(Player2 + " wins! Congrats.");
        scoreLabel2.setText("Score: " + player2score);

        endOfGame();

    }

    public static void player1Bust()
    {

        player1score = 0;
        scoreLabel1.setText("Score " + player1score);

    }

    public static void player2Bust()
    {

        player2score = 0;
        scoreLabel2.setText("Score " + player2score);

    }




    public static void roll()
    {
        //rolls 2D6
        Random random = new Random();
        int die1 = random.nextInt(6);
        int die2 = random.nextInt(6);

        //chooses images based on dies, and sets the border
        if (turn == 1)
        {
            player1Dice.getChildren().set(0, new ImageView( new Image(dieFaces[die1])));
            player1Dice.getChildren().set(1, new ImageView((new Image(dieFaces[die2]))));
            player1Status.setStyle("-fx-border-style: solid outside;" + "-fx-border-insets: 0;" + "-fx-border-color: RED;" + "-fx-border-width: 4;");
            player2Status.setStyle("-fx-box-border: transparent");
        }
        else if (turn == 2)
        {
            player2Dice.getChildren().set(0, new ImageView(new Image(dieFaces[die1])));
            player2Dice.getChildren().set(1, new ImageView(new Image(dieFaces[die2])));
            player2Status.setStyle("-fx-border-style: solid outside;" + "-fx-border-insets: 0;" + "-fx-border-color: RED;" + "-fx-border-width: 4;");
            player1Status.setStyle("-fx-box-border: transparent");
        }

        //masterdies used for scoring
        masterDie1 = die1 + 1;
        masterDie2 = die2 + 1;

    }

    @Override
    public void start(Stage stage)
    {
        //The root pane
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(5,75,5,75));

        //Layout
        HBox header = new HBox();
        HBox middle = new HBox();


        //header.setStyle("-fx-border-style: solid inside;" + "-fx-border-insets: 5;");
        //footer.setStyle("-fx-border-style: solid inside;" + "-fx-border-insets: 5;");

        Label topLabel = new Label("Welcome " + Player1 +" and " + Player2);

        //ading the labels, the bottom will change
        header.getChildren().add(topLabel);
        footer.getChildren().add(footerLabel);

        //defualt images
        Image image = new Image("Game/DieFace1.gif");
        player1Dice.getChildren().addAll(new ImageView(image), new ImageView(image));
        player2Dice.getChildren().addAll(new ImageView(image), new ImageView(image));

        Label player1Header = new Label(Player1);
        Label player2Header = new Label(Player2);

        //border used for trouble shooting
        //have outline change based on whose turn?
        //player1Status.setStyle("-fx-border-style: solid outside;" + "-fx-border-insets: 0;" + "-fx-border-color: RED;" + "-fx-border-width: 4;");
        //player2Status.setStyle("-fx-border-style: solid inside;" + "-fx-border-insets: 5;");

        //The keep buttons
        HBox player1Buttons = new HBox();
        HBox player2Buttons = new HBox();

        player1Buttons.setPadding(new Insets(5,20,5,20));
        player2Buttons.setPadding(new Insets(5,20,5,20));
        player1Buttons.setSpacing(40);
        player2Buttons.setSpacing(40);

        //dice btns
        Button player1Dice1 = new Button("Keep Dice 1");
        Button player1Dice2 = new Button("Keep Dice 2");
        Button player2Dice1 = new Button("Keep Dice 1");
        Button player2Dice2 = new Button("Keep Dice 2");

        //buttons linked to cmds
        player1Dice1.setOnAction(e -> player1Keep1());
        player1Dice2.setOnAction(e -> player1Keep2());
        player2Dice1.setOnAction(e -> player2Keep1());
        player2Dice2.setOnAction(e -> player2Keep2());

        player1Buttons.getChildren().addAll(player1Dice1, player1Dice2);
        player2Buttons.getChildren().addAll(player2Dice1, player2Dice2);

        //The keep both Buttons
        HBox lastButtons1 = new HBox();
        HBox lastButtons2 = new HBox();

        lastButtons1.setPadding(new Insets(5, 90 ,5, 75));
        lastButtons2.setPadding(new Insets(5,90,5,75));

        Button keepBoth1 = new Button("Keep Both");
        Button keepBoth2 = new Button("Keep Both");

        //btns linked to cmds
        keepBoth1.setOnAction(e -> player1KeepBoth());
        keepBoth2.setOnAction(e -> player2KeepBoth());

        lastButtons1.getChildren().add(keepBoth1);
        lastButtons2.getChildren().add(keepBoth2);

        HBox scoreBox1 = new HBox();
        HBox scoreBox2 = new HBox();

        scoreBox1.getChildren().add(scoreLabel1);
        scoreBox2.getChildren().add(scoreLabel2);

        //Building the player status
        player1Status.getChildren().addAll(player1Header, scoreBox1, player1Dice, player1Buttons,lastButtons1);
        player2Status.getChildren().addAll(player2Header, scoreBox2, player2Dice, player2Buttons,lastButtons2);


        //the HBox that contains player status
        middle.setSpacing(10);
        middle.setPadding(new Insets(5,-30,5,-30));
        middle.getChildren().addAll(player1Status,player2Status);

        //adding to root
        root.getChildren().addAll(header, middle, footer);

        //Starting
        Scene scene = new Scene(root, 600,360);
        stage.setScene(scene);
        stage.setTitle("30 or Bust");
        stage.show();
        roll();


    }

    public static void main(String[] args)
    {
        launch();

    }
}
