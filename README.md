Manolache Marius-Alexandru, 323CA

First, the directory's:

- children: contains a Child, which is a Visitable Class used for storing children information,
extended by the Baby, Kid and Teen classes,
which are instantiated using the Factory Class ChildrenFactory

- input: contains all the classes that deal with the Input
Product Class contains the information regarding a gift
Change Class contains the information regarding a yearly change
UpdateGiftList is Utility Class used for applying a yearly change
InputLoader Class is used as a database for the Input

- output: contains a Singleton Class used as a database for the Ouput
and has the exact structure as the json output file

- santa: contains the classes that deal with handing out gifts to the children
Santa Class calculates a child's budget and assigns him presents 
Child Visitor is an interface for all the Child Visitors, implemented only by AverageScoreVisitor for the moment
AverageScoreVisitor is a Visitor class which calculates a Child's average nice score based on his history

The flow of the app:

The Main Class coordinates the whole program
The input is read using Jackson from json files
Each test is split into 2 parts: the initial data phase and the changes phase

After the initial data phase and after each annual change is applied,
a new set of children are created using the Factory Method,
they can be either Baby, Kid or Teen, and based on that
the AverageScoreVisitor class calculates their average nice score
and then Santa assigns every Child a present

niceScoreBonus -> implemented inside the AverageScoreVisitor class, pretty simple

Elves -> "white" is ignored,
      -> "black" and "pink" are implemented using Strategy Pattern, inside ElfBudgetStrategy folder
      -> "yellow" is implemented as part of Santa,
         all the children with the yellow elf are stored inside a list
         after Santa assigns gifts, this list is parsed,
         and every eligible child gets a gift

Now the problem is that I was keeping a list with the cheapest gifts from every category,
if a gift goes out of stock, this list updates, but the yellow elf assigns a gift only if
the cheapest gift from the most like category of the child is still in stock. So I created
a HashSet containing all the cheapest out-of-stock gifts.

Strategy -> "id" is ignored, the children are always sorted based on id at the start of a test / after a change,
            it's just that the other strategies don't apply, like not using one at all
         -> "niceScore" and "niceScoreCity" are implemented using Strategy Pattern, inside GiftAssignStrategy
         -> for the city scores, I keep a map with every city and it's scores,
            and a map with every city and the number of children in that city,
            after completing these 2 maps, I calculate the niceScoreCity for each kid

