# restful-api-service-backend

### Description
----
This Java project uses Web Play FrameWork (https://www.playframework.com/) and is used as REST service by all my projects as well as my bio-site (http://www.yogeshjadhav.website/). This service is live at http://api.yogeshjadhav.website/.

### Prerequisites
----
- Java 1.8
- MYSQL (recommended ver: 5.7.17) instance running locally or remotely. Use **db_setup.sql** for setting up the database schema. Place your connection endpoint url in **conf/application.conf** against **db.default.url** and connection credentials against **db.default.username** and **db.default.password**.
- (Optional) Firebase Messaging Credentials. I use them to trigger notifications about various events on the client devices of my android app Intellecto (https://play.google.com/store/apps/details?id=com.jadhav.yogesh.intellecto). Add your firebase authorization key against **firebase.notification.v0.authorization.key** in **conf/application.conf**.
- (Optional) AWS SimpleEmailService credentails. I use AWS SES to send OTP on the client devices for user to sign into Intellecto app. Add your AWS SES access and secret keys against **aws.ses.access.key** and **aws.ses.secret.key** in **conf/application.conf**. You will have to move your AWS SES out of sandbox (https://docs.aws.amazon.com/ses/latest/DeveloperGuide/request-production-access.html), if not done already, to register your email address or domain name to send emails to your clients. Add your registered email address against **aws.ses.from.email** in **conf/application.conf**.
- (Optional) I use **intellecto.api.key** located in **conf/application.conf** to authorize incoming API requests to the service. This ensures that the requests have organically come from android devices only.
- All the above (Optional) point are redundant if you are not the owner of the Intellecto app, that is, me! :P. Just for the sake of completion and clarity to the reader of this README, I have mentioned them.

### Installation
----
> Clone the repository.

``` sh
git clone https://github.com/yogeshjadhav7/restful-api-service-backend.git
```

> Install the Play Framework and the dependendies of the project.
``` sh
cd restful-api-service-backend
./activator clean compile
```

> Run the installation.
``` sh
./activator clean run
```

>> Test the installation.
``` sh
curl http://localhost:9000
```

### Production Deployment:
----
> Go to the directory.
``` sh
cd path-to-root-dir-of-project
```
> We would be using port 9000 for this service. Firstly, check whether any process is using that port. Kill the process if it is using 9000 port. This will also help in redeployment of this service by terminating the already running instance on the same port.
``` sh
lsof -n -i :9000 | grep LISTEN | awk '{ print $2 }' | uniq | xargs kill -9
```

> Create RUNNING_PID/ in target directory if not present. This directory will house the process id of the play instance. The configuration of the same is specified as Java option in **build.sbt** file. 
javaOptions in Universal ++= Seq(
..
)
``` sh
mkdir -p target/RUNNING_PID/
```

> Delete the **play.pid** of last ran play instance. This will help in redeployment of the service.
``` sh
rm -rf target/RUNNING_PID/play.pid
```

> Finally, you are readty to start the service on port 9000. If you want to run the service on different port, you can by ensuring the corresponding changes in steps above. You could also specify other Java options in **build.sbt**.
``` sh
./activator "start -Dhttp.port=9000"
```

> Or you could use the already created shell script **prod_script.sh** to deploy.
``` sh 
sh prod_script.sh
```