import { TokenService } from './token/TokenService';
import { RequestService } from './request/RequestService';
import { Configuration } from './config/Configuration';
import { LoginService } from './login/LoginService';

export interface ServicesBundle {
  loginService: LoginService;
  tokenService: TokenService;
  requestService: RequestService;
  config: Configuration;
}
