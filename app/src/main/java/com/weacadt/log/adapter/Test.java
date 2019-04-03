package com.weacadt.log.adapter;

public class Test {
    private String todoThing;
    private boolean isDone;

    public Test(String todoThing) {
        this.todoThing = todoThing;
        isDone = false;
    }

    public String getTodoThing() {
        return todoThing;
    }

    public void setTodoThing(String todoThing) {
        this.todoThing = todoThing;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
