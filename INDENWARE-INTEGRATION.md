# Indenware-Anbindung – Phase 2

## Aktueller Stand
Die installierbare Android-App verwendet **Demoprodukte**. Der Checkout bleibt deaktiviert. Die Datei `src/indenware.js` definiert eine sichere Integrationsgrenze und validiert spätere Produktdaten. Sie stellt **noch keine Verbindung** zum Shop her.

## Benötigte Informationen vom Indenware-Support
- Name und Version der verwendeten Indenware-Shopplattform
- Dokumentation der verfügbaren REST-/Shop-APIs, Authentifizierung und Berechtigungen
- Produktkatalog: Kategorien, Varianten, Bilder, Preise, Steuer, Pfand, Bestand
- Warenkorb/Checkout: Gastbestellung, Liefergebiete, Versandkosten, Lieferfenster
- Unterstützte Zahlungsarten und Weiterleitung zu einem sicheren Kreditkarten-Checkout
- Bestellanlage, Zahlungsstatus, Bestellstatus und Stornierungen
- Testsystem/Sandbox und CORS-Regeln

## Sicherheit
Keine API-Schlüssel oder Shop-Administrator-Passwörter in der App oder in GitHub ablegen. Falls die Indenware-API geheime Zugangsdaten benötigt, einen eigenen Server-Proxy verwenden. Preise, Pfand, Versandkosten und Gesamtsummen werden im Backend autoritativ neu berechnet. Kreditkartendaten niemals selbst erfassen oder speichern.

## Geplanter API-Vertrag für eine eigene Bridge
`GET /v1/catalog` → `{ "products": [{ "id": "...", "name": "...", "category": "...", "priceCents": 649, "depositCents": 330, "available": true, "imageUrl": "https://..." }] }`

Dies ist ein **interner Zielvertrag**, keine Behauptung über die tatsächlichen Indenware-Endpunkte.

## Nächste Freigabe
Erst nach schriftlich bestätigter API-Dokumentation, Testbestellungen und Zahlungsprüfungen wird der Live-Modus aktiviert.
