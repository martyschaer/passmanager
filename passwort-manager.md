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
Der Passwort-Manager kann auch direkt per Link gestartet werden: <a href='../webstart/passmanager-1.0-SNAPSHOT.jnlp' onclick="return launchApplication('../webstart/passmanager-1.0-SNAPSHOT.jnlp');">Passwort-Manager Java Web Start</a>.
<SCRIPT src="http://java.com/js/dtjava.js"></SCRIPT>
<script>
    function launchApplication(jnlpfile) {
        dtjava.launch(            {
                url : 'passmanager-1.0-SNAPSHOT.jnlp',
                jnlp_content : 'PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4KPGpubHAgc3BlYz0iMS4wIiB4bWxuczpqZng9Imh0dHA6Ly9qYXZhZnguY29tIiBocmVmPSJwYXNzbWFuYWdlci0xLjAtU05BUFNIT1Quam5scCI+CiAgPGluZm9ybWF0aW9uPgogICAgPHRpdGxlPlNhbXBsZSBKYXZhRlggQXBwbGljYXRpb248L3RpdGxlPgogICAgPHZlbmRvcj5HaWJiPC92ZW5kb3I+CiAgICA8ZGVzY3JpcHRpb24+U2FtcGxlIEphdmFGWCBBcHBsaWNhdGlvbi48L2Rlc2NyaXB0aW9uPgogICAgPG9mZmxpbmUtYWxsb3dlZC8+CiAgPC9pbmZvcm1hdGlvbj4KICA8cmVzb3VyY2VzPgogICAgPGoyc2UgdmVyc2lvbj0iMS44KyIgaHJlZj0iaHR0cDovL2phdmEuc3VuLmNvbS9wcm9kdWN0cy9hdXRvZGwvajJzZSIvPgogICAgPGphciBocmVmPSJwYXNzbWFuYWdlci0xLjAtU05BUFNIT1QtamZ4LmphciIgc2l6ZT0iMTU2MDAiIGRvd25sb2FkPSJudWxsIiAvPgogICAgPGphciBocmVmPSJsaWIvamF4Yi1qYXZhLXRpbWUtYWRhcHRlcnMtMS4xLjMuamFyIiBzaXplPSIxNDE2NSIgZG93bmxvYWQ9Im51bGwiIC8+CiAgPC9yZXNvdXJjZXM+CjxzZWN1cml0eT4KICA8YWxsLXBlcm1pc3Npb25zLz4KPC9zZWN1cml0eT4KICA8amZ4OmphdmFmeC1kZXNjICB3aWR0aD0iODAwIiBoZWlnaHQ9IjYwMCIgbWFpbi1jbGFzcz0iY2guZ2liYi5pZHBhLnBhc3NtYW5hZ2VyLk1haW5BcHAiICBuYW1lPSJwYXNzbWFuYWdlci0xLjAtU05BUFNIT1QiIC8+CiAgPHVwZGF0ZSBjaGVjaz0iYmFja2dyb3VuZCIvPgo8L2pubHA+Cg=='
            },
            {
                javafx : '8.0+'
            },
            {}
        );
        return false;
    }
</script>