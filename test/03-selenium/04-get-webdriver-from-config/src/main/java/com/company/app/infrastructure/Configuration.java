package com.company.app.infrastructure;

public class Configuration {
    private String url;
    private String username;
    private String key;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String uname) {
        username = uname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
