package com.example.client1.savedemotwo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{

    EditText editText1,editText2;
    TextView textViewResult;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 =(EditText)findViewById(R.id.editText1);
        editText2 =(EditText)findViewById(R.id.editText2);
        textViewResult =(TextView)findViewById(R.id.resultTextView);
    }

    // write text to file
    public void WriteBtn(View v)
    {
        // add-write text into file
        try
        {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(editText1.getText().toString());
            outputWriter.write(editText2.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void ReadBtn(View v)
    {
        //reading text from file
        try
        {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0)
            {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
           // Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();
            textViewResult.setText(s);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}