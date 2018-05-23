package com.utcn.medpat.presentation.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.service.MessagingService;
import com.utcn.medpat.model.Message;
import com.utcn.medpat.model.User;
import com.utcn.medpat.presentation.activity.MainActivity;
import com.utcn.medpat.presentation.adapter.MessageAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucian on 5/22/2018.
 */

public class MessageFragment extends Fragment {
    private static final String TAG = "MessageFragment";

    private MessagingService messagingService;
    private User currentUser;
    private ListView listView;
    private List<Message> messageList;
    private SwipeRefreshLayout swipeLayout;

    public void setUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messages_fragment,container,false);
        listView = view.findViewById(R.id.message_list);
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(() -> updateList(view.findViewById(R.id.message_list)));

        updateList(listView);
        return view;
    }

    private void updateList(ListView listView){
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.SERVER_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

         messagingService = retrofit.create(MessagingService.class);

        Call<List<Message>> messageCall = messagingService.getInbox(currentUser.getUsername());

        messageCall.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                messageList = response.body();
                Log.i("MESSAGES", "Size: "+messageList.size());

                listView.setAdapter(new MessageAdapter(listView.getContext(), listView.getId(), messageList));
                swipeLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Log.e("MESSAGES", t.getMessage());
            }
        });

    }
}
