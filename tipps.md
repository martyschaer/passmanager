---
layout: page
title: Tipps
permalink: /tipps/
order: 3
---
* TOC
{:toc}

# Tipps zum Sichern Ihrer digitalen Identität

## Passwörter
Passwörter verwenden wir heutzutage fast überall. Ob es sich nun um die Passwörter für ein soziales Netzwerk, für eine Bank oder für sonst eine Webseite handelt. Natürlich wollen wir nicht, dass sich andere Personen Zugriff auf unsere Daten verschaffen. Also ist es wichtig ein gutes Passwort zu wählen!

Die nächsten Abschnitte befassen sich alle mit den Best Practices [^1], wie man seine Passwörter am besten wählen und aufbewewahren sollte.

### Komplexizität
Heuzutage können Angreifer mehrere Milliarden Passwörter pro Sekunde generieren und austesten. Ein Passwort wie "apfel" oder "password" werden dabei innerhalb von ein paar Milisekunden geknackt.

Nachfolgend ein paar wichtige Hilfestellungen, die die Komplexizität eines Passwortes erhöhen und dadurch den Schutz gegen einen Angreifer verbessern.

 - Keine Wörter aus einem Wörterbuch verwenden. Diese Wörter werden meistens immer zuerst getestet und können innerhalb eines Bruchteils einer Sekunde alle überprüft werden.

 - Die Länge des Passwortes sollte mindestens 8 Zeichen betragen. Desto länger ein Passwort ist, desto schwieriger ist es für den Angreifer, das Passwort herauszufinden. Am Besten sollten die Passwörter so lange wie möglich sein. Mit jedem Zeichen mehr erhöht sich die Komplexizität exponentiell.    
 **Zur Veranschaulichung:**  
 Ein Passwort mit Gross-, Kleinbuchstaben, Zahlen und Sonderzeichen mit der Länge von 6 Zeichen, lässt sich innerhalb von ein paar Minuten knacken, während ein ähnliches Passwort mit 8 Zeichen schon einige Tage dauert. Ein Passwort mit 10 oder mehr Zeichen benötigt dann schon eine sehr lange Zeit bis es geknackt ist.

 - Man sollte verschiedene Arten von Zeichen verwenden. Anstatt nur Kleinbuchstaben für das Passwort zu verwenden, sollten Klein-, Grossbuchstaben, Zahlen und Sonderzeichen gemischt verwenden.  
 **Zur Veranschaulichung:**  
 Ein Passwort der Länge 8, welches nur aus Kleinbuchstaben besteht, lässt sich in einer Minute knacken. Ein Passwort der Länge 8 mit allen möglichen Zeichen dauert dann schon mehrere Tage.

- Wer sich nicht die Mühe machen will, selber ein starkes Passwort auszudenken, kann mithilfe von Passwort-Generatoren starke Passwörter generieren.  
**Zur Veranschaulichung:**  
 Um an einem konkreten Beispiel zu sehen wie sicher ein Passwort ist, kann bestehende Software verwendet werden. Wir empfehlen ein Produkt namens [zxcvbn](https://www.bennish.net/password-strength-checker/). Es basiert auf einer [Technologie von Dropbox](https://blogs.dropbox.com/tech/2012/04/zxcvbn-realistic-password-strength-estimation/).    
**Man sollte seine echten Passwörter *nie* auf einer unbekannten Website eingeben.** Daher verwendet man am besten ein Passwort, das dem zu testenden Passwort nur ähnlich ist.

### Wiederverwendung
Man sollte möglichst für jede Webseite / Dienst, bei dem man ein Passwört benötigt, ein anderes wählen. Dann ist man für den Fall geschützt, dass auf irgendeiner Seite sein Passwort herausgefunden wurde. Wenn man überall das selbe Passwort verwendet hätte, könnte der Angreifer in alle Konten eindringen. Bei verschiedenen Passwörtern ist dies nicht der Fall.

Zusätzlich sollte man sein Passwort bei Seiten, bei denen Passwörter und Benutzerdaten entwendet wurden, sofort ändern! Dies zählt auch für Seiten, bei denen man das gleiche Passwort verwendet.

Auf [https://haveibeenpwned.com/](https://haveibeenpwned.com/) kann man nachschauen auf welchen Seiten, bei denen man registriert ist, Angreifer möglicherweise Zugriff auf Benutzerdaten erhalten haben.

### Aufbewahrung
Ein weiterer wichtiger Punkt ist die Aufbewahrung von Passwörtern. Selbst wenn man das beste Passwort nutzt, welches man sich vorstellen kann, bringt es dann nichts, wenn man es auf ein Post-it schreibt und dieses an den Bildschirm klebt.

Die beste Vorraussetzung für eine gute Aufbewahrung der Passwörter ist natürlich, wenn man sie auswendig lernt und sie aus dem Grund gar nicht aufbewahren muss. Jedoch ist es, besonders wenn man viele verschiedene und komplexe Passwörter hat, nicht möglich sich alle zu merken. Abhilfe schaffen hier sogenannte Passwort-Manager.

Ein Passwort-Manager dient dazu, alle Passwörter, welche man besitzt, zu verwalten. Bei den meisten Passwort-Managern kann man verschiedene Seiten erfassen und dazu das jeweilige Passwort. Nun muss man sich insgesamt nur noch ein Passwort merken und zwar das Master-Passwort mit welchen man auf den Passwort-Manager zugreift und dann den vollen Zugriff auf seine Passwörter hat. Der Nachteil ist natürlich, wenn von jemandem das Master-Passwort herausgefunden wird, hat derjenige auch Zugriff auf alle Passwörter.

Im Rahmen der IdPA haben wir neben dieser Webseite auch noch einen solchen Passwort-Manager erstellt. Dieser und Informationen zu diesem kann man auf der [Passwort-Manager](/passmanager/passwort-manager) Seite finden.

## Zweifaktorauthentifizierung (2FA)
Eine weitere Möglichkeit sich bei Webseiten anzumelden ist zusätzlich zu einem Passwort die Zweifaktorauthentifizierung, kurz 2FA. Bei der 2FA wird neben dem Passwort noch auf einen zusätzlichen Sicherheitsmechanismus gesetzt. In den meisten Fällen gibt man sein Passwort ein und danach bekommt man z.B. per E-Mail einen Code auf sein Handy, welchen man auch noch eingeben muss, bevor man angemeldet wird. Auf diese Weise können Angreifer, welche das Passwort herausgefunden haben trotzdem nicht Zugriff auf das Konto erhalten, ausser sie hätten gleichzeitig auch direkten Zugriff auf das Handy.

Heutzutage bieten die meisten grösseren Seiten Möglichkeiten zur 2FA an. Wenn man sich also nicht nur auf ein Passwort verlassen will, sollte man die 2FA aktivieren.

# Glossar

[^1]: Das beste Verhalten / Idealverhalten / Erfolgsmethode
