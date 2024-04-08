# Flashcard Project

## Your new favourite school application

My project will be a flashcard application where the user will be able to create new flashcard sets, 
and then add question-answer pairs into the sets. The user will be able to view sets, the
questions in the sets, and the answer to these questions along with some, statistics about the chosen card.
It will allow users to mark whether they get the question correct, or incorrect when attempting the 
flashcards allowing them to  focus on the mastery of the set, while also letting them remove
flashcards  that they have already mastered. 

This application can be used by students to help them  prepare for assessments, by people at work who
need  to prepare for a presentation, by individuals learning a new language, and by many others who
are trying to learn something new, or attempting to gauge their understanding of a topic. I am 
designing this as I believe using active recall is a very effective way to learn new information,
and using flashcards is a technique I am familiar with as I have used them many times for school. Flashcards can
have many uses, and can be used by a wide variety of individuals helping them in their 
day-to-day lives. This application is also something I will be able to use once completed, 
to help me in future courses.



**User Stories**:
- As a user, I want to be able to create a new flashcard set, which can be named based on the task it is for
- As a user, I want to be able to select a flashcard set, and add a new flashcard
to it, which includes a question, and answer
- As a user, I want to be able to view all the flashcards in a flashcard set
- As a user, I want to be able to select a flashcard, and add a statistic
for how many times I have gotten it correct, and how many times I have got it incorrect
- As a user, I want to be able to delete a flashcard from my set
- As a user, I want to be able to save a flashcard set, and the flashcards in it to a file
- As a user, I want to be able to load a saved flashcard set from a file

**Instructions for Grader**
- You can generate the first required action related to the user story "adding multiple flashcards to a flashcard set" by
clicking the add flashcard button, and then it will be displayed on the application
- You can generate the second required action related to the user story "removing flashcards" by selecting a flashcard
and clicking the delete flashcard button
- You can generate the third action related to the user story "updating flashcards" by selecting a flashcard
and clicking the update flashcard button, and then prompting if you got the question correct or incorrect
- You can locate my visual component by running the application as it is on the main menu
- You can save the state of my application by closing the application where you will be asked if you would like to save
- You can reload the state of my application by clicking the load set button on the main menu

**Phase 4: Task 2:**

Sun Apr 07 23:31:41 PDT 2024

Flashcard: 2+2, has been added to the set

Sun Apr 07 23:31:46 PDT 2024

Flashcard: 2+5, has been added to the set

Sun Apr 07 23:31:53 PDT 2024

Flashcard: What phase is this?, has been added to the set

Sun Apr 07 23:31:56 PDT 2024

Flashcard: 2+2, has been answered correctly

Sun Apr 07 23:31:58 PDT 2024

Flashcard: 2+5, has been answered incorrectly

Sun Apr 07 23:32:00 PDT 2024

Flashcard: What phase is this?, has been answered correctly

Sun Apr 07 23:32:01 PDT 2024

Flashcard: What phase is this?, has been answered correctly

Sun Apr 07 23:32:02 PDT 2024

Flashcard: What phase is this?, has been deleted from the set
  
**Phase 4: Task 3**

If I were to refactor my project I would like to implement the composite pattern on my project. I would like to do this
as it would allow users to have flashcard sets with flashcards, and they could also have the option to put a flashcard set
that is a subtype of their current set directly in the set with its own questions. For example if someone has a Math set, 
they could have subsets for each lecture in the math set along with some general questions in the main set. Using the design 
pattern to implement these desired changes would still allow the user to perform the user stories I wanted this application 
to have after some refactoring was done. Along with this I would rename some classes, and fields to make the project
layout more clear for others working with the code.