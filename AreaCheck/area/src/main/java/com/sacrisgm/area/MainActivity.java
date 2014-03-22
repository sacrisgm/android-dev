package com.sacrisgm.area;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    EditText area[][];

    byte area_byte[][];
    Button btn_rst;
    TextView txt_check;
    String TAG="MyLog:";

    private static final byte str=3, col=3;

    private byte numbCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        area = new EditText [str][col];
        area_byte = new byte [str][col];

        area[0][0] = (EditText) findViewById(R.id.edit_00);
        area[0][1] = (EditText) findViewById(R.id.edit_01);
        area[0][2] = (EditText) findViewById(R.id.edit_02);
        area[1][0] = (EditText) findViewById(R.id.edit_10);
        area[1][1] = (EditText) findViewById(R.id.edit_11);
        area[1][2] = (EditText) findViewById(R.id.edit_12);
        area[2][0] = (EditText) findViewById(R.id.edit_20);
        area[2][1] = (EditText) findViewById(R.id.edit_21);
        area[2][2] = (EditText) findViewById(R.id.edit_22);

        btn_rst = (Button) findViewById(R.id.btn_rst);
        txt_check = (TextView) findViewById(R.id.txt_check);

        InputFilter customFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence arg0, int arg1, int arg2, Spanned arg3, int arg4, int arg5) {
                //Log.d(TAG, "arg0: " + arg0 + "; arg1: " + arg1 + "; arg2: " + arg2 + "; arg4: " + arg4 + "; arg5: " + arg5);
                if(arg4 < 1 && arg0.length() > 0) {
                    if (arg0.charAt(0) == '0') {
                        return "";
                    }
                    for (byte i = 0; i < str; i++)
                        for (byte j = 0; j < col; j++) {
                            if (area_byte[i][j] == (byte) arg0.charAt(0))
                                return "";
                        }
                    numbCount++;
                    return null;
                }
                return "";
            }

        };

        for(byte i=0; i < str; i++)
            for(byte j=0; j < col; j++) {
                area[i][j].addTextChangedListener(new CustomTextWatcher(area[i][j]));
                area[i][j].setFilters(new InputFilter[]{customFilter});
            }
        btn_rst.setOnClickListener(this);


    }


    private void reset()
    {
        for(byte i=0; i < str; i++)
            for(byte j=0; j < col; j++)
            {
                area[i][j].setText("");
                area_byte[i][j]=0;
            }


        txt_check.setText(R.string.txt_check);

        numbCount=0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rst:
                reset();
                break;
        }
    }


    private void check() {
        byte sum;

        check:
        {
            sum=0;
            // Проверяем строки на сумму ( 15 )
            for (byte i = 0; i < str; i++) {
                for (byte j = 0; j < col; j++)
                    sum = (byte)(sum + area_byte[i][j]);
                if (sum != 15)
                    break check;
                sum=0;
            }

            // Проверяем столбцы...
            for (byte j = 0; j < col; j++) {
                for (byte i = 0; i < str; i++)
                    sum += area_byte[i][j];
                if (sum != 15)
                    break check;
                sum=0;
            }

            // Проверяем диагональ 0:0 to 2:2...
            for (byte j = 0; j < str; j++)
                sum += area_byte[j][j];
            if (sum != 15)
                break check;
            sum=0;
            // Проверяем диагональ 0:2 2:0...
            for (byte i = 0, j = 2; i < str; i++, j--)
                sum += area_byte[i][j];
            if (sum != 15)
                break check;

            txt_check.setText("Yeah! All right!");
            for (EditText areaX[]: area)
                for (EditText areaXY: areaX)
                    areaXY.setBackgroundColor(Color.GREEN);

                    return;
        }

        resetTE();

    }

    private void resetTE() {
        txt_check.setText(R.string.txt_check);
        for (EditText areaX[]: area)
            for (EditText areaXY: areaX) {
                if(numbCount == 9)
                    areaXY.setBackgroundColor(Color.RED);
                else
                    areaXY.setBackgroundColor(Color.WHITE);
            }
    }



    private class CustomTextWatcher implements TextWatcher {
        private EditText mEditText;
        //private byte editNum;

        public CustomTextWatcher(EditText e) {
            mEditText = e;
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable s) {
                switch (mEditText.getId()) {
                    case R.id.edit_00:
                        if (mEditText.length() == 0) {
                            area_byte[0][0] = 0;
                            numbCount--;
                            resetTE();
                        } else {
                            area_byte[0][0] = (byte) (mEditText.getText().charAt(0) - 48);
                            Log.d(TAG, "AREA 00: " + area_byte[0][0]);
                        }
                        break;

                    case R.id.edit_01:
                        if (mEditText.length() == 0) {
                            area_byte[0][1] = 0;
                            numbCount--;
                            resetTE();
                        } else
                            area_byte[0][1] = (byte) (mEditText.getText().charAt(0) - 48);
                        break;

                    case R.id.edit_02:
                        if (mEditText.length() == 0) {
                            area_byte[0][2] = 0;
                            numbCount--;
                            resetTE();
                        } else
                            area_byte[0][2] = (byte) (mEditText.getText().charAt(0) - 48);
                        break;

                    case R.id.edit_10:
                        if (mEditText.length() == 0) {
                            area_byte[1][0] = 0;
                            numbCount--;
                            resetTE();
                        } else
                            area_byte[1][0] = (byte) (mEditText.getText().charAt(0) - 48);
                        break;

                    case R.id.edit_11:
                        if (mEditText.length() == 0) {
                            area_byte[1][1] = 0;
                            numbCount--;
                            resetTE();
                        } else
                            area_byte[1][1] = (byte) (mEditText.getText().charAt(0) - 48);
                        break;

                    case R.id.edit_12:
                        if (mEditText.length() == 0) {
                            area_byte[1][2] = 0;
                            numbCount--;
                            resetTE();
                        } else
                            area_byte[1][2] = (byte) (mEditText.getText().charAt(0) - 48);
                        break;

                    case R.id.edit_20:
                        if (mEditText.length() == 0) {
                            area_byte[2][0] = 0;
                            numbCount--;
                            resetTE();
                        } else
                            area_byte[2][0] = (byte) (mEditText.getText().charAt(0) - 48);
                        break;

                    case R.id.edit_21:
                        if (mEditText.length() == 0) {
                            area_byte[2][1] = 0;
                            numbCount--;
                            resetTE();
                        } else
                            area_byte[2][1] = (byte) (mEditText.getText().charAt(0) - 48);
                        break;

                    case R.id.edit_22:
                        if (mEditText.length() == 0) {
                            area_byte[2][2] = 0;
                            numbCount--;
                            resetTE();
                        } else
                            area_byte[2][2] = (byte) (mEditText.getText().charAt(0) - 48);
                        break;
                }

                if(numbCount == 9)
                    check();
        }
    }

}
