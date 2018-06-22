package com.adilkhan.githubrepositorieslist.Model;

public class SingleRow {


   private String avatar_url,name,description,language,watchers,open_issues;

   private long dataId;

   public SingleRow(){}

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public SingleRow(long dataId ,String name, String description, String language, String watchers, String open_issues,String avatar_url) {
        this.avatar_url = avatar_url;
        this.name = name;
        this.description = description;
        this.language = language;
        this.watchers = watchers;
        this.open_issues = open_issues;
        this.dataId=dataId;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWatchers() {
        return watchers;
    }

    public void setWatchers(String watchers) {
        this.watchers = watchers;
    }

    public String getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(String open_issues) {
        this.open_issues = open_issues;
    }
}
