package com.example.myapplication____tictactoe;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.GridLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int active_player=0;
    boolean game_is_active = true;

    int[] game_state = {2,2,2,2,2,2,2,2,2};
    int [][] winning_positions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){

        ImageView counter=(ImageView) view;

        int tapped_counter = Integer.parseInt(counter.getTag().toString());
        if (game_state[tapped_counter] ==2 && game_is_active) {
            game_state[tapped_counter] = active_player;
            counter.setTranslationY(-1000f);
            if (active_player == 0) {
                counter.setImageResource(R.drawable.circle);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.x_new);
                active_player = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int [] winning_position :winning_positions){
                if (game_state[winning_position[0]] == game_state[winning_position[1]]
                        && game_state[winning_position[1]] == game_state[winning_position[2]]
                        && game_state[winning_position[0]]!=2)
                {//somebody wins
                    game_is_active = false;
                    String winner= " ";

                    if (game_state[winning_position[0]] == 1) {
                        winner = "Cross";
                    }
                    if (game_state[winning_position[0]] == 0) {
                        winner = "Circle";
                    }



                    TextView winners_message = (TextView) findViewById(R.id.winners_message);
                    winners_message.setText(winner + " has Won!!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.play_again_layout);
                    layout.setVisibility(View.VISIBLE);
                }else
                {
                    boolean game_is_over = true;
                    for(int counter_game : game_state)
                    {
                        if (counter_game==2)
                        {
                            game_is_over = false;
                        }
                    }
                    if (game_is_over )
                    { TextView winners_message = (TextView) findViewById(R.id.winners_message);
                        winners_message.setText("Draw!!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.play_again_layout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }




            }

        }}
    public void play_again(View view)
    { game_is_active =true;
        LinearLayout layout =(LinearLayout) findViewById(R.id.play_again_layout);
        layout.setVisibility(View.INVISIBLE);
        active_player = 0;


        for (int i = 0 ; i< game_state.length; i++){
            game_state[i] = 2;
        }
        GridLayout grid_layout = (GridLayout) findViewById(R.id.grid_layout);

        for (int i = 0;i<grid_layout.getChildCount();i++)
        {
            ((ImageView) grid_layout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }}