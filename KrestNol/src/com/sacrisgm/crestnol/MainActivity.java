package com.sacrisgm.crestnol;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener{

    Button btnGm[][], btnRst;
    TextView textDebug;
    String btnLabel = "X";
    byte clickCount=0;
	
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

        textDebug = (TextView) findViewById(R.id.textDebug);

    }


    private boolean checkWin()
    {
        boolean winFlag=false;
        textDebug.setText("In checkWin");
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
            textDebug.setText("In first for");
			if( btnGm[i][i].getText().toString().equals(btnLabel) )
			{
				if( i == 1 ) // проверка диагоналей
				{
                    textDebug.setText("In if i == 1");
                    // диагональ от 0:0 к 2:2
                    winFlag=false;
                    for(byte j=0; j < 3; j++)
                        if( btnGm[j][j].getText().toString().equals(btnLabel) )
                            winFlag=true;

                    if(winFlag)
                    {
                        for(byte j=0; j < 3; i++)
                            btnGm[j][j].setTextColor(Color.RED);
                        break;
                    }


                    // диагональ от 0:2 к 2:0
                    winFlag=false;
					for(byte j=0, z=2; j < 3; j++, z--)
						if( btnGm[j][z].getText().toString().equals(btnLabel) )
                            winFlag=true;

                    if(winFlag)
                    {
                        for(byte j=0, z=2; j < 3; j++, z--)
                            btnGm[j][z].setTextColor(Color.RED);
                        break;
                    }

				}

                // горизонталь
                winFlag=false;
				for(byte j=0; j < 3; j++)
					if( btnGm[i][j].getText().toString().equals(btnLabel) )
                        winFlag=true;

                if(winFlag)
                {
                    for(byte j=0; j < 3; j++)
                        btnGm[i][j].setTextColor(Color.RED);
                    break;
                }

                // вертикаль
                winFlag=false;
				for(byte j=0; j < 3; j++)
					if( btnGm[j][i].getText().toString().equals(btnLabel) )
                        winFlag=true;

                if(winFlag)
                {
                    for(byte j=0; j < 3; j++)
                        btnGm[j][i].setTextColor(Color.RED);
                    break;
                }

			}
		}

        if(winFlag)
        {
            for(Button btnx[]: btnGm)
                for(Button btnxy: btnx)
                    btnxy.setClickable(false);
            return true;
        }

        return false;
    }


	private void selectTrueWay()
	{
		for(Button btnx[]: btnGm)
            for(Button btnxy: btnx)

                if(btnxy.getText() == "")
				{
					btnxy.setTextColor(Color.GRAY);
					btnxy.setText(btnLabel);
				}
	}
	

	private void reset()
	{
        for(Button btnx[]: btnGm)
            for(Button btnxy: btnx)
            {
                btnxy.setClickable(true);
                btnxy.setText("");
                btnxy.setTextColor(Color.BLACK);
            }
        clickCount = 0;
		btnLabel = "X";
	}



    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btn00:
				btnGm[0][0].setTextColor(Color.BLACK);
                btnGm[0][0].setText(btnLabel);
				btnGm[0][0].setClickable(false);
                break;
            case R.id.btn01:
                btnGm[0][0].setTextColor(Color.BLACK);
                btnGm[0][1].setText(btnLabel);
				btnGm[0][1].setClickable(false);
                break;
            case R.id.btn02:
                btnGm[0][0].setTextColor(Color.BLACK);
				btnGm[0][2].setText(btnLabel);
				btnGm[0][2].setClickable(false);
                break;
            case R.id.btn10:
                btnGm[0][0].setTextColor(Color.BLACK);
				btnGm[1][0].setText(btnLabel);
				btnGm[1][0].setClickable(false);
                break;
            case R.id.btn11:
                btnGm[0][0].setTextColor(Color.BLACK);
				btnGm[1][1].setText(btnLabel);
				btnGm[1][1].setClickable(false);
                break;
            case R.id.btn12:
                btnGm[0][0].setTextColor(Color.BLACK);
				btnGm[1][2].setText(btnLabel);
				btnGm[1][2].setClickable(false);
                break;
            case R.id.btn20:
                btnGm[0][0].setTextColor(Color.BLACK);
				btnGm[2][0].setText(btnLabel);
				btnGm[2][0].setClickable(false);
                break;
            case R.id.btn21:
                btnGm[0][0].setTextColor(Color.BLACK);
				btnGm[2][1].setText(btnLabel);
				btnGm[2][1].setClickable(false);
                break;
            case R.id.btn22:
                btnGm[0][0].setTextColor(Color.BLACK);
				btnGm[2][2].setText(btnLabel);
				btnGm[2][2].setClickable(false);
                break;
			case R.id.btnRst:
				reset();
				return;
        }
        clickCount++;

        textDebug.setText("Debug check ok");

        if(clickCount > 4) // победным может быть 5+ ход
            if(checkWin())
                return;

        btnLabel = (btnLabel.equals("X") ? "O": "X");
	   // selectTrueWay();

    }


}
