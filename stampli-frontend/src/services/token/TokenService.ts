export interface TokenService {
  setToken(token: Token): Promise<void>;

  unsetToken(): void;

  getToken(): Token | undefined;

  isAuthenticated(): boolean;
}

export type Token = string;
