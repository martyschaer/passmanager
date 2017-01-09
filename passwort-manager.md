---
layout: page
title: Passwort-Manager
permalink: /passwort-manager/
order: 4
---
* TOC
{:toc}

Zusätzlich zu unserer Umfrage und unseren Tipps für den Schutz der digitalen Identität haben wir auch noch die Idee gehabt einen Passwort-Manager als Produkt zu erstellen. Da eines der grössten Probleme im Zusammenhang mit der Sicherheit die Passwörter sind, ist es wichtig, dass man diese gut aufbewahrt.
Passwörter von Software aufzubewahren löst auch folgendes Problem: Gute Passwörter sind zu komplex und zahlreich um Sie alle in Erinnerung zu halten.

# Technische Details
Unser Passwort-Manager wurde mit Hilfe von Java 8 und JavaFX umgesetzt, damit eine Plattformübergreifende Nutzung gesichert ist.

## Verschlüsselung
Zur Verschlüsselung verwendet unser Passwortmanager AES256/CBC/PKCS5Padding.

 - **AES256**

AES (Advanced Encryption Standard) ist ein symmetrisches Verschlüsslungsverfahren. Der Schlüssel zu Ver- und Entschlüsseln ist also identisch. Die Zahl 256 zeigt dabei die Länge des Schlüssels in Bit auf.

 - **CBC**

 Lorem lorem

 - **PKCS5Padding**

 Dolorem ipsumer

# Benutzeranleitung
Coming soon (hopefully)

# Download
Das GitHub-Repository mit dem Code für den Passwort-Manager kann unter [https://github.com/martyschaer/passmanager](https://github.com/martyschaer/passmanager) gefunden werden. Downloads die neueste Version können auf der [Release-Seite](https://github.com/martyschaer/passmanager/releases/latest) gefunden werden.

# Java Web Start
Der Passwort-Manager kann auch direkt per Link gestartet werden: [Passwort-Manager per Java Web Start](../webstart/passmanager-1.0-SNAPSHOT.jnlp).