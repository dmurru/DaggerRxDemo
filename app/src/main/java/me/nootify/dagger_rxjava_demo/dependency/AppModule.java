package me.nootify.dagger_rxjava_demo.dependency;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.nootify.dagger_rxjava_demo.service.FlowerService;
import retrofit2.Retrofit;

/**
 * Created by davide on 25/04/16.
 */
@Module
public class AppModule
{
    @Provides
    @CustomScope
    FlowerService provideFlowerService(Retrofit retrofit)
    {
        return retrofit.create(FlowerService.class);
    }
}
