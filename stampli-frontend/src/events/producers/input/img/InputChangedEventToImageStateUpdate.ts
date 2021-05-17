import { Observer } from 'rxjs';
import { InputEvent } from '../InputEvent';
import { InputState } from '../../../../state/form/input/InputState';
import { action } from 'mobx';
import { InputChangedEvent } from '../InputChangedEvent';
import { RequestResolvedEvent } from '../../request/RequestResolvedEvent';
import { ImageValue } from '../../../../components/simple/form/img/ImageValue';

export class InputChangedEventToImageStateUpdate<T> implements Observer<InputEvent<String>> {
  constructor(protected inputState: InputState<ImageValue>) {}

  complete(): void {}

  error(err: any): void {}

  next(inputEvent: InputEvent<T>): void {
    if (inputEvent instanceof InputChangedEvent) {
      action(() => (this.inputState.valueState.value = (inputEvent as InputChangedEvent<ImageValue>).changedValue))();
    }

    if (inputEvent instanceof RequestResolvedEvent) {
      this.inputState.valueState.value = (inputEvent as RequestResolvedEvent<ImageValue>).requestResponse
        .payload as ImageValue;
    }
  }
}
