import { TokenService } from './token/TokenService';
import { RequestService } from './request/RequestService';
import { Configuration } from './config/Configuration';
import { LoginService } from './login/LoginService';
import { RegisterService } from './register/RegisterService';
import { BusinessSettings } from './business/settings/BusinessSettings';
import { EventRequester } from '../events/producers/request/EventRequester';
import { BusinessProfileService } from './business/profile/BusinessProfileService';
import { BusinessChallengeService } from './business/challenge/BusinessChallengeService';
import { RxStomp } from '@stomp/rx-stomp';
import { StampService } from './business/stamps/StampService';

export interface ServicesBundle {
  loginService: LoginService;
  registerService: RegisterService;
  tokenService: TokenService;
  requestService: RequestService;
  config: Configuration;
  businessSettings: BusinessSettings;
  eventRequester: EventRequester;
  businessProfileService: BusinessProfileService;
  businessChallengeService: BusinessChallengeService;
  socket: RxStomp;
  stampService: StampService;
}
