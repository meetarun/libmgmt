The Application Library Management System is created using

		SpringBoot v2.2.1 
		Spring Security v5.2.1
		Hibernate
		ReactJS and Bootstrap
		Log4j
		Postgresql 12.0


I have used 
<B>
		Jasypt Encoder    (v1.9.3)
		Bcrypt Password Encoder 
</B>
to encrypt all the passwords used in DB and application properties.
I have further used 
<B><h4>
		JWT Token for API Authentication<br>
		Role Based Authentication <br>
		Added CORS Support<br>
		and Method Level Security<br>
	</h4></B>
		
and created Junit SpringBootTest cases.

I have created a Pageable Search in spring and created fuzzy search UI with custom footbar for search result navigation.


Admin Can 

	Search Books
	Add Book
	View all the issued books
	
Student Can

	Search Books
	View his issued books


Following roles were created for Library Management,
	ROLE_ADMIN,
	ROLE_SUPERVISOR
	ROLE_STUDENT



How to Install and Use the Application,

1.	Run the SQL Scripts in the librarydb_sql.sql file  , this will create the  db named  ‘librarydb’ and a user named ‘librarydbuser’  with password ‘libarydbuser1’  and required tables with seed data for the application.

2.	Download the Library Springboot Application code from git link https://github.com/meetarun/libmgmt

Import the same in your preferred IDE or compile the code directly using maven command
 mvn install  or mvn clean install
this will create the jar  in target folder and  .m2\repository\com\apus\arunlib\0.0.1-SNAPSHOT\arunlib-0.0.1-SNAPSHOT.jar

3.	Now start the jar using  java -jar <jar path>

4.	Download the UI code from the link, https://github.com/meetarun/libmgmt

5.	Start the UI using the npm commands

npm install
npm start

6.	Now access the UI with the URL http://localhost:3000/


I have also provided the <B> Postman Collection of the Library APIs </B> in the file  libraryDB_Target.postman_collection.json, The application can be accessed via this endpoints using valid jwtToken in Authorization Header.

The user passwords can be encrypted using   PwdEncrypterUtil  class which has main method.

The db connection and test user Input passwords can be encrypted and decrypted using  JasyptEncoderUtil
Provided in the application package.


