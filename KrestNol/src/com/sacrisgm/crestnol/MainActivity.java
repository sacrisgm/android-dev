package com.sacrisgm.crestnol;

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
    TextView txtWin, txtDbg;
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
		
        txtWin = (TextView) findViewById(R.id.txtWin);
		txtDbg = (TextView) findViewById(R.id.txtDbg);
        for(byte i=0; i < 3; i++)
			for(byte j=0; j < 3; j++)
        		btnGm[i][j].setOnClickListener(this);
		btnRst.setOnClickListener(this);

    }


    public boolean checkWin()
    {
		
		for(byte i=0; i < 3; i++)
		{
			if( !btnGm[i][i].getText().toString().equals("") )
			{	
				if( i == 1 )
				{
					for(byte j=0, z=2; j < 3; j++, z--)
					{
						if(j==2)
						{
							return true;
						}
						if(btnGm[j][z].getText() != btnGm[j+1][z-1].getText())
							break;
					}
					
					for(byte j=0; j < 3; j++)
					{
						if(j==2)
						{
							return true;
						}
						if(btnGm[j][j].getText() != btnGm[j+1][j+1].getText())
							break;
					}
				}
				
				for(byte j=0; j < 3; j++)
				{
					if( j==2 )
					{
						return true;
					}
					if( btnGm[i][j].getText() != btnGm[i][j+1].getText() )
						break;
				}
				

				for(byte j=0; j < 3; j++)
				{
					if( j==2 )
					{
						return true;
					}
					if( btnGm[j][i].getText() != btnGm[j+1][i].getText() )
						break;
				}
			}
		}
		/*
        for(int i=0; i < 3; i++)
             if( !btnGm[0][i].getText().toString().equals("") && (btnGm[0][i].getText() == btnGm[1][i].getText() ) &&  ( btnGm[1][i].getText().toString()== btnGm[2][i].getText().toString() ) )
             	win();
		for(int i=0; i < 3; i++)
			if( btnGm[i][0].getText().toString() != "" && (btnGm[i][0].getText().toString() == btnGm[i][0].getText().toString() ) &&  ( btnGm[i][1].getText().toString()== btnGm[i][2].getText().toString() ) )
             	win();
		
		if( btnGm[0][2].getText().toString() != "" && (btnGm[0][2].getText() == btnGm[1][1].getText().charAt(0) == btnGm[0][2].getText().charAt(0) ) )
			win();
		*/
    return false;    
    }

    public void win()
    {

		for(byte i=0; i < 3; i++)
			for(byte j=0; j < 3; j++)
				btnGm[i][j].setClickable(false);
        if (btnLabel == "X")
            txtWin.setText("Win O");
        else
            txtWin.setText("Win X");
    }
	
	public void reset()
	{
		for(byte i=0; i < 3; i++)
			for(byte j=0; j < 3; j++)
			{
				btnGm[i][j].setClickable(true);
				btnGm[i][j].setText("");
			}
		
		btnLabel = "X";
		txtWin.setText("Goes: X");
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
		
        if (btnLabel == "X")
		{
            btnLabel = "O";
			txtWin.setText("Goes: O");
        }
		else
		{
            btnLabel = "X";
			txtWin.setText("Goes: X");
		}
		
		if(checkWin())
			win();
		
    }


}
