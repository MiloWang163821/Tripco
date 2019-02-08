# Sprint 4 - *T11* - *The Hobbytes*

## Goal

### Interactive Maps and Shorter Trips!
### Sprint Leader: *Jiaming Sun*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint4.md).
* Version in pom.xml should be `<version>4.0.0</version>`.
* Increment deployed for demo and testing as server-4.0.jar on the production server.
* Increment release `v4.0` created on GitHub with appropriate version number and name.
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
#### Continuous Integration / Delivery
* Travis successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 
* All pull requests are deployed on the development server.
* The development server is never broken.  If broken, it is fixed immediately.


## Plan

*Fixing API issue in the previous sprint.*
*Adding more objects in the search component.*
*Adding new objects in config component.*
*Adding feature - Using worldmap to make work trip.*
*Implement shorter trip.*
*Able to view trip in other tools.*

![img_2036](https://user-images.githubusercontent.com/31226100/47468074-980feb80-d7b6-11e8-9bf4-3c7973aeb915.jpg)


Epics planned for this sprint.

* *## #228 User: I want to create even shorter trips*
* *## #72 User: I want to plan trips worldwide*
* *## #64 User: I want to view my trip in other tools*
* *## #70 User:  I want trip planning to be fast*
* *## #229 User: I want the shortest trip possible*


## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | *5* | *total* |
| Tasks |  *12*   | *total* | 
| Story Points |  *32*  | *total* | 

*Enter the `# Planned` at the beginning of the sprint.  Include a discussion of planning decisions based on the planned number of story points versus how many were completed in previous sprints.*

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *2018/10/24* | *#none* | *#241* | *#241* | 
| *2018/10/26* | *#none* | *#230 #239* | *#230* | 
| *2018/10/29* | *#241 #250 #251* | *#239 #233 #238 #248* | *#238* | 
| *2018/10/31* | *#258 #237 #238* | *#239 #248* | *#none* | 
| *2018/11/2* | *#248 #239* | *#235 #236* | *#none* | 
| *2018/11/5* | *#236 #236 #264* | *#234* | *#none* | 
| *2018/11/7* | *#234 #228 #229 #283 #240* | *#none* | *#none* | 

*Add a new row for the scrum session after each lecture. *

## Review

The overall results of this sprint are incredibly positive. This is the first sprint that we have completed that we have had time to do fairly involved testing on the deployed server, and to verify that it works to the extent that we believe it to work. However we also didn't complete two significant things, the first being 3-opt, the second being client support for kml. These didn't get completed because of our work in other areas, like ensuring that 2-opt was done correctly. We completed kml mapping client-side, we upped the speed on trip planning, we adding 2-opt planning, we also added more testing, specifically client-side testing, and we also updated to a worldwide map. 
*An introductory paragraph describing the overall results of the sprint.*

#### Completed Epics in Sprint Backlog 

The first epic we completed was updating to the worldwide map. This was combined with fixing an error we were having with maven and was relatively straight forward. We did have to go back and fix the line drawing to wrap around the Pacific ocean in the case that that line is shorter. We also completed the optimization 2-opt which was primarily done and debugged by John and JC. This was done by looking at reference material from the slides and from the internet and working through every line of the solution to find the error that was made. We made our trip planning faster not by using concurrency, as suggested, but by updating our code to use a distance table (calculating that table is O(n^2) ) instead of calculating as we needed it which was much slower. 
*Describe the solution based on the completed epics and list the epics below.*

* *## User: I want to plan trips worldwide: we updated to worlwide map and added worldwide searches*
* *## User: I want trip planning to be fast: we add a distance table, that majorly improved performance*
* *## User: I want to view my trip in other tools: we added support for KML mapping serverside*
* *## User: I want to create even shorter trips: we added support for 2-opt*
* 

#### Incomplete Epics in Sprint Backlog 

The two capabilities we did not include in release were KML mapping client-side and 3-opt. Three-opt was not done because in the little time we had left after debugging 2-opt, we couldn't get 3-opt to work correctly (it currently gets stuck in an infinite loop). So we left it out of config and will persue it next sprint. KML mapping client-side was not done because we simply lacked the time and resources to do it. We very quickly got stuck behind Google's paywall and were unable to climb it.
*Describe capabilities not included in the release and list the epics below with an explanation.*

* *## User: I want the shortest trip possible: we had a bug that we were unable to find and squash, namely we get stuck in an infinite loop*
*

#### What Went Well

What went well was primarily the testing. Our testing numbers were always among the best, however that was because we had not updated our client/package.json to accurately reflect client testing. This was noticed and fixed and then we went and added client side testing to once again have testing coverage above 60%. We also were able to debug several problems with our code relatively quickly including a bug in 2-opt that was incredibly subtle. We also fixed a bug in the client were searching with nothing in the search bar caused a white-out crash. 
*Describe what went well during the sprint in general terms followed by a more detailed list.*

* Testing
* Bug fixing
* Adding features

#### Problems Encountered and Resolutions

*Describe what problems occurred during the sprint in general terms followed by a more detailed list.*

* *The only problem is from the previous sprint, which is we cannot make our plan functional when we deploy to the black bottle. However, we fix that problem at the beginning of this sprint so we can make sure the new stuff are added in a clean and correct code.*
*

## Retrospective

*We did not have any huge problem with our work but it does not mean that we do not have small issues. We always have some issues along with the new feature, and there is another issue comming out when we fix the last issue. However, we did a great job in this Sprint because we always fix the issue before moving on the next part. Also, eveyone in the team are helping each other.*

#### What we changed this sprint

*We did much more test in this sprint comparing the previous sprints. We add more client side testing in order to move on the next part carefully. We did not cover the test that much in the previous sprint so it caused that we had some serious problem when we deploy our jar file to the black bottle.*

#### What we did well

*We did a great job of work allocation for everyone in the team. Everyone in the team picks his stongest part in order to do a better job in this sprint. We do not have any issue about the new features and old features. But we still need to do better on clean code part because some part of code look really bad, fortunately, we fix as much as we can in this sprint.*

#### What we need to work on

*We need a better layout for our coding style in order to archeive the clean code.*

#### What we will change next sprint 

*We should manage the time more carefully because the we did not do much at the beginning of this sprint.*
