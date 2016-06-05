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

import boochatech.cross_cross.Model.Product;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Loh on 4/6/2016.
 */
public class ToDoListSection extends StatelessSection {
    private List<Product> taskList;
    Context mContext;

    public String header;
    final static int TODAY = 0;
    final static int TOMORROW = 1;
    final static int SIX_JUNE = 2;
    final static int TEN_JUNE = 3;

    public ToDoListSection() {
        super(R.layout.item_card_header, R.layout.item_card);
    }

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
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyItemViewHolder itemHolder = (MyItemViewHolder) holder;

        // bind your view here
        itemHolder.tvItem.setText(taskList.get(position).getName());
        itemHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemHolder.recyclerView.getVisibility() == View.VISIBLE) {
                    itemHolder.recyclerView.setVisibility(View.GONE);
                }else{
                    itemHolder.recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

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
        RecyclerView recyclerView;

        public MyItemViewHolder(View itemView) {
            super(itemView);

            tvItem = (TextView) itemView.findViewById(R.id.card_title);
            colorBar = (View) itemView.findViewById(R.id.card_bar);
            card = (CardView) itemView.findViewById(R.id.card);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.action_suggestion_list);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new HorizontalPurchaseInAppRecyclerViewAdapter(mContext, getSuggestions(0)));

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
                //suggestions.add(new Product("", -1, "Search the nearest flower shop", -1, R.drawable.google, -1));
                return suggestions;
            default:
                return suggestions;
        }
    }

}
