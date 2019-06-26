package nj.com.loginsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText name,email,password,c_password;
    private TextView phoneno2;
    private Button btn_regist;
    private ProgressBar loading;
    String GetEditText1,GetEditText2,GetEditText3,GetEditText4;
    private static String URL_REGIST="https://sizeable-associates.000webhostapp.com/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading =findViewById(R.id.loading);
        name=findViewById(R.id.edittextn);
        email=findViewById(R.id.edittexte);
        password=findViewById(R.id.edittextp);
        c_password=findViewById(R.id.edittextcp);
        btn_regist=findViewById(R.id.btn_regist);
        phoneno2=findViewById(R.id.phoneno2);
        phoneno2.setText(getIntent().getStringExtra("phno"));


        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetEditText1 = name.getText().toString();
                GetEditText2 = email.getText().toString();
                GetEditText3 = password.getText().toString();
                GetEditText4 = c_password.getText().toString();

                if(TextUtils.isEmpty(GetEditText1) || TextUtils.isEmpty(GetEditText2) || TextUtils.isEmpty(GetEditText3) || TextUtils.isEmpty(GetEditText4)) {
                    StringBuilder alert2 = new StringBuilder();
                    alert2.append("Fill in all details !!!");
                    Toast.makeText(getApplicationContext(), alert2.toString(),Toast.LENGTH_LONG).show();
                }
                else if(!GetEditText3.equals(GetEditText4)){
                    StringBuilder alert3 = new StringBuilder();
                    alert3.append("Passwords do not match !!!");
                    Toast.makeText(getApplicationContext(), alert3.toString(),Toast.LENGTH_LONG).show();
                }
                else if(GetEditText3.length() < 8){
                    StringBuilder alert5 = new StringBuilder();
                    alert5.append("Passwords should contain atleast 8 characters!!!");
                    Toast.makeText(getApplicationContext(), alert5.toString(),Toast.LENGTH_LONG).show();
                }
                else {
                    Regist();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String name=this.name.getText().toString().trim();
        final String email=this.email.getText().toString().trim();
        final String phno2=this.phoneno2.getText().toString().trim();
        final String password=this.password.getText().toString().trim();


        StringRequest stringRequest =new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{

                            JSONObject jsonObject =new JSONObject(response);
                            String msg=jsonObject.getString("success");
                            if(msg.equals("1")){
                                Toast.makeText(RegisterActivity.this,"Register Success!",Toast.LENGTH_LONG).show();
                                loading.setVisibility(View.GONE);
                                btn_regist.setVisibility(View.VISIBLE);

                            }else  if(msg.equals("0")){
                                Toast.makeText(RegisterActivity.this,"Registration not done",Toast.LENGTH_LONG).show();
                                loading.setVisibility(View.GONE);
                                btn_regist.setVisibility(View.VISIBLE);
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this,"Register error!"+e.toString(),Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,"Register error!"+error.toString(),Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);

                    }
                })
        {

            protected Map<String,String>getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("name",name);
                params.put("email",email);
                params.put("phone",phno2);
                params.put("password",password);
                return params;

            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
