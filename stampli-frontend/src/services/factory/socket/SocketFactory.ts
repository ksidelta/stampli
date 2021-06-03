import { Configuration } from '../../config/Configuration';
import { RxStomp } from '@stomp/rx-stomp';

export class SocketFactory {
  constructor(public configuration: Configuration) {}

  public createSocket(): RxStomp {
    const stomp = new RxStomp();

    const brokerURL = new URL('/socket', this.configuration.baseUrl.replace(/https?:/, 'ws:')).href;

    stomp.configure({ brokerURL, debug: console.warn });
    stomp.activate();

    return stomp;
  }
}
