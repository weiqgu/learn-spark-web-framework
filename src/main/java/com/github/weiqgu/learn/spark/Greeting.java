package com.github.weiqgu.learn.spark;

public class Greeting {
    private String greeting;
    private String from;
    private String to;

    public Greeting() {
        this("Hello", "Spark", "World");
    }

    public Greeting(String greeting, String from, String to) {
        this.greeting = greeting;
        this.from = from;
        this.to = to;
    }
    public String getGreeting() {
        return greeting;
    }

    public Greeting setGreeting(String greeting) {
        this.greeting = greeting;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public Greeting setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public Greeting setTo(String to) {
        this.to = to;
        return this;
    }
}
