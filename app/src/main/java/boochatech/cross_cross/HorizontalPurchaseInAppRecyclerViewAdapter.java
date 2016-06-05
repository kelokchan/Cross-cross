package boochatech.cross_cross;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.molpay.molpayxdk.MOLPayActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import boochatech.cross_cross.Model.Product;

/**
 * Created by User on 6/5/2016.
 */
public class HorizontalPurchaseInAppRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalPurchaseInAppRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<Product> products = new ArrayList<>();
    private boolean isAction;

    public HorizontalPurchaseInAppRecyclerViewAdapter(Context c, List<Product> products, boolean isAction) {
        mContext = c;
        this.products = products;
        this.isAction = isAction;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_productName, textview_description;
        public ImageView imageview_productImg;
        public RatingBar ratingBar;
        public Button buyButton;
        public RelativeLayout rl;

        public TextView actionText;
        public ImageView actionIcon;
        public CardView actionCardView;
        public ViewHolder(View view) {
            super(view);
            imageview_productImg = (ImageView) view.findViewById(R.id.purchase_image);
            textview_productName = (TextView) view.findViewById(R.id.purchase_name);
            textview_description = (TextView) view.findViewById(R.id.purchase_description);
            ratingBar = (RatingBar) view.findViewById(R.id.purchase_rating);
            buyButton = (Button) view.findViewById(R.id.purchase_buy_now);
            rl = (RelativeLayout) view.findViewById(R.id.purchase_rl);

            actionText = (TextView) view.findViewById(R.id.action_text);
            actionIcon = (ImageView) view.findViewById(R.id.action_image);
            actionCardView = (CardView) view.findViewById(R.id.action_card_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isAction)
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_suggest_action, parent, false));
        else
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card_in_app_purchase, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (isAction) {
            Picasso.with(mContext).load(products.get(position).getImage()).into(holder.actionIcon);
            holder.actionText.setText(products.get(position).getName());

            holder.actionCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(position){
                        case 2:
                            mContext.startActivity(new Intent("com.waze"));
                            break;
                    }
                }
            });
        } else {
            if (!products.get(position).getDescription().equals("")) {
                holder.imageview_productImg.setBackgroundResource(products.get(position).getImage());
                if (!holder.textview_productName.equals(""))
                    holder.textview_productName.setText(products.get(position).getName());
                else {
                    holder.textview_productName.setVisibility(View.GONE);
                    holder.buyButton.setVisibility(View.GONE);
                }
                holder.textview_description.setText(products.get(position).getDescription());
                if (products.get(position).getRating() != -1)
                    holder.ratingBar.setRating((float) products.get(position).getRating());
                else holder.ratingBar.setVisibility(View.GONE);
                holder.buyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, Object> paymentDetails = new HashMap<>();
                        paymentDetails.put(MOLPayActivity.mp_amount, "1.10");
                        paymentDetails.put(MOLPayActivity.mp_username, "api_MOLWallet_Dev1");
                        paymentDetails.put(MOLPayActivity.mp_password, "api_walletdev1");
                        paymentDetails.put(MOLPayActivity.mp_merchant_ID, "MOLWallet_Dev1");
                        paymentDetails.put(MOLPayActivity.mp_app_name, "ahkl01");
                        paymentDetails.put(MOLPayActivity.mp_order_ID, "46757");
                        paymentDetails.put(MOLPayActivity.mp_currency, "MYR");
                        paymentDetails.put(MOLPayActivity.mp_country, "MY");
                        paymentDetails.put(MOLPayActivity.mp_verification_key, "c6c5cdaabf772a366e1b5ba8a512afac");
                        paymentDetails.put(MOLPayActivity.mp_bill_description, "buy buy");
                        paymentDetails.put(MOLPayActivity.mp_bill_name, "test molpay demo");
                        paymentDetails.put(MOLPayActivity.mp_bill_email, "ahkl@gmail.com");
                        paymentDetails.put(MOLPayActivity.mp_bill_mobile, "60163218880");
                        paymentDetails.put(MOLPayActivity.mp_channel_editing, false);
                        paymentDetails.put(MOLPayActivity.mp_editing_enabled, false);
                        paymentDetails.put(MOLPayActivity.mp_request_type, "");
                        paymentDetails.put(MOLPayActivity.mp_channel, "");
                        paymentDetails.put(MOLPayActivity.mp_transaction_id, "");
                        paymentDetails.put("domain_mode", "1");

                        Intent intent = new Intent(mContext, MOLPayActivity.class);
                        intent.putExtra(MOLPayActivity.MOLPayPaymentDetails, paymentDetails);
                        ((MainActivity) mContext).startActivityForResult(intent, MOLPayActivity.MOLPayXDK);
                    }
                });
            } else {
                holder.imageview_productImg.setBackgroundResource(products.get(position).getImage());
                holder.rl.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
