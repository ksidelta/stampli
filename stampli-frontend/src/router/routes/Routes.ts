export const Routes = {
  business: { root: '/business/', settings: '/business/settings/', qrCode: '/business/qr/' },
  challenge: {
    claim: (
      baseUrl: string | undefined,
      businessId: string | number,
      issuerId: string | number,
      nonce: string | number
    ) =>
      baseUrl
        ? new URL(`/challenge/claim/${businessId}/${issuerId}/${nonce}/`, baseUrl).href
        : `/challenge/claim/${businessId}/${issuerId}/${nonce}/`
  }
};
