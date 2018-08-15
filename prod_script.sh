cd /home/yogesh/apps/play-apps/restful-api-service-backend/
lsof -n -i :9000 | grep LISTEN | awk '{ print $2 }' | uniq | xargs kill -9
mkdir -p target/RUNNING_PID/
rm -rf target/RUNNING_PID/play.pid
./activator "start -Dhttp.port=9000"
