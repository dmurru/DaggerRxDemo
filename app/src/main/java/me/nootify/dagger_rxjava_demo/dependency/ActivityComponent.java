package me.nootify.dagger_rxjava_demo.dependency;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;
import me.nootify.dagger_rxjava_demo.ui.MainActivity;

/**
 * Created by davide on 25/04/16.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent
{
    void inject(MainActivity activity);
}

