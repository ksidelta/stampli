import { TokenService } from './token/TokenService';
import { RequestService } from './request/RequestService';
import { Configuration } from './config/Configuration';
import { LoginService } from './login/LoginService';
import { RegisterService } from './register/RegisterService';
import { BusinessSettings } from './business/admin/settings/BusinessSettings';
import { EventRequester } from '../events/producers/request/EventRequester';
import { BusinessProfileService } from './business/admin/profile/BusinessProfileService';
import { BusinessChallengeService } from './business/admin/challenge/BusinessChallengeService';
import { RxStomp } from '@stomp/rx-stomp';
import { StampService } from './business/common/stamps/StampService';
import { BusinessInfoService } from './business/common/business/BusinessInfoService';
import { RoutingService } from '../router/services/RoutingService';

export interface ServicesBundle {
  socket: RxStomp;
  requestService: RequestService;
  config: Configuration;
  eventRequester: EventRequester;

  loginService: LoginService;
  registerService: RegisterService;
  tokenService: TokenService;

  businessProfileService: BusinessProfileService;
  businessChallengeService: BusinessChallengeService;
  businessSettings: BusinessSettings;

  stampService: StampService;
  businessInfoService: BusinessInfoService;

  routingService: RoutingService;
}
