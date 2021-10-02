# _The Checkout Technical Challenge_

This challenge is meant to demonstrate your technical ability and your proficiency with Java technologies.  You are welcome to use any software package and consult external reference materials (web, textbooks, articles, product manuals etc.). All work must be your own.

You have been given an implementation of a Supermarket checkout program. Your task is to modify this solution and make the code more readable, 
correct it where you think it is necessary, bring it in line with modern Java software development best practices and/or change the code to comply with what you consider to be _good_!

There are many ways in which you may choose to refactor the code.  In addition to writing clear code, we'd like you to use this opportunity to demonstrate your ability to improve your code using small, self-contained changes, each with a descriptive commit message explaining what you achieved.

You should not spend more than a few hours on this problem. Please provide the source code along with `README.md` instructions on how to build/test/run the system. If you have any questions, feel free to open a Github Issue.  You likely won't have time to do all the refactoring that you would like to do, so please feel free to describe other refactoring steps that you would have taken if you had more time.

**When you have completed the challenge please submit a pull request.**

## Supermarket Checkout ##

Supermarket checkout is a program that calculates the total price of a number of items. Some discount rules may apply.

Some items have multiple prices based on price rules such as:
- *Rule1*: buy any 3 equal priced items and pay for 2
- *Rule2*: buy 2 equal priced items for a special price
- *Rule3*: buy 3 (in a group of items) and the cheapest is free
- *Rule4*: for each `N` items `X`, you get `K` items `Y` for free

The program takes input from a CSV file with the checkout items. The file contains the `item-id`, the `group-id`, the quantity and the unit price.
A second input file should contain the configuration of the rules and to which items they apply. For example, *Rule2* needs to know to which `item-id` it applies to and what is the final price to charge. Similarly, *Rule4* needs `N`, `X`, `K` and `Y` to be defined. Rule1 and Rule3 should work out of the box without needing extra configuration.
Each item can have at most only one discount applied. If more than one rule applies, pick the best discount for the user.
The output required is the receipt with the actual price of every item and the grand total.

