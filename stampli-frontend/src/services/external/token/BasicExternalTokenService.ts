import { ExternalTokenService } from './ExternalTokenService';
import { RequestService } from '../../request/RequestService';
import { TokenService } from '../../token/TokenService';
import { endpointMap } from '../../request/constants/EndpointMap';

export class BasicExternalTokenService implements ExternalTokenService {
  constructor(protected requestService: RequestService, protected tokenService: TokenService) {
  }

  async regenerateToken(token: string): Promise<void> {
    await this.tokenService.setToken(token)
      .then(() => this.requestService.query(endpointMap.LOGIN_TOKEN, 'post'));
  }
}