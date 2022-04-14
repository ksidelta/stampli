import { ValueState } from './ValueState';
import { ImageValue } from '../../../../components/simple/form/img/ImageValue';

export class ImageValueState implements ValueState<ImageValue> {
  constructor(public value: ImageValue) {}
}
