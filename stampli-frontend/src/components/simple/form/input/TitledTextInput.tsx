import React from 'react';
import { EventDrivenTextInput } from './EventDrivenTextInput';
import { TitledInputComponent } from './TitledInputComponent';
import { Subject } from 'rxjs';
import { InputEvent } from '../../../../events/producers/input/InputEvent';
import { StateForTextInput } from './StateForTextInput';
import { InputState } from '../../../../state/form/input/InputState';

export const TitledTextInput = ({
  subject,
  state,
  title
}: {
  subject: Subject<InputEvent<string>>;
  state: InputState<string>;
  title: string;
}) => (
  <StateForTextInput state={state}>
    {value => (
      <EventDrivenTextInput topic={subject}>
        {onChange => <TitledInputComponent title={title} type={'text'} value={value} onChange={onChange} />}
      </EventDrivenTextInput>
    )}
  </StateForTextInput>
);
