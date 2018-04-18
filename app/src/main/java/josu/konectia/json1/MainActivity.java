package josu.konectia.json1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        attemptoLogin();

        TextView datos = (TextView) findViewById(R.id.id_datos);

    }

    private void attemptoLogin(){


        new Thread(new Runnable(){

            @Override
            public void run(){

                Response response;

                HttpClient client = new HttpClient();
                try{

                    response = client.get();


                    if(response.isSuccessful() && !response.toString().isEmpty()){
                        parseResponse(response);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseResponse(Response response){

        try{

            String responseString = response.body().string();
            response.close();

            JSONObject jsonObject = new JSONObject(responseString);

            //Recogemos cada elelmentos del Json
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String surname = jsonObject.getString("surname");
            String email = jsonObject.getString("email");
            String password = jsonObject.getString("password");
            String data = name + surname + email + password;

            TextView datos = (TextView) findViewById(R.id.id_datos);


    // Sale una exeption por lo que hay que crear un Asynctask estamos creando en un hilo principal otro hilo al principal
            datos.setText(data);


        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
