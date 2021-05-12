import { RequestService } from '../../../../services/request/RequestService';
import { Observer, Subject } from 'rxjs';
import { InputEvent } from '../../../events/form/input/InputEvent';
import { InputState } from '../../../../state/form/input/InputState';
import { InputChangedEvent } from '../../../events/form/input/InputChangedEvent';
import { debounceTime, map, mergeMap, tap } from 'rxjs/operators';

export class InputChangedEventToApiQuery<T> implements Observer<InputEvent<T>> {
  querySubject = new Subject<InputChangedEvent<T>>();

  constructor(
    protected inputState: InputState<T>,
    protected requestService: RequestService,
    protected name: string,
    protected url: string
  ) {
    this.querySubject
      .pipe(
        debounceTime(1000),
        tap(() => this.inputState.loadingState.load()),
        map(payload =>
          this.requestService.query(this.url, 'post', { 'content-type': 'text/plain' }, payload.changedValue)
        ),
        mergeMap(promise => promise),
        tap(() => this.inputState.loadingState.finish())
      )
      .subscribe();
  }

  complete(): void {}

  error(err: any): void {}

  next(inputEvent: InputEvent<T>): void {
    if (inputEvent instanceof InputChangedEvent && inputEvent.inputName === this.name) {
      this.querySubject.next(inputEvent);
    }
  }
}
