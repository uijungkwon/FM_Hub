package com.example.fm_hub_yeongsinkeem3;
public class FriendItem {
    String name;
    String reserve;
    String audience;
    int resourceId;

    public FriendItem(int resourceId, String name, String reserve, String audience) {
        this.name = name;
        this.reserve = reserve;
        this.audience = audience;
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getAudience() {
        return audience;
    }

    public String getName() {
        return name;
    }
    public String getReserve() { return reserve;}

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAudience(String audience) {this.audience = audience; }
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
