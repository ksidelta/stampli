import { Token } from '../token/TokenService';
import { LoginService } from './LoginService';
import { RequestService } from '../request/RequestService';
import { endpointMap } from '../request/constants/EndpointMap';
import { headersMap } from '../request/constants/HeadersMap';

export class BasicLoginService implements LoginService {
  constructor(protected requestService: RequestService) {}

  async login(user: string, password: string): Promise<Token> {
    const logQuery = await this.requestService.query(endpointMap.LOGIN, 'post');
    if (logQuery.isSuccessful()) {
      return logQuery.headers[headersMap.SET_TOKEN];
    }

    if (logQuery.isNotFound()) {
      return Promise.reject('Wrong Credentials');
    }

    return Promise.reject('Error');
  }
}
