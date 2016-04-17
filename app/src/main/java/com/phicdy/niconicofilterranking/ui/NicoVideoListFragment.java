package com.phicdy.niconicofilterranking.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
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
import com.phicdy.niconicofilterranking.rss.RssParser;
import com.phicdy.niconicofilterranking.util.DateUtil;
import com.phicdy.niconicofilterranking.video.NicoVideo;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
            if (videos == null) {
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
                Document document = getDocumentFromNicoChart();
                if (document == null) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                    return;
                }
                RssParser parser = new RssParser();
                ArrayList<NicoVideo> videos = parser.parseNicoChartFeed(document);
                Message msg = handler.obtainMessage();
                Bundle data = new Bundle();
                data.putParcelableArrayList(videoKey, videos);
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }.start();
    }

    @WorkerThread
    private Document getDocumentFromNicoChart() {
        URL url;
        try {
            url = new URL("http://www.nicochart.jp/ranking/feed/");
            return Jsoup.connect(url.toString()).get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
