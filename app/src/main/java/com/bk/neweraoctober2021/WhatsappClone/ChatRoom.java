package com.bk.neweraoctober2021.WhatsappClone;

class ChatRoom {
    private String image, roomName, latestMsg, lastMsgTime;
    private int unseenMsgCount;
    private boolean seenLatest;

    public ChatRoom(){

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getLatestMsg() {
        return latestMsg;
    }

    public void setLatestMsg(String latestMsg) {
        this.latestMsg = latestMsg;
    }

    public String getLastMsgTime() {
        return lastMsgTime;
    }

    public void setLastMsgTime(String lastMsgTime) {
        this.lastMsgTime = lastMsgTime;
    }

    public int getUnseenMsgCount() {
        return unseenMsgCount;
    }

    public void setUnseenMsgCount(int unseenMsgCount) {
        this.unseenMsgCount = unseenMsgCount;
    }

    public boolean isSeenLatest() {
        return seenLatest;
    }

    public void setSeenLatest(boolean seenLatest) {
        this.seenLatest = seenLatest;
    }
}
