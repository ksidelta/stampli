import { Token } from '../token/TokenService';
import { RequestService } from '../request/RequestService';
import { endpointMap } from '../request/constants/EndpointMap';
import Logger from 'js-logger';
import { RegisterService } from './RegisterService';

export class BasicRegisterService implements RegisterService {
  constructor(protected requestService: RequestService) {}

  async register(user: string, password: string): Promise<void> {
    Logger.debug(`Register initiated by ${user} and ${password}`);

    const logQuery = await this.requestService.query(endpointMap.REGISTER_BASIC, 'post', undefined, {
      email: user,
      password
    });

    if (logQuery.isSuccessful()) {
      Logger.debug(`User ${user} registered`);
      return Promise.resolve();
    }

    if (logQuery.isDuplicate()) {
      return Promise.reject('User is already registered');
    }

    return Promise.reject('Unknown error occured');
  }
}
