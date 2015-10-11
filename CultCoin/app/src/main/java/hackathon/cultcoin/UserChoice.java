package hackathon.cultcoin;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class UserChoice extends AppCompatActivity {


    private String cpf;
    private String passwd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);
        TextView qtd_cc    = (TextView) findViewById(R.id.qtd_cultcoin);
        TextView  you_got  = (TextView) findViewById(R.id.you_got);
        Bundle extras = getIntent().getExtras();
        String qtd = (String) extras.get("coins");
        String name = (String) extras.get("name");
        qtd_cc.setText(qtd);
        you_got.setText("Olá " + name + ", você tem");
    }


    public void scan_qr(View v) {
        Intent qrDroid = new Intent("la.droid.qr.scan"); //Set action "la.droid.qr.scan"
        startActivityForResult(qrDroid, 0);
        //Log.d("Insert: ", "Inserting ..");
    }


    public void pay_activity(View v) {
        Intent pay = new Intent(this, PayActivity.class);
        startActivity(pay);
    }




    @Override
    /**
     * Reads data scanned by user and returned by QR Droid
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( 0 == requestCode && null!=data && data.getExtras()!=null ) {
            //Read result from QR Droid (it's stored in la.droid.qr.result)
            String result = data.getExtras().getString("la.droid.qr.result");
            //Just set result to Toast to be able to view it
            int idx = result.indexOf('/');
            String name = result.substring(0, idx);
            final String coins = result.substring(idx + 2, result.length());

            Bundle extras = getIntent().getExtras();
            cpf = (String) extras.get("cpf");
            passwd = (String) extras.get("passwd");



            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:

                            String url = "http://10.0.1.8/update_coins/" + cpf + "/" + passwd + "/" + coins;
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
                                            TextView qtd_cc  = (TextView) findViewById(R.id.qtd_cultcoin);
                                            int qtd = Integer.valueOf(qtd_cc.getText().toString()) + Integer.valueOf(coins);
                                            qtd_cc.setText(String.valueOf(qtd));
                                            Log.d("Value", String.valueOf(qtd));
                                        }

                                        @Override
                                        public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                                            Log.d("ERROR", String.valueOf(statusCode));
                                            client.cancelAllRequests(true);
                                        }
                                    }
                            );
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Você está no " + name + ". Deseja coletar " + coins + " CultCoins?")
                    .setPositiveButton("Yep", dialogClickListener)
                    .setNegativeButton("Nop", dialogClickListener).show();




            Toast.makeText(getApplicationContext(), coins, Toast.LENGTH_SHORT).show();
        }
    }
}
