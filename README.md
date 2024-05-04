<h1>About the Second Project</h1>
<h2>The Project is a Login's System than have very important Programming concepts
like a OOP's Pillars, Collections data's Structure(ArrayList, Sets...), Regular
Expressions, Interations/Integration with a Relational DataBase, Error's Treatment and 
use of JavaMail API, in this Readme file i will speak about the project as a whole and in 
brief.</h2>

<h2>Exceptions's Package</h2>
<p>This package has personalized Exceptions than can happen in execution
of Application, now see all exceptions in this Package</p>
<p>*InvalidOptionException = This exception can happen if the user insert an invalid option(an option than can exists for example)</p>
<p>*MailException = this exception can happen if has an problem in your mail(invalid mail)</p>
<p>*PasswordException = this exception can happen if has an problem in your password(invalid password, the password don't follow the requiriments)</p>
<p>*VerificationCodeException = this exception can happen if the verification code sent is not equals to than user inserted</p>

<h2>Validator's Package</h2>
<p>The package contains two classes than has similar task, but in different parts of an email. In this package, 
the classes is responsible for validation of Email and Password, about i will explain</p>
<p>*MailValidator.class = this class contains an single static method than validates an String, and see if contains an @ and .com</p>
<p>*PasswordValidator.class = this class contains too an single static method than validates an string from a regex. thereFore
if an passwrod dont have at least, 4 characters, 1 number and 1 special character, the password is invalid!</p>

<h2>DataBaseClass's Package</h2>
<p>The package contains two class than is responsible of connection of DataBase and interact of a Database, in this project we used the
PostgreSQL, and we created an single dataBase called usuarios</p>
<p>*ConnectDataBase class = Class responsible for the connection with a dataBase, this class use the Java Data Base connectivity to communicates
with the dataBase.The JDBC API intermediates the communication of Project and Relational DataBase, the api use SQL code, becoming a api of low level
(Attributes = Connection connection, String urlConnection, String userDataBase, String passwordDataBase, Methods=StartConnection)</p>
<p>*DAOUser class = Class responsible to realize the crud of App, she contains methods than can manipulate the usuarios's data Base, have methods
like a insert a user in db, delete a user, seeAllrows...</p>

<h2>Service's package</h2>
<p>The package contains three class than is responsible of Realize logins, User's Register and an Class than is responsible of
send mails utilizing JavaMail API.</p>
<p>*EmailService class = Class responsible for Send emails in application</p>
<p>*Login Class = Class responsible for realize logins and User's Authentication</p>
<p>*User class = Class responsible for User's Register</p>
