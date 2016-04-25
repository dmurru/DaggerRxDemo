package me.nootify.dagger_rxjava_demo.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.nootify.dagger_rxjava_demo.R;

/**
 * Created by davide on 25/04/16.
 */
public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.Holder>
{
    private final LayoutInflater mLayoutInflater;
    private List<FlowerResponse> mFlowerList;
    private FlowerClickListener mListener;

    public FlowerAdapter(FlowerClickListener listener, LayoutInflater inflater)
    {
        mListener = listener;
        mLayoutInflater = inflater;
        mFlowerList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new Holder(mLayoutInflater.inflate(R.layout.item_flower, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position)
    {
        FlowerResponse currFlower = mFlowerList.get(position);

        holder.mName.setText(currFlower.getName());
        holder.mPrice.setText(String.format(Locale.ITALIAN, "$%.2f", currFlower.getPrice()));

        Glide.with(holder.itemView.getContext()).load(Constant.PHOTO_URL + currFlower.getPhoto()).into(holder.mPhoto);
    }

    @Override
    public int getItemCount()
    {
        return mFlowerList.size();
    }

    public void addFlowers(List<FlowerResponse> flowerResponses)
    {
        mFlowerList.addAll(flowerResponses);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @Bind(R.id.flowerPhoto)
        ImageView mPhoto;

        @Bind(R.id.flowerName)
        TextView mName;

        @Bind(R.id.flowerPrice)
        TextView mPrice;

        public Holder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(Holder.this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            mListener.onClick(getLayoutPosition(), mFlowerList.get(getAdapterPosition()).getName());
        }
    }

    public interface FlowerClickListener
    {
        void onClick(int position, String name);
    }
}
