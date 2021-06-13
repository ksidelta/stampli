import { BusinessInfoService } from './BusinessInfoService';
import { RequestService } from '../../../request/RequestService';
import { endpointMap } from '../../../request/constants/EndpointMap';

export class BasicBusinessInfoService implements BusinessInfoService {
  constructor(public requestService: RequestService) {}

  async getLogoSrc(businessId: number): Promise<string> {
    return endpointMap.BUSINESS_LOGO(businessId);
  }
}
