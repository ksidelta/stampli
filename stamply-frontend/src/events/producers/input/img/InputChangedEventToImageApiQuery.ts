import { RequestService } from '../../../../services/request/RequestService';
import { Observer, Subject } from 'rxjs';
import { InputEvent } from '../InputEvent';
import { InputState } from '../../../../state/form/input/InputState';
import { InputChangedEvent } from '../InputChangedEvent';
import { debounceTime, map, mergeMap, tap } from 'rxjs/operators';
import { ImageValue } from '../../../../components/simple/form/img/ImageValue';

export class InputChangedEventToImageApiQuery implements Observer<InputEvent<ImageValue>> {
  querySubject = new Subject<InputChangedEvent<ImageValue>>();

  constructor(
    protected inputState: InputState<ImageValue>,
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
            { 'content-type': 'image/png' },
            Buffer.from(payload.changedValue.data)
          )
        ),
        mergeMap(promise => promise),
        tap(() => this.inputState.loadingState.finish())
      )
      .subscribe();
  }

  complete(): void {}

  error(err: any): void {}

  next(inputEvent: InputEvent<ImageValue>): void {
    if (inputEvent instanceof InputChangedEvent) {
      this.querySubject.next(inputEvent as InputChangedEvent<ImageValue>);
    }
  }
}
