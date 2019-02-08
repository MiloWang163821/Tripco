# Sprint 2 - 11 - The Hobbytes

## Goal

### A mobile, responsive map and itinerary!
### Sprint Leader: JC Mies

## Definition of Done

* Sprint Review and Restrospectives completed (sprint2.md).
* Version in pom.xml should be `<version>2.0.0</version>`.
* Increment deployed for demo and testing.
* Increment release `v2.0` created on GitHub with appropriate version number and name.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order.
* Unit tests are fully automated.
#### Configuration Management
* Code adheres to Google style guides for Java and JavaScript.
* Code Climate maintainability of A or B.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration
* Continuous integration successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 


## Plan

We are hoping to produce a mobile responsive, website that can map the trip and also show the detailed itenerary. While other tasks would be nice, keeping focus on the main objectives is key. We are hoping to the itenerary and map displayed by the end of the second week and testing everything in the final week.


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | All code should be clean | Custom Units, Interoperabilty, Map/Itenerary, Mobile Responsive |
| Tasks |  Verify code is clean   | See Zenhub for complete list, not duplicating here |  

## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| 09/14 | Plan | None | None | 
| 09/17 | 89(client map), 92(legDistance) | 88(Server map), 85(itenerary) | None
| 09/19 | 79(Distance buttons), 80(Distance buttons) | 88(Server map), 85(itenerary) | None
| 09/21 | 85(Itenerary), 97(itenerary, button) | 88(Server map) | None
| 09/24 | None | 82(Custom Unit Button), 88(Server map), 101(upload on client) | None
| 09/26 | 82(Custom Unit Button), 106(Plan Button), 101(upload on client) | 88(Server map) | None

## Review

The overall results of this sprint are mixed. I am incredibly proud of the team for comepleting all of the storypoints that they did. However, I am also disappointed that our server failed in testing becuase of a trivial oversight. We assumed that the json file would have all of the fields needed for the entire process. However, according to the TFFI doc, several fields are optional. This was not something we caught. 

#### Completed epics in Sprint Backlog 

Our solution was focused on completing the itenerary and map as well as displaying a mobile responisive and user friendly site.

* *##I want a map and itinerary for my trip : This was completed to the best of our ability, however in testing our solution failed to an oversight in TFFI*
* *##I want to supply my own units for distances : This was also completed to the best of our ability, and in our testing it was working properly
* *##All clients and servers should interoperate : This was done primarily by using a valid TFFI, however it is untested
*

#### Incomplete epics in Sprint Backlog 

The only epic that was not completed was clean code. This was not complete do to time and some difficulties in working with code climate. We were focused on capabilty over clean code, which might hurt is in the long run.

* *## All code should be clean: We simply lacked the time to fully go through and clean all of our code. Most of our code is clean by nature, but obviously there are some uncean portions that were not resolved*
*

#### What went well

What I feel like went well was team coordination. Every member of the team worked incredibly well together and were incredibly supportive of each other. We also managed to complete most tasks what we set out to do.

* *Worked as a team*
* *Completed major tasks*

#### Problems encountered and resolutions

The major problems that we ran into were mainly due to a misunderstanding of what was required or a misunderstanding of the code already in repository. For example, I ran into the issue that while testing the server, the current working directory is t11/server, whowever whle running the current working directory is just t11. Other issues encountered were due to not implimenting the plan and upload buttons soon enough.
* *CWD problems*
* *Starting too late on key parts*
*

## Retrospective

While this sprint went well, there are still key things to take away from it. Namely, working sooner and plan better. 

#### What we changed this sprint

This sprint we are focused on starting early and not breaking master. We are doing this by working on getting elements completed in the first two weeks and by testing every change to master.

#### What we did well

We started our job earlier than Sprint1 and we make a big progress on JavaScript.
We didn't break the master and test code before merge it.
Although each of us have different working habits and schedules, we still can meet together in the lab and work on the issues.
We can help each other and not only focus on our own job.

#### What we need to work on

we found that we had failed to test for alternate data types and variations in the TIFI document. We did not prioritize the right sprints which meant that we were not able to catch bugs in a timely manner, an exelent example of this is that the map was the last thing we got working on the client side and this caused us to find errors in the SVG() method far to late. Untimatly what failed was the file loader which had so far worked for every case we had handed it and just what went wrong is a mystery to us.

#### What we will change next sprint 

We need to change the way we test the file because there is a missed test for this sprint. Also, we should probably read the TFFI more carefully in order to avoid the unnecessary problems. At the last, we need to understand the react completely to do a greater job in the next sprint.

