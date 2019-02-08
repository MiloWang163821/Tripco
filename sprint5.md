# Sprint 5 - 11 - The Hobbytes

## Goal

### Wrap It Up!
### Sprint Leader: JC mies

## Definition of Done

* Sprint Review and Restrospectives completed (sprint5.md).
* Version in pom.xml should be `<version>5.0.0</version>`.
* Increment deployed for demo and testing as server-5.0.jar on the production server.
* Increment release `v5.0` created on GitHub with appropriate version number and name.
* Epics and Tasks updated in Zenhub.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order for all methods/functions.
* Unit tests are fully automated.
* Code coverage is at least 50%, 70% preferred.
#### Clean Code
* Code Climate maintainability of A or B.
* Code adheres to Google style guides for Java and JavaScript.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* All tests pass.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration / Delivery
* Travis successfully builds and tests on all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 
* All pull requests are deployed on the development server.
* The development server is never broken.  If broken, it is fixed immediately.


## Plan

We expect to vastly improve our user experience and UI as well as finish up some leftover threads from previous sprints. Namely, getting 3-opt done and concurrency to improve performance. 
We are planning on adding a scrollbar to Search and Itinerary because these are the things that get large and intrusive. We also would like to refactor Search to make it more user friendly, like moving the search button upwards, and putting the search filters in a dropdown menu.
As for 3-opt and concurrency, we are planning on finishing both by fixing up code from sprint4, as we had started 3-opt there. We are planning on parallelizing Nearest Neighbor, 2-opt, and 3-opt. 
We also need to improve our map to a react leaflet map.

Epics planned for this sprint.

* *## User: make the system easier to use: this is essentially telling us to update our UI, which is in desperate need of improvement.*
* *## User: I want an interactive map: this is updating to a react leaflet map.*
* *## User: I want trip planning to be fast: this is concurrency, we are going to be utilizing java's inherent concurrency.*
* *## User: I want the shortest trip possible: This is 3-opt, we will be completing this*
* *## User: I want to know who to thank for this application: this is adding a credits somewhere, this is low on our priority list, as it is probably low on the client's wish list*
*


## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | 5 | 3 |
| Tasks |  24   | 27 | 
| Story Points |  44  | 52 | 

We have always had to add story points as the sprint goes on. Bugs are discovered or we find out we need to do impliment something else to make a technology work. This sprint was no different. We ended up only adding 8 story points for us, which we believe is a new low. We think this speaks to how well we have figured out planning, though we are still 8 points from perfect. 


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| 11/16 | 304, 295 | 286, 288 | none |
| 11/26 | none | 286, 288 | none |
| 11/28 | 287, 292 | 286, 288 | none |
| 11/30 | 297, 304, 286, 288, 291 | 305 | none |
| 12/3 | 294 | 305 | none | 
| 12/5 | | 305 | none  (3-opt is hard) |
| *date* | *#task, ...* | *#task, ...* | *none* | 


## Review

This sprint went oddly. Many of the difficult technologies got implimented incredibly well and went smoother than expected. There were of course errors and glitches, but none that evaded us for long. So concurrency and 3-opt will implimented relatively easily. However, during this sprint our team coordination fell completely. Our team failed to communicate what was getting done and by whom. This lead to many fairly trivial things not getting done until the last minute. Overall, we are pleased that we got done what we did however we are disapointed in our coordination.

#### Completed Epics in Sprint Backlog 

Our solution to this sprint was to deliver functionality serverside over functionality client-side. Though our client-side needed to be usable. We focused on 3-opt and concurrency over cookies and interactive map. This bogged us down because 3-opt, while straigtforward, took us several hours to do code review looking for that last little bug. Concurrency was much faster, as we had designed our optimization code to be parallelizable. 

* *## User: i want to know who to thank for this application: This was straightforward, however we couldn't add in a picture of all of us as us 4 were never in the same location the entire sprint*
* *## User: I want the shortest trip possible: this is 3-opt and while our version isn't fully correct, do to we believe a difference in NN, our version is incredibly fast and short
* *## User: I want trip planning to be fast: This is concurrency, which was shockingly easy for us to impliment. We had taken this into design consideration when designing 2- and 3-opt
* *## User: Make the system easier to use: This boiled down to a bunch of UI improvements that all got handled last minute
* 

#### Incomplete Epics in Sprint Backlog 

We were unable to finish the interactive map and remembering options. We had planned from the beginning to only do the remembering options if we completed early because it seemed minor to us. If we had time for it, great, if not, well, the other tasks were more important. We are disapointed that we were unable to complete the interactive map primarily due to us being unable to duplicate the example code. We decided that our time was better spent polishing 3-opt, concurrency, and the client's user interface.  

* *## User: I want my options remembered so I don't have to fix them all the time: We decided this was the least important epic in our backlog because it was a whole new technology to learn that we didn't have time for this sprint*
* *## User: I want an interactive map: This epic frankly would have taken way too much of our time. While we proabably could have finished it with enough work, this would probably have cost us concurrency
*

#### What Went Well

* Testing
* Bug fixing
* Technology Implimentation (besides leaflet)

#### Problems Encountered and Resolutions

The biggest problems we faced this sprint weren't technological problems, they were personal team problems. Two of our members were unable to participate as much as the rest of us would like do to a multitude of reasons, including illness and a car accident. This went unfortunately unresolved throughout the entire sprint. The only major technical problem we faced was React's leaflet which we were unable to use this sprint. There are some other minor problems listed below.
*Describe what problems occurred during the sprint in general terms followed by a more detailed list.*

* Team Coordination
* Leaflet, unable to use
* Bug fixing on Search Client
* Unable to test client/src/Marginals and other similar code
* Some difficulty in 3-opt, specifically in the ordering of swaps

## Retrospective

This sprint, from a retrospective standpoint, is a flashing red warning sign. We struggled mightly to communicate as a team and never met all four of us face to face or had a four person meeting. This lead to major inabilities to measure who was doing what. Two team members did regularly attend meetings and class and it was clear what they were doing and when they were doing it. However the remaining two team members didn't attend the meetings and this made it basically impossible to measure what they were doing. While they had their reasons, namely a major injury stemming from a car accident and an illness, this became a major detriment to our ability to complete the sprint. This needs to get fixed for future sprints. 

#### What we changed this sprint

This sprint we wanted to continue what had worked pretty well in the previous sprint; start work early, deploy Tuesday and then fix the deploy, and work together. This did not go as planned. 

#### What we did well

We did almost everything even through some of our team members get injured by car accident and missed meetings. We are very pleased that given our struggles, we completed as much as we did. We all have definitevely gotten better at the basic technologies, whether that is javascript or familiarity with the travelling salesman problem. We also did shockingly well on concurrency. John came up with a very efficient design that got implemented and then worked. With no bugs. It was a thing of beauty to watch. 

#### What we need to work on

We absolutely, positively need to work and team communication and coordination. Team meetings the entire sprint were lightly attended and we could not communicate what we were working on without those meetings. Other minor things could use some work (namely leaflet), but they are not nearly as important as this.

#### What we will change next sprint 

The one thing we will change for next sprint is team communication. Every team member is either at the weekly meeting or they are calling in from home. Every scrum meeting, every member will be asked to report what they have done since the last scrum meeting. If they are not in attendence, they will be asked online. If anyone fails this, we will seek help from a manager. 
