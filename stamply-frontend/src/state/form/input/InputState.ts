import { LoadingState } from './loading/LoadingState';
import { ValueState } from './value/ValueState';
import { LoadingStateImpl } from './loading/LoadingStateImpl';
import { StringValueState } from './value/StringValueState';
import { makeAutoObservable } from 'mobx';
import { ImageValue } from '../../../components/simple/form/img/ImageValue';
import { ImageValueState } from './value/ImageValueState';

export class InputState<T> {
  constructor(public loadingState: LoadingState, public valueState: ValueState<T>) {}

  static createStringInputState(): InputState<string> {
    return new InputState<string>(
      makeAutoObservable(new LoadingStateImpl()),
      makeAutoObservable(new StringValueState(''))
    );
  }

  static createImageState(): InputState<ImageValue> {
    return new InputState<ImageValue>(
      makeAutoObservable(new LoadingStateImpl()),
      makeAutoObservable(new ImageValueState(new ImageValue(Buffer.from(''), '')))
    );
  }
}
