export const Routes = {
  business: { root: '/business/', settings: '/business/settings', qrCode: '/business/qr' },
  challenge: {
    claim: (baseUrl: string, businessId: number, issuerId: number, nonce: number) =>
      new URL(`/challenge/claim/${businessId}/${issuerId}/${nonce}`, baseUrl).href
  }
};
