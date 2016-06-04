package boochatech.cross_cross;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by Loh on 4/6/2016.
 */
public class ToDoListSection extends StatelessSection {

    public static List<String> myList = Arrays.asList("Item1", "Item2", "Item3");
    final static int TODAY = 0;
    final static int TOMORROW = 1;
    final static int SIX_JUNE = 2;
    final static int TEN_JUNE = 3;

    public ToDoListSection() {
        super(R.layout.item_card_header, R.layout.item_card);
    }

    public ToDoListSection(int i) {
        super(R.layout.item_card_header, R.layout.item_card);
    }

    @Override
    public int getContentItemsTotal() {
        return myList.size();
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
        MyItemViewHolder itemHolder = (MyItemViewHolder) holder;

        // bind your view here
        itemHolder.tvItem.setText(myList.get(position));
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
        headerHolder.tvTitle.setText("Today");
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvItem;
        private final View colorBar;

        public MyItemViewHolder(View itemView) {
            super(itemView);

            tvItem = (TextView) itemView.findViewById(R.id.card_title);
            colorBar = (View) itemView.findViewById(R.id.card_bar);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }


}
