import React from 'react';
import { TextInputState } from './TextInputState';
import { observer } from 'mobx-react';

export const TextInputStatefulComponent = observer((
  {
    state,
    children
  }: {
    state: TextInputState,
    children: (value: string, onChange: (value: string) => void) => React.ReactNode
  }) => <>{children(state.text(), (text) => state.setText(text))}</>
);

