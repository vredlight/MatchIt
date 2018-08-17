package com.example.ved.matchit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0-blue  ,, 1-yellow
    int activePlayer = 0;

    int[] gamestate = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5,},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameIsActive = true;


    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gamestate[tappedCounter] == 2 && gameIsActive){

            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.blue_striker);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.red_striker);
            activePlayer = 0;
        }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(400);

        }


          for(int[] winningPosition : winningPositions){

              if(gamestate[winningPosition[0]] == gamestate[winningPosition[1]]
                      && gamestate[winningPosition[1]] == gamestate[winningPosition[2]]
                      && gamestate[winningPosition[0]] != 2)
              {
                    // someone has won..
                    gameIsActive = false;

                    String winner = "Red";

                    if(gamestate[winningPosition[0]] == 0)
                        winner = "Blue";

                    String winnerMsg = (winner + " has won..!");

                  TextView msg = (TextView) findViewById(R.id.winnerMsg);
                  msg.setText(winnerMsg);

                  LinearLayout winnerTemplate = (LinearLayout) findViewById(R.id.winnerTemplate);
                  winnerTemplate.setVisibility(View.VISIBLE);

                  GridLayout board = (GridLayout) findViewById(R.id.board);
                  board.setAlpha(.3f);
                  board.setClickable(false);

              } else{
                      boolean gameIsOver = true;

                      for (int counterState : gamestate)
                          if (counterState == 2) gameIsOver = false;


                      if(gameIsOver){

                          TextView msg = (TextView) findViewById(R.id.winnerMsg);
                          msg.setText("GAME is Drawn..!");

                          LinearLayout winnerTemplate = (LinearLayout) findViewById(R.id.winnerTemplate);
                          winnerTemplate.setVisibility(View.VISIBLE);

                          GridLayout board = (GridLayout) findViewById(R.id.board);
                          board.setAlpha(.3f);
                          board.setClickable(false);
                      }


              }
        }

        }

        public void playAgain(View view){

            GridLayout board = (GridLayout) findViewById(R.id.board);
            board.setAlpha(1);
            board.setClickable(true);

            int activePlayer = 0;

           for (int i =0 ; i < gamestate.length ; i++)
               gamestate[i] = 2;

           for(int i = 0 ; i < board.getChildCount() ; i++) {
               ((ImageView)board.getChildAt(i)).setImageResource(0);
           }

            gameIsActive = true;

            LinearLayout winnerTemplate = (LinearLayout) findViewById(R.id.winnerTemplate);
            winnerTemplate.setVisibility(View.GONE);

            }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
