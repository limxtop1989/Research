package com.limxtop.research.animator.base;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.limxtop.research.R;

import java.util.List;

/**
 * Created by limxtop on 4/23/16.
 */
public class RecyclerViewSImpleAdapter extends RecyclerView.Adapter<RecyclerViewSImpleAdapter.ViewHolder> {

    private List<ViewEntry> mDatas;
    private View.OnClickListener mOnClickListner;

    public RecyclerViewSImpleAdapter(List<ViewEntry> datas, View.OnClickListener listener) {
        mDatas = datas;
        mOnClickListner = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_simple_view_holder, parent, false);
        View text = itemView.findViewById(R.id.text);
        text.setOnClickListener(mOnClickListner);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position).getText());
        holder.mTextView.setTag(mDatas.get(position).getmClass());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
