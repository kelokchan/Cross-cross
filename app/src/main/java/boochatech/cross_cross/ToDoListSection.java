package boochatech.cross_cross;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import boochatech.cross_cross.Model.Action;
import boochatech.cross_cross.Model.Product;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Loh on 4/6/2016.
 */
public class ToDoListSection extends StatelessSection {
    private List<Product> taskList;
    private List<Action> actions;
    Context mContext;
    int[] colors;

    public String header;
    final static int TODAY = 0;
    final static int TOMORROW = 1;
    final static int SIX_JUNE = 2;
    final static int TEN_JUNE = 3;

    public ToDoListSection(Context c, int i, String header, List<Product> taskList) {
        super(R.layout.item_card_header, R.layout.item_card);
        mContext = c;
        this.header = header;
        this.taskList = taskList;
    }

    @Override
    public int getContentItemsTotal() {
        return taskList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new MyItemViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyItemViewHolder itemHolder = (MyItemViewHolder) holder;
        final String name = taskList.get(position).getName();

        if (name.toLowerCase().startsWith("flow"))
            itemHolder.recyclerView.setAdapter(new HorizontalPurchaseInAppRecyclerViewAdapter(mContext, getSuggestions(0),false));
        else if (name.toLowerCase().startsWith("mov"))
            itemHolder.recyclerView.setAdapter(new HorizontalPurchaseInAppRecyclerViewAdapter(mContext, getSuggestions(2),false));
        else if (name.toLowerCase().startsWith("send"))
            itemHolder.recyclerView.setAdapter(new HorizontalPurchaseInAppRecyclerViewAdapter(mContext, getSuggestions(1),true));
        // bind your view here
        itemHolder.tvItem.setText(name);
        itemHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.toLowerCase().startsWith("mov") || name.toLowerCase().startsWith("flow")) {
                    if (itemHolder.recyclerView.getVisibility() == View.VISIBLE) {
                        itemHolder.recyclerView.setVisibility(View.GONE);
                    } else {
                        itemHolder.recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        itemHolder.cardImage.setImageResource(taskList.get(position).getShopLogo());
        colors = mContext.getResources().getIntArray(R.array.color_list);
        int colorIndex = new Random().nextInt(colors.length);
        itemHolder.colorBar.setBackgroundColor(colors[colorIndex]);

    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
        headerHolder.tvTitle.setText(header);
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvItem;
        private final View colorBar;
        CardView card;
        ExpandableRelativeLayout expandableLayout;
        CircleImageView cardImage;
        RecyclerView recyclerView;

        public MyItemViewHolder(View itemView) {
            super(itemView);

            tvItem = (TextView) itemView.findViewById(R.id.card_title);
            colorBar = (View) itemView.findViewById(R.id.card_bar);
            card = (CardView) itemView.findViewById(R.id.card);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.action_suggestion_list);
            cardImage = (CircleImageView) itemView.findViewById(R.id.card_image);

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new HorizontalPurchaseInAppRecyclerViewAdapter(mContext, getSuggestions(0), false));

        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;


        public HeaderViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);

        }
    }

    public List<Product> getSuggestions(int i) {
        List<Product> suggestions = new ArrayList<>();
        switch (i) {
            case 0:
                suggestions.add(new Product("Weekly's Lovely Bouquet", 4, String.format("%1$s - MYR%2$s", "Regular", 58), 58, R.drawable.flower1, R.drawable.fifty_gram));
                suggestions.add(new Product("Happy Bunch - Lily", 4, String.format("%1$s - MYR%2$s", "Regular", 42), 42, R.drawable.flower2, R.drawable.happy_bunch));
                suggestions.add(new Product("", -1, "", -1, R.drawable.google_card, -1));
                return suggestions;
            case 1:
                suggestions.add(new Product("Call a Grab to destination", -1, "", -1, R.drawable.grabcar, -1));
                suggestions.add(new Product("Launch Waze", -1, "", -1, R.drawable.waze, -1));
                suggestions.add(new Product("Online check-in", -1, "", -1,R.drawable.airasia, -1));

            default:
                suggestions.add(new Product("X-men Apocalypse", 3, String.format("%1$s - MYR%2$s", "Regular", 12), 12, R.drawable.movie_xmen, R.drawable.movie_xmen));
                suggestions.add(new Product("Me Before You", 4, String.format("%1$s - MYR%2$s", "Regular", 12), 12, R.drawable.movie_mebeforeu, R.drawable.movie_mebeforeu));
                suggestions.add(new Product("Bad neighbours 2", 3, String.format("%1$s - MYR%2$s", "Regular", 12), 12, R.drawable.movie_bad, R.drawable.movie_bad));
                return suggestions;
        }
    }

}
