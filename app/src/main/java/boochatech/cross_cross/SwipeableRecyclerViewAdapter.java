package boochatech.cross_cross;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;

import boochatech.cross_cross.helper.ItemTouchHelperAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by Kelok on 05/06/2016.
 */
public class SwipeableRecyclerViewAdapter extends SectionedRecyclerViewAdapter implements ItemTouchHelperAdapter {
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position, RecyclerView rv) {
        notifyItemRemoved(position);
        Snackbar.make(rv, "Done for now", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDrop(int fromPosition, int toPosition) {

    }
}
