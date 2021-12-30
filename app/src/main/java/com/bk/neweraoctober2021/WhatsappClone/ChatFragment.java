package com.bk.neweraoctober2021.WhatsappClone;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bk.neweraoctober2021.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private ListView listView;
    private ArrayList<ChatRoom> chatRooms = new ArrayList<>();

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setListeners();
        setupAdapter();
    }

    private void findViews(View v){
        listView = v.findViewById(R.id.listview);
    }

    private void setListeners(){

    }

    private void setupAdapter(){
        for(int i=0; i < 10; i++){
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setRoomName("Jane");
            chatRoom.setLatestMsg("Hey, how are you?");
            chatRoom.setLastMsgTime("9:30 PM");
            if(i % 2 == 0){
                chatRoom.setSeenLatest(true);
            } else{
                chatRoom.setSeenLatest(false);
                chatRoom.setUnseenMsgCount(3);
            }

            chatRooms.add(chatRoom);
        }

        ChatRoomAdapter adapter = new ChatRoomAdapter(getContext(), chatRooms);
        listView.setAdapter(adapter);
    }

    private class ChatRoomAdapter extends ArrayAdapter<ChatRoom>{
        private ArrayList<ChatRoom> data;
        private Context context;

        public ChatRoomAdapter(Context context, ArrayList<ChatRoom> data){
            super(context, R.layout.whatsapp_chat_listview_item);
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ChatRoom chatRoom = data.get(position);
            View view;

            if (convertView == null){
                Holder holder = new Holder();
                view = LayoutInflater.from(context).inflate(R.layout.whatsapp_chat_listview_item, null);
                holder.tvRoomName = view.findViewById(R.id.tvRoomName);
                holder.tvLatestMsg = view.findViewById(R.id.tvLatestMsg);
                holder.tvLastMsgTime = view.findViewById(R.id.tvLastMsgTime);
                holder.tvUnseenMsg = view.findViewById(R.id.unseenMsgCount);
                holder.unseenMsgLayout = view.findViewById(R.id.unseenMsgCountLayout);

                holder.tvRoomName.setText(chatRoom.getRoomName());
                holder.tvLatestMsg.setText(chatRoom.getLatestMsg());
                holder.tvLastMsgTime.setText(chatRoom.getLastMsgTime());

                if(!chatRoom.isSeenLatest()){
                    holder.tvLastMsgTime.setTextColor(Color.parseColor("#008a0e"));
                    holder.unseenMsgLayout.setVisibility(View.VISIBLE);
                    holder.tvUnseenMsg.setText(Integer.toString(chatRoom.getUnseenMsgCount()));
                } else {
                    holder.unseenMsgLayout.setVisibility(View.INVISIBLE);
                }

                view.setTag(holder);
            } else {
                Holder holder = (Holder) convertView.getTag();
                view = convertView;
                holder.tvRoomName.setText(chatRoom.getRoomName());
                holder.tvLatestMsg.setText(chatRoom.getLatestMsg());
                holder.tvLastMsgTime.setText(chatRoom.getLastMsgTime());
            }

            return view;
        }

        public ArrayList<ChatRoom> getData(){
            return data;
        }

        private class Holder{
            ImageView imgChat;
            TextView tvRoomName, tvLatestMsg, tvLastMsgTime, tvUnseenMsg;
            FrameLayout unseenMsgLayout;
        }
    }
}