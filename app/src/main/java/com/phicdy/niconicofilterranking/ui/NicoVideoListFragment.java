package com.phicdy.niconicofilterranking.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.phicdy.niconicofilterranking.video.NicoVideo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class NicoVideoListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static NicoVideoListFragment newInstance(String param1, String param2) {
        NicoVideoListFragment fragment = new NicoVideoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NicoVideoListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }

        final String videoKey = "videoKey";
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle data = msg.getData();
                ArrayList<NicoVideo> videos = data.getParcelableArrayList(videoKey);
                mAdapter = new NicoVideoListAdapter(videos, getContext());
                mListView.setAdapter(mAdapter);
            }
        };
        new Thread() {
            @Override
            public void run() {
                final URL url;
                try {
                    url = new URL("http://www.nicochart.jp/ranking/feed/");
                    if (!"http".equalsIgnoreCase(url.getProtocol())
                            && !"https".equalsIgnoreCase(url.getProtocol())) {
                        return;
                    }
                    Document document = Jsoup.connect(url.toString()).get();
                    RssParser parser = new RssParser();
                    ArrayList<NicoVideo> videos = parser.parseNicoChartFeed(document);
                    Message msg = handler.obtainMessage();
                    Bundle data = new Bundle();
                    data.putParcelableArrayList(videoKey, videos);
                    msg.setData(data);
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
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

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            NicoVideo selectedVideo = (NicoVideo)mAdapter.getItem(position);
            mListener.onFragmentInteraction(selectedVideo.getUrl());
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String url);
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
            holder.tvInfo.setText(nicoVideo.getPlayCount());
            holder.tvPublishedDate.setText(nicoVideo.getPublishedDate());
//            holder.ivThumbnail.setImageBitmap();

            return row;
        }

        private class ViewHolder {
            TextView tvTitle;
            TextView tvInfo;
            TextView tvPublishedDate;
            ImageView ivThumbnail;
        }
    }
}
