---
layout: page
title: Passwort-Manager
permalink: /passwort-manager/
order: 4
---
* TOC
{:toc}

Zusätzlich zu unserer Umfrage und unseren Tipps für den Schutz der digitalen Identität haben wir auch noch die Idee gehabt, einen Passwort-Manager als Produkt zu erstellen. Da eines der grössten Probleme im Zusammenhang mit der Sicherheit die Passwörter sind, ist es wichtig, dass man diese gut aufbewahrt.
Passwörter von Software aufzubewahren löst auch das folgende Problem: Gute Passwörter sind zu komplex und zu zahlreich, um Sie alle in Erinnerung zu behalten.

# Technische Details
Unser Passwort-Manager wurde mit Hilfe von Java 8 und JavaFX umgesetzt, damit eine plattformübergreifende Nutzung gesichert ist.

## Verschlüsselung
Zur Verschlüsselung verwendet unser Passwort-Manager AES256/CBC/PKCS5Padding.

 - **AES256**

AES (Advanced Encryption Standard) ist ein symmetrisches Verschlüsslungsverfahren. Der Schlüssel zu Ver- und Entschlüsseln ist also identisch. Die Zahl 256 zeigt dabei die Länge des Schlüssels in Bit auf. Symetrische Verschlüsselungsverfahren sind gut anhand der Analogie mit einem Schloss eines Behälters (enthält in unserem Fall die Passwörter, welche wir im Passwort-Manager schützen wollen) und einem Schlüssel (Key, welcher aus einem Passwort generiert werden kann und zum Ver- und Entschlüsseln verwendet wird) zu verstehen.

 - **CBC**

CBC (Cipher Block Chaining) beschreibt ein Detail des Verschlüsselungsmechanismus. Beim Verschlüsseln wird der zu schützende Text aufgeteilt in Blöcke. Jeder Block wirkt bei der Verschlüsselung des nächsten Blocks mit. Das heisst, auch wenn 2 gleiche Blöcke verschlüsselt werden (`AAA` und `AAA`) sehen sie verschlüsselt nicht mehr gleich aus (`TW9` und `ISB`) sofern nicht alle vorhergehenden Blöcke genau gleich sind. Dies macht es schwieriger für einen potentiellen Angreifer, etwas über die verschlüsselten Daten herauszufinden.

 - **PKCS5Padding**

PKCS5Padding ist ein Füllschema. Um sicherzustellen das AES256-CBC korrekt funktioniert müssen die oben beschriebenen Blocks voll gefüllt sein. Ein Block ist jeweils 8 Bytes (8 Zeichen) lang. Wenn der Klartext also zum Beispiel `AADAMMVEEMYL` (12 Zeichen) ist und in Blocks zu 8 Zeichen aufgeteilt werden soll, erhält man: `AADAMMVE` und einen Rest von `EMYL` (nur 4 Zeichen). Der zweite Block muss also aufgefüllt werden.

Mit dem PKCS5Padding-Schema werden Blocks mit der Anzahl der aufgefüllten Zeichen aufgefüllt. In unserem Beispiel wird also `EMYL` zu `EMYL4444` aufgefüllt, da 4 Zeichen aufgefüllt werden müssen.


 - **Achtung:**
Die Erklärungen auf dieser Seite sind zum besseren Verständnis stark vereinfacht.

# Benutzeranleitung
Die Kurzanleitung können Sie sich [hier herunterladen](/passmanager/assets/pdf/manual.pdf).

# Download
Das GitHub-Repository mit dem Code für den Passwort-Manager kann unter [https://github.com/martyschaer/passmanager](https://github.com/martyschaer/passmanager) gefunden werden. Downloads für die neueste Version können auf der [Release-Seite](https://github.com/martyschaer/passmanager/releases/latest) gefunden werden.

# Java Web Start
Der Passwort-Manager kann auch direkt per Link gestartet werden: [Passwort-Manager per Java Web Start](../webstart/passmanager-1.0-SNAPSHOT.jnlp).
