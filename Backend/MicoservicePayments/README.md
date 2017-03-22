# NWT2017-ETF
Aplikacija na predmetu NWT 2016/17

### Potrebni alati

Da bi aplikacija radila ispravno potrebno je instalirati:

* [Wamp](http://www.wampserver.com/en/#download-wrapper) - Server na kojem ce se nalaziti MySql baza (moguce promjene kasnije)
* Neki IDE/Editor koji planirate koristiti (Netbeans, Eclipse, IntelliJ...)
* [Postman](https://www.getpostman.com/) - Potreban za testiranje endpointa

### Postavke Wamp-a

Nakon sto se istalira potrebno je otvoriti bazu podataka kroz phpMyAdmin ili kroz CMD (kako kome lakse) i kreirati pocetnu tabelu "banking". 
(Kasnije cemo napraviti da se baza sama kreira ukoliko ne postoji.)

### Postavke IDE/Editora

Nakon uspjesne pull/update operacije otvoriti projekat u IDE/Editoru i poskidati dependencies-e koji su navedeni u pom.xml fajlu. Poslije toga potrebno je da podesite postavke (url, username, password) prema vasim postavkama wamp baze u fajlu koji se nalazi na putanji:
"**src/resources/application.properties**".
Ako sve prodje bez problema pokrenuti aplikaciju putem RUN komande.

### Test
Da bi se provjerilo da li je sve uredu potrebno je pomocu Postman-a uraditi jedan GET zahtjev na lokaciju http://localhost:8080/accounts/testing koja ce dodati 3 dummy racuna u tabelu. 
A ako zelimo provjeriti da je to uredu uradimo jos jedan GET zahtjev prema http://localhost:8080/accounts?page=0&size=3


