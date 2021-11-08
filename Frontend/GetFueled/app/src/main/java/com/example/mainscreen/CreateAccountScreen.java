package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the activity for creating an account. It allows to user to input a username, email, password
 * and adds it to the dataabse of users.
 */
//@author-Andrea Gameros
public class CreateAccountScreen extends AppCompatActivity {

    private EditText Email;
    private EditText UserName;
    private EditText Password;
    private EditText Password2;
    private Button CreateAccount;
    private Button SigninHere;
    RequestQueue requestQueue;

    /**
     * Creates screen for create account screen based on create_account_screen.xml
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_screen);

        Email = (EditText)findViewById(R.id.etEmail);
        UserName = (EditText)findViewById(R.id.etUserName);
        Password = (EditText)findViewById(R.id.etPassword);
        Password2 = (EditText)findViewById(R.id.etPassword2);
        CreateAccount = (Button)findViewById(R.id.btnCreateAccount);
        SigninHere = (Button)findViewById(R.id.btnSigninHere);

        /**
         * When create account button is clicked the info is put into the database with createAccount()
         */
        CreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                createAccount(Email.getText().toString(),UserName.getText().toString(), Password.getText().toString(), Password2.getText().toString());
            }
        });

        /**
         * When sign in here button is clicked, it brings the user back to the login screen.
         */
        SigninHere.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent newIntent = new Intent(view.getContext(), LoginScreen.class);
                startActivity(newIntent);
            }
        });
    }

    /**
     * Inputs everything into database
     * @param email
     * @param username
     * @param password
     * @param password2
     */
    private void createAccount(String email, String username, String password, String password2)
    {
        //if passwords don't match let user know
        if(checkPassword(password, password2) == false)
        {
            Toast.makeText(CreateAccountScreen.this,"Passwords don't match", Toast.LENGTH_LONG).show();
        }

        //if passwords match send to database
        else
        {
            PostRequest();
        }
    }

    /**
     * Checks the passwords match
     * @param password
     * @param password2
     * @return
     */
    private boolean checkPassword(String password, String password2)
    {
        //then store all information to database
        if (password.equals(password2)) {
            return true;
        }
        //send error message
        return false;
    }

    /**
     * POST account to database based on given info
     */
    private void PostRequest()
    {
        final String username, email, password;
        requestQueue = Volley.newRequestQueue(this);
        //String url = "https://8710b90a-ebe0-4f8f-956e-5c6998590fe8.mock.pstmn.io/Post";
        String url = "http://coms-309-059.cs.iastate.edu:8080/user";

        username = UserName.getText().toString();
        email = Email.getText().toString();
        password = Password.getText().toString();

        abstract class MyJsonArrayRequest extends JsonRequest<JSONArray> {


            public MyJsonArrayRequest(int method, String url, JSONObject jsonRequest,
                                      Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
                super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                        errorListener);
            }
        }

        JSONObject object = new JSONObject();

        try
        {
            object.put("name", username);
            object.put("email", email);
            object.put("password", password);
            object.put("roleType", "user");
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }



        MyJsonArrayRequest jsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(CreateAccountScreen.this, "Account created", Toast.LENGTH_LONG).show();
                        Intent newIntent = new Intent(CreateAccountScreen.this, LoginScreen.class);
                        startActivity(newIntent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(CreateAccountScreen.this, "Failed to create account", Toast.LENGTH_LONG).show();
            }
        })
        {
            /**
             @Override
             protected Map<String, String> getParams()
             {
             Map<String, String> params = new HashMap<>();
             String u = UserName.getText().toString();
             String e = Email.getText().toString();
             String p = Password.getText().toString();
             msgResponse.append(u + " " + e + " " + p);
             System.out.println(u + " " + e + " " + p);
             params.put("username", UserName.getText().toString());
             params.put("email", Email.getText().toString());
             params.put("password", Password.getText().toString());

             return params;
             }
             **/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                String responseString;
                JSONArray array = new JSONArray();
                if (response != null) {

                    try {
                        responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        JSONObject obj = new JSONObject(responseString);
                        (array).put(obj);
                    } catch (Exception ex) {
                    }
                }
                //return array;
                return Response.success(array, HttpHeaderParser.parseCacheHeaders(response));
            }

        };


        //requestQueue = Volley.newRequestQueue(CreateAccountPage.this);
        requestQueue.add(jsonArrayRequest);
    }
}