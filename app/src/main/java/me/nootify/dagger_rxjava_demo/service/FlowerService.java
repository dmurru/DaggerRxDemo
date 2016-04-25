package me.nootify.dagger_rxjava_demo.service;

import java.util.List;

import me.nootify.dagger_rxjava_demo.model.FlowerResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by davide on 25/04/16.
 */
public interface FlowerService
{
    @GET("/feeds/flowers.json")
    Observable<List<FlowerResponse>> getFlowers();
}
