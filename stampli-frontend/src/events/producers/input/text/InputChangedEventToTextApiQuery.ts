import { RequestService } from '../../../../services/request/RequestService';
import { Observer, Subject } from 'rxjs';
import { InputEvent } from '../InputEvent';
import { InputState } from '../../../../state/form/input/InputState';
import { InputChangedEvent } from '../InputChangedEvent';
import { debounceTime, map, mergeMap, tap } from 'rxjs/operators';

export class InputChangedEventToTextApiQuery implements Observer<InputEvent<String>> {
  querySubject = new Subject<InputChangedEvent<String>>();

  constructor(
    protected inputState: InputState<String>,
    protected requestService: RequestService,
    protected url: string,
    protected debouncePeriod: number = 1000
  ) {
    this.querySubject
      .pipe(
        debounceTime(debouncePeriod),
        tap(() => this.inputState.loadingState.load()),
        map(payload =>
          this.requestService.query(
            this.url,
            'put',
            { 'content-type': 'text/plain;charset=UTF-8' },
            payload.changedValue
          )
        ),
        mergeMap(promise => promise),
        tap(() => this.inputState.loadingState.finish())
      )
      .subscribe();
  }

  complete(): void {}

  error(err: any): void {}

  next(inputEvent: InputEvent<String>): void {
    if (inputEvent instanceof InputChangedEvent) {
      this.querySubject.next(inputEvent);
    }
  }
}
