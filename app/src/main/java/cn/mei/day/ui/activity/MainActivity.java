package cn.mei.day.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import cn.mei.day.R;
import cn.mei.day.ui.AppBaseActivity;
import cn.mei.day.ui.adapter.DayItemAdapter;

public class MainActivity extends AppBaseActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.root_main)
    ConstraintLayout rootMain;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        RecyclerView.Adapter dayItemAdatper;
        recyclerview.setAdapter(new DayItemAdapter());

    }


    @OnClick({R.id.recyclerview, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                //SelectColorActivity.startActivity(mContext);
                Intent intent = new Intent(this,CreateActivity.class);
                startActivity(intent);
                break;
        }
    }
}
