package cn.mei.day.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import butterknife.BindView;
import cn.mei.day.R;
import cn.mei.day.ui.AppBaseActivity;

public class SelectColorActivity extends AppBaseActivity {

    @BindView(R.id.list_item)
    ListView listItem;
    private String[] mColorArray;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,SelectColorActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_color;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mColorArray = getResources().getStringArray(R.array.array_color);

        ColorAdapter adapter = new ColorAdapter();
        listItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public class ColorAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mColorArray.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mContext, R.layout.item_color, null);
            view.setBackgroundColor(Color.parseColor(mColorArray[position]));
            return view;
        }
    }

}
