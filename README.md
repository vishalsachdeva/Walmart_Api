Steps: -

1) It will ask you to enter the product name

2) As you enter the product name it will connect walmart api of product search via

method product_search_api. I will return the product id

3) Once we get product id, walmart api for recommendation will be connected via

method recommendation search api and it will return array list of recommended

products if the recommendations are available otherwise program will exit

4) Once we get array list of recommendation, this will be passed to the method

review search api which will return the recommendations in sorted order with

there ratings.

Example: -

Enter the product name

phone

Product id of product searched for: 27251410

Product Id with the ratings in sorted order:

[25026216=4.74, 45075370=4.67, 37560693=4.62, 27251409=4.3, 10992508=4.13,

22084643=4.11, 25026223=4.03, 16472530=3.98, 16923599=3.86, 21438946=2.86]

Enter the product name

shoe

Product id of product searched for: 34348912

No Recommendations

Testing

Test and testRunner class is used to do the unit testing of the Application using

JUnit
