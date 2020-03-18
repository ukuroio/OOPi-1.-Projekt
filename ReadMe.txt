Jäägiklassidest ja polünoomidest üle jäägiklassikorpuste.

Meie projektiks oli luua programm, mis võtab kasutajalt sisse algarvulise 
mooduli ja oskab siis vastavas jäägiklassikorpuses ja polünoomide ringis 
üle selle korpuse kõiksugu tehteid teha. Siin dokumendis seletan veidi neid
matemaatilisi kontsepte.

Jäägiklassiring mooduli p järgi on algebraline struktuur, mis samastab iga
täisarvu tema jäägiga jagamisel arvuga p (a->a%p). Tähistame Z_p. Tuleb välja, et selline 
definitsioon säilitab vajalikke tehteid ja täidab teisi ringi aksioome.
Nt mistahes kahe täisarvu a ja b korral 
1) (a%p) + (b%p) = (a + b) % b
2) (a%p)*(b%p)=(a*b)%p

Konkreetsemalt olgu ring Z_8. Siis 
17*35=1*3=3
2*4=8=0
5*5=1

Panime tähele, et 2*4=0 ehkki 2 != 0 ja 4 != 0. Selliseid elemente, mille puhul see võimalik on,
nimetatakse nulliteguriteks. Samuti täheldame, et mõne elemendipaari korrutis on 1 (nt 5*5=1, 3*3=1). 
Selliseid elemente, mille korrutamisel on võimalik saada tulemuseks 1, nimetatakse pööratavaks. 
Tuleb välja, et parajasti siis kui p (moodul) on algarv, ei leidu nullitegureid ja iga jäägiklass 
peale 0 on pööratav. Siis nimetame Z_p jäägiklassikorpuseks. Sellisel juhul saame loomulikul viisil 
defineerida jagamise kui pöördarvuga korrutamise.

Nt vaadame Z_5. 
Siin 3*2 = 1, seega 3^(-1) = 2 ja 4/3 = 4*3^(-1) = 4*2=3. 
Kontrolliks 3*3=4.

Meie programmis on klass Jäägiklass, mis oskabki teha täpselt neid tehteid mingis jäägiklassikorpuses. 

Lisan siia veel natuke jagamise algoritmist:
Vaatame elemnti a != 0. a järguks nimetame naturaalarvu n, mis on vähim arv nii, et a**n=1. (st minimaalne 
arv, mitu korda peab a-d korrutama iseendaga, et saada 1). Lagrange'i teoreem ütleb, et igas 
jäägiklassikorpuses jagab iga elemendi järk arvu p-1. Veel, igas jäägiklassikorpuses leidub vähemalt mingi 
element, mille järk on täpselt p-1.

Kui me suudame leida sellise elemendi a, siis saame panna tema astmed listi ja terve korpuse kirjutada kui
[a^0,a^1,a^2,...,a^(p-2)]. Siis on korrutamine ja jagamine lihtsad: lihtsalt liida/lahuta astendajad. Sellel
põhineb ka meie jagamisalgoritm. Klass/meetod tegurdus leiab kõik p jagajad (optimaalsuse huvides 
maksimaalsed peale p-1) ja siis kontrollime elemente läbi kuni mingi element ei anna neil astmetel 
tulemuseks 1. 

Nüüd veel polünoomidest.

Polünoome saab luua üle jäägiklassikorpuste täpselt nagu üle reaal- või kompleksarvude. Meie polünoomid on 
ArrayList tüüpi massiivid. Polünoomide liitmine, korrutamine, lahutamine on tavalised. Ka polünoomide jagamine
on tavaline, kuid teen kiirelt siiski algoritmist ülevaate:

olgu f ja g polünoomid üle reaalarvude (nii on ehk kergem aru saada). f = x^3+x^2+3x+1, g=x^2+1.

x^3 + x^2 + 3x + 1 : (x^2+1) = x
-x^3        -x
      x^2 + 2x + 1

Jagasime pealiikmeid ja siis lahutame jagatis*jagaja jagatavast. Nüüd kordame algoritmi.

 x^3 + x^2 + 3x + 1 : (x^2+1) = x + 1
-x^3         -x
--------------------
       x^2 + 2x + 1
      -x^2      - 1
--------------------
             2x

Kuna 2x aste on väiksem kui x^2 aste, siis jagamist enam ei toimu ja 2x on jääk. Meie jagamise
meetod väljastabki massiivi massiividest kujul [[jagatis],[jääk]].

Lõpetuseks on meil veel ka SÜT ja VÜK funktsioonid.

SÜT jaoks teen kiire ülevaate Eukleidese algoritmist täisarvudel. 
Eukleidese algoritm on algoritm, millega saab leida mistahes kahe täisarvu (polünoomi üle korpuse) 
suurima ühisteguri. Algoritm rakendab lihtsalt järjest jäägiga jagamist. Alguses jagame suurt arvu väiksemaga,
seejärel jagame eelmist jagajat jäägiga. Itereerime seda kuni jääk on 0 ja SÜT on see jääk, mille saime enne 0.

Näide: Leiame SÜT(1234,224)
1234 =  5*224 + 114
 224 =  1*114 + 110
 114 =  1*110 +   4
 110 = 27*  4 +   2  <--
   4 =  2*  2 +   0

SÜT(1234,224) = 2.

Ja päris lõpuks, teame, et kehtib VÜK(a,b) = a*b/SÜT(a,b) ehk
VÜK(1234,224) = 138208



Martin Puškin programmeeris Jäägiklassi klassi ja polünoomidega tehted peale SÜT, VÜK. 
Uku Roio programmeeris SÜT ja VÜK meetodid, kasutajaliidese ja lisas ka erindite viskamise vajalikesse kohtadesse.


Lisa:
Seda projekti saaks ka edasi arendada:
Esiteks on väga praktiline loomulik Eukleidese algoritmi laiendus, mis leiab lineaarkombinatsiooni argumentidest a,b, 
mille tulemus on SÜT(a,b). Nt saame, et 2 = 303*224-55*1234. Seda saab loomulikult teha ka polünoomidel.

Samuti saaks ideed edasi arendada ja luua polünoomid üle üldiste lõplike (lõplik arv elemente) korpuste. 
Kõik võimalikud lõplikud korpused üldse jagunevad kaheks:
1) jäägiklassikorpused (mille juba tegime)
2) korpused, mille elemendid on sellised polünoomid üle jäägiklassikorpuste, mille aste on ülimalt n. Kui nende 
polünoomide korrutamise tulemusena korrutise aste ületab n, siis asendatakse korrutis jäägiga, mis 
saadakse jagamisel korrutist jäägiga mingi kindla varem valitud n+1 - astme polünoomiga. 
Saaksime sellised korpused ka programmeerida, siis teha polünoomide klass üle üldise lõpliku korpuse. 




Loodan väga, et meie töö ei ole liiga matemaatiline ja sellest aru saamine/selle faili lugemine ei valmista teile 
liialt peavalu! Rühmatöö juhendis soovitati teha programm, mis lõimub õpitavaga ja ma oleksin eelmine semester Algebra I
aines sellise programmi eest vägagi tänulik olnud. Pealegi pole mina kunagi enne nii palju programmeerimist nautinud kui 
selle projekti tegemisel.

Olge terve!
Martin Puškin




