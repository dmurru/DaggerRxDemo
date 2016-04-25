package me.nootify.dagger_rxjava_demo.service;

import java.util.List;

import me.nootify.dagger_rxjava_demo.model.FlowerResponse;
import rx.Observable;

/**
 * Created by davide on 25/04/16.
 */
public interface FlowerViewInterface
{
    void onCompleted();

    void onError(String message);

    void onFlowers(List<FlowerResponse> flowerResponses);

    Observable<List<FlowerResponse>> getFlowers();
}
