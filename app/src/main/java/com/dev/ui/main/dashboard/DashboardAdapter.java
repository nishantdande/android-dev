package com.dev.ui.main.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.R;
import com.dev.data.network.model.Story;
import com.dev.ui.base.BaseViewHolder;
import com.dev.utils.AppLogger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DashboardAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<Story> mStories;

    public DashboardAdapter(List<Story> mStories) {
        this.mStories = mStories;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mStories != null && mStories.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mStories != null && mStories.size() > 0) {
            return mStories.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Story> stories) {
        if (mStories.size()>0){
            mStories.addAll(mStories.size()-1,stories);
        } else {
            mStories.addAll(0,stories);
        }
        notifyDataSetChanged();
    }

    public interface Callback {
        void onStoryClick(Story story);
        void onRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.title)
        AppCompatTextView titleTextView;

        @BindView(R.id.type)
        AppCompatTextView contentTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            titleTextView.setText("");
            contentTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final Story story = mStories.get(position);

            if (story.getTitle() != null) {
                titleTextView.setText(story.getTitle());
            }

            if (story.getType() != null) {
                contentTextView.setText(story.getType());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (story != null) {
                        try {
                            if (mCallback != null)
                                mCallback.onStoryClick(story);
                        } catch (Exception e) {
                            AppLogger.d("url error");
                        }
                    }
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {


        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onRetryClick();
        }
    }
}
