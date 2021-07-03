import { ExternalTokenService } from './ExternalTokenService';
import { RequestService } from '../../request/RequestService';
import { TokenService } from '../../token/TokenService';
import axios from 'axios';
import { headersMap } from '../../request/constants/HeadersMap';
import { endpointMap } from '../../request/constants/EndpointMap';
import { Configuration } from '../../config/Configuration';

export class BasicExternalTokenService implements ExternalTokenService {
  constructor(protected config: Configuration, protected tokenService: TokenService) {
  }

  async regenerateToken(token: string): Promise<void> {
    axios.post(endpointMap.LOGIN_TOKEN, {}, {
      baseURL: this.config.baseUrl,
      headers: { [headersMap.AUTHORIZATION]: `Bearer ${token}` }
    }).then(x => this.tokenService.setToken(x.headers[headersMap.SET_TOKEN]));
  }
}