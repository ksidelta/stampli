import { Configuration } from '../../config/Configuration';
import { RxStomp } from '@stomp/rx-stomp';

export class SocketFactory {
  constructor(public configuration: Configuration) {}

  public createSocket(): RxStomp {
    const stomp = new RxStomp();

    const url = /http(s?):\/\/(.*)/.exec(this.configuration.baseUrl) || ['', '', 'localhost'];

    const brokerURL = new URL('/socket', `ws${url[1]}://${url[2]}`).href;

    stomp.configure({ brokerURL, debug: console.warn });
    stomp.activate();

    return stomp;
  }
}
