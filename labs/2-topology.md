<link rel='stylesheet' href='../assets/css/main.css'/>

[<< back to main index](../README.md) 

Lab 2 : Topology
===========================

### Overview
Run a Storm topology in local mode

### Depends On 
None

### Run time
40 mins

### Project Directory
src/main/java/x/lab_02/


---------
Step 1 :   Understanding Dataflow
---------
<img src="../assets/images/2a.png" style="border: 5px solid grey ; max-width:100%;" /> 

---------
Step 2 :   Editing Files in 'src/main/java/x/lab_02/'
---------
You will need to edit these files and fix TODO items.

* ClickstreamSplitter.java
* DomainCounter.java
* DomainCountTopology.java

Hints for building topology:

* build out step by step, run it and debug
* start with a spout, run it and make sure it produces output
* then add a bolt, run it and see how data flows

---------
Step 3 :   Run the completed topology and inspect the output
---------
Here is a sample output:

```console

00:21:47.126 [Thread-15-clickstream-executor[2 2]] DEBUG x.lab_02.ClickstreamSpout - sent tuple : [1451635200005,ip_67,user_16,clicked,facebook.com,campaign_5,91,session_251]
00:21:47.132 [Thread-23-domain-extractor-executor[4 4]] DEBUG x.lab_02.ClickstreamSplitterBolt - [1451635200005,ip_67,user_16,clicked,facebook.com,campaign_5,91,session_251]  --> facebook.com
00:21:47.134 [Thread-17-domain-counter-executor[3 3]] DEBUG x.lab_02.DomainCounterBolt - 1 entries in the map
00:21:47.135 [Thread-17-domain-counter-executor[3 3]] DEBUG x.lab_02.DomainCounterBolt -     facebook.com = 1
00:21:47.629 [Thread-15-clickstream-executor[2 2]] DEBUG x.lab_02.ClickstreamSpout - sent tuple : [1451635200010,ip_57,user_89,viewed,foxnews.com,campaign_4,17,session_224]
00:21:47.631 [Thread-23-domain-extractor-executor[4 4]] DEBUG x.lab_02.ClickstreamSplitterBolt - [1451635200010,ip_57,user_89,viewed,foxnews.com,campaign_4,17,session_224]  --> foxnews.com
00:21:47.634 [Thread-17-domain-counter-executor[3 3]] DEBUG x.lab_02.DomainCounterBolt - 2 entries in the map
00:21:47.634 [Thread-17-domain-counter-executor[3 3]] DEBUG x.lab_02.DomainCounterBolt -     facebook.com = 1
00:21:47.635 [Thread-17-domain-counter-executor[3 3]] DEBUG x.lab_02.DomainCounterBolt -     foxnews.com = 1
```
