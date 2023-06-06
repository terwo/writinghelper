# My Personal Project - Composition Idiom Fancifier

## What will the application do?

Help make one's writing more ***fancy***:
- **Keep track** of idioms the user has added.
- Use tags to make searching for certain idioms **easier**.
- Optional comment box to list the significance of the idiom to the user 
and **when to use it**.
- After finding a suitable expression, it can guide one to make changes to their writing.

## Who will use it?

Anyone that's willing to spend time developing a collection of idioms for future use
and wishes to improve their writing through figurative language will hopefully find use
in this application.


## How's it of importance to me?

I wish to improve my proficiency in several languages, particularly Japanese, and
one cannot do so without being able to use very idiomatic expressions of a language. 
Writing is also one of the four aspects of a language, and as the world 
is becoming more globalized, strong communication skills are becoming more important.
Figurative language captures subtleties in meaning, and while they sometimes
make one's message unclear, if used effectively, can convey nuanced messages 
to the reader. I hope this application can aid in this endeavor.

When I encounter a new expression, I also find that a rather deep impression 
is left within me, and I hope to be able to keep track of these personal 
experiences over time. As such, this application can also act like a journal.

## User Stories

- As a user, I want to be able to add an idiom entry, with the idiom itself,
a reading, meaning, optional comment, and tags to a collection of idioms.
- As a user, I want to be able to add to or remove tags from an idiom.
- As a user, I want to be able to search for tags and view a list of idioms with that tag.
- As a user, I want to be able to edit my comment for an idiom, to account for new
experiences or good example sentences with this idiom.
- As a user, I want to be able to save my WritingHelper to file.
- As a user, I want to be able to load my WritingHelper from file.

## Phase 4: Task 2
  Wed Nov 24 22:43:08 PST 2021  
  Created new tag sorrow  
  Wed Nov 24 22:43:08 PST 2021  
  New idiom entry has been made  
  Wed Nov 24 22:43:08 PST 2021  
  New idiom entry has been made  
  Wed Nov 24 22:44:11 PST 2021  
  Created new tag peace  
  Wed Nov 24 22:44:11 PST 2021  
  New idiom entry has been made  
  Wed Nov 24 22:44:17 PST 2021  
  Comments have been edited  
  Wed Nov 24 22:44:21 PST 2021  
  Removed tag sorrow  
  Wed Nov 24 22:44:21 PST 2021  
  Created new tag fortune  
  Wed Nov 24 22:44:26 PST 2021  
  Removed tag peace  
  Wed Nov 24 22:44:26 PST 2021  
  Created new tag nature  
  Wed Nov 24 22:44:29 PST 2021  
  Searched for idioms with tag n  
  Wed Nov 24 22:44:29 PST 2021  
  Searched for idioms with tag na  
  Wed Nov 24 22:44:29 PST 2021  
  Searched for idioms with tag nat  
  Wed Nov 24 22:44:29 PST 2021  
  Searched for idioms with tag natu  
  Wed Nov 24 22:44:29 PST 2021  
  Searched for idioms with tag natur  
  Wed Nov 24 22:44:30 PST 2021  
  Searched for idioms with tag nature

## Phase 4: Task 3

- There are currently bidirectional associations between WritingHelperGUI and IdiomCollectionTableGUI, and between
IdiomCollectionTableGUI and IdiomCollectionOptionsGUI. This is high coupling, and I would refactor it so that if big
changes were made, I wouldn't have to fix up so many classes. I believe this can be done by removing the association
from WritingHelperGUi to IdiomCollectionTableGUI, and from IdiomCollectionTableGUI to IdiomCollectionOptionsGUI
- There is also low cohesion in the IdiomCollectionOptionsGUI. I have allocated making new entries to a new class,
but I would also refactor this class so that the other options supported by it are made into new classes. Additionally,
the behaviour for printing logged events to the console is in the WritingHelperGUI class, so to further increase
cohesion, I would refactor my project to follow the observer pattern.
- If the above were to be done, I would also refactor my project to include some abstract classes or interfaces to
lower reduplicated code. Since most of the features supported work with buttons, implementing some common behaviour
between all these would be logical.