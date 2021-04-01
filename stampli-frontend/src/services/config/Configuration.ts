import { OpenIdConfiguration } from './parts/OpenIdConfiguration';

export class Configuration {
  constructor(public baseUrl: string, public openIdConfiguration: OpenIdConfiguration) {}
}
