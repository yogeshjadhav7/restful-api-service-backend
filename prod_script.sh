lsof -n -i :9000 | grep LISTEN | awk '{ print $2 }' | uniq | xargs kill -9
mkdir /home/yogesh/apps/play-apps/restful-api-app/target/RUNNING_PID/
rm -rf /home/yogesh/apps/play-apps/restful-api-app/target/RUNNING_PID/play.pid
./activator "start -Dhttp.port=9000"