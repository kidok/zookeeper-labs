<link rel='stylesheet' href='../assets/css/main.css'/>

[<< back to main index](../README.md) 

Lab 1.1 : Install ZooKeeper locally
===========================

### Overview
Install ZooKeeper on a local server

### Depends On 
None

### Run time
10 mins

## STEP 1: Download the latest stable version of ZooKeeper

## STEP 2: Unzip the download

(for example)

    tar xf zookeeper-3.4.8.tar.gz

## STEP 3: Configuration

Assuming you have downloaded and unpackaged a ZooKeeper distribution, go to a
shell, change directory ( cd ) to the project’s root, and rename the sample configuration
file:

    mv conf/zoo_sample.cfg conf/zoo.cfg
    
It is a good idea to change the ZK data directory in the zoo.cfg file
    
    dataDir=/users/me/zookeeper
    
(why? - give two reasons)
    
## STEP 4: Start ZooKeeper server
    
    ./zkServer.sh start
    ZooKeeper JMX enabled by default
    Using config: /home/mark/zookeeper-3.4.8/bin/../conf/zoo.cfg
    Starting zookeeper ... STARTED

## STEP 5: Restart ZK server in the foreground

    # bin/zkServer.sh start-foreground
    
Another trick: 
    
    CTRL-Z to send the server in the background
    fg to bring it back into the foreground
    
Analyze the output of the ZooKeeper server. What can we tell about it?
    
## STEP 6: Start the client
    
In a different shell, to see the results better
    
    # bin/zkCli.sh
    
## STEP 7: List, create and delete nodes    
Inside this shell do the following commands (sample output is shown below)
    
    [zk: localhost:2181(CONNECTED) 1] ls / 
    [zookeeper]
    [zk: localhost:2181(CONNECTED) 2] ls /zookeeper
    [quota]
    [zk: localhost:2181(CONNECTED) 3] ls /zookeeper/quota
    
Still inside the shell
    
    create /workers "my-data"
    
    ls /workers 
    
    get /workers
    
    delete /workers
    
    ls /
    
    quit
    
    
## STEP 8: Stop the ZooKeeper server
    
    # bin/zkServer.sh stop
    
    JMX enabled by default
    Using config: ../conf/zoo.cfg
    Stopping zookeeper ... STOPPED
    
### Answers 
    
### To STEP 3
    
1. To not overfill the root partition
2. /tmp gets partially erased on reboot     