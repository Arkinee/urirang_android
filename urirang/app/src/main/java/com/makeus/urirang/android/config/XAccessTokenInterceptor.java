package com.makeus.urirang.android.config;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.makeus.urirang.android.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.makeus.urirang.android.src.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);

        if (jwtToken != null) {
            Log.d("TAG", "token: " + jwtToken);
            builder.addHeader("Authorization", jwtToken);
        }
        return chain.proceed(builder.build());
    }

}
