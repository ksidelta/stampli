export interface TokenService {
  setToken(token: Token): Promise<void>;

  unsetToken(): void;

  getToken(): Token;

  isAuthenticated(): boolean;
}

export type Token = string;
