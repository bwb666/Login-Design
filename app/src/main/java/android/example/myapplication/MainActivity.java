package android.example.myapplication;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText re_password;
    private EditText re_email;
    private Button button_login;
    private Button button_register;
    private Button button_linktoLogin;
    private Button button_linktoRegister;
    private EditText login_email;
    private EditText login_password;
    private Button button_logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        //setContentView(R.layout.layout_login);
        //setContentView(R.layout.activity_main);

        re_password=findViewById(R.id.re_password);
        re_email=findViewById(R.id.re_email);
        button_register=findViewById(R.id.btnRegister);
        button_linktoLogin=findViewById(R.id.btnLinkToLoginScreen);


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTask task=new registerTask();
                task.execute();
            }
        });

        button_linktoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.layout_login);
                button_login = findViewById(R.id.btnLogin);
                login_email = findViewById(R.id.lo_email);
                login_password = findViewById(R.id.lo_password);
                button_login = findViewById(R.id.btnLogin);
                button_linktoRegister = findViewById(R.id.btnLinkToRegisterScreen);
                button_linktoRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.layout_register);
                        re_password = findViewById(R.id.re_password);
                        re_email = findViewById(R.id.re_email);
                        button_register = findViewById(R.id.btnRegister);
                        button_linktoLogin = findViewById(R.id.btnLinkToLoginScreen);

                        button_register.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                registerTask task = new registerTask();
                                task.execute();
                            }
                        });

                        button_linktoLogin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setContentView(R.layout.layout_login);
                                button_login = findViewById(R.id.btnLogin);
                            }
                        });

                    }
                });

                button_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loginTask task=new loginTask();
                        task.execute();
                    }
                });
            }
        });



    }

    class registerTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
            editor.putString("email",re_email.getText().toString());
            editor.putString("password",re_password.getText().toString());
            editor.apply();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this, "Sign up success!", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.layout_login);
            login_email=findViewById(R.id.lo_email);
            login_password=findViewById(R.id.lo_password);
            button_login=findViewById(R.id.btnLogin);
            button_linktoRegister=findViewById(R.id.btnLinkToRegisterScreen);
            button_linktoRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setContentView(R.layout.layout_register);
                    re_password=findViewById(R.id.re_password);
                    re_email=findViewById(R.id.re_email);
                    button_register=findViewById(R.id.btnRegister);
                    button_linktoLogin=findViewById(R.id.btnLinkToLoginScreen);



                    button_register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            registerTask task=new registerTask();
                            task.execute();
                        }
                    });

                    button_linktoLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.layout_login);
                            button_login=findViewById(R.id.btnLogin);
                        }
                    });

                }
            });

            button_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginTask task=new loginTask();
                    task.execute();
                }
            });
        }
    }

    class loginTask extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
            String email_store=pref.getString("email","");
            String password_store=pref.getString("password","");
            if(login_email.getText().toString().equals(email_store)){
                if(login_password.getText().toString().equals(password_store)){
                    return true;
                }
            }else{
                return false;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean==true){
                Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_main);
                button_logout=findViewById(R.id.btnLogout);
                button_logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.layout_login);
                    }
                });
            }else{
                Toast.makeText(MainActivity.this, "Name not exist!", Toast.LENGTH_SHORT).show();
            }
        }
    }



}







