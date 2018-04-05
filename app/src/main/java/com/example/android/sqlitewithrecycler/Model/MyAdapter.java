package com.example.android.sqlitewithrecycler.Model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sqlitewithrecycler.R;
import com.example.android.sqlitewithrecycler.item_display;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Info> mInfoList;
    private Context mContext;
    private RecyclerView mRecyclerV;

    public MyAdapter(Context context, List<Info> myDataset, RecyclerView recyclerView) {
        mInfoList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    public void add(int position, Info info) {
        mInfoList.add(position, info);
        notifyItemInserted(position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Info info = mInfoList.get(position);
        holder.mHeadText.setText(info.getHead());
      //  holder.mBodyText.setText(info.getBody());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), item_display.class );
                intent.putExtra("string1", info.getHead().toString());
                intent.putExtra("string2", info.getBody().toString());
                v.getContext().startActivity(intent);

            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mBodyText, mHeadText;

        public View layout;
        public ViewHolder(View v) {
            super(v);

            layout = v;
            mHeadText = (TextView) v.findViewById(R.id.headText);
            mBodyText = (TextView) v.findViewById(R.id.bodyText);


        }
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }
}
