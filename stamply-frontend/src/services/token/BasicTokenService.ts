import { TokenService } from './TokenService';
import Logger from 'js-logger';
import JWT from 'jsonwebtoken';

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

  getUserId(): number | undefined {
    if (!this.token) {
      return undefined;
    }
    const decodedToken = JWT.decode(this.token);

    if (typeof decodedToken !== 'object' || !decodedToken) {
      return undefined;
    }

    return 'sub' in decodedToken ? parseInt(decodedToken?.sub as string)  : undefined;
  }
}
