import { InputState } from '../../../state/form/input/InputState';
import React from 'react';
import { observer } from 'mobx-react';

export const StatedChallengeQRCode = observer(
  ({ state, children }: { state: InputState<string>; children: (value: string) => React.ReactNode }) => (
    <>{state.valueState.value ? children(state.valueState.value) : undefined}</>
  )
);
