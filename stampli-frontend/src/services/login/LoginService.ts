export interface LoginService {
  setToken(token: string): Promise<void>;
  getToken(): string;
  isAuthenticated(): boolean;
}
