<link rel='stylesheet' href='../assets/css/main.css'/>

[<< back to main index](../README.md) 

Lab: Master-worker design
=========================

### Overview
Imitate master-worker recipe with zkClil

### Depends On 
None

### Run time
30 mins


---------
Step 1:  Create the master
---------

    create -e /master "master1.example.com:2223" 
    ls/
    get /master
    
Explain the purpose of these steps

---------
Step 2:  Try to create another master
---------

    create -e /master "master2.example.com:2223"
    
What happens?

---------
Step 3:  Set a watch on master
---------

    stat /master true
    
Watch what happens when the first process is deleted or expires

(Hint): You should see

    WATCHER::
    WatchedEvent state:SyncConnected type:NodeDeleted path:/master
    
Now try to acquire a master

---------
Step 4:  Create a worker
---------

    create -e /workers/worker1.example.com "worker1.example.com:2224"

---------
Step 5:  Master detects workers
---------

Find the zkCli command for the worker

---------
Step 6:  (Bonus) Add "Tasks" role
---------

Find the zkCli command for the roles

## Done
