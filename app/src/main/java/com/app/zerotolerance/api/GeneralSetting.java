package com.app.zerotolerance.api;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeneralSetting {
    public static String URLS = "http://192.168.1.10/apizt/api/";

    public static MobileService getRetrofit(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(new GeneralSetting().URLS)
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MobileService.class);
    }

    public static MobileService getRetrofitAuth(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(new GeneralSetting().URLS)
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MobileService.class);
    }

//    public static MobileService getRetrofitFile(){
//        Retrofit retrofit= new Retrofit.Builder()
//                .baseUrl(new GeneralSetting().UrlFile)
//                .client(getUnsafeOkHttpClient().build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        return retrofit.create(MobileService.class);
//    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30000, TimeUnit.SECONDS);
            builder.writeTimeout(30000, TimeUnit.SECONDS);
            builder.readTimeout(30000, TimeUnit.SECONDS);
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class Security{
        public static String token;
        public static int code_list_id;

        public static String getToken() {
            return token;
        }

        public static void setToken(String token) {
            Security.token = token;
        }

        public static int getCode_list_id() {
            return code_list_id;
        }

        public static void setCode_list_id(int code_list_id) {
            Security.code_list_id = code_list_id;
        }
    }
}
