# Sprint 3 - *11* - *The Hobbytes*

## Goal

### Build shorter trips!
### Sprint Leader: *Xujun Wang*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint3.md).
* Version in pom.xml should be `<version>3.0.0</version>`.
* Increment deployed for demo and testing as server-3.0.jar.
* Increment release `v3.0` created on GitHub with appropriate version number and name.
* Epics and Tasks updated in Zenhub.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order for all methods/functions.
* Unit tests are fully automated.
* Code coverage is at least 50%, 70% preferred.
#### Clean Code
* Code adheres to Google style guides for Java and JavaScript.
* Code Climate maintainability of A or B.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration
* Travis successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 


## Plan

* Add Level 2 and 3 software engineering processes/tools
– Clean Code, Coverage, White Box Testing, Code Climate
* Learn some additional technologies
– SQL (MariaDB)
– Traveling Salesman Problem
* Add features
– Produce shorter trips
– Build trips from existing information

Epics planned for this sprint.

* *#62 User: I want to design a trip from scratch so I can stoop using the other tool*
* *#71 User: I want my trips to be shorter*
* *#74 User: I want to make and save changes to the trip.*
* *#63 User: I want to choose what information is displayed in the itinerary and map*


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *6* | *6* |
| Tasks |  *26*   | *26* | 
| Story Points |  *46*  | *46* | 

*Enter the `# Planned` at the beginning of the sprint.  Include a discussion of planning decisions based on the planned number of story points versus how many were completed in previous sprints.*

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *2018/10/6* | *none* | *#148, #168 #164* | *#168* | 
| *2018/10/8* | *#164 #168* | *#140, #147* | *none* |
| *2018/10/10* |*#140, #147 #148* |*#176 #172* |*#176*|
| *2018/10/12* |*#176 #172* | *#180 #181 #182* | *none* |
| *2018/10/15* |*#180 #181 #182* | *#144 #145* | none|
| *2018/10/17* | *#144 #145* | none | *#144 #145*|



*Add a new row for the scrum session after each lecture. *

## Review

The overall results of this sprint are much more positive than last sprint. All major tasks got completed and all functionality was delivered, most of it delivered early in the sprint. There are a couple of little items that didn't get completed to satisifaction, but these came from a desire to finish the major functionality.
*An introductory paragraph describing the overall results of the sprint.*

#### Completed Epics in Sprint Backlog 

*Our solution was focused on completing the searching places, saving trips, as well as trip optimization.*

* *User: I want to make and save changes to the trip*
* *User: I want to design a trip from scratch so I can stoop using the other tool*
* *User: I want my trips to be shorter*

#### Incomplete Epics in Sprint Backlog 

* *Tripco: All code must be tested: We cannot connect to our own server, so that we cannot plan the trip on the black-bottle, while we can did that on the local*

#### What Went Well

We did a much better job of planning and not getting in our own way. We also did a much better job of getting important tasks done early, so we didn't have to wait on them later in the sprint. We also think we did a better job on writing the client as a whole because we all became much better javascript programmers. 

* *Good communication*
* *Teach and help each other*


#### Problems Encountered and Resolutions

No real problems occured. There were a couple minor breaks in master, where the code would compile but due to some trivial error, the code would fail at functionallity. These were always relatively simple to resolve. Other issues that occured were mainly due to us trying some solution to a task, and that solution not working. When that happened, we always found a working solution.

* People can always get into the lab and work together. Especially pair work.
* There was an error about deployment. When our jar filed is deployed and then run the project, some fucntions are not working, but those function will work on localhost for no reason.

## Retrospective

*An introductory paragraph for your retrospective.*

#### What we changed this sprint

We will ensure that in this sprint work that we start is not dependent on code that is not yet writen thus making it almost imposible to verify. furthermore there will be a greater level of TA utilization this sprint as we will be ensuring that we are able to ask questions quickly rather than stall for days because of a lack of information.

#### What we did well

Our testing and code smells both improved tremendusly, we also delivered an exelent level of continuous integration, fully implementing all the pieces of one thing before moving to the next. there was almost no rush to get things done at the last minute and we were able to provide thorough feedback to each other about what we were working on.

#### What we need to work on

We need to improve our planning stage in the next sprint and more carefully and thoroughly break tasks down acording to what they will actualy require. The goal we should aim for is 4+ issues for each epic at least, each of which will take apart the large scale work objectives and provide any one person with a selection of simple delliverables that they can work from. All this, if implemented should improve the groups workflow now that everyone is getting comfortable with the system and the requirements of that system.

#### What we will change next sprint 

We are going to greatly expand the time we take planning for the sprint, taking at least an hour to break down each epic into small tasks and making sure that everyone understands the requirements that we need to meet for the sprint. This will happen on saturday right after we present and we will have all the work from sprint 3 fresh in our minds.
