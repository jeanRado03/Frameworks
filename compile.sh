jar -cf framework.jar classes
cp /home/rado/NetBeansProjects/Frameworks/build/web/WEB-INF/frameworks.jar /home/rado/NetBeansProjects/Test/build/web/WEB-INF/lib/
cd /home/rado/Temp
javac -d . Emp.java
javac -d . Dept.java
cd ~
jar -cf Temp.war Temp
cp /home/rado/Temp.war /home/rado/NetBeansProjects/Test/build/web/WEB-INF/classes

