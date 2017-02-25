package com.mallow.michael.linuxcommandcheatsheet;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private final String[] commands = {"cd", "move into directory", "pwd", "print working directory", "ls", "list directory content", "touch",
            "Create an empty file", "mkdir", "creates a new directory", "df", "displays amount of disk space", "echo", "repeats a string to output",
    "free", "displays amount of free and used memory", "passwd", "updates user's password", "ps", "reports status of current processes", "shutdown",
    "turns off the computer", "sudo", "gives admin root access", "tar", "creates tar archive"};
    private TableLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (TableLayout) findViewById(R.id.layout);
        fillTable();
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544/6300978111");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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
        if (id == R.id.action_suggestions) {
            sendEmail();
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

    //method created by User Psypher from stackOverflow
    //link to thread: http://stackoverflow.com/questions/28546703/how-to-code-using-android-studio-to-send-an-email
    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"mallowdev@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Linux Command CheatSheet request");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Intent in = new Intent();
            //startActivity(in, MainActivity.class);
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
