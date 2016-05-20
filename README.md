PubSub For Java
===============

### Compiling and Running
`javac Test.java && java Test`

### Using PubSub

##### Creating an event handler
```java
PubSub events= new PubSub();
```

##### Subscribing(or Binding) an event
```java
events.on("event_name", new Callback() {
    public void run() {
        // Callback Code Goes Here...
    }
});
```

##### Publishing(or Emitting) an event
```java
events.emit("event_name");
```

##### UnSubscribing(or UnBinding) an event
```java
events.off("event_name");
```


##### Subscribing(or Binding) a time event
```java
events.onTime(3000, "eventName", new Callback() {
    public void run() {
        System.out.println("This runs 3 seconds after executing the .emit()");
    }
});
```

##### Publishing(or Emitting) a time event
```java
events.emitTime("eventName");
```


##### UnSubscribing(or UnBinding) a time event
```java
events.offTime("eventName");
```
