package cn.logcode.library.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CaostGrace on 2018/5/28 21:37
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.http
 * @class_name: HttpManager
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class HttpManager {

    private static HttpManager DEFAULT = null;

    public static HttpManager getInstance(String base_url){
        if(DEFAULT == null){
            DEFAULT =  new HttpManager
                    .Builder()
                    .baseUrl(base_url)
                    .build();
        }
        return DEFAULT;
    }



    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    private OkHttpClient client;
    private Retrofit retrofit;

    private HttpManager(OkHttpClient client, Retrofit retrofit){
        this.client = client;
        this.retrofit = retrofit;
    }

    public Builder newBuilder() {
        return new Builder(client,retrofit);
    }

    public <T> T createApi(Class<T> cls){
        return retrofit.create(cls);
    }

    public static class Builder{

        private CallAdapter.Factory callFactory;
        private Converter.Factory converterFactory;
        private HttpUrl base_url;

        private OkHttpClient client;
        private Retrofit retrofit;

        public Builder(){
            callFactory = RxJava2CallAdapterFactory.create();
            converterFactory = GsonConverterFactory.create(gson);

            client = new OkHttpClient
                    .Builder()
                    .connectTimeout(5,TimeUnit.SECONDS)
                    .readTimeout(5,TimeUnit.SECONDS)
                    .writeTimeout(5,TimeUnit.SECONDS)
                    .addInterceptor(new HttpLoggingInterceptor())
                    .build();
        }

        public Builder(OkHttpClient client,Retrofit retrofit){
            this.client = client;
            this.retrofit = retrofit;

            callFactory = RxJava2CallAdapterFactory.create();
            converterFactory = GsonConverterFactory.create(gson);
            base_url = retrofit.baseUrl();

        }

        public Builder callAdapterFactory(CallAdapter.Factory factory){
            this.callFactory = factory;
            return this;
        }

        public Builder converterFactory(Converter.Factory factory){
            this.converterFactory = factory;
            return this;
        }

        public Builder baseUrl(String url){
            this.base_url = HttpUrl.parse(url);
            return this;
        }

        public Builder client(OkHttpClient client){
            this.client = client;
            return this;
        }



        public HttpManager build(){
            retrofit = new Retrofit
                    .Builder()
                    .addConverterFactory(converterFactory)
                    .addCallAdapterFactory(callFactory)
                    .baseUrl(base_url)
                    .client(client)
                    .build();
            return new HttpManager(client,retrofit);
        }
    }
}
