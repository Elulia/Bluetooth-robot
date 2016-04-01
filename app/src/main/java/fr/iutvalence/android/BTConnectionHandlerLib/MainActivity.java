package fr.iutvalence.android.BTConnectionHandlerLib;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blancare.bluetoothrobot.R;

import java.io.IOException;

import fr.iutvalence.android.BTConnectionHandlerLib.exceptions.BTHandlingException;

public class MainActivity extends Activity {

    private BTConnectionHandler connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.connect = new BTConnectionHandler(this);
    }

    public void buttonConnect(View v) throws BTHandlingException, IOException {
        EditText deviceName = (EditText)findViewById(R.id.deviceName);
        /*Toast t = Toast.makeText(this, deviceName.getText(), Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();*/
        try {
            this.connect.connectToBTDevice(deviceName.getText().toString());
        }catch(BTHandlingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void commande(View v){
        EditText t = (EditText)findViewById(R.id.sendButton);
        try {
            this.connect.sendData(t.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BTHandlingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
