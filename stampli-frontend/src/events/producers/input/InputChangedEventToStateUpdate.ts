import { Observer } from 'rxjs';
import { InputEvent } from './InputEvent';
import { InputState } from '../../../state/form/input/InputState';
import { action } from 'mobx';
import { InputChangedEvent } from './InputChangedEvent';
import { RequestResolvedEvent } from '../request/RequestResolvedEvent';

export class InputChangedEventToStateUpdate<T> implements Observer<InputEvent<T>> {
  constructor(protected inputState: InputState<T>, protected name: string) {}

  complete(): void {}

  error(err: any): void {}

  next(inputEvent: InputEvent<T>): void {
    if (inputEvent instanceof InputChangedEvent && inputEvent.inputName === this.name) {
      action(() => (this.inputState.valueState.value = inputEvent.changedValue.value))();
    }

    if (inputEvent instanceof RequestResolvedEvent) {
      this.inputState.valueState.value = inputEvent.requestResponse.payload;
    }
  }
}
