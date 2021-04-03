import { TokenService } from './TokenService';

export class BasicTokenService implements TokenService {
  token: string | undefined = undefined;

  getToken(): string {
    return this.token || '';
  }

  isAuthenticated(): boolean {
    return this.token !== undefined;
  }

  async setToken(token: string | undefined): Promise<void> {
    this.token = token;
  }
}
