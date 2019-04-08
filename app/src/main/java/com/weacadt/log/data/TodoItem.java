package com.weacadt.log.data;

public class TodoItem {
    private String thing;
    private boolean isDone;

    public TodoItem(String thing) {
        this.thing = thing;
        isDone = false;
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
}
