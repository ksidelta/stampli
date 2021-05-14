import { Observer } from 'rxjs';
import { RequestResolvedEvent } from './RequestResolvedEvent';
import { RequestResponseState } from './RequestResponseState';

export class RequestResponseStateUpdater<T> implements Observer<RequestResolvedEvent<T>> {
  constructor(protected responseState: RequestResponseState<T>) {}

  complete(): void {}

  error(err: any): void {}

  next(requestResponse: RequestResolvedEvent<T>): void {
    this.responseState.updateResponse(requestResponse.requestResponse);
  }
}
