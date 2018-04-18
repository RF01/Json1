package josu.konectia.json1;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 4T on 18/04/2018.
 */

public class HttpClient extends OkHttpClient {
    private OkHttpClient client;

    public HttpClient(){
        client = new OkHttpClient();
    }

    public Response get() throws Exception{

        //preparmaos la; peticion al serrvidor
       Request request;

        request = new Request.Builder()
                .url("http://android.kernet.es/curso/ws/login.json")
                .build();
        // hacemos la llamada al sevidro
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()){
            throw new IOException("Uneexpected code " + response);
        }
        //devolvemos la respuesta del servidor
        return response;

    }
}
