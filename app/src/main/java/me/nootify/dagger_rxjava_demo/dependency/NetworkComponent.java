package me.nootify.dagger_rxjava_demo.dependency;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by davide on 25/04/16.
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent
{
    Retrofit retrofit();
}
