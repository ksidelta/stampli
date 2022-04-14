import { ValueState } from './ValueState';

export class StringValueState implements ValueState<string> {
  constructor(public value: string) {}
}
