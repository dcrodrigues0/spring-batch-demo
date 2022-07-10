# JOB
* Jobs are step sequences and each step has your logic
* Job Instance = **Logic** execution
* Job Execution = **Physic** execution
* There are a lot of Job Executions in a Job Instance (There are a lot of physic executions in a Logic execution)
* There are a lot of Job Instances in a Job (There are a lot of Logic executions in a job)


# JOB Repository stored metadata
* How much logic executions = BATCH_JOB_INSTANCE <br/>
* How much physic executions = BATCH_JOB_EXECUTION <br/>
* Which data was saved in job execution = BATCH_JOB_EXECUTION_CONTEXT <br/>
* Which step was executed = BATCH_STEP_EXECUTION <br/>
* Specific logic step executed = BATCH_STEP_EXECUTION_CONTEXT <br/>

#STEP
* The next step execution depends on currently step state <br/>
ex: To get a next step the current step must have be completed or finished
* Step execution represents a physic execution of step **associated with Job Execution**
* There are two different types of how Step Execution defines your logic:,<br/>
**Tasklet**: Tasklet is commonly used to develop simple tasks<br/>
**Chunk**: Chunk is commonly used to complex data processes that needs to be processed in stages,
Chunks are divided in tasks of read, processes and write. Each chunk has your own transaction

#Chunks Lifecycle
* Reader receive a collection
* Processor receive a collection item
* Writer receive a processed collection