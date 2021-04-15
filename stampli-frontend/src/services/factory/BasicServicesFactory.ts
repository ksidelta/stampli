import { ServicesFactory } from './ServicesFactory';
import { BasicTokenService } from '../token/BasicTokenService';
import { Configuration } from '../config/Configuration';
import { OpenIdConfiguration } from '../config/parts/OpenIdConfiguration';
import { ServicesBundle } from '../ServicesBundle';
import { BasicRequestService } from '../request/BasicRequestService';
import { AuthenticationAwareRequestService } from '../request/AuthenticationAwareRequestService';
import { BasicLoginService } from '../login/BasicLoginService';
import { makeAutoObservable } from 'mobx';
import { BasicRegisterService } from '../register/BasicRegisterService';

// TODO make it use env!
export class BasicServicesFactory implements ServicesFactory {
  create(): ServicesBundle {
    const config = new Configuration(
      'https://stampli.at.hsp.sh',
      new OpenIdConfiguration('155167860801-q5hj0hi3hhdrgton1aoplfd026tgfch7.apps.googleusercontent.com')
    );
    const tokenService = makeAutoObservable(new BasicTokenService());
    const requestService = new AuthenticationAwareRequestService(new BasicRequestService(config), tokenService);
    const loginService = new BasicLoginService(requestService);
    const registerService = new BasicRegisterService(requestService);
    return { config, tokenService, requestService, loginService, registerService };
  }
}
