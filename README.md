Wordle tribute for Programming Languages.

 #Build Needs
 
 -Maven
 
 -Docker
 
 -Node
  
cd Softdle/Softdle/backend/softdle
  sudo docker build -t db .
  sudo docker run -p 5432:5432
  mvn spring-boot:run
cd Softdle/Softdle/frontend
  npm install
  npm start
