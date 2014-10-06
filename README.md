markov
======

CS 251 Intermediate Programming
Markov Chain Text Generation
Brooke Chenoweth
Fall 2014
For this project, you will create a program that creates a Markov chain from a text le
and generates some gibberish.
I expect you to submit at least two les:
StringChain.java
, to represent the Markov
Chain, and
Markov.java
(working version already provided to you), to contain the main
method for the program.
1 StringChain
Write a class named
StringChain
to represent a Markov Chain of
String
objects.
1.1 Public Methods
Your class should have the following public methods. (You will also likely need some private
helper methods to implement them.)
public StringChain(int order)
{ The constructor will take one integer argument
representing the order of the chain.
public void addItems(Iterator<String> itemIter)
{ This method will add a se-
quence of items to the chain, as given by a string iterator. It is possible to add more
than one sequence to the chain by calling
addItems
multiple times.
public List<String> generate(int n, Random rand)
{ Generate a list of
String
s
of length
n
, using the provided random number generator.
1.2 Internal Structures
Order of the markov chain. This is the value passed into the constructor. It isn't going
to change, so make it nal.
Map of prexes to sux probabilities. This map will be lled based on the sequence
given to the
addItems
method. Remember,
addItems
may be called more than once.
Create private nested classes for the prexes and sux probability objects. It is up to
you whether they should be static or non-static classes.
1





{
Prex key class { A simple implementation would wrap a list of Strings. Since this
going to be used as a Map key, you'll need to override the
equals
and
hashCode
methods, at least.
{
Sux probabilities class { A simple implementation might just wrap a list of
Strings, with more common values appearing more often in the list. This does
have the disadvantage of taking up more space than a fancier implementation.
A more complicated implementation might wrap a map of unique sux strings
to frequency or probability values. This implementation would save space, but
would be trickier to implement correctly.
However you choose to implement this, you should have a way of taking a random
number generator and selecting a sux, with more common frequencies being
more likely.
2 Markov
I am providing you with
Markov.java
to handle the text le parsing. You may use it as
is, or you can modify it if, for example, you want to add additional ways to break up the
text besides words and sentences. If you do change it, make sure the original behaviour
still works. Your
StringChain
class should work with the original
Markov
program, so no
changing the test code to work around issues in your implementation!
The
Markov
program will take at least 4 command-line arguments.
1. The order of the Markov chain.
2. The number of items that should be in the generated output.
3. Either \
word
" or \
char
" to indicate if the source text will be broken into words or
characters.
4. The name of the rst text le that will be used to generate the random text.
5. Any additional arguments are the names of additional text les to add to the chain.
For example, the command
java Markov 2 100 word sonnet.txt
generates 100 words of random text based on the contents of
sonnet.txt
using a Markov
chain of order 2.
The command
java Markov 3 500 char MobyDick.txt sonnet.txt
generates 500 characters of random text based on the contents of
MobyDick.txt
and
sonnet.txt
using a Markov chain of order 3.
The generated text should be printed to standard output.
2
3 Hints
java.util.Scanner
extends
Iterator<String>
You may nd some of the methods in
java.util.Collections
quite useful.
When adding a sequence to your table of prexes and suxes, pad the beginning and
end with nonword strings (An empty string will do the trick.).
When you generate the output, start with an initial prex of all nonword strings and
you'll nd the starting word as its sux.
Similarly, if you pad the end with nonwords, you'll be able to keep generating text
when you would otherwise have terminated.
Example table for order 2 chain from input:
Quickly, he ran and he ran until
he stopped.
Prex
Suxes and Probability
NONWORDNONWORD
Quickly,
(100%)
NONWORD
Quickly,
he
(100%)
Quickly, he
ran
(100%)
he ran
and
(50%),
until
(50%)
ran and
he
(100%)
ran until
he
(100%)
until he
stopped.
(100%)
he stopped.
NONWORD
(100%)
stopped.
NONWORD
NONWORD
(100%)
4 Challenges
Want more Markov chain fun? Try some of these ideas, but make sure you don't break the
original requirements.
How else might you break up a text? Add additional options beyond
char
and
word
.
You'll probably want to look at the
Pattern
class to learn more about regular expres-
sions.
If one of the le names is a directory, add all the les inside it to the chain. If you
want to get even fancier, do this recursively.
How fast is your program? Do you have to wait when adding several novels? Try to
track down where the time is going and improve it.
Could you make a generic
MarkovChain<T>
class that can make chains of anything, not
just
String
s? Your
StringChain
class could then simply extend
MarkovChain<String>
and do very little work itself.
Have you generated some particularly amusing text? Share them in the discussion
forum. What source les did you use and what settings?
3








