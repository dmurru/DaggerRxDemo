package me.nootify.dagger_rxjava_demo.dependency;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by davide on 25/04/16.
 */
@Module
public class NetworkModule
{
    private String mBaseUrl;

    public NetworkModule(String baseUrl)
    {
        mBaseUrl = baseUrl;
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory()
    {
        return GsonConverterFactory.create();
    }


    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory()
    {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, RxJavaCallAdapterFactory callAdapterFactory)
    {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();

    }
}
