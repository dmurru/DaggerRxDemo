package me.nootify.dagger_rxjava_demo.dependency;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;
import me.nootify.dagger_rxjava_demo.ui.MainActivity;

/**
 * Created by davide on 25/04/16.
 */
@CustomScope
@Component(modules = { AppModule.class}, dependencies = NetworkComponent.class)
public interface AppComponent
{
    ActivityComponent plus(ActivityModule module);
}

