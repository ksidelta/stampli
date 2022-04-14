import { RequestResponse } from '../../../services/request/response/RequestResponse';
import { create200BasicRequestResponse } from '../../../services/request/response/BasicRequestResponse.util';
import { EventRequester } from './EventRequester';
import { ReplaySubject, Subject } from 'rxjs';
import { givwhenify } from '../../../utils/tests/GivWhenify';
import { RequestResolvedEvent } from './RequestResolvedEvent';
import { InputEvent } from '../input/InputEvent';

describe('EventRequester', () => {
  givwhenify(
    'given request service',
    async () => ({
      async query<PAYLOAD, RESPONSE>(
        path: string,
        method: 'get' | 'post',
        headers?: Record<string, string>,
        payload?: PAYLOAD
      ): Promise<RequestResponse<RESPONSE>> {
        return create200BasicRequestResponse(new Payload());
      }
    }),
    requestService => {
      it('when request sent then eventually some event is emitted', async () => {
        const eventRequester = new EventRequester(requestService());
        const subject: Subject<any> = new ReplaySubject();

        eventRequester.onSubject(subject).withPayloadType(Payload).request('/lol/');

        await expect(new Promise(resolve => subject.subscribe(resolve))).resolves.toBeTruthy();
      });

      it('when request sent then eventually payload is wrapped into type', async () => {
        const eventRequester = new EventRequester(requestService());
        const subject: Subject<InputEvent<Payload>> = new ReplaySubject();

        eventRequester.onSubject(subject).withPayloadType(Payload).request('/lol/');

        await expect(
          new Promise<InputEvent<Payload>>(resolve => subject.subscribe(resolve)).then(
            x => (x as RequestResolvedEvent<Payload>).requestResponse.payload
          )
        ).resolves.toBeInstanceOf(Payload);
      });
    }
  );
});

class Payload {
  constructor(public text: string = 'hello') {}
}
