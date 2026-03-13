package com.ansimue.nht.dto;

public class UserStatsDTO {
    private int storiesCount;
    private int totalReads;
    private int followers;
    private int following;

    public UserStatsDTO(int storiesCount, int totalReads, int followers, int following) {
        this.storiesCount = storiesCount;
        this.totalReads = totalReads;
        this.followers = followers;
        this.following = following;
    }

    public int getStoriesCount() {
        return storiesCount;
    }

    public void setStoriesCount(int storiesCount) {
        this.storiesCount = storiesCount;
    }

    public int getTotalReads() {
        return totalReads;
    }

    public void setTotalReads(int totalReads) {
        this.totalReads = totalReads;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }
}
