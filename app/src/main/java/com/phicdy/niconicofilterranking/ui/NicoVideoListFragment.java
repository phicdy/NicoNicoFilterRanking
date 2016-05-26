package com.phicdy.niconicofilterranking.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.phicdy.niconicofilterranking.R;
import com.phicdy.niconicofilterranking.ranking.CategorySetting;
import com.phicdy.niconicofilterranking.ranking.FeatureData;
import com.phicdy.niconicofilterranking.ranking.NicoChartRequestChannel;
import com.phicdy.niconicofilterranking.ranking.RankingFactory;
import com.phicdy.niconicofilterranking.util.DateUtil;
import com.phicdy.niconicofilterranking.util.PreferenceHelper;
import com.phicdy.niconicofilterranking.video.NicoVideo;
import com.squareup.picasso.Picasso;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Collection;

public class NicoVideoListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;
    private AbsListView mListView;

    private ListAdapter mAdapter;

    public NicoVideoListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startGetRanking();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nicovideo, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        // Set empty view
        TextView emptyView = (TextView) view.findViewById(android.R.id.empty);
        mListView.setEmptyView(emptyView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private final String videoKey = "videoKey";
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            ArrayList<NicoVideo> videos = data.getParcelableArrayList(videoKey);
            if (videos == null || videos.size() == 0) {
                videos = new ArrayList<>();
                setEmptyText(getActivity().getString(R.string.network_error));
            }

            mAdapter = new NicoVideoListAdapter(videos, getContext());
            mListView.setAdapter(mAdapter);
            mListener.OnNicoChartLoadFinished();
        }
    };

    private void startGetRanking() {
        mListener.OnNicoChartLoadStart();
        new Thread() {

            @Override
            public void run() {
                // Start requests
                PreferenceHelper helper = PreferenceHelper.getInstance(getContext());
                CategorySetting setting = helper.getCategorySetting();
                NicoChartRequestChannel channel = new NicoChartRequestChannel();
                FeatureData data = channel.startGetMyRanking(setting);

                while (!data.isLoaded()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Generate ranking
                Collection<Document> allDocuments = data.getAllDocuments();
                ArrayList<NicoVideo> videos = RankingFactory.generateRanking(allDocuments);

                // Send to UI thread
                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(videoKey, videos);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            NicoVideo selectedVideo = (NicoVideo)mAdapter.getItem(position);
            Uri uri = Uri.parse(selectedVideo.getUrl());
            Intent i = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public interface OnFragmentInteractionListener {
        public void OnNicoChartLoadStart();
        public void OnNicoChartLoadFinished();
    }

    class NicoVideoListAdapter extends ArrayAdapter<NicoVideo> {
        public NicoVideoListAdapter(ArrayList<NicoVideo> videos, Context context) {
            super(context, R.layout.item_nicovideo_list, videos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            // Use contentView and setup ViewHolder
            View row = convertView;
            if (convertView == null) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                row = inflater.inflate(R.layout.item_nicovideo_list, parent, false);
                holder = new ViewHolder();
                holder.tvTitle = (TextView) row.findViewById(R.id.tv_nicovideo_title);
                holder.tvInfo = (TextView) row.findViewById(R.id.tv_nicovideo_info);
                holder.tvPublishedDate = (TextView) row.findViewById(R.id.tv_nicovideo_published_date);
                holder.ivThumbnail = (ImageView) row.findViewById(R.id.iv_nicovideo_thumbnail);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            NicoVideo nicoVideo = this.getItem(position);

            holder.tvTitle.setText(nicoVideo.getTitle());
            holder.tvInfo.setText(generateNicoVideoInfo(nicoVideo));
            String originalDate = nicoVideo.getPublishedDate();
            String formattedDate = DateUtil.convertNicoChartDate(originalDate);
            holder.tvPublishedDate.setText(formattedDate);
            Picasso.with(getContext()).load(nicoVideo.getThumbnailPath()).into(holder.ivThumbnail);

            return row;
        }

        private String generateNicoVideoInfo(NicoVideo video) {
            String playCountDesc = getString(R.string.play_count_desc);
            String commentCountDesc = getString(R.string.comment_count_desc);
            String myListCountDesc = getString(R.string.mylist_count_desc);
            String playCount = video.getPlayCount();
            String commentCount = video.getCommentCount();
            String myListCount = video.getMyListCount();
            return playCountDesc + playCount + " " +
                    commentCountDesc + commentCount + " " +
                    myListCountDesc + myListCount;
        }

        private class ViewHolder {
            TextView tvTitle;
            TextView tvInfo;
            TextView tvPublishedDate;
            ImageView ivThumbnail;
        }
    }
}
