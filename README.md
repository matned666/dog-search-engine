#SEARCH ENGINE  ver. 0.1

This is my first training try with an own hibernate program. <br />
This is a simple JFrame with search engine, connected with<br />
First you can choose your table, than you can guuugle your records :-)<br />
<br />
JFrame -> Model Contract View pattern common in Android <br />
<br />
You can search them by name regex, age, weight, <br />
owner name , etc.<br />
<br />
A simple database sql file is in resources folder.<br />
It is with a table creation and 1000 records (dog.sql).<br />
<br />
Hibernate.cfg.xml file is also in resources, but you'd have to<br /> 
check it in my first commit.<br />
You have to add your database name, host, and user with <br />
privileges, of course password and mapping class to entities.<br />

#GUIDE
USE:<br />
Choose your desired table and search by pressing enter or search button. That's all<br />
"a%" gives all entries on letter 'a'<br />
"al%" entries on 'al'<br />
...<br />
- you can open advanced search options.<br />
- you can check fields to show in your search TODO<br />
- you can check fields to show TODO<br />

#INSTALLATION
BEFORE RUNNING:<br />
Configure your database data in Hibernate.cfg.xml<br />
 - host, database name, login and password<br />
 - add Entities <mapping class = "eu.mrndesign.matned.searchEngine.data.hibernate.entity.Product"/><br />
Create database and run database queries (/resources) to create first tables and entries.<br />

IF YOU D LIKE TO ATTACH ANOTHER DATABASE TABLE:<br />
Add name of the table to the db_collection table 
- Create Entity
- Create Dao implementing DaoInterface 
 - Dao should have list of fields returning as below example:<br />
         return Arrays.asList("NUMBER::Id::",<br />"VARCHAR::Name::",<br />"CHECKBOX::Gender::M::F", <br />"NUMBER::Age::", <br />"VARCHAR::Race::", <br />"NUMBER::Weight::", <br />"VARCHAR::Owner name::", <br />"VARCHAR::Owner surname::");<br /><br />
- Add case in DataInterpreter getResultList(String item) as below:<br />
            case "Dog": { <br />
               dao = new DogDao(item);<br />
                return dao.find();<br />
            }<br />
  
#FEATURES:
- Auto advance search layout based on entity structure
- Many search options including sorting and sql select
- Easy new tables implementation - only 4 steps
- Nice and legible layout
- GUUUUGLEEE !!!! :-)

#TODO
- TESTS !!
- order by and select functions are not done yet
- code refactor (for ex. if waterfalls, etc.)
- result records amount limiter

My website: http://mrndesign.eu

SUBSCRIBE FOR MORE 