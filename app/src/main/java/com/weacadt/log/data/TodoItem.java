package com.weacadt.log.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class TodoItem {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "Order")
    private Long order;

    private String thing;
    private boolean isDone;

    private int year;
    private int month;
    private int dayOfMonth;

    public TodoItem() {

    }

    public TodoItem(Long order, String thing) {
        this.order = order;
        this.thing = thing;
        isDone = false;
    }

    public TodoItem(Long id, Long order, String thing) {
        this.id = id;
        this.order = order;
        this.thing = thing;
        isDone = false;
    }

    public TodoItem(Long order, String thing, boolean isDone, int year, int month, int dayOfMonth) {
        this.order = order;
        this.thing = thing;
        this.isDone = isDone;
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    @Generated(hash = 42533009)
    public TodoItem(Long id, Long order, String thing, boolean isDone, int year, int month,
            int dayOfMonth) {
        this.id = id;
        this.order = order;
        this.thing = thing;
        this.isDone = isDone;
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
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

    public Long getOrder() {
        return this.order;
    }

    public void setOrder(Long order) {
        this.order = order;
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

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
