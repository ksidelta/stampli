import { Token, TokenService } from './TokenService';

export const LOCALSTORAGE_TOKEN = 'token';

export class LocalStorageTokenService implements TokenService {
  constructor(protected tokenService: TokenService) {}

  firstTokenRead = true;

  getToken(): Token | undefined {
    if (this.firstTokenRead) {
      this.loadTokenFromLocalstorage();
      this.firstTokenRead = false;
    }
    return this.tokenService.getToken();
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  setToken(token: Token): Promise<void> {
    localStorage.setItem(LOCALSTORAGE_TOKEN, token);
    return this.tokenService.setToken(token);
  }

  unsetToken(): void {
    localStorage.removeItem(LOCALSTORAGE_TOKEN);
    this.tokenService.unsetToken();
  }

  private loadTokenFromLocalstorage() {
    const token = localStorage.getItem(LOCALSTORAGE_TOKEN);
    token ? this.setToken(token) : this.unsetToken();
  }
}
