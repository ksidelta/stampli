import { BusinessProfileService } from './BusinessProfileService';
import { RequestService } from '../../request/RequestService';
import { endpointMap } from '../../request/constants/EndpointMap';

export class BasicBusinessProfileService implements BusinessProfileService {
  constructor(protected requestService: RequestService) {}

  getCurrentBusinessId(): Promise<number> {
    return (
      this.requestService
        .query(endpointMap.BUSINESS, 'get')
        // .then(x => (x.isSuccessful() ? x : Promise.reject()))
        .then(x => (x.payload as { id: number }).id)
    );
  }
}
