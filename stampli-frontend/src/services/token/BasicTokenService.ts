import { TokenService } from './TokenService';
import Logger from 'js-logger';
import { makeAutoObservable } from 'mobx';

export class BasicTokenService implements TokenService {
  token: string | undefined = undefined;

  getToken(): string | undefined {
    return this.token;
  }

  isAuthenticated(): boolean {
    return this.token !== undefined;
  }

  async setToken(token: string | undefined): Promise<void> {
    Logger.debug(`updating token ${token}`);
    this.token = token;
  }

  unsetToken(): void {
    Logger.debug(`invalidated token`);
    this.token = undefined;
  }
}
