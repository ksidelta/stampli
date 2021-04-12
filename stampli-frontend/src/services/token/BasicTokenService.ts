import { TokenService } from './TokenService';
import Logger from 'js-logger';

export class BasicTokenService implements TokenService {
  token: string | undefined = undefined;

  getToken(): string {
    return this.token || '';
  }

  isAuthenticated(): boolean {
    return this.token !== undefined;
  }

  async setToken(token: string | undefined): Promise<void> {
    Logger.debug(`updating token ${token}`);
    this.token = token;
  }
}
