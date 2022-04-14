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
import { LocalStorageTokenService } from '../token/LocalStorageTokenService';
import { BasicBusinessSettings } from '../business/admin/settings/BasicBusinessSettings';
import { EventRequester } from '../../events/producers/request/EventRequester';
import { BasicBusinessProfileService } from '../business/admin/profile/BasicBusinessProfileService';
import { BasicBusinessChallengeService } from '../business/admin/challenge/BasicBusinessChallengeService';
import { SocketFactory } from './socket/SocketFactory';
import { StampServiceImpl } from '../business/common/stamps/StampServiceImpl';
import { PreloadingBusinessInfoService } from '../business/common/business/PreloadingBusinessInfoService';
import { BasicBusinessInfoService } from '../business/common/business/BasicBusinessInfoService';
import { RoutingServiceInstance } from '../../router/services/RoutingService';
import { BasicExternalTokenService } from '../external/token/BasicExternalTokenService';

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
    const tokenService = new LocalStorageTokenService(makeAutoObservable(new BasicTokenService()));
    const requestService = new AuthenticationAwareRequestService(new BasicRequestService(config), tokenService);
    const externalTokenService = new BasicExternalTokenService(config, tokenService);
    const loginService = new BasicLoginService(requestService);
    const registerService = new BasicRegisterService(requestService);
    const businessSettings = makeAutoObservable(new BasicBusinessSettings('', undefined));
    const eventRequester = new EventRequester(requestService);
    const businessProfileService = new BasicBusinessProfileService(requestService);
    const businessChallengeService = new BasicBusinessChallengeService(
      requestService,
      tokenService,
      businessProfileService
    );
    const socket = new SocketFactory(config).createSocket();
    const stampService = new StampServiceImpl(requestService);
    const businessInfoService = new PreloadingBusinessInfoService(new BasicBusinessInfoService(requestService));
    const routingService = RoutingServiceInstance;

    return {
      socket,
      config,
      eventRequester,

      tokenService,
      requestService,
      loginService,
      registerService,
      externalTokenService,

      businessSettings,
      businessProfileService,
      businessChallengeService,
      businessInfoService,

      stampService,

      routingService
    };
  }

  async fetchConfiguration(): Promise<Configuration> {
    return new Configuration(
      process.env.BASE_URL || 'https://stampli.at.hsp.sh',
      new OpenIdConfiguration(process.env.OAUTH__GOOGLE__CLIENT_ID || '155167860801-q5hj0hi3hhdrgton1aoplfd026tgfch7.apps.googleusercontent.com')
    );
  }
}
