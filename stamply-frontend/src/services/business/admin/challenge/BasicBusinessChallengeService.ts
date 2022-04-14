import { BusinessChallengeService, ChallengeDTO } from './BusinessChallengeService';
import { TokenService } from '../../../token/TokenService';
import { BusinessProfileService } from '../profile/BusinessProfileService';
import { RequestService } from '../../../request/RequestService';
import { endpointMap } from '../../../request/constants/EndpointMap';

export class BasicBusinessChallengeService implements BusinessChallengeService {
  constructor(
    protected requestService: RequestService,
    protected tokenService: TokenService,
    protected businessProfileService: BusinessProfileService
  ) {}

  async getChallenge(): Promise<ChallengeDTO> {
    const userId = this.tokenService.getUserId();
    const businessId = await this.businessProfileService.getCurrentBusinessId();

    if (!userId) {
      return Promise.reject('user unlogged');
    }

    return new ChallengeDTO(userId, businessId, await this.getNonce(businessId));
  }

  private getNonce(businessId: number): Promise<number> {
    return this.requestService
      .query(endpointMap.BUSINESS_QR_CHALLENGE(businessId), 'post')
      .then(x => (x.payload as { nonce: number }).nonce);
  }
}
