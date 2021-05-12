import { TokenService } from './token/TokenService';
import { RequestService } from './request/RequestService';
import { Configuration } from './config/Configuration';
import { LoginService } from './login/LoginService';
import { RegisterService } from './register/RegisterService';
import { BusinessSettings } from './business/settings/BusinessSettings';

export interface ServicesBundle {
  loginService: LoginService;
  registerService: RegisterService;
  tokenService: TokenService;
  requestService: RequestService;
  config: Configuration;
  businessSettings: BusinessSettings;
}
