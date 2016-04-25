package me.nootify.dagger_rxjava_demo.application;

import android.app.Application;

import me.nootify.dagger_rxjava_demo.dependency.AppComponent;
import me.nootify.dagger_rxjava_demo.dependency.AppModule;
import me.nootify.dagger_rxjava_demo.dependency.DaggerAppComponent;
import me.nootify.dagger_rxjava_demo.dependency.DaggerNetworkComponent;
import me.nootify.dagger_rxjava_demo.dependency.NetworkComponent;
import me.nootify.dagger_rxjava_demo.dependency.NetworkModule;
import me.nootify.dagger_rxjava_demo.model.Constant;

/**
 * Created by davide on 25/04/16.
 */
public class FlowerApplication extends Application
{
    private AppComponent mAppComponent;

    @Override
    public void onCreate()
    {
        resolveDependency();
        super.onCreate();
    }


    private void resolveDependency()
    {
        mAppComponent = DaggerAppComponent.builder()
                                          .networkComponent(getNetworkComponent())
                                          .build();
    }

    private NetworkComponent getNetworkComponent()
    {
        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                                                                  .networkModule(new NetworkModule(Constant.BASE_URL))
                                                                  .build();

        return networkComponent;
    }

    public AppComponent getAppComponent()
    {
        return mAppComponent;
    }

}
