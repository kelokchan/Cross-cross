package boochatech.cross_cross.helper;

import android.support.v7.widget.RecyclerView;

/**
 * Created by kuokkit.chan on 20/4/2016.
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position, RecyclerView rv);

    void onDrop(int fromPosition, int toPosition);
}
