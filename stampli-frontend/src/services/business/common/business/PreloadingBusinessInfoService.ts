import { BusinessInfoService } from './BusinessInfoService';

export class PreloadingBusinessInfoService implements BusinessInfoService {
  constructor(public businessInfoService: BusinessInfoService) {}

  getLogoSrc(businessId: number): Promise<string> {
    return this.businessInfoService.getLogoSrc(businessId).then(
      url =>
        new Promise((resolve, reject) => {
          const img = new Image();
          img.onload = () => resolve(url);
          img.onerror = err => reject(err);
          img.src = url;
        })
    );
  }
}
