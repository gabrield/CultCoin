package hackathon.cultcoin;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;



public class CultcoinActivity extends AppCompatActivity {

    //DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cultcoin);
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.coin);

        //DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
        //Log.d("Insert: ", "Inserting ..");
        //db.addUser(new User("Gabriel", "10570835771", "123456"));


    }

    /** Called when the user clicks the Login button */
    public void login(View view) {
        final Intent intent = new Intent(this, UserChoice.class);
        final EditText cpf = (EditText) findViewById(R.id.cpf);
        final EditText passwd = (EditText) findViewById(R.id.password);
        Log.d("CPF", cpf.getText().toString() );
        Log.d("Password", passwd.getText().toString());



        String url = "http://10.0.1.8/check_user/" + cpf.getText().toString() + "/" + passwd.getText().toString();
        //String url = "http://google.com";
        Log.d("URL", url);
        final AsyncHttpClient client = new AsyncHttpClient();
        //client.setProxy("192.168.0.100", 80);
        //RequestParams params = new RequestParams();
        //params.put("key", "value");
        //params.put("more", "data");

        client.get(url, null, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"
                        Log.d("OK", "200");
                        try {
                            JSONObject reader = new JSONObject(res);
                            intent.putExtra("cpf", cpf.getText().toString());
                            intent.putExtra("passwd", passwd.getText().toString());
                            Log.d("RESPONSE", res);
                            intent.putExtra("name", reader.get("name").toString());
                            intent.putExtra("coins", reader.get("coins").toString());
                        } catch (JSONException e){}

                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.d("ERROR", String.valueOf(statusCode));
                        client.cancelAllRequests(true);
                    }
                }
        );



    }



    /** Called when the user clicks the Register button */
    public void register(View view) {
        final Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        // Do something in response to button
    }




}
