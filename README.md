## 📑 Aurkibidea

1. 🎮 [Proiektuaren aurkezpena](#-steamdb--javafx-aplikazioa)
2. 📌 [Ezaugarri nagusiak](#-ezaugarri-nagusiak)
3. 🧭 [Aplikazioaren egitura](#-aplikazioaren-egitura)
   - 🏠 [Menu nagusia](#-menu-nagusia)
   - 👥 [Players (Dashboard)](#-players-dashboard)
   - 🛒 [Shop](#-shop)
4. 🔍 [Bilaketa-sistema](#-bilaketa-sistema)
5. 🌐 [Steam API erabilera](#-steam-api-erabilera)
6. 🛠 [Erabilitako teknologiak](#-teknologiak-erabilita)
7. 📂 [Proiektuaren egitura](#-proiektuaren-egitura-yaml)
8. 🐞 [Akatsak eta murrizketak](#-akatsak-eta-murrizketak-bugs)
9. 📚 [Erabilitako iturriak](#-erabilitako-iturriak-fuentes)
10. 👨‍💻 [Aplikazioaren sortzaileak](#-aplikazioaren-sortzaileak)


---

# 🎮 SteamDB – JavaFX Aplikazioa

SteamDB **JavaFX erabiliz garatutako mahaigaineko aplikazio bat** da, **Steam Web API** kontsumitzen duena.  
Aplikazio honek Steam-eko jokoen informazio garrantzitsua bistaratzen du, hala nola **unean uneko jokalari kopurua**, **prezioak**, **generoak** eta **oinarrizko datuak**.

---

## 📌 Ezaugarri nagusiak

- Steam-eko joko ezagunen bistaratzea  
- Uneko jokalari kopuruaren kontsulta  
- Bilaketa dinamikoa jokoaren izenaren arabera  
- Denda-ikuspegia prezioekin (eurotan)  
- Pantailen arteko nabigazioa JavaFX + FXML erabiliz  
- Interfaze grafiko modernoa (hover efektuak eta estilo pertsonalizatuak)

---

## 🧭 Aplikazioaren egitura

Aplikazioa hainbat pantailatan banatuta dago, bakoitza bere kontrolatzailearekin.

### 🏠 Menu nagusia
Hasierako pantaila, erabiltzaileak aplikazioko atal nagusiak aukeratzeko aukera duena:
- **Players**: jokalarien informazioa ikusteko
- **Shop**: denda eta prezioen informazioa ikusteko

<img width="1247" height="783" alt="image" src="https://github.com/user-attachments/assets/f924c91a-f8a2-4633-b31d-1689d701c469" />

---

### 👥 Players (Dashboard)
Pantaila honetan jokoen informazio orokorra erakusten da:
- Jokoaren izena  
- Generoa  
- Irudia  
- Uneko jokalari kopurua  

Bilaketa-eremu baten bidez, erabiltzaileak jokoak filtratu ditzake idazten duen heinean.

<img width="1494" height="854" alt="image" src="https://github.com/user-attachments/assets/a27904c5-cb90-49a8-a99e-b0266f68b65e" />


---

### 🛒 Shop
Denda-atalean jokoen prezioak bistaratzen dira:
- Jokoa doakoa den ala ez  
- Prezioa eurotan  
- Informazioa Steam Store API-tik zuzenean lortua  

<img width="1494" height="854" alt="image" src="https://github.com/user-attachments/assets/7adf896e-59e2-4e07-915e-21804aa8b72d" />


---

## 🔍 Bilaketa-sistema

Aplikazioak **bilaketa dinamikoa** erabiltzen du.  
Erabiltzaileak testu-eremuan idazten duen heinean, jokoen zerrenda automatikoki eguneratzen da, izenarekin bat datozen emaitzak soilik erakutsiz.

![OpenDataSteamDB](https://github.com/user-attachments/assets/c26ca21a-13c6-4ea0-8355-a900cfc01450)

---

## 🌐 Steam API erabilera

Aplikazioak Steam-eko hainbat API erabiltzen ditu:
- **ISteamUserStats** → Uneko jokalari kopurua  
- **Steam Store API** → Jokoaren informazioa eta prezioak  

API deien bidez jasotako datuak JSON formatutik irakurri eta aplikazioan erabiltzen dira.

---

## 🛠 Teknologiak erabilita

- **Java 17+**
- **JavaFX**
- **FXML**
- **Gson (JSON parsing-a)**
- **Steam Web API**

---

## 📂 Proiektuaren egitura (YAML)

```yaml
project:
  name: SteamDB
  language: Java
  framework: JavaFX
  structure:
    open.data:
      - Main.java
      - SteamAPI.java
      - SteamDBPController.java
      - DashboardController.java
      - ShopController.java
      - fxml:
          - SteamDBP.fxml
          - Dashboard.fxml
          - Shop.fxml
    open.components:
      - GameCard.java
    open.model:
      - GameInfo.java
    resources:
      images:
        - SteamDBLogo.png

```

## 🐞 Akatsak eta murrizketak (Bugs)

Gaur egun, aplikazioak **kargatze-denbora nahiko luzea** izan dezake zenbait egoeratan. Arazo hau batez ere honako arrazoi honengatik gertatzen da:

- Aplikazioak **Steam Web API**-ra eta **Steam Store API**-ra deitzen du zuzenean.
- Erabiltzailearen **interneteko konexioaren abiaduraren** araberakoa da kargatze-denbora.
- Konexioa motela bada edo APIek erantzun geldoa ematen badute, aplikazioa motelago has daiteke.

Etorkizunean, arazo hau hobetzeko aukera hauek aztertzea aurreikusten da:
- Datuen **cache sistema** bat gehitzea
- Kargatze-prozesuan **loading animazioak** txertatzea
- API deiak **hari paraleloetan (threads)** exekutatzea


---

## 📚 Erabilitako iturriak (Fuentes)

Proiektua garatzeko eta informazioa lortzeko honako iturri ofizialak erabili dira:

- **Steam-en webgune ofiziala**  
  Jokoen informazio orokorra eta APIen dokumentazioa kontsultatzeko.

- **SteamDB**  
  Steam-eko datuen estatistikak eta erreferentzia gisa erabiltzeko plataforma.

Iturri hauek funtsezkoak izan dira jokoen datuak modu fidagarrian eskuratu eta aplikazioan integratzeko.


---

## 👨‍💻 Aplikazioaren sortzaileak

Proiektu hau honako garatzaileek sortu dute:

- **Markel Hernandez**
- **Julen Vazquez**

Bi sortzaileek elkarlanean garatu dute SteamDB JavaFX aplikazioa, bai logika bai interfaze grafikoaren diseinua landuz.


---

