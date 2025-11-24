# RobotWorkplaceMultithreading
**23.11 2025**

projekt pro školu SPŠE Jěčná

## autor
Tadeáš Máca

email: tadeas.maca@gmail.com*

## popis projektu
Projekt popisuje továrnu s mnoha pracovními pozicemi a autonomními roboty.
Každá pracovní pozice si může vyžádat materiál, který ji pak jeden z robotů doručí.

Projekt by mělo být možné využít v praxi po připojení API robotů a pracovních pozic.

## architektura aplikace

Aplikace je obsahuje objekty `RobotDispatcher`,`Workplace` a `Robot`.

### RobotDispatcher

Má na starost všechny roboty, zadává jim práci.
- Využit návrhový vzor ThreadPool
- Každý robot získává od RobotDispatchera práci

### Robot
Rozváží materiály po továrně.
Metoda robota `work()` se dá napojit na API továrního robota

### Workplace
Pracovní pozice, pokud nemá materiály pošle požadavek RobotDispatcherovi

## licence
Tento projekt je licencován pod MIT Licencí.

Více najdete zde [LICENSE](LICENSE)

## instalace
pro spuštění programu je potřeba Java verze 17 a Maven

zkopírujte repozitorář

```
git clone https://github.com/tadeas223/RobotWorkplaceMultithreading
cd RobotWorkplaceMultithreading
```

spusťte pomocí Maven

```
mvn exec:java -Dexec.mainClass="org.example.Main"
```

případně pokud není možnë použít maven nainstalujte pomocï příkazů níže
```
cd src\main\java\org\example
javac Main.java Material.java WorkTimer.java robots\Robot.java robots\RobotDispatcher.java robots\RobotStatus.java robots\RobotTask.java workplaces\Workplace.java
cd ../..
java org.example.Main
```


## TODO
- přidat kontrolu správnosti adresy pracovní pozice
