# App_Dir
Assignment_AppDirect

#Steps to run:

1.Via github:
a.Clone the project from github 
URL: https://github.com/shaileshrahinj/App_Dir.git
Command:
Shailesh_Rahinj@HJD10731 MINGW64 /e/App_Dir
$ git clone https://github.com/shaileshrahinj/App_Dir.git

b.Go to the master branch
Shailesh_Rahinj@HJD10731 MINGW64 /e/App_Dir
$ git checkout master

c.To have the latest code on your system.
Shailesh_Rahinj@HJD10731 MINGW64 /e/App_Dir
$ git pull origin master


2.Once you have the project in your IDE ex: Eclipse, navigate to "Resource Jars" folder in the project and select all the jar file and perform the following :
 right click on all the jar-> click on build path-> Add to build path.

3.Navigate to below file  and replace the  excel sheet(TestData.xlsx) location :
App_Dir/src/data/Constant.java 
Ex: String Path_TestData = "F://YourSystemsPath//WorkSelenium//AppDir1//src//data//TestData.xlsx";

4.Navigate to below properties file and change the location of key: "driverPath".(You need to give path upto the "BrowserDrivers" folder)
App_Dir/src/pkg/app.properties 
Ex: 
driverPath=F:\\YourSystemsPath\\WorkSelenium\\AppDir1\\BrowserDrivers

#optional
5.If you want to activate log4j logging then please follow below steps:
Add the location of the log4j.properties files by clicking on: Run->Run Configuration -> [classpath tab] -> click on user Entries -> Advanced -> Select Add Folder -> select the location of your log4j.properties file
and then -> OK -> run

That’s It :) you would be able to run the project now.




Note 1: If you want to directly have a project without using git, you can copy the "App_Dir" project present under "Automation Project" folder from the google drive link. Add the project as a java project. 
And repeat the steps 2 to step 5 mentioned above.

Note 2: I have already sent the jars along with the project, though if you want it as a Maven project then you can just right click on the project-> click on configure->convert to maven project. pom.xml file is present as well.

