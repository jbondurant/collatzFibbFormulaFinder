# collatzVariantSearch

Recreational number theory. The Collatz rule is "3n+1 if odd, n/2 if even"; this
searches what happens when you change the pieces: the multiplier, the added
constant, and the divisibility rules. Each variant runs over a range of starting
numbers (BigInteger, multithreaded) to see which rules still send everything to 1.

Findings so far, recorded live in the comments of `SequenceGenerator.java`:
"ok yeah no pattern emerges, oh well :)"

Not an attempt to prove anything, just poking at the landscape for fun.

(This repo used to be called collatzFibbFormulaFinder: it began as a formula
finder that rediscovers the golden ratio from raw Fibonacci terms, by brute
forcing constants of the form (a + sqrt(b)) / c until one predicted every next
term. That proof of concept got deleted in a cleanup; the Collatz half stayed.)
