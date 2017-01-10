---
layout: page
title: Wie sichern wir unsere digitale Identität?
permalink: /
order: 1
---
* TOC
{:toc}

# Grobkonzept

Die Planung hat sich als relativ einfach herausgestellt und die ganze Arbeit konnte in verschiedene Module unterteilt werden: Umfrage, Informationswebsite, Passwort-Manager und Dokumentation.

Wir haben uns entschieden, zur Informationsbeschaffung eine Umfrage zu verwenden und diese Online ([http://reddit.com/r/samplesize](http://reddit.com/r/samplesize)) zu veröffentlichen, um möglichst viele und möglichst diverse Antworten zu erhalten. Diese Aufgabe musste als erstes erledigt werden, die Folgenden konnten parallelisiert werden. Unsere Aufteilung hat ziemlich gut funktioniert.

# Phase 1: Arbeit am Projekt

**Umfrage**

Wir haben zu dritt die Umfrage erstellt und ausgewertet. Es konnten wertvolle Erkenntnisse zur aktuellen Benutzung von digitalen Geräten und Dienstleistungen gewonnen werden. Anhand dieser Erkenntnisse konnten wir Probleme identifizieren und gezielt angehen. Das Resultat der Auswertung kann auf der Seite unseres Passwort-Managers ([https://martyschaer.github.io/passmanager](https://martyschaer.github.io/passmanager)) gelesen werden. Im Rückblick ist reddit nicht die ideale Gruppe für eine solche Umfrage, da die meisten Benutzer der Website eher bessere Computerkenntnisse als die restliche Bevölkerung besitzen. Wir haben das zu kompensieren versucht, indem wir uns im Internet anhand von anderen Studien mit grösseren Stichproben informiert haben (z.B. [https://wpengine.com/unmasked/](https://wpengine.com/unmasked/)).

**Informationswebsite**

Marius und Severin haben sich zuerst der Informationswebsite angenommen. Diese ist in Kramdown verfasst und wird mit Jekyll zu html konvertiert und direkt auf Github gehostet.

Die Aufgabe der Informationswebsite ist es, als Einstieg für das Projekt zu dienen. Da Kramdown ein erweiterter Dialekt von Markdown ist, fiel uns das Schreiben eher einfach, obwohl es einige Stolpersteine mit unbekannter Syntax gab.

**Passwort-Manager**

Der Passwort-Manager wurde mit Java FX 8 umgesetzt, was die neuste GUI-Technologie von Java ist. Aufgrund der fehlenden Entwicklungstools auf den Schullaptops musste vor allem für die Entwicklung des Passwort-Managers per Fernwartung auf einem anderen Gerät gearbeitet werden, was leider nicht besonders gut funktioniert hat. Die Verzögerung von Tastendruck bis Reaktion auf dem Bildschirm betrug aus bisher unbekannten Gründen meistens mehrere Sekunden, bei übermässigem Einsatz von Scrolling sogar bis zu einer Minute! Dies gestaltete die Tipparbeit natürlich ziemlich abenteuerlich. Der Rest der Entwicklung verlief gut.

**Dokumentation**

Die Dokumentation befindet sich auf der Informationswebsite und im kommentierten Quellcode, da eine einzige Informationsquelle praktischer zur Bearbeitung ist. Den Rest haben wir in Google Docs erstellt, da dort die Kollaborations-Funktionalität eine der besten ist.

# Phase 2: Vertiefung, Fertigstellung

￼￼￼**Informationswebseite**

Bis zum Schluss haben wir stetig an der Dokumentation auf unserer Webseite gearbeitet. Aus diesem Grund hatten wir am Schluss eigentlich nicht mehr viel zu tun, es fehlte nur noch die Informationsseite zum Passwort-Manager inklusive des Download-Links, Erklärung über die Funktionsweise und der Benutzeranleitung.

Mit der Webseite an sich hatten wir relativ wenige Probleme. Die grössten Probleme haben sich meistens im Zusammenhang mit der Textformatierung ergeben, da wir die Syntax von Kramdown noch nicht gut genug kannten. Ansonsten lief alles ziemlich gut und wir sind zufrieden mit dem Ergebnis.

**Passwort-Manager**

Als der Passwort-Manager fertiggestellt wurde, mussten wir ihn irgendwie auf der Webseite zur Verfügung stellen. Dazu haben wir eine ausführbare Datei vom Passwort-Manager erstellt und diese auf unser GitHub-Repository hochgeladen. Ausserdem haben wir mit Java Web Start ermöglicht, dass der Passwort-Manager auch ohne expliziten Download und ohne Installation per einfachem Link gestartet werden kann.

Diesen Download-Link haben wir dann einfach auf unserer Seite eingebunden und schon waren wir auch mit dem Passwort-Manager fertig. Dies war relativ einfach und bei dieser Phase sind mit dem Passwort-Manager keine Probleme mehr aufgetreten.

**Dokumentation**

Ein grosser Teil der Dokumentation ist ja im Grund die Webseite, also haben wir schon dort den grössten Teil behandelt. Die restlichen Dokumentationen wie das Merkblatt und die Benutzeranleitung für den Passwort-Manager mussten wir jedoch trotzdem noch erstellen.

Da sind wir leider gegen Schluss ein bischen in Zeitknappheit gekommen, da wir die Dokumentationen immer wieder nach hinten geschoben haben. Schlussendlich konnte wir sie trotzdem noch pünktlich für den Abgabetermin fertigstellen.
