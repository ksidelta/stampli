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
import axios from 'axios';
import Logger from 'js-logger';

// TODO make it use env!
export class BasicServicesFactory implements ServicesFactory {
  static defaultConfiguration = new Configuration(
    'https://stampli.at.hsp.sh',
    new OpenIdConfiguration('155167860801-q5hj0hi3hhdrgton1aoplfd026tgfch7.apps.googleusercontent.com')
  );

  async create(): Promise<ServicesBundle> {
    const config = await this.fetchConfiguration().catch(x => {
      Logger.error(`Couldn't load settings, using defaults`);
      return BasicServicesFactory.defaultConfiguration;
    });
    const tokenService = makeAutoObservable(new BasicTokenService());
    const requestService = new AuthenticationAwareRequestService(new BasicRequestService(config), tokenService);
    const loginService = new BasicLoginService(requestService);
    const registerService = new BasicRegisterService(requestService);
    return { config, tokenService, requestService, loginService, registerService };
  }

  async fetchConfiguration(): Promise<Configuration> {
    const config = axios.get('/configuration.json');
    return config.then(x => x.data);
  }
}
