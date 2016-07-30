package cn.mei.day.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Xiao_Bailong on 2016/7/30 0030.
 */

public class DayItemAdapter extends RecyclerView.Adapter<DayItemAdapter.DayHolder> {



    @Override
    public DayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(DayHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DayHolder extends RecyclerView.ViewHolder{

        public DayHolder(View itemView) {
            super(itemView);
        }
    }
}
