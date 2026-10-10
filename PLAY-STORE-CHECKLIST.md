# Durstoase – Google Play Store Vorbereitung

## Stand
- Die bisherige APK ist eine **Debug-Testversion** und nicht für den Play Store geeignet.
- Der separate Workflow `Android Play Store Bundle` erstellt nach manueller Auslösung ein signiertes **AAB**.
- Ohne die vier GitHub-Actions-Secrets für die Signatur bricht dieser Workflow absichtlich ab. Es werden keine Schlüssel im Repository gespeichert.
- Die bisherige Android-Test-APK wird unverändert weiter gebaut.

## Erforderlich vor dem ersten Release
1. Google Play Console Entwicklerkonto einrichten und die Identitätsprüfung abschließen.
2. Einen **dauerhaft sicher aufbewahrten Upload-Schlüssel** erzeugen. Ein Schlüsselverlust kann die spätere Aktualisierung erschweren. Schlüsseldatei niemals in GitHub hochladen.
3. In GitHub unter Settings → Secrets and variables → Actions folgende Secrets anlegen:
   - `ANDROID_KEYSTORE_BASE64`: base64-kodierte Upload-Keystore-Datei
   - `ANDROID_KEYSTORE_PASSWORD`: Keystore-Passwort
   - `ANDROID_KEY_ALIAS`: Alias des Upload-Schlüssels
   - `ANDROID_KEY_PASSWORD`: Passwort des Schlüssels
4. Im Workflow „Android Play Store Bundle“ → Run workflow ausführen und den Build prüfen.
5. Die endgültige Paket-ID `de.durstoase.shop`, App-Name, Versionierung und Markenrechte bestätigen, **bevor** die App im Play Store angelegt wird.
6. Ein echtes App-Icon, Screenshots, Kurz-/Langbeschreibung und Supportkontakt bereitstellen.
7. Öffentliche Datenschutzerklärung, Daten-Sicherheitsformular, Werbeangaben und gegebenenfalls Konto-Löschung/Altersfreigabe nach tatsächlicher Website-Funktionalität prüfen.
8. Testen: Shop-Laden, Login, Cookies, Warenkorb, Pfand, Lieferung, Checkout, Zahlungen, externe Zahlungsweiterleitungen, Rücknavigation und Netzwerkfehler.
9. Google Play kann reine WebView-Wrapper wegen fehlender eigenständiger App-Funktionalität ablehnen. Vor Veröffentlichung zusätzliche mobile App-Funktionen und aktuelle Richtlinien prüfen.
10. Gegebenenfalls vorgeschriebene geschlossene Tests für neue persönliche Entwicklerkonten erfüllen.

## Datenschutz und Zahlungsabwicklung
Die App lädt `https://www.durstoase.de/` in einer Android WebView. Welche personenbezogenen Daten, Cookies und Drittanbieter-Dienste verwendet werden, hängt vom bestehenden Shop ab und muss mit dem Shopbetreiber geprüft werden. Keine Kreditkartendaten in eigener App-Logik speichern.

## Release-Vorgang
Der Release-Workflow startet **nur manuell**, nicht bei jedem Push. Er lädt ein AAB als GitHub-Artefakt hoch; er veröffentlicht nichts automatisch im Play Store.
