package com.weacadt.log.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TodoItem {
    @Id(autoincrement = true)
    private Long id;

    private String thing;
    private boolean isDone;
    private Date date;

    public TodoItem(String thing) {
        this.thing = thing;
        isDone = false;
    }

    public TodoItem(String thing, Date date) {
        this.thing = thing;
        this.date =date;
        isDone = false;
    }

    @Generated(hash = 972858843)
    public TodoItem(Long id, String thing, boolean isDone, Date date) {
        this.id = id;
        this.thing = thing;
        this.isDone = isDone;
        this.date = date;
    }

    @Generated(hash = 1307818545)
    public TodoItem() {
    }

    public String getTodoThing() {
        return thing;
    }

    public void setTodoThing(String todoThing) {
        this.thing = todoThing;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThing() {
        return this.thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
