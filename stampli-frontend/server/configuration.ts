import { Configuration } from '../src/services/config/Configuration';
import { OpenIdConfiguration } from '../src/services/config/parts/OpenIdConfiguration';

export const configurationForFrontend = async (): Promise<Configuration> => {
  const baseUrl = process.env.BASE_URL || 'https://stampli.at.hsp.sh';
  const oauthGoogleClientId =
    process.env.OAUTH__GOOGLE__CLIENT_ID || '155167860801-q5hj0hi3hhdrgton1aoplfd026tgfch7.apps.googleusercontent.com';

  if (baseUrl && oauthGoogleClientId) {
    return new Configuration(baseUrl, new OpenIdConfiguration(oauthGoogleClientId));
  }

  return Promise.reject();
};
