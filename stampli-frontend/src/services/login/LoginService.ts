import { Token } from '../token/TokenService';

export interface LoginService {
  login(user: string, password: string): Promise<Token>;
}
