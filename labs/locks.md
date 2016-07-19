<link rel='stylesheet' href='../assets/css/main.css'/>

[<< back to main index](../README.md) 

Lab: Locks in multiple sessions
===============================

### Overview
Manually implement a lock from two sessions

### Depends On 
None

### Run time
30 mins


---------
Step 1:   Analyze the following scenario
---------

We want to implement critical sections through locks. Say that we have an application with n processes trying to acquire a lock. 
How do we express this in terms of znodes?

---------
Step 2:   Implement the design
---------

Each process p tries to create a znode, say /lock. If p succeeds in creating the znode, it has the lock and can proceed to execute its critical section. 
Implement this with zkCli and verify that other clients cannot acquire this lock.

---------
Step 3:   Analyze potential failures
---------

One potential problem is that p could crash and never release the lock. In this case, no other process will ever be able to acquire the
lock again, and the system could seize up in a deadlock. How do we avoid this?

---------
Step 4:   Make the design stable
---------

To avoid such situations, we have to make the /lock znode ephemeral when we create it. 

1. Create an ephemeral lock, verify that the systems still works as planned. 
2. Imitate the process crashing (by exiting the shell). Verify that the lock is eventually released.

Hint: use the zkCli documentation described in this post http://stackoverflow.com/questions/28589703/zookeeper-zkcli-sh-create-switches-documentation

---------
Step 5:   Imitate nodes waiting
---------
 
1. Verify that Other processes that try to create /lock fail so long as the znode exists. 
2. Make other nodes watch for changes to /lock and try to acquire the lock again once they detect that /lock has been deleted. 
3. Imitate this:  upon receiving a notification that /lock has been deleted, the other process repeats the steps of attempting 
to create /lock and, if another process has created the znode already, watching it.
 

## Done
