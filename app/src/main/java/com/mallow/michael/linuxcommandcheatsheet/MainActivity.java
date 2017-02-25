package com.mallow.michael.linuxcommandcheatsheet;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String[] commands = {"cd", "move into directory", "pwd", "print working directory", "ls", "list directory content", "touch [filename]", "Create an empty file"};
    private TableLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (TableLayout) findViewById(R.id.layout);
        fillTable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Used to fill the main TableView with TableRows filled with the commands
     */
    private void fillTable(){
        TableRow tableRows[] = new TableRow[commands.length /2];
        for(int j = 0; j < commands.length-1; j+=2) {
            int i = 0;
            tableRows[i] = new TableRow(this);
            int lengthFirst;
            int lengthSecond;
            TextView spaces = new TextView(this);


            TextView t1 = new TextView(this);
            t1.setTextColor(Color.parseColor("#00FF2E"));
            t1.setLayoutParams(new android.widget.TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
            lengthFirst = (int) t1.getTextSize();
            t1.setText(commands[j]);
            tableRows[i].addView(t1);


            t1 = new TextView(this);
            t1.setText(commands[j+1]);
            t1.setTextColor(Color.parseColor("#00FF2E"));
            lengthSecond = (int) t1.getTextSize();
            spaces.setText(numSpaces(lengthFirst, tableRows[i].getMeasuredWidth(), lengthSecond));
            tableRows[i].addView(spaces);
            tableRows[i].addView(t1);
            layout.addView(tableRows[i]);
            i++;
        }
    }

    /**
     *
     * @param word  length of the first word
     * @param lengthOfLine  length of the line
     * @param word2     length of the second word
     * @return  a String with all of the spaces
     */
    private String numSpaces(int word, int lengthOfLine, int word2) {
        int temp =lengthOfLine- (word+word2 + 1);  //adding +1 for the '|' in the middle of the line
        String retVal = "";
        for(int i =0; i < temp; i++)
            retVal = retVal + " ";
        return retVal;
    }
}
