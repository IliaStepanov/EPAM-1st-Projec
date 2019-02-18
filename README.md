# EPAM-1st-Projec

   
To run this application you will need local tomcat installed and integrated into IntelliJ IDEA Ultimate.
Tomcat can be downloaded from https://tomcat.apache.org/download-80.cgi

To configure Tomcat for its first use, start by opening the file conf/tomcat-users.xml in
your favorite text editor. Place the following tag between the `<tomcat-users>`
`</tomcat-users>` XML tags:

`<user username="admin" password="admin" roles="manager-gui,admin-gui" />`


Open the conf/web.xml file. Search the file for the text org.apache.jasper.servlet
.JspServlet. Below the tag that contains this text are two `<init-param>` tags add the following init
parameters below the existing init parameters:

```
<init-param>
   <param-name>compilerSourceVM</param-name>
   <param-value>1.8</param-value>
</init-param>
<init-param>
   <param-name>compilerTargetVM</param-name>
   <param-value>1.8</param-value>
</init-param>
```


Add Tomcat as a local application server by providing path to your Tomcat directory in Settings -> Applications Servers

To add tomcat to your project click the run/debug configurations icon (a down arrow) on the toolbar, then click Edit Configurations.

Add local Tomcat server, add exploded artifact in deployment tab.

Log=Pass for tests.

Admin  EMAIL@MAIL.COM = PASSWORD
User ExampleEmail2@google.com = examplePassword2
