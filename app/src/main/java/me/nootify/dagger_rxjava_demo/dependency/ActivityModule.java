package me.nootify.dagger_rxjava_demo.dependency;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import me.nootify.dagger_rxjava_demo.base.FlowerPresenter;
import me.nootify.dagger_rxjava_demo.base.Presenter;
import me.nootify.dagger_rxjava_demo.service.FlowerService;
import me.nootify.dagger_rxjava_demo.service.FlowerViewInterface;
import me.nootify.dagger_rxjava_demo.ui.MainActivity;
import retrofit2.Retrofit;

/**
 * Created by davide on 25/04/16.
 */
@Module
public class ActivityModule
{
    private MainActivity mMainActivity;

    public ActivityModule(MainActivity mainActivity)
    {
        mMainActivity = mainActivity;
    }

    @Provides
    FlowerPresenter provideFlowerPresenter()
    {
        return new FlowerPresenter(mMainActivity);
    }
}
