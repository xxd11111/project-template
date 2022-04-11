package com.xxd.redis.queue;

/**
 * TODO
 *
 * @Author xxd
 * @Date 2021/12/15
 * @Version 1.0
 */
public class TaskItem<T> {
    public String id;
    public T msg;

    @Override
    public String toString() {
        return "TaskItem{" +
                "id='" + id + '\'' +
                ", msg=" + msg +
                '}';
    }

    public TaskItem() {
    }

    public TaskItem(String id, T msg) {
        this.id = id;
        this.msg = msg;
    }
}
