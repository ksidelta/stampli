import { InputEvent } from './InputEvent';

export class InputChangedEvent<T> implements InputEvent<T> {
  constructor(public inputName: string, public changedValue: T) {}
}
