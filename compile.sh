#/home/rado/Bureau/Frameworktest/Framework/src/annotation
#/home/rado/Bureau/Frameworktest/Framework/src/etu2014/framework
#/home/rado/Bureau/Frameworktest/Framework/src/etu2014/framework/servlet
#/home/rado/Bureau/Frameworktest/Framework/src/exception

javac -d . /home/rado/Bureau/Frameworktest/Framework/Url.java
javac -d . /home/rado/Bureau/Frameworktest/Framework/Mapping.java
javac -d . /home/rado/Bureau/Frameworktest/Framework/ModelView.java
javac -d . /home/rado/Bureau/Frameworktest/Framework/UrlInconue.java
javac -d . /home/rado/Bureau/Frameworktest/Framework/FrontServlet.java
cp -r /home/rado/Bureau/Frameworktest/annotation /home/rado/Bureau/Frameworktest/Framework/classes/
cp -r /home/rado/Bureau/Frameworktest/etu2014 /home/rado/Bureau/Frameworktest/Framework/classes/
cp -r /home/rado/Bureau/Frameworktest/exception /home/rado/Bureau/Frameworktest/Framework/classes/
jar cvf /home/rado/Bureau/Frameworktest/Framework.jar -C /home/rado/Bureau/Frameworktest/Framework/classes .
cp /home/rado/Bureau/Frameworktest/Framework.jar /home/rado/Bureau/Frameworktest/Temp/WEB-INF/lib/
javac -d /home/rado/Bureau/Frameworktest/Test/WEB-INF/classes/ /home/rado/Bureau/Frameworktest/Test/src/java/test/Dept.java
javac -d /home/rado/Bureau/Frameworktest/Test/WEB-INF/classes/ /home/rado/Bureau/Frameworktest/Test/src/java/test/Emp.java
cd Temp
jar -cvf /home/rado/Bureau/Frameworktest/Test/Test.war *
cd ../
cp /home/rado/Bureau/Frameworktest/Test/Test.war /var/lib/tomcat9/webapps
