package com.sacrisgm.crestnol;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button btnGm[][], btnRst;
    private byte area[][];
	private byte btnLabel = 2; // 1 - O; X - 2;
    private byte clickCount=0;
	
	private static final byte str=3, col=3;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		area = new byte [str][col];
		btnGm = new Button [str][col];

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

        for(Button btnX[]: btnGm)
            for(Button btnXY: btnX)
        		btnXY.setOnClickListener(this);
		btnRst.setOnClickListener(this);

    }


    private boolean checkWin()
    {
        boolean winFlag=false;
		
		for(byte i=0; i < str; i++)
		{
			if( area[i][i]==btnLabel )
			{
				if( i == 1 ) // проверка диагоналей
				{
                    // диагональ от 0:0 к 2:2
                    winFlag=true;
                    for(byte j=0; j < str; j++)
                        if( !(area[j][j]==btnLabel) )
                        {
							winFlag=false;
							break;
						}
                    
					if(winFlag)
                    {
                        for(byte j=0; j < str; j++)
                            btnGm[j][j].setTextColor(Color.RED);
                        break;
                    }

                    // диагональ от 0:2 к 2:0
                    winFlag=true;
					for(byte j=0, z=(byte)(col-1); j < str; j++, z--)
						if( !(area[j][z]==btnLabel) )
                        {
							winFlag=false;
							break;
						}
                    
					if(winFlag)
                    {
                        for(byte j=0, z=(byte)(col-1); j < str; j++, z--)
                            btnGm[j][z].setTextColor(Color.RED);
                        break;
                    }
					
				}

                // горизонталь
                winFlag=true;
				for(byte j=0; j < col; j++)
					if( !(area[i][j]==btnLabel) )
                    {
						winFlag=false;
						break;
					}
					
                if(winFlag)
                {
                    for(byte j=0; j < col; j++)
                        btnGm[i][j].setTextColor(Color.RED);
                    break;
                }

                // вертикаль
                
				winFlag=true;
				for(byte j=0; j < str; j++)
					if( !(area[j][i]==btnLabel) )
                    {
						winFlag=false;
						break;
					}
					
                if(winFlag)
                {
                    for(byte j=0; j < str; j++)
                        btnGm[j][i].setTextColor(Color.RED);
                    break;
                }

			}
		}
		
		if(winFlag)
		{
			for(Button btnX[]: btnGm)
				for(Button btnXY: btnX)
					btnXY.setClickable(false);
			return(true);
		}
		
        return false;
    }


	private void selectTrueWay()
	{
		for(byte i=0; i < str; i++)
            for(byte j=0; j < col; j++)
                if(area[i][j] == 0 || area[i][j] > 2)
					area[i][j]=(byte)(btnLabel+2);

		drawBtnGm();
	}
	

	private void reset()
	{	
		for(byte i=0; i < str; i++)
			for(byte j=0; j < col; j++)
			{
				area[i][j]=0;
				btnGm[i][j].setText("");
				btnGm[i][j].setClickable(true);
				//btnGm[i][j].setBackgroundColor(Color.LTGRAY);
			}
			
        clickCount = 0;
		btnLabel = 2;
	}

	private void drawBtnGm()
	{
		/*
			0 = empty
			1 = O
			2 = X
			3 = grey O
			4 = grey X
		*/
		
		for(byte i=0; i < str; i++)
			for(byte j=0; j < col; j++)
			{
				if(area[i][j]==1)
				{
					btnGm[i][j].setTextColor(Color.BLACK);
					btnGm[i][j].setText("O");
					btnGm[i][j].setClickable(false);
				}

				if(area[i][j]==2)
				{
					btnGm[i][j].setTextColor(Color.BLACK);
					btnGm[i][j].setText("X");
					btnGm[i][j].setClickable(false);
				}
				
				if(area[i][j]==3)
				{
					btnGm[i][j].setTextColor(Color.WHITE);
					btnGm[i][j].setText("O");
				}
				

				if(area[i][j]==4)
				{
					btnGm[i][j].setTextColor(Color.WHITE);
					btnGm[i][j].setText("X");
				}	
				
			}
	}

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.btn00:
				area[0][0]=btnLabel;
                break;
            case R.id.btn01:
                area[0][1]=btnLabel;
                break;
            case R.id.btn02:
                area[0][2]=btnLabel;
				break;
            case R.id.btn10:
				area[1][0]=btnLabel;
                break;
            case R.id.btn11:
                area[1][1]=btnLabel;
				break;
            case R.id.btn12:
				area[1][2]=btnLabel;
                break;
            case R.id.btn20:
                area[2][0]=btnLabel;
				break;
            case R.id.btn21:
                area[2][1]=btnLabel;
				break;
            case R.id.btn22:
                area[2][2]=btnLabel;
				break;
			case R.id.btnRst:
				reset();
				return;
        }
        clickCount++;
		
		drawBtnGm();

        if( clickCount > 4 ) // победным может быть 5+ ход
            if( checkWin() )
                return;
		
        btnLabel = (btnLabel == (byte)1 ? (byte)2: (byte)1);
	    selectTrueWay();
    }


}
