import { Observer } from 'rxjs';
import { RequestResolvedEvent } from './RequestResolvedEvent';
import { RequestResponseState } from './RequestResponseState';
import { InputEvent } from '../input/InputEvent';

export class RequestResponseStateUpdater<T> implements Observer<InputEvent<T>> {
  constructor(protected responseState: RequestResponseState<T>) {}

  complete(): void {}

  error(err: any): void {}

  next(requestResponse: InputEvent<T>): void {
    if (requestResponse instanceof RequestResolvedEvent) {
      this.responseState.updateResponse(requestResponse.requestResponse);
    }
  }
}
