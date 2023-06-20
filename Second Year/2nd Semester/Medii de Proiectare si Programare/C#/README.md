# mpp-proiect-csharp-PaulaBeatrice
mpp-proiect-csharp-PaulaBeatrice created by GitHub Classroom

Organizatorii  unui  concurs  de  triatlon  folosesc  un  sistem  soft  pentru  gestiunea  rezultatelor  obținute  de participanți. Persoana  responsabilă  de  fiecare probă  (arbitrul)  folosește  o  aplicație  desktop  cu  următoarele funcționalități:
  1. Login.  După  autentificarea  cu  succes,  o  nouă  fereastră  se  deschide  în care  sunt  afișate  numele  arbitrului, lista  participanților  și  numărul  total  de  puncte  obținut  până  acum  de  fiecare  participant.  Participanții  sunt afișați în ordine alfabetică după nume.
  2. Adăugare  rezultat.  La  finalul  unei  probe,  arbitrul  responsabil  de  proba  respectivă  introduce  pentru  fiecare participant   numărul   de   puncte   obținut   (dacă   este   cazul).   După   adăugarea   unui   nou   rezultat,   lista participanților și numărul total de puncte obținute se actualizează automat pentru toți arbitrii.
  3. Raport. Un  arbitru  poate  vizualiza  în  altă  listă/alt  tabel  toți  participanții  care  au  obținut puncte  la  proba respectivă în ordine descrescătoare după numărul de puncte obținut.   
  4. Logout.
  
 Vom folosi 4 entitati: </br>
  a) Entity : clasa abstracta ce va fi extinsa de celelalte doua clase. Ofera si un id entitatitlor </br>
  b) Referee: clasa care va retine atributele unui arbitru: username si password(pentru parte de logare in aplicatie), first name si last name(informatii generale despre
 arbitru) si activity, care va reprezenta tipul de activitate de care este responsabil arbitrul (inotul, ciclismul sau alergatul) </br>
  c) Participant: clasa care va retine informatiile despre participantii la concursul de triatlon. Va avea ca atribute: first name si last name(informatii generale despre participant) si points, care va reprezenta numarul de puncte obtinute la concurs. 
 
 d)Result: clasa care va retine un istoric al participarii la concurs. Va avea atributele: referee si participant(arbitrul si participantul ce au luat parte la proba), activity(activitatea desfasurata) si points(nr de puncte obtinute la acea proba)



In saptamana 9 am incarcat TemaProiect4 (solutia closs-platform) in directorul "Laborator_5". Am folosit Protocol Buffers si am implementat clientul in limbajul Java, iar serverul este in limbajul C# 
