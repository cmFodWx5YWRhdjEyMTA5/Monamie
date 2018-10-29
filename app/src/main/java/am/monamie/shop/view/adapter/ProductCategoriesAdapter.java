package am.monamie.shop.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;

import java.util.List;

import am.monamie.shop.R;
import am.monamie.shop.model.get.ProductCategoriesResponse;

public class ProductCategoriesAdapter extends RecyclerView.Adapter<ProductCategoriesAdapter.MyViewHolder> {
    private static final String TAG = ProductCategoriesAdapter.class.getName();
    private Context context;
    private ProductCategoriesResponse list;

    public ProductCategoriesAdapter(Context context, ProductCategoriesResponse list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_categories_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(list.getData().getUsers().get(position).getName());
        Glide
                .with(context)
                .load(list.getData().getUsers().get(position).getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.thumbnailImage);
    }

    @Override
    public int getItemCount() {
        return list.getData().getTotal();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImage;
        TextView title;
        ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnailImage = itemView.findViewById(R.id.productImageItemID);
            title = itemView.findViewById(R.id.productItemTitleID);
            progressBar = itemView.findViewById(R.id.productProgressBarID);
            FadingCircle doubleBounce = new FadingCircle();
            progressBar.setIndeterminateDrawable(doubleBounce);
        }
    }

}
