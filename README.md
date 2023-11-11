# nye-progtech
Programozási technológiák beadandó feladat.

Feladat: Wumplusz parancssoros labirintusjáték implementáció
A wumplusz egy egyszemélyes problémamegoldó 2D-táblajáték, aminek jelen feladatban a pályaszerkesztőjét és játékmenetét kell implementálni.
A program egy felhasználónév bekérésével kezdődik, utána a program egy alapmenüvel folytatódik, amiből lehet lépni pályaszerkesztésbe, fájlból beolvasásba, adatbázisból betöltésbe, adatbázisba mentésbe, játszásba, kilépésbe.
A pályaszerkesztés és a fájlból beolvasás közül elég az egyiket implementálni.
Mind a fájlbeolvasásnál, mind a szerkesztés vége jelzése után validálni kell az inputot a helyes darabszámokra is.
A játék módba lépve:
  A HŐS minden pillanatában legyen olyan tulajdonsága, hogy merre néz; valamint, hogy hány nyila van (a nyilak száma kezdetben megegyezik a wumpuszok számával)
  A HŐS-nek legyenek ilyan akciói: lép, fordul jobbra, fordul balra, lő, aranyat felszed
  A lövés hatása: a nyíl egyenesen, a hős nézési irányába indul, a falakon nem tud átmenni -- ott megsemmisül, de ha előtte eltalálja a wumpuszt, akkor az lekerül a pályáról, egy sikolyt hallatva (ezt nem kell most implementálni)
  A lépés, fordulások hatását nem kell magyarázni, világos (remélem, VS)
  A verembe lépve, elveszit egy nyilat
  A wumpuszra lépve meghal a hős
  A felszedés hatása, hogy már birtokolja az aranyat a hős
  A játékmenet közbenső állapotait nem szükséges menteni
  Legyen olyan akció is, hogy "felad", akkor kilépünk a játékból
  Legyen olyan akció is, hogy "halasztás", akkor elmentjük a játékállást, társítva a felhasználói névvel -- ezt az 1. fázisban még nem kell
  Ha a hős teljesítette a küldetést, akkor elmentjük a felhasználónevet és a pontszámot -- ezt az 1. fázisban még nem kell kiírjuk, hogy ennyi-meg-ennyi lépéssel teljesítetted a pályát, ügyes vagy, s visszalépünk az alapmenübe
