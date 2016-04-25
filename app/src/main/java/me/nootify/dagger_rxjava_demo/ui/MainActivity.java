package me.nootify.dagger_rxjava_demo.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.nootify.dagger_rxjava_demo.R;
import me.nootify.dagger_rxjava_demo.application.FlowerApplication;
import me.nootify.dagger_rxjava_demo.base.FlowerPresenter;
import me.nootify.dagger_rxjava_demo.base.Presenter;
import me.nootify.dagger_rxjava_demo.dependency.ActivityModule;
import me.nootify.dagger_rxjava_demo.model.FlowerAdapter;
import me.nootify.dagger_rxjava_demo.model.FlowerResponse;
import me.nootify.dagger_rxjava_demo.service.FlowerService;
import me.nootify.dagger_rxjava_demo.service.FlowerViewInterface;
import rx.Observable;

public class MainActivity extends AppCompatActivity implements FlowerViewInterface, FlowerAdapter.FlowerClickListener
{
    @Inject
    FlowerService mService;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private FlowerAdapter mAdapter;
    private ProgressDialog mDialog;

    @Inject
    FlowerPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolveDependency();

        ButterKnife.bind(MainActivity.this);

        configView();

        //mPresenter = new FlowerPresenter(MainActivity.this);
        mPresenter.onCreate();
    }

    private void resolveDependency()
    {
        ((FlowerApplication) getApplication())
                .getAppComponent()
                .plus(new ActivityModule(this))
                .inject(MainActivity.this);
    }

    private void configView()
    {
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new FlowerAdapter(MainActivity.this, getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCompleted()
    {
        mDialog.dismiss();
        Toast.makeText(MainActivity.this, "Download Completed..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message)
    {
        mDialog.dismiss();
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFlowers(List<FlowerResponse> flowerResponses)
    {
        mAdapter.addFlowers(flowerResponses);
    }

    @Override
    public Observable<List<FlowerResponse>> getFlowers()
    {
        return mService.getFlowers();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchFlowers();
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    @Override
    public void onClick(int position, String flowerName)
    {
        Toast.makeText(MainActivity.this, "You clicked on " + flowerName, Toast.LENGTH_SHORT).show();
    }
}
