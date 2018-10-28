package am.monamie.shop.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import am.monamie.shop.R;
import am.monamie.shop.model.ContactUsModel;

public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsAdapter.MyViewHolder> {
    private Context context;
    private List<ContactUsModel> list;

    public ContactUsAdapter(Context context, List<ContactUsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_us_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.thumbnailImage.setImageResource(list.get(position).getImagePath());
        holder.title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView thumbnailImage;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.contact_us_cardViewID);
            thumbnailImage = itemView.findViewById(R.id.contact_us_thumbnailImageID);
            title = itemView.findViewById(R.id.contact_us_recycler_title);
        }
    }
}
