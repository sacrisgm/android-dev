package com.sacrisgm.crestnol;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
//import android.os.*;
//import android.util.*; 
//import android.app.*;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btnGm[][], btnRst;
    TextView txtWin;
    String btnLabel = "X";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
		
		btnGm = new Button [3][3];

        btnGm[0][0] = (Button) findViewById(R.id.btn00);
        btnGm[0][1] = (Button) findViewById(R.id.btn01);
        btnGm[0][2] = (Button) findViewById(R.id.btn02);
        btnGm[1][0] = (Button) findViewById(R.id.btn10);
        btnGm[1][1] = (Button) findViewById(R.id.btn11);
        btnGm[1][2] = (Button) findViewById(R.id.btn12);
        btnGm[2][0] = (Button) findViewById(R.id.btn20);
        btnGm[2][1] = (Button) findViewById(R.id.btn21);
        btnGm[2][2] = (Button) findViewById(R.id.btn22);
		btnRst = (Button) findViewById(R.id.btnRst);

        for(Button btnx[]: btnGm)
            for(Button btnxy: btnx)
        		btnxy.setOnClickListener(this);
		btnRst.setOnClickListener(this);

        txtWin = (TextView) findViewById(R.id.txtWin);
    }


    private boolean checkWin()
    {
        byte winCor=0, winPth=0;

        /*
        winPth направление линии победы
         0 - используем, как флаг ( отсутсвие победы )
         1 - горизонталь
         2 - вертикаль
         3 - диагональ от 0:0 к 2:2
         4 - диагональ от 0:2 к 2:0
        */
		
		for(byte i=0; i < 3; i++)
		{
			if( !btnGm[i][i].getText().toString().equals("") )
			{
				if( i == 1 )
				{
                    winPth=3; // диагональ от 0:0 к 2:2
                    for(byte j=0; j < 2; j++)
                    {
                        if(btnGm[j][j].getText() != btnGm[j+1][j+1].getText())
                        {
                            winPth=0;
                            break;
                        }
                    }
                    if(winPth!=0)
                        break;

                    winPth=4; // диагональ от 0:2 к 2:0
					for(byte j=0, z=2; j < 2; j++, z--)
					{
						if(btnGm[j][z].getText() != btnGm[j+1][z-1].getText())
                        {
                            winPth=0;
							break;
                        }
					}
                    if(winPth!=0)
                        break;

				}

                winPth=1; // горизонталь
				for(byte j=0; j < 2; j++)
				{
					if( btnGm[i][j].getText() != btnGm[i][j+1].getText() )
                    {
                        winPth=0;
                        break;
                    }
				}
                if(winPth!=0)
                {
                    winCor=i;
                    break;
                }

                winPth=2; // вертикаль
				for(byte j=0; j < 2; j++)
				{
					if( btnGm[j][i].getText() != btnGm[j+1][i].getText() )
                    {
                        winPth=0;
						break;
                    }
				}
                if(winPth!=0)
                {
                    winCor=i;
                    break;
                }

			}
		}


        if(winPth!=0)
        {
            for(Button btnx[]: btnGm)
                for(Button btnxy: btnx)
                    btnxy.setClickable(false);

            switch (winPth) // выделяем победную линию =)
            {
                case 1:                 // горизонталь
                    for(byte j=0; j < 3; j++)
                        btnGm[winCor][j].setTextColor(Color.RED);
                    break;
                case 2:                 // вертикаль
                    for(byte i=0; i < 3; i++)
                        btnGm[i][winCor].setTextColor(Color.RED);
                    break;
                case 3:                 // диагональ от 0:0 к 2:2
                    for(byte i=0; i < 3; i++)
                        btnGm[i][i].setTextColor(Color.RED);
                    break;
                case 4:                 // диагональ от 0:2 к 2:0
                    for(byte i=0, j=2; i < 3; i++, j--)
                        btnGm[i][j].setTextColor(Color.RED);
                    break;

            }


            txtWin.setText("Win " + btnLabel);

            return true;
        }

        return(false);
    }

	public void reset()
	{
        for(Button btnx[]: btnGm)
            for(Button btnxy: btnx)
            {
                btnxy.setClickable(true);
                btnxy.setText("");
                btnxy.setTextColor(Color.BLACK);
            }
		btnLabel = "X";
		txtWin.setText(R.string.game_start);
	}

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btn00:
                btnGm[0][0].setText(btnLabel);
				btnGm[0][0].setClickable(false);
                break;
            case R.id.btn01:
                btnGm[0][1].setText(btnLabel);
				btnGm[0][1].setClickable(false);
                break;
            case R.id.btn02:
				btnGm[0][2].setText(btnLabel);
				btnGm[0][2].setClickable(false);
                break;
            case R.id.btn10:
				btnGm[1][0].setText(btnLabel);
				btnGm[1][0].setClickable(false);
                break;
            case R.id.btn11:
				btnGm[1][1].setText(btnLabel);
				btnGm[1][1].setClickable(false);
                break;
            case R.id.btn12:
				btnGm[1][2].setText(btnLabel);
				btnGm[1][2].setClickable(false);
                break;
            case R.id.btn20:
				btnGm[2][0].setText(btnLabel);
				btnGm[2][0].setClickable(false);
                break;
            case R.id.btn21:
				btnGm[2][1].setText(btnLabel);
				btnGm[2][1].setClickable(false);
                break;
            case R.id.btn22:
				btnGm[2][2].setText(btnLabel);
				btnGm[2][2].setClickable(false);
                break;
			case R.id.btnRst:
				reset();
				return;
        }

        if(!checkWin())
        {
            if (btnLabel == "X")
                btnLabel = "O";
		    else
                btnLabel = "X";

            txtWin.setText("Goes: " + btnLabel);
        }
    }


}
