#github linke:
https://github.com/shirlosh/


#### Window's App Evolution Timetable #### 
An effortless way to find the best schedule that's meet your school workers and classes requirements.



#####   SYSTEM REQUIREMENTS  #####

# Runs best on Windows 7 (and higher) or macOS (10.11.6) with Linux (centOS 6.7) or higher

# Requires Java 1.8 (https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)


You can access your application from: http://localhost:8080/sample



If it doesn't open properly, check the log files (e.g. tomcat/logs/catalina.out) for problems with deployment.



##### RUNNING #####

1. Download and unpack the war file from git

2. Copy the WEB_war.war file to %CATALINA_HOME%\webapps ( E.g.: C:\tomcat\webapps )

3. Run %CATALINA_HOME%\bin\startup.bat

4. Go to %CATALINA_HOME%\conf\server.xml and take the port for the HTTP protocol. <Connector port="8080" ... />. The default value is 8080.

5. Access the following URL: http://localhost:8080/WEB_war/



* ALGORITHM INFO
individual - a TT solution
fitness - the grade of a solution (range of 0 – 100)
population - a group of solutions
each generation contains a population



#### System performance ####

				generation amount
			1,000		10,000		100,000
population	200	1sec		8.414sec	91.453sec
size		500	1.437sec	19.463sec	194.532sec
		1,000	3.86sec		42.856sec	429.477sec


