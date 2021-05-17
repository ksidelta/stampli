import { observer } from 'mobx-react';
import { InputState } from '../../../../state/form/input/InputState';
import React from 'react';
import { ImageValue } from './ImageValue';

export const StateForImageUpload = observer(
  ({ state, children }: { state: InputState<ImageValue>; children: (value: ImageValue) => React.ReactNode }) => (
    <>{children(state.valueState.value)}</>
  )
);
