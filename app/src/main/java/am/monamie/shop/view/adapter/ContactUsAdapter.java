package am.monamie.shop.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import am.monamie.shop.view.constants.AppConstants;
import am.monamie.shop.view.util.MonamieAnimation;

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
        clickListener(position, holder.cardView);
    }

    private void clickListener(int position, CardView cardView) {
        cardView.setOnClickListener(v -> {
            switch (position) {
                case 0:
                    showAboutUsDialog();
                    break;
                case 1:
                    break;
                case 2:
                    // map Mon amie location
                    Intent intentLocation = new Intent(Intent.ACTION_VIEW);
                    intentLocation.setData(Uri.parse(AppConstants.LOCATION_MONAMIE));
                    v.getContext().startActivity(intentLocation);
                    break;
                case 3:
                    // website Mon amie
                    goToWebsite(v, AppConstants.WEBSITE_MONAMIE);
                    break;
                case 4:
                    // website Facebook
                    goToWebsite(v, AppConstants.FACEBOOK_MONAMIE);
                    break;
                case 5:
                    // website Instagram
                    goToWebsite(v, AppConstants.INSTAGRAM_MONAMIE);
                    break;
                case 6:
                    // website Twitter
                    goToWebsite(v, AppConstants.TWITTER_MONAMIE);
                    break;
                case 7:
                    // website Google Plus
                    goToWebsite(v, AppConstants.GOOGLE_PLUS_MONAMIE);
                    break;
            }
        });
    }

    private void goToWebsite(View view, String url) {
        Intent intentWebSite = new Intent(Intent.ACTION_VIEW);
        intentWebSite.setData(Uri.parse(url));
        view.getContext().startActivity(intentWebSite);
    }

    private void showAboutUsDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_about_us, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);
        //show dialog
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
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
