import { RequestService } from '../../request/RequestService';
import { endpointMap } from '../../request/constants/EndpointMap';
import { StampService } from './StampService';

export class StampServiceImpl implements StampService {
  constructor(protected requestService: RequestService) {}

  getStamps(businessId: number): Promise<number> {
    return this.requestService
      .query(endpointMap.BUSINESS_STAMP_INFO(businessId), 'get')
      .then(x => (x.payload as { quantityOfStamps: number }).quantityOfStamps);
  }
}
