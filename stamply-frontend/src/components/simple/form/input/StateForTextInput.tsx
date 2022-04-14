import React from 'react';
import { InputState } from '../../../../state/form/input/InputState';
import { observer } from 'mobx-react';

export const StateForTextInput = observer(
  ({ state, children }: { state: InputState<string>; children: (value: string) => React.ReactNode }) => (
    <>{children(state.valueState.value)}</>
  )
);
