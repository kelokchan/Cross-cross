package boochatech.cross_cross;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Loh on 5/6/2016.
 */
public class HorizontalColorPicker extends RecyclerView.Adapter<HorizontalColorPicker.ViewHolder> {

    Context context;
    int[] colors;

    public HorizontalColorPicker(Context context) {
        this.context = context;
        colors = context.getResources().getIntArray(R.array.color_list);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        FloatingActionButton fBtn;

        public ViewHolder(View view) {
            super(view);
            fBtn = (FloatingActionButton) view.findViewById(R.id.color_ball);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_color_ball, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.fBtn.setBackgroundTintList(ColorStateList.valueOf(colors[position]));
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }
}
