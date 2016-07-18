<link rel='stylesheet' href='../assets/css/main.css'/>

[<< back to main index](../README.md) 

Lab: Master-worker design
=========================

### Overview
Run a Storm topology in local mode

### Depends On 
None

### Run time
40 mins


---------
Step 1 :   Inspect 'src/main/java/x/lab_01/'
---------
This directory has 3 files:
* ClickstreamSpout.java
* TupleCounterBolt.java
* CountTopology.java
 
---------------------------
Step 2 :   Spout : 'src/main/java/x/lab_01/ClickstreamSpout.java'
---------------------------
Edit the above file and fix the TODO items.  
Here are some hints:

TODO-1
```java
    declarer.declare(new Fields("clickstream"));
```

TODO-2
```java
    Values v = new Values(clickstream);
    collector.emit(v);
```


---------
Step 3 :   Bolt : 'src/main/java/x/lab_01/TupleCounterBolt.java'
---------
Edit the above file and fix the TODO items.  
Here are some hints:

Hint for TODO-2
```java
    this.counter ++;
    LOG.debug(this.counter + " : " + tuple);
```

---------
Step 4 :   Topology : 'src/main/java/x/lab_01/CountTopology.java'
---------
Edit this file.

**TODO-1 : Setup a Spout**
```java
    builder.setSpout("clickstream", new ClickstreamSpout());
```

**=> Run the topology and see what happens.**  
Inspect the 'output console'.   
If the program doesn't automatically terminate, 'kill it' manually.

**TODO-2 : Setup a Bolt**
```java
    builder.setBolt("counter", new TupleCounterBolt());
```
**=> Run the topology and see what happens.**  
Do the tuples get to bolt?  Why or why not?

**TODO-3 : Connect Spout & Bolt**
Connect Spout and Bolt using shuffle grouping.
```java
    builder.setBolt("counter", new TupleCounterBolt())
        .shuffleGrouping("clickstream");
```
Now run the topology and see what happens.  
Does the data flow from Spout --> Bolt?

## Done
