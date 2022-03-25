import { makeAutoObservable } from 'mobx';

export interface TextInputState {
  setText(text: string): void;

  text(): string;
}

export class TextInputStateImpl implements TextInputState {
  private constructor(public value: string) {
  }

  setText(text: string): void {
    this.value = text;
  }

  text(): string {
    return this.value;
  }

  static create(defaultValue: string): TextInputState {
    return makeAutoObservable(new TextInputStateImpl(defaultValue));
  }
}