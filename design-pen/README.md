Design a Pen
=========================

Requirements Gathering:
--------------------------
1. Pen is anything that can write
2. Gel, Ball, Fountain, Marker
3. Ball pen has ball pen refill
4. Gel pen has Gel pen refill
5. A refill can have a Tip and Ink
6. Ink can be of different colors
7. A Fountain pen has an Ink
8. Refill has radius
9. Fountain pen's Tip has radius
10. Each pen can write in different ways
11. Some pens write in same way
12. Pen has a brand and name
13. Some pens may allow refilling while other pens might not


Entities:
---------
Pen
Refill
Ink
Tip

+-----------+
|Pen	 	|
| {abstract}|
+-----------+
|-name	 	|
|-brand	 	|
|-PenType	| 		
|-price  	|
+-----------+
|+write()	|
|+getColor()|
+-----------+

+-------------------+
|<<enumeration>>	|
|PenType			|
+-------------------+
|GEL				|
|BALL				|
|MARKER				|
|FOUNTAIN			|
+-------------------+

+-------------------+
|BallPen 			|
+-------------------+
|-Refill 			|
|-canChangeRefill	|
+-------------------+
|+changeRefill()	|
|+write()			|
|+getColor()		|
+-------------------+

+-------------------+
|GelPen 			|
+-------------------+
|-Refill 			|
|-canChangeRefill	|
+-------------------+
|+changeRefill()	|
|+write()			|
|+getColor()		|
+-------------------+

+-------------------+
|MarkerPen 			|
+-------------------+
|-Refill 			|
|-canChangeRefill	|
+-------------------+
|+changeRefill()	|
|+write()			|
|+getColor()		|
+-------------------+

+-------------------+
|FountainPen		|
+-------------------+
|-Ink 				|
|-Tip 				|
|-canFillInk:boolean|
+-------------------+
|+fillInk()			|
|+write()			|
|+getColor()		|
+-------------------+

+-------------------+
|Refill				|
+-------------------+
|-RefillType		|
|-Tip 				|
|-Ink 				|
+-------------------+
|+getColor()		|
|+getRemainingInk()	|
+-------------------+

+-------------------+
|<<enumeration>>	|
|RefillType			|
+-------------------+
|GEL				|
|BALL				|
|MARKER				|
+-------------------+

+-------------------+
|BallPenRefill		|
+-------------------+
|+BallPenRefill()	|
+-------------------+

+-------------------+
|GelPenRefill		|
+-------------------+
|+GelPenRefill()	|
+-------------------+

+-------------------+
|MarkerRefill		|
+-------------------+
|+MarkerRefill()	|
+-------------------+

+-------------------+
|Ink				|
+-------------------+
|-color				|
|-InkType			|
|-density			|
+-------------------+
|+getColor()		|
+-------------------+

+-------------------+
|<<enumeration>>	|
|InkType			|
+-------------------+
|GEL				|
|NON_GEL			|
|FLUID				|
|FOUNTAIN			|
+-------------------+

+-------------------+
|Tip				|
+-------------------+
|-radius			|
|-TipType			|
+-------------------+

+-------------------+
|<<enumeration>>	|
|TipType			|
+-------------------+
|NORMAL				|
|BALL				|
|FOUNTAIN			|
|SPONGE				|
+-------------------+


Relationships:
--------------
Pen <-> Refill

1 -> 1

1 <- 1

1 <-> 1

Refill <-> Ink

1 <-> 1

Refill Tip

1 <-> 1

Pen Ink

1 <-> 1
