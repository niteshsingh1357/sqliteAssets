package com.example.android.sqlitewithrecycler.Model;

public class Info {

    private long id;
    private String head;
    private String body;

    public Info(long aLong, String string, String cursorString) {
    }

    public Info(String head, String body) {
        this.id = id;
        this.head = head;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
