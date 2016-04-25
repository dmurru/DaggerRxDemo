package me.nootify.dagger_rxjava_demo.base;

import java.util.List;

import javax.inject.Inject;

import me.nootify.dagger_rxjava_demo.model.FlowerResponse;
import me.nootify.dagger_rxjava_demo.service.FlowerViewInterface;
import rx.Observer;

/**
 * Created by davide on 25/04/16.
 */
public class FlowerPresenter extends BasePresenter implements Observer<List<FlowerResponse>>
{
    private FlowerViewInterface mViewInterface;

    @Inject
    public FlowerPresenter(FlowerViewInterface viewInterface)
    {
        mViewInterface = viewInterface;
    }

    @Override
    public void onCompleted()
    {
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e)
    {
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(List<FlowerResponse> flowerResponses)
    {
        mViewInterface.onFlowers(flowerResponses);
    }

    public void fetchFlowers()
    {
        unSuscribeAll();
        subscribe(mViewInterface.getFlowers(), FlowerPresenter.this);
    }
}
