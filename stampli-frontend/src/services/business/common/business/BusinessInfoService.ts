export interface BusinessInfoService {
  getLogoSrc(businessId: number): Promise<string>;
}
