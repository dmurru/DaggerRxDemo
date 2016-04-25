package me.nootify.dagger_rxjava_demo.base;

/**
 * Created by davide on 25/04/16.
 */
public interface Presenter
{
    void onCreate();

    void onPause();

    void onResume();

    void onDestroy();

    void fetchFlowers();
}
