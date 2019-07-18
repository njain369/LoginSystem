package nj.com.loginsystem;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SendSmsandRecv extends AppCompatActivity {

    EditText etphone,etMsg;
    Button btnSendSms,btnReadSms,signout;

    ListView lvSms;
    ArrayList<String> smsData =new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    private final int REQ_CODE_PERMISSION_SEND_SMS=121;
    private final int REQ_CODE_PERMISSION_READ_SMS=121;
    private final int REQ_CODE_PERMISSION_RECEIVE_SMS=121;



    private final String SERVER ="https://sizeable-associates.000webhostapp.com/save_sms0.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_smsand_recv);


       etphone=findViewById(R.id.etphone);
       etMsg =findViewById(R.id.etMsg);

       btnReadSms = findViewById(R.id.btnreadsms);
       btnSendSms =findViewById(R.id.btnsendsms);
       signout = findViewById(R.id.signout);
       lvSms=findViewById(R.id.lvsms);
       arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,smsData);
       lvSms.setAdapter(arrayAdapter);

        StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
       btnReadSms.setEnabled(false);
       btnSendSms.setEnabled(false);

        // Obtain the FirebaseAnalytics instance.

        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
/**********Sending SMS************/
       if(checkPermission(Manifest.permission.SEND_SMS)){
           btnSendSms.setEnabled(true);

       }else{
           ActivityCompat.requestPermissions(SendSmsandRecv.this,
                   new String[] {Manifest.permission.SEND_SMS},
           REQ_CODE_PERMISSION_SEND_SMS);
       }

/**********Reading SMS************/
        if(checkPermission(Manifest.permission.READ_SMS)){
            btnReadSms.setEnabled(true);

        }else{
            ActivityCompat.requestPermissions(SendSmsandRecv.this,
                    new String[] {Manifest.permission.READ_SMS},
                    REQ_CODE_PERMISSION_READ_SMS);
        }

        /**********Receiving SMS************/
        if(checkPermission(Manifest.permission.RECEIVE_SMS)){
            btnSendSms.setEnabled(true);

        }else{
            ActivityCompat.requestPermissions(SendSmsandRecv.this,
                    new String[] {Manifest.permission.RECEIVE_SMS},
                    REQ_CODE_PERMISSION_RECEIVE_SMS);
        }
        /******Checking permission end****/

   btnReadSms.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           ContentResolver cr=getContentResolver();
           Cursor c= cr.query(Uri.parse("content://sms/inbox"),
                   null,null,null,null);

           /***GET all Messages Here/*/

           StringBuffer info =new StringBuffer();
           for(int i=0;i<c.getColumnCount();i++){
               info.append("Column_Name: "+c.getColumnName(i) + "\n");
           }

           Toast.makeText(SendSmsandRecv.this,info.toString(),Toast.LENGTH_SHORT).show();

           int indexbody =c.getColumnIndex("body");
           int indexPhone =c.getColumnIndex("address");
           int indexDate=c.getColumnIndex("date");
           int indexDateSent =c.getColumnIndex("date_sent");

           if(indexbody<0 || !c.moveToFirst()) return;

           arrayAdapter.clear();
           do{
               String phone =c.getString(indexPhone);
               String msg=c.getString(indexbody);
               String str="SMS from: "+phone+"\n"+msg;

               String ddate =c.getString(indexDate);
               String dateSent =c.getString(indexDateSent);

               DateFormat df =new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
               Date thisDate =new Date(Long.parseLong(ddate));
               ddate=df.format(thisDate);
                thisDate =new Date(Long.parseLong(dateSent));
                dateSent =df.format(thisDate);
                str +="\nDate: "+ddate;
                str +="\nDateSent"+dateSent;

                arrayAdapter.add(str);

   //Send sms to database
           }while(c.moveToNext());
           c.close();
       }
   });

   btnSendSms.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String phonenum=etphone.getText().toString();
           String message=etMsg.getText().toString();

          // SmsManager smsManager =SmsManager.getDefault();
           //smsManager.sendTextMessage(phonenum,null,message,null,null);

//           Toast.makeText(SendSmsandRecv.this,"Sms sent to "+phonenum,Toast.LENGTH_LONG).show();
           //TODO REcord all sending sms ,which are sending using app;

           try {
               // Construct data
               String apiKey = "apikey=" + "AYEmQHWVmr4-ExkZdbv7dc3DsIu4DV0VMv3v9RFkBk";
               String message1 = "&message=" +message;
               String sender = "&sender=" + "Niraj Jain";
               String numbers = "&numbers=" +phonenum;

               // Send data
               HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
               String data = apiKey + numbers + message1 + sender;
               conn.setDoOutput(true);
               conn.setRequestMethod("POST");
               conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
               conn.getOutputStream().write(data.getBytes("UTF-8"));
               final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
               final StringBuffer stringBuffer = new StringBuffer();
               String line;
               while ((line = rd.readLine()) != null) {
                   stringBuffer.append(line);
               }
               rd.close();
                Toast.makeText(getApplicationContext(),"Yeah Message send successfully",Toast.LENGTH_SHORT).show();
               //return stringBuffer.toString();
           } catch (Exception e) {
               //System.out.println("Error SMS "+e);
               //return "Error "+e;
               Toast.makeText(getApplicationContext(),"ERROR_SMS"+e,Toast.LENGTH_SHORT).show();

           }

       }
   });

   signout.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           finish();

            startActivity(new Intent(SendSmsandRecv.this,LoginActivity.class));

       }
   });
/*For Session MANAGEMENT
        HashMap<String,String> user=sessionManager.getUserDetail();
       String mName =user.get(sessionManager.NAME);
        String mEmail =user.get(sessionManager.EMAIL);
    */
    }

    private boolean checkPermission(String permission){
        int permissionCode = ContextCompat.checkSelfPermission(this,permission);
        return permissionCode == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  int[] grantResults) {

        switch(requestCode){
            case REQ_CODE_PERMISSION_READ_SMS:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    btnReadSms.setEnabled(true);
                    btnSendSms.setEnabled(true);
                }
                break;

        }
    }



    public void sendSMS(String Phone,String msg,String ddate,String dateSend){


// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url =SERVER+"?phone="+Phone;
       url += "&msg" +Uri.encode(msg);
        url += "&date=" +Uri.encode(ddate);

        url += "&date_sent=" +Uri.encode(dateSend);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
