/**
 * Indenware integration boundary – phase 2.
 * No undocumented endpoint is assumed. No credentials are stored in the app.
 * A future authenticated server-side bridge will expose a versioned catalog.
 */
export const SHOP_MODE = 'demo';
export const API_BASE_URL = '';
export function normalizeProduct(item) {
  if (!item || typeof item !== 'object') throw new Error('Ungültiges Produkt');
  const id = String(item.id ?? '');
  const name = String(item.name ?? '');
  const priceCents = Number(item.priceCents);
  const depositCents = Number(item.depositCents ?? 0);
  if (!id || !name || !Number.isSafeInteger(priceCents) || priceCents < 0 ||
      !Number.isSafeInteger(depositCents) || depositCents < 0) {
    throw new Error('Produktdaten unvollständig');
  }
  return {
    id, name, category: String(item.category ?? 'Sonstige'),
    priceCents, depositCents,
    imageUrl: typeof item.imageUrl === 'string' && item.imageUrl.startsWith('https://') ? item.imageUrl : null,
    available: item.available === true
  };
}
export async function loadCatalog() {
  if (SHOP_MODE !== 'live' || !API_BASE_URL) {
    return { mode: 'demo', products: [], message: 'Demo: Indenware ist noch nicht verbunden.' };
  }
  const response = await fetch(API_BASE_URL + '/v1/catalog', { headers: { Accept: 'application/json' } });
  if (!response.ok) throw new Error('Katalog derzeit nicht erreichbar');
  const data = await response.json();
  if (!Array.isArray(data.products)) throw new Error('Ungültiges Katalogformat');
  return { mode: 'live', products: data.products.map(normalizeProduct) };
}
export function checkoutEnabled() {
  return SHOP_MODE === 'live' && Boolean(API_BASE_URL);
}
