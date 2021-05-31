export interface TokenService {
  setToken(token: Token): Promise<void>;

  unsetToken(): void;

  getToken(): Token | undefined;

  isAuthenticated(): boolean;

  getUserId(): number | undefined;
}

export type Token = string;
