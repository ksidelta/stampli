import { RequestService } from './RequestService';
import { LoginService } from '../login/LoginService';
import { RequestResponse } from './response/RequestResponse';

const SET_TOKEN = 'Set-Token';
const AUTHORIZATION = 'Authorization';

export class AuthenticationAwareRequestService implements RequestService {
  constructor(protected requestService: RequestService, protected loginService: LoginService) {}

  query<PAYLOAD, RESPONSE>(
    path: string,
    method: 'get' | 'post',
    headers: Record<string, string>,
    payload?: PAYLOAD
  ): Promise<RequestResponse<RESPONSE>> {
    return this.requestService
      .query<PAYLOAD, RESPONSE>(path, method, { ...headers, ...this.defaultHeaders() }, payload)
      .then(async x => {
        if (SET_TOKEN in x.headers) {
          await this.loginService.setToken(x.headers[SET_TOKEN]);
        }
        return x;
      });
  }

  private defaultHeaders(): Record<string, string> {
    return this.loginService.isAuthenticated() ? { Authorization: `Bearer ${this.loginService.getToken()}` } : {};
  }
}
