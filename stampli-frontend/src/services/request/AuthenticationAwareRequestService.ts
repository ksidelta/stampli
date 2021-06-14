import { RequestService } from './RequestService';
import { TokenService } from '../token/TokenService';
import { RequestResponse } from './response/RequestResponse';
import { headersMap } from './constants/HeadersMap';
import Logger from 'js-logger';

export class AuthenticationAwareRequestService implements RequestService {
  constructor(protected requestService: RequestService, protected loginService: TokenService) {}

  query<PAYLOAD, RESPONSE>(
    path: string,
    method: 'get' | 'post',
    headers: Record<string, string>,
    payload?: PAYLOAD
  ): Promise<RequestResponse<RESPONSE>> {
    return this.requestService
      .query<PAYLOAD, RESPONSE>(path, method, { ...headers, ...this.defaultHeaders() }, payload)
      .then(async x => {
        if (headersMap.SET_TOKEN in x.headers) {
          Logger.debug(`Found Set-Token in Headers`);
          await this.loginService.setToken(x.headers[headersMap.SET_TOKEN]);
        }

        if (headersMap.WWW_AUTHENTICATE in x.headers) {
          Logger.debug(`Found WWW-Authenticate in Headers`);
          await this.loginService.unsetToken();
        }

        return x;
      });
  }

  private defaultHeaders(): Record<string, string> {
    return this.loginService.isAuthenticated()
      ? { [headersMap.AUTHORIZATION]: `Bearer ${this.loginService.getToken()}` }
      : {};
  }
}
