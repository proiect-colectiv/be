    (Razvan)
  !!! Pentru cei ce implementeaza repo si creaza DB:
    * verificati daca compileaza si ruleaza versiunea lasata de mine inainte sa implementati ceva
    * decomentati liniile 18 si 21 din fisierul build.gradle 
        (18:  //implementation 'org.springframework.boot:spring-boot-starter-data-jpa')       
        (21:  //runtimeOnly 'org.postgresql:postgresql')
    * dupa ce decomentati liniile de mai sus si se updateaza proiectul e posibil sa primiti o eroare la runtime. 
      cel mai probabil e din cauza ca spring-jpa incearca sa caute o baza de date pe care sa o foloseasca dar nu gaseste nici una. 
      dupa ce creati baza de date ar trebui sa se rezolve eroarea.
    * metodele getter si setter pentru User si pentru SportiveLocation le-am generat eu pentru ca biblioteca jackson le foloseste ca sa 
      transforme obiectele in JSON cand sunt trimise ca si response in Controller.
    * daca nu va merge ceva sau nu stiti cum sa configurati bd-ul dati un mesaj, incerc sa ajut :))  
   
    
