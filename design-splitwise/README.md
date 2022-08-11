# Design Splitwise

### Requirements Gathering

1. Users can register and update their profiles
2. A user's profile should contain at least their name, phone number and password
3. Users can participate in expenses with other users
4. Users can participate in groups. 
5. To add an expense, a user must specify either the group, or the other users involved in the expense, along with who paid what and who owes what. They must also specify a description for the expense. 
6. A user can see their total owed amount 
7. A user can see a history of the expenses they're involved in 
8. A user can see a history of the expenses made in a group that they're participating in 
9. Users shouldn't be able to query about groups they are not a member of 
10. Only the user who has created a group can add/remove members to the group 
11. Users can request a settle-up. The application should show a list of transactions, which when executed will ensure that the user no longer owes or recieves money from any other user. Note that this need not settle-up any other users. 
12. Users can request a settle-up for any group they're participating in. The application should show a list of transactions, which if executed, will ensure that everyone participating in the group is settled up (owes a net of 0 Rs). Note that will only deal with the expenses made inside that group. Expenses outside the group need not be settled. Good to Have Requirements 
13. When settling a group, we should try to minimize the number of transactions that the group members should make to settle up. 

**Note**: _All tests will be performed in one go. The application doesn't need to persist data between runs._

---
### Input Format

    Register vinsmokesanji 003 namisswwaann
    
    u1 is registering with the username "vinsmokesanji", phone "003" and password as "namisswwaann"
    
    u1 UpdateProfile robinchwan
    
    u1 is updating their profile password to "robinchwan"
    
    u1 AddGroup Roommates
    
    u1 is creating a group titled "Roommates"
    
    u1 AddMember g1 u2
    
    u1 is adding u2 as a member of the group "Roommates" (which has the id g1)
    
    u1 MyTotal
    
    u1 is asking to see the total amount they owe/recieve after everything is settled.
    
    u1 History
    
    u1 is asking to see their history of transactions (whether added by themselves or someone else)
    
    u1 Groups
    
    u1 is asking to see the groups that they're a member of
    
    u1 SettleUp
    
    u1 is asking to see the list of transactions they should perform to settle up
    
    u1 SettleUp g1
    
    u1 is asking to see the list of transactions that need to be performed by members of g1 to completely settle up the group.
    
    u1 Expense g1 iPay 1000 Equal Desc Wifi Bill
    
    u1 is adding an expense in the group g1. u1 paid 1000 Rs each user of g1 owes an equal amount (the exact amount will depend on the number of users in group g1. Say g1 has 5 users, then the amount owed by each will be 200Rs).
    
    u1 Expense u2 u3 u4 iPay 1000 Equal Desc Last night dinner
    
    u1 is adding an expense with users u2, u3 and u4. u1 paid 1000 Rs each user owes an equal amount - 250Rs.
    
    u1 Expense u2 u3 iPay 1000 Percent 20 30 50 Desc House rent
    
    u1 is adding an expense with users u2 and u3 u1 paid 1000 Rs u1 owes 20% (200Rs), u2 owes 30% (300Rs) and u3 owes 50% (500Rs).
    
    u1 Expense u2 u3 u4 iPay 1000 Ratio 1 2 3 4 Desc Goa trip
    
    u1 is adding an expense with users u2, u3 and u4. u1 paid 1000 Rs u1 owes 100Rs (1 part), u2 owes 200Rs (2 parts), u3 owes 300Rs (3 parts) and u4 owes 400Rs (4 parts).
    
    u1 Expense u2 u3 iPay 1000 Exact 100 300 600 Desc Groceries
    
    u1 is adding an expense with users u2 and u3. u1 paid 1000 Rs u1 owes 100Rs, u2 owes 300Rs and u3 owes 600Rs.
    
    u1 Expense u2 u3 MultiPay 100 300 200 Equal Desc Lunch at office
    
    u1 is adding an expense with users u2 and u3. u1 paid 100 Rs, u2 paid 300Rs and u3 paid 200Rs. Each user owes an equal amount - 200Rs.
    
    u1 Expense u2 u3 MultiPay 500 300 200 Percent 20 30 50 Desc Netflix subscription
    
    u1 is adding an expense with users u2 and u3. u1 paid 500 Rs, u2 paid 300Rs and u3 paid 200Rs. u1 owes 20% (200Rs), u2 owes 30% (300Rs) and u3 owes 50% (500Rs).


---
### Database Setup
    postgres=# create user sanket with password 'sanket';
    
    CREATE ROLE
    
    postgres=# create database splitwise;
    
    CREATE DATABASE
    
    postgres=# grant all privileges on database splitwise to sanket;
    
    GRANT

---
### Entities

---
### Strategies

---
### Relationships

---
### Class Diagram

![image](https://github.com/sanketwakhare/lld-designs/blob/master/design-splitwise/src/diagrams/splitwise-class-diagram.png)


---

### Schema Diagram

![image](https://github.com/sanketwakhare/lld-designs/blob/master/design-splitwise/src/diagrams/splitwise-schema-diagram.png)