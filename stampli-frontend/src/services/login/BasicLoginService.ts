import { LoginService } from './LoginService';

export class BasicLoginService implements LoginService {
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
