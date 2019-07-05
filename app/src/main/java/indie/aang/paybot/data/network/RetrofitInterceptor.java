package indie.aang.paybot.data.network;


import androidx.annotation.NonNull;

import indie.aang.paybot.utilities.AppLogger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by AangJnr on 05, December, 2018 @ 8:16 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */



public class RetrofitInterceptor implements Interceptor {
    private String token;


    public  RetrofitInterceptor (String key){
        this.token = key;
    }



    @Override public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (token != null && !token.isEmpty()) {
            if(!token.contains("Bearer"))
            token = "Bearer " + token;

            request = request.newBuilder()
                    .addHeader("Authorization", token)
                    .build();

            AppLogger.e("####### RETROFIT INTERCEPTOR TOKEN IS ", token);
        }
        return chain.proceed(request);
    }

}