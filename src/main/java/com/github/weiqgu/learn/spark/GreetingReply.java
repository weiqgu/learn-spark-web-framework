package com.github.weiqgu.learn.spark;

public class GreetingReply {

    private String reply;
    private String from;
    private String to;

    public GreetingReply(Greeting greeting) {
        this.reply = "Thank you";
        this.from = "Spark";
        this.to = greeting.getFrom();
    }

    public String getReply() {
        return reply;
    }

    public GreetingReply setReply(String reply) {
        this.reply = reply;
        return this;
    }
}
