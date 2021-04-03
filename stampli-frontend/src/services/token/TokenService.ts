export interface TokenService {
  setToken(token: Token): Promise<void>;
  getToken(): Token;
  isAuthenticated(): boolean;
}

export type Token = string;
