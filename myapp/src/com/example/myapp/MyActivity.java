package com.example.myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.myapp.MESSAGE";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn = (Button) findViewById(R.id.go);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText)MyActivity.this.findViewById(R.id.edt);
                TextView txt= (TextView)MyActivity.this
                        .findViewById(R.id.txt);
                txt.setText(getString(R.string.msg_dialog)+editText.getText());
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //groupId,itemid,orderId,txt
        menu.add(0,1,1,R.string.menu1_txt);
        menu.add(0,2,1,R.string.menu2_txt);
        return true;
    }
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        switch(id) {
            case 1:
//                AlertDialog.OnShowListener() {
                new AlertDialog.Builder(MyActivity.this) .setMessage(R.string.showTxt).show();
//                return true;
            case 2:
                finish();
                break;
            }
        return true;
    }
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edt);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

}

