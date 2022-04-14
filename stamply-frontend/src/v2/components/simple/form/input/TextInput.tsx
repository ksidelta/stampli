import React from 'react';
import { TextInputState } from './TextInputState';
import { TextInputStatefulComponent } from './TextInputStatefulComponent';
import { TitledInputComponent } from '../../../../../components/simple/form/input/TitledInputComponent';

export const TitledTextInput = ({ state, title, type }: {
  state: TextInputState,
  title: string,
  type: 'password' | 'text'
}) =>
  <TextInputStatefulComponent state={state}>
    {(text, setText) => <TitledInputComponent title={title} type={type} value={text} onChange={setText} />}
  </TextInputStatefulComponent>;