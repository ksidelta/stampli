import { LoadingState } from './loading/LoadingState';
import { ValueState } from './value/ValueState';
import { LoadingStateImpl } from './loading/LoadingStateImpl';
import { StringValueState } from './value/StringValueState';
import { makeAutoObservable } from 'mobx';

export class InputState<T> {
  constructor(public loadingState: LoadingState, public valueState: ValueState<T>) {}

  static createStringInputState(): InputState<string> {
    return new InputState<string>(
      makeAutoObservable(new LoadingStateImpl()),
      makeAutoObservable(new StringValueState(''))
    );
  }
}
