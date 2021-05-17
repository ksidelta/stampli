import { RequestService } from '../../../services/request/RequestService';
import { Subject } from 'rxjs';
import { RequestResolvedEvent } from './RequestResolvedEvent';
import { InputEvent } from '../input/InputEvent';

export class EventRequester {
  constructor(protected requestService: RequestService) {}

  onSubject<T>(subject: Subject<InputEvent<T>>) {
    const requestService = this.requestService;

    return {
      withPayloadType(ctor: { new (): T }) {
        return {
          request(url: string) {
            requestService
              .query(url, 'get', {})
              .then(response => subject.next(new RequestResolvedEvent<T>(response.asType(ctor))))
              .catch(x => console.log('O CIE CHUJ'));
            // TODO: change catch semantics for request service
          }
        };
      },

      withConversion(conversion: (payload: any) => T) {
        return {
          request(url: string) {
            requestService
              .query(url, 'get', { accept: 'image/png' })
              .then(response => subject.next(new RequestResolvedEvent<T>(response.withConversion(conversion))));
            // TODO: change catch semantics for request service
          }
        };
      }
    };
  }
}
