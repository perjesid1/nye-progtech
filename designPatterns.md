<p><strong>Név:</strong> Perjési Dániel<br>
<strong>Neptunkód:</strong> RZPRT6<br>
<strong>Tantárgy:</strong> Programozási technológiák (BMI1303L)</p>

<h1>Tervezési minták egy OO programozási nyelvben. MVC, mint modell-nézet-vezérlő minta és néhány másik tervezési minta</h1>

<h2>Tervezési minták</h2>

<h3>Mit nevezünk tervezési mintának?</h3>

<p>A tervezési minták (design patterns) megoldásokat kínálnak gyakran előforduló problémákra a szoftvertervezés területén.<br>
A minta nem egy konkrét kódrészlet, hanem egy általános koncepció arra, hogyan lehet hatékonyan kezelni egy adott típusú problémát.<br>
A tervezési minta leírása objektum orientált programok esetében a megoldás egy magasabb szintű leírása, így megadja azokat az egymással kommunikáló objektumokat, osztályokat, amelyek együttes viselkedése az adott problémára megoldás lehet.<br>
A tervezési minták által a rendszer egyszerűbb, karbantarhatóbb és újrafelhasználhatóbb lesz.</p>

<h3>Tervezési minták felépítése</h3>

<ul>
	<li><strong>A minta neve (Pattern Name):</strong> Fontos a jó elnevezés, mivel a név már maga utalhat arra, hogy milyen problémára is próbál megoldást nyújtani.</li>
	<li><strong>Szándék és motiváció (Intent & Motivation):</strong> Röviden leírja a problémát és elmagyarázza a probléma megoldását is.</li>
	<li><strong>Felépítés és péda (Structure & Sample Code):</strong> Megmutatja a minta részeit, annak összefüggéseit, azaz minta elemeinek absztrakt leírása és az azok között lévő kapcsolatok és feladatok. Egy kódpélda valamely népszerű programozási nyelven megkönnyíti a minta megértését.</li>
</ul>

<h3>Tervezési minták kategóriái</h3>

<p>A tervezési minták csoportosíthatóak rendeltetésük szerint. Ez a csoportosítás a tervezési minták alkalmazási területére és céljára fókuszál.</p>

<ul>
	<li>Létrehozási minták: Ezek a minták arra összpontosítanak, hogyan lehet létrehozni objektumokat, ezek növelik a meglévő kód rugalmasságát és újrafelhasználsát.
	<ul>
		<li>Gyártó művelet (Factory Method): A Gyártó művelet, más néven Virtual Constructor, egy olyan tervezési minta, amely egy interfészt definiál egy objektum létrehozásához, de azt a létrehozási folyamatot az alosztályokra hagyja.</li>
		<li>Absztrakt gyártó (Abstract Factory): Az Absztrakt gyártó egy olyan tervezési minta, amely egy interfészt biztosít a kapcsolódó vagy egymástól függő objektumok családjának létrehozásához anélkül, hogy a konkrét osztályokat specifikálná.</li>
		<li>Egyke (Singleton): A Singleton egy olyan tervezési minta, amely biztosítja, hogy egy osztályból csak egy példány létezhessen, és ehhez globális hozzáférést biztosít.</li>
		<li>Építő (Builder): Az Építő egy olyan tervezési minta, amely a komplex objektumok létrehozását szolgálja, miközben megengedi azok lépésről lépésre történő konfigurációját.</li>
		<li>Prototípus (Prototype): A Prototípus egy olyan tervezési minta, amely a prototípus példány használatával egy alap objektumot definiál, majd az újabb objektumokat ennek a prototípusnak a lemásolásával állítja elő.</li>
	</ul></li>
	<li>Szerkezeti minták: Ezek a minták az osztályok és objektumok összetételével foglalkoznak. Elmagyarázzák, hogyan lehet az objektumokat és osztályokat nagyobb szerkezetekbe összeállítani, miközben a szerkezeteket rugalmasan és hatékonyan tartják.
	<ul>
		<li>Díszítő (Decorator): A Díszítő egy olyan tervezési minta, amely lehetővé teszi az objektum funkcionalitásának kiterjesztését dinamikusan.</li>
		<li>Illesztő (Adapter): Az Illesztő egy olyan tervezési minta, amely lehetővé teszi egy már meglévő osztály használatát anélkül, hogy annak interfészét megváltoztatnánk.</li>
		<li>Híd (Bridge): A Híd egy olyan tervezési minta, amely szétválasztja az absztrakciót a megvalósítástól, így mindkettőt külön-külön fejleszthetjük. Az absztrakció egy interfészt definiál, míg a megvalósítás egy másik hierarchiát alkot.</li>
		<li>Összetétel (Composite): Az Összetétel egy olyan tervezési minta, amely lehetővé teszi az objektumok hierarchikus szerkezetének létrehozását, és azokat egyformán kezeli. Az összetételnek egy közös interfésze van mind a leveleknek, mind a konténereknek, így egyszerűen kezelhetők és cserélhetők.</li>
		<li>Homlokzat (Facade): A Homlokzat egy olyan tervezési minta, amely egy egységes interfészt biztosít egy rendszer számára, és egyszerűsíti a rendszer többi részéhez való hozzáférést. A Homlokzat mögött rejlő komplexitást elrejti egy egységes interfész mögött, így a kliensek könnyedén használhatják a rendszer egy részét anélkül, hogy ismernék a belső működését.</li>
		<li>Pehelysúlyú (Flyweight): A Pehelysúlyú egy olyan tervezési minta, amely lehetővé teszi a nagy számú kis súlyú objektum hatékony kezelését. Ehhez a minta a közös adatokat kiszervezi, és azokat megosztja az objektumok között.</li>
		<li>Helyettes (Proxy): A Helyettes egy olyan tervezési minta, amely egy másik objektum helyettesítését biztosítja. A Helyettes lehetőséget ad egy köztes objektumnak, hogy kontrollálja vagy kiegészítse egy másik objektum hozzáférését.</li>
	</ul></li>
	<li>Viselkedési minták: Ezek a minták az objektumok közötti kommunikációra és az algoritmusok kiosztására összpontosítanak. Az objektumok közötti hatékony kommunikációról való gondoskodás mellett a felelősség megosztásáról is gondoskodnak.
	<ul>
		<li>Parancs (Command): A Parancs egy olyan tervezési minta, amely egy parancsot reprezentál egy objektumban, lehetővé téve ezzel a kérések paraméterezhetővé és eltolhatóvá tételét, kérések rögzítését és visszavonását, valamint különböző kéréseket tároló objektumok kezelését.</li>
		<li>Megfigyelő (Observer): A Megfigyelő egy olyan tervezési minta, amely az objektumok közötti egyes események követését és azokra reagálást teszi lehetővé. Az egyik objektum változása esetén a Megfigyelők (megfigyelő objektumok) értesítést kapnak és reagálhatnak az eseményre.</li>
		<li>Látogató (Visitor): A Látogató egy olyan tervezési minta, amely lehetővé teszi egy objektum struktúrájának módosítását anélkül, hogy megváltoztatnánk azok osztályait. A Látogató segítségével új műveletek adhatók hozzá egy objektumcsoporton belül, és a látogatott objektumok egységes interfészen keresztül válnak hozzáférhetővé.</li>
		<li>Felelősséglánc (Chain of Responsibility): A Felelősséglánc egy olyan tervezési minta, amely egy sor objektumban szétválasztja egy kérés feldolgozását. Az objektumok sorban végigpróbálják a kérést, és az az objektum kezeli el, amelynek felelősségi terjedelme tartalmazza a kérést.</li>
		<li>Bejáró (Iterator): A Bejáró egy olyan tervezési minta, amely segít egy objektum elemein végigiterálni anélkül, hogy feltárnánk az objektum belső szerkezetét. A Bejáró általában egy interfészt biztosít, amely definiálja az iteráció műveleteit, és különböző objektumok implementálhatják ezt az interfészt.</li>
		<li>Közvetítő (Mediator): A Közvetítő egy olyan tervezési minta, amely egy közvetítő objektumot használ a rendszer komponensei közötti kommunikáció irányítására. Ez segít elkerülni a komponensek közvetlen kölcsönhatását, és laza kapcsolatot biztosítani közöttük.</li>
		<li>Emlékeztető (Memento): Az Emlékeztető egy olyan tervezési minta, amely egy objektum állapotának elmentését és visszaállítását biztosítja anélkül, hogy feltárnánk vagy befolyásolnánk az objektum belső reprezentációját.</li>
		<li>Állapot (State): Az Állapot egy olyan tervezési minta, amely lehetővé teszi egy objektum állapotának dinamikus változását, és az objektum viselkedését attól függően módosítja, hogy milyen állapotban van.</li>
		<li>Stratégia (Strategy): A Stratégia egy olyan tervezési minta, amely lehetővé teszi egy algoritmus cseréjét az anélkül, hogy befolyásolná az objektum, amely használja azt. A stratégia egy családja cserélhető algoritmusokat kínál egy közös interfészen keresztül.</li>
		<li>Sablonfüggvény (Template Method): A Sablonfüggvény egy olyan tervezési minta, amely definiál egy algoritmust, de néhány lépést a leszármazott osztályokra hagy.</li>
	</ul></li>
</ul>

<h3>MVC, mint modell-nézet-vezérlő minta</h3>
<p>Az MVC (Model-View-Controller) egy tervezési minta, amelyet szoftvertervezési és fejlesztési folyamatok során alkalmaznak. Az alapvető elképzelés az, hogy az alkalmazás elkülönített részeire (model, view, controller) bontsa az alkalmazás logikáját.</p>

<ul>
	<li><strong>Model:</strong> Az alkalmazás állapotát és adatkezelését kezeli. Felelős az adatok tárolásáért, feldolgozásáért és a szükséges információk biztosításáért a nézet és a vezérlő számára.</li>
	<li><strong>View:</strong> A felhasználói felület megjelenítéséért felelős. Az adatokat megjeleníti, és lehetőséget nyújt a felhasználói interakcióra.</li>
	<li><strong>Controller:</strong> A felhasználói interakciókat kezeli, értelmezi és a megfelelő műveleteket hajtja végre. A modellel és a nézettel együttműködve irányítja az alkalmazás működését.</li>
</ul>
