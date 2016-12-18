package com.tom.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView animationIV;
    //private Button buttonA, buttonB, buttonC;
    private AnimationDrawable animationDrawable;
    private ImageView mImgViewScissors, mImgViewRock, mImgViewPaper, mImgViewPlayer;
    private TextView mTxtViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        animationIV = (ImageView) findViewById(R.id.animationIV);
        animationIV.setImageResource(R.drawable.animation1);
        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
        animationDrawable.start();

        mImgViewScissors = (ImageView) findViewById(R.id.imgViewScissors);
        mImgViewRock = (ImageView) findViewById(R.id.imgViewRock);
        mImgViewPaper = (ImageView) findViewById(R.id.imgViewPaper);
        mImgViewPlayer = (ImageView) findViewById(R.id.imgViewPlayer);
        mTxtViewResult = (TextView) findViewById(R.id.txtViewResult);

        mImgViewScissors.setOnClickListener(imgViewPlayOnClick);
        mImgViewRock.setOnClickListener(imgViewPlayOnClick);
        mImgViewPaper.setOnClickListener(imgViewPlayOnClick);
    }

    private View.OnClickListener imgViewPlayOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            mImgViewScissors.setOnClickListener(null);
            mImgViewRock.setOnClickListener(null);
            mImgViewPaper.setOnClickListener(null);
            int iComPlay = (int) (Math.random() * 3 + 1);
            animationDrawable.stop();
            if(iComPlay == 1){
                animationIV.setImageResource(R.drawable.scissors);
            }else if(iComPlay == 2) {
                animationIV.setImageResource(R.drawable.rock);
            }else{
                animationIV.setImageResource(R.drawable.paper);
            }

            String result = "";
            //Player
            switch (v.getId()) {
                case R.id.imgViewScissors:
                    // do something
                    Log.d("tag", "剪刀");
                    mImgViewPlayer.setImageResource(R.drawable.scissors);
                    if(iComPlay == 1){
                        result = "平手";
                    }else if(iComPlay == 2){
                        result = "電腦贏";
                    }else{
                        result = "玩家贏";
                    }
                    break;
                case R.id.imgViewRock:
                    // do something else
                    Log.d("tag", "石頭");
                    mImgViewPlayer.setImageResource(R.drawable.rock);
                    if(iComPlay == 1){
                        result = "玩家贏";
                    }else if(iComPlay == 2){
                        result = "平手";
                    }else{
                        result = "電腦贏";
                    }
                    break;
                case R.id.imgViewPaper:
                    // i'm lazy, do nothing
                    Log.d("tag", "布");
                    mImgViewPlayer.setImageResource(R.drawable.paper);
                    if(iComPlay == 1){
                        result = "電腦贏";
                    }else if(iComPlay == 2){
                        result = "玩家贏";
                    }else{
                        result = "平手";
                    }
                    break;
            }

            mTxtViewResult.setText(result);

            new Thread(new Runnable(){
                @Override
                public void run() {
                    try{
                        Thread.sleep(5000);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    finally{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                animationIV.setImageResource(R.drawable.animation1);
                                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                                mImgViewScissors.setOnClickListener(imgViewPlayOnClick);
                                mImgViewRock.setOnClickListener(imgViewPlayOnClick);
                                mImgViewPaper.setOnClickListener(imgViewPlayOnClick);
                                mImgViewPlayer.setImageResource(android.R.color.transparent);
                                mTxtViewResult.setText("");
                                animationDrawable.start();
                            }
                        });

                    }
                }
            }).start();
        }
    };
}
