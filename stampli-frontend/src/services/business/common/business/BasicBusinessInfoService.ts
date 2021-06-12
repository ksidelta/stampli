import { BusinessInfoService } from './BusinessInfoService';
import { RequestService } from '../../../request/RequestService';
import { endpointMap } from '../../../request/constants/EndpointMap';

export class BasicBusinessInfoService implements BusinessInfoService {
  constructor(public requestService: RequestService) {}

  getLogoSrc(businessId: number): Promise<string> {
    return this.requestService
      .query(endpointMap.BUSINESS_LOGO(businessId), 'get')
      .then(x => x.asType(String).payload as string);
  }
}
