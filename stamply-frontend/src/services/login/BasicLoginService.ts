import { Token } from '../token/TokenService';
import { LoginService } from './LoginService';
import { RequestService } from '../request/RequestService';
import { endpointMap } from '../request/constants/EndpointMap';
import { headersMap } from '../request/constants/HeadersMap';
import Logger from 'js-logger';

export class BasicLoginService implements LoginService {
  constructor(protected requestService: RequestService) {}

  async login(user: string, password: string): Promise<Token> {
    Logger.debug(`Login initiated by ${user} and ${password}`);

    const logQuery = await this.requestService.query(endpointMap.LOGIN_BASIC, 'post', undefined, {
      email: user,
      password
    });

    if (logQuery.isSuccessful()) {
      const token = logQuery.headers[headersMap.SET_TOKEN];

      Logger.debug(`User ${user} Logged In By token: ${token}`);
      return token;
    }

    if (logQuery.isNotFound()) {
      Logger.debug(`User ${user} not found`);
      return Promise.reject('Wrong Credentials');
    }

    Logger.error(`Strange state in BasicLoginService`);
    return Promise.reject('Error');
  }
}
