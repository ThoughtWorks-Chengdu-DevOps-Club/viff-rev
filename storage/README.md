# HOW TO RUN THIS
```
java -jar ./build/libs/storage-0.0.1-SNAPSHOT.jar --spring.profiles.active=tianbo
```

# Docker stuff
## create docker volume storage
```
docker create -v /storage --name viff-file-storage java:8 /bin/true
docker create -v /storage --name viff-db-storage mysql /bin/true
```
## run storage volume storage
 ```
 docker run -d --volumes-from viff-file-storage --name viff-file-storage_1 java:8
 docker run -d --volumes-from viff-db-storage --name viff-db-storage_1 mysql
 ```